from flask import Flask
from datetime import datetime

app = Flask(__name__)
file_path = "/data/resume.txt"

@app.route("/")
def home():
    with open(file_path, "a") as f:
        f.write(f"Visited at: {datetime.now()}\n")
    return "<h1>Python Named Volume Example</h1><p>File saved in volume!</p>"

if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000)
