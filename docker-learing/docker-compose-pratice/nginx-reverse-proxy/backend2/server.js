const express = require("express");
const app = express();

app.get("/", (req, res) => {
  res.send("🔥 Hello from Backend 2");
});

app.listen(3002, () => {
  console.log("Backend2 running on port 3002");
});
