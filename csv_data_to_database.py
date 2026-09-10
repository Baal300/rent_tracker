import requests
import csv
import datetime

url = "http://localhost:8080/housing"
csv_file = "data/immo_listings.csv"


def post_csv_data():
    try:
        with open(csv_file, mode="r", encoding="utf-8") as file:
            reader = csv.DictReader(file)
            for row in reader:
                # Convert CSV string values into correct types for JSON payload
                payload = {
                    "rentCost": float(row["price"]),
                    "apartmentSize": float(row["size"]),
                    "city": row["city"],
                    "date": datetime.datetime.now().isoformat(),  # TODO set date of when listing was scraped
                }

                try:
                    response = requests.post(url, json=payload)
                    if response.status_code == 201:
                        print(f"Successfully saved")
                    else:
                        print(
                            f"Failed to save data: {response.status_code} - {response.json()["message"]}"
                        )

                except requests.exceptions.RequestException as e:
                    print(f"Request error: {e}")
    except FileNotFoundError:
        print(f"Could not find the file '{file}'.")


if __name__ == "__main__":
    post_csv_data()
