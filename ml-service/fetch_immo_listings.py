import requests
import csv
import datetime as dt

cities = {
    "Berlin": {"name": "berlin", "state": "berlin"},
    "Munich": {"name": "muenchen", "state": "bayern"},
    "Cologne": {"name": "koeln", "state": "nordrhein-westfalen"},
}


def fetch_listings(city):
    """Fetch listings from the API based on state and city (only page 1 for now)."""
    url = "https://api.mobile.immobilienscout24.de/search/list"

    params = {
        "pricetype": "calculatedtotalrent",
        "realestatetype": "apartmentrent",
        "searchType": "region",
        "geocodes": f"de/{city["state"]}/{city["name"]}",
        "pagenumber": 1,
    }

    headers = {
        "User-Agent": "ImmoScout_28.1_26.5.2_._",
        "Accept": "application/json",
        "Content-Type": "application/json",
        "Connection": "keep-alive",
    }

    print(f"-----Listings in {city["name"]}, {city["state"]} (page 1)-----")

    try:
        response = requests.post(url, params=params, headers=headers)

        if response.status_code == 200:
            data = response.json()
            return data
        else:
            print(f"Error: {response.status_code} {response.text}")
            return None

    except requests.exceptions.RequestException as e:
        print(f"Request error: {e}")
        return None


def save_to_csv(data, filename):
    """Save the data to a CSV file."""

    with open(filename, mode="w", newline="", encoding="utf-8") as file:
        fieldnames = data[0].keys() if data else []

        writer = csv.DictWriter(file, fieldnames=fieldnames)
        writer.writeheader()

        for row in data:
            writer.writerow(row)

        print(f"Data saved in '{filename}'")


if __name__ == "__main__":
    data = []
    retrieval_date = dt.datetime.now().strftime("%Y-%m-%d %H:%M:%S")

    for city in cities:
        result = fetch_listings(cities[city])

        if result:
            try:
                entries = result["resultListItems"]

                for entry in entries:
                    id = entry["item"]["id"]
                    # Remove "€" and "." from the price string
                    price = (
                        entry["item"]["attributes"][0]["value"]
                        .replace(".", "")
                        .strip("€")
                        .strip()
                    )

                    # Remove "m²" from the size string and replace "," with "."
                    size = (
                        entry["item"]["attributes"][1]["value"]
                        .strip("m²")
                        .strip()
                        .replace(",", ".")
                    )
                    data.append(
                        {
                            "id": id,
                            "price": price,
                            "size": size,
                            "city": city,
                            "retrieval_date": retrieval_date,
                        }
                    )
            except Exception as e:
                print(f"Error: {e}")

    save_to_csv(
        data, f"../data/immo_listings_{dt.datetime.now().strftime("%Y-%m-%d")}.csv"
    )
