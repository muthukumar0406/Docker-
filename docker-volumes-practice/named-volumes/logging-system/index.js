const fs = require("fs");

const logFile = "/logs/app.log";

fs.appendFileSync(logFile, `Log saved at: ${new Date()}\n`);

console.log("Log written inside named volume!");
