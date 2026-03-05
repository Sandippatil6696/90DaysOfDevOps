from flask import Flask
import redis
import mysql.connector

app = Flask(__name__)

cache = redis.Redis(host='redis', port=6379)

@app.route("/")
def home():
    cache.incr('hits')
    return f"Hello! Sandip This page has been visited by you {cache.get('hits').decode()} times."

if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000)