# Rent Tracker ML service

Install packages in virtual environment:
```powershell
python -m pip install -r python-service/requirements.txt
```

Deploy the service:
```powershell
uvicorn main:app --reload --port 8001
```