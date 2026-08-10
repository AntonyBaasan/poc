# FastAPI local run guide

From this folder, use a virtual environment and then start the app.

## 1) Create and activate a virtual environment

```bash
cd /Users/ant/git/poc/webdevtools/backend
python3 -m venv .venv
source .venv/bin/activate
```

## 2) Add required packages to requirements.txt

Add the packages you need into a file named `requirements.txt` in this folder, for example:

```txt
fastapi
uvicorn
```

Then install them:

```bash
python -m pip install --upgrade pip
pip install -r requirements.txt
```

## 3) Run the app

```bash
uvicorn main:app --reload --host 0.0.0.0 --port 8000
```

## 4) Open the API

- Swagger docs: http://localhost:8000/docs
- Root endpoint: http://localhost:8000/
- Example hello route: http://localhost:8000/hello/World

## Optional: stop the server

Press Ctrl+C in the terminal.

## Notes

- If you close the terminal, reactivate the venv with:

```bash
cd /Users/ant/git/poc/webdevtools
source .venv/bin/activate
```
