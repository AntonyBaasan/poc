from pathlib import Path

from fastapi import FastAPI
from fastapi.responses import FileResponse
from fastapi.staticfiles import StaticFiles

app = FastAPI()

BASE_DIR = Path(__file__).resolve().parent
STATIC_DIR = BASE_DIR / "static"


app.mount(
    "/assets",
    StaticFiles(directory=STATIC_DIR / "assets"),
    name="assets",
)


@app.get("/api/hello")
def hello():
    return {"message": "Hello from FastAPI"}


@app.get("/{path:path}")
async def serve_react(path: str):
    file_path = STATIC_DIR / path

    if file_path.is_file():
        return FileResponse(file_path)

    # React SPA fallback
    return FileResponse(STATIC_DIR / "index.html")
