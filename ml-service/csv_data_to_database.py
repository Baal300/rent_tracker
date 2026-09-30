import requests
import csv
import sys
import datetime as dt

url = "http://localhost:8080/housing"


def post_csv_data(file):
    try:
        with open(file, mode="r", encoding="utf-8") as file:
            reader = csv.DictReader(file)
            for row in reader:
                # Convert CSV string values into correct types for JSON payload
                payload = {
                    # "id": row["id"],
                    "rentCost": float(row["price"]),
                    "apartmentSize": float(row["size"]),
                    "city": row["city"],
                    "date": dt.datetime.strptime(
                        row["retrieval_date"], "%Y-%m-%d %H:%M:%S"
                    ).isoformat(),
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


def main(args):
    if len(args) < 2:
        print("Hint: Pass a csv file as argument")
        sys.exit(1)
    print(sys.argv)
    csv_file = args[1]
    post_csv_data(csv_file)


if __name__ == "__main__":
    main(sys.argv)
