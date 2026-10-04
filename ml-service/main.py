from contextlib import asynccontextmanager
import pandas as pd
from fastapi import FastAPI
from pydantic import BaseModel, Field
from sklearn.linear_model import LinearRegression
import numpy as np

DATA_FILE = "../data/immo_listings_2026-09-28.csv"

model_berlin = LinearRegression(fit_intercept=False)
model_munich = LinearRegression(fit_intercept=False)
model_cologne = LinearRegression(fit_intercept=False)


@asynccontextmanager
async def lifespan(app: FastAPI):
    print("Training models on startup...")
    train_models()
    yield
    print("Shutdown")


app = FastAPI(lifespan=lifespan)


class PredictionRequest(BaseModel):
    apartmentSize: int = Field(description="Apartment size in square metres")


class PredictionResponse(BaseModel):
    apartmentSize: int
    predictedRent: float


def train_models() -> None:
    data_file = DATA_FILE
    data = pd.read_csv(data_file)

    city_dfs = {
        city: city_df.reset_index(drop=True)
        for city, city_df in data.groupby(
            "city"
        )  # split data grouped by city into separate dataframes
    }

    berlin_df = city_dfs["Berlin"]
    munich_df = city_dfs["Munich"]
    cologne_df = city_dfs["Cologne"]

    model_berlin.fit(
        berlin_df["size"].to_numpy().reshape(-1, 1),
        berlin_df["price"].to_numpy().reshape(-1, 1),
    )

    model_munich.fit(
        munich_df["size"].to_numpy().reshape(-1, 1),
        munich_df["price"].to_numpy().reshape(-1, 1),
    )

    model_cologne.fit(
        cologne_df["size"].to_numpy().reshape(-1, 1),
        cologne_df["price"].to_numpy().reshape(-1, 1),
    )


@app.on_event("startup")
def startup() -> None:
    train_models()


@app.get("/")
def read_root():
    return {"Hello": "World"}


@app.get("/prediction", response_model=PredictionResponse)
def predict(apartmentSize: int, city: str) -> PredictionResponse:
    prediction = model_berlin.predict(np.array([apartmentSize]).reshape(-1, 1)).item()
    print(f"Predicted rent cost for {apartmentSize} sqm in {city}: {prediction}")

    return PredictionResponse(
        apartmentSize=apartmentSize,
        predictedRent=round(float(prediction), 2),
    )
