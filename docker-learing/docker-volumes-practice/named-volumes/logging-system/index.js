const express = require("express");
const fs = require("fs");
const app = express();

const logDir = "/namdenodelogs";
const logFile = `${logDir}/app.log`;

// Create folder if not exists
if (!fs.existsSync(logDir)) {
    fs.mkdirSync(logDir, { recursive: true });
}

app.get("/", (req, res) => {
    fs.appendFileSync(logFile, `Visited at: ${new Date()}\n`);
    res.send("<h1>Node Named Volume Example</h1><p>Log saved!</p>");
});

app.listen(3000, () => console.log("Server running on port 3000"));
