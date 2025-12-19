const express = require("express");
const mysql = require("mysql2");
const cors = require("cors");

const app = express();

app.use(cors({
  origin: "*",        // 👈 allow all (learning purpose)
  methods: ["GET"],
}));

// DB connection
const db = mysql.createConnection({
  host: "employee-db", // 👈 container name
  user: "root",
  password: "root123",
  database: "employeedb"
});

// connect
db.connect(err => {
  if (err) {
    console.log("DB connection failed", err);
  } else {
    console.log("DB connected");
  }
});

// API endpoint
app.get("/employees", (req, res) => {
  db.query("SELECT * FROM employees", (err, result) => {
    if (err) {
      return res.status(500).send(err);
    }
    res.json(result);
  });
});

// start server
app.listen(3000, () => {
  console.log("Server running on port 3000");
});
