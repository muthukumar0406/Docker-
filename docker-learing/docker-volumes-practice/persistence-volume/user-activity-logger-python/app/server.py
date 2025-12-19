from flask import Flask
import datetime
import os

app = Flask(__name__)

LOG_FILE = "/data/activity.log"   # <-- volume mount location

@app.route("/")
def home():
    time_now = datetime.datetime.now()
    info = f"User visited at: {time_now}\n"

    # Append data to volume
    with open(LOG_FILE, "a") as f:
        f.write(info)

    return f"""
    <h1>Activity Logged Successfully!</h1>
    <p>{info}</p>
    <p>Check your log file inside the persistence-volume.</p>
    """

if __name__ == "__main__":
    os.makedirs("/data", exist_ok=True)
    app.run(host="0.0.0.0", port=5000)
