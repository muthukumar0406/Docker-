from flask import Flask

app = Flask(__name__)

@app.route("/")
def home():
    print("Bind mount working!")  # Logs on every request
    return "<h1>Python Bind Mount </h1><p>Live reload working!</p>"

if __name__ == "__main__":
    # Debug=True and use_reloader=True enable live reload
    app.run(host="0.0.0.0", port=5000, debug=True, use_reloader=True)
