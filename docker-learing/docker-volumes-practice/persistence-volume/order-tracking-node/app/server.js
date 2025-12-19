const express = require("express");
const fs = require("fs");
const app = express();

const DATA_FILE = "/data/orders.txt";

app.get("/", (req, res) => {

    const orderId = "ORDER-" + Math.floor(Math.random() * 100000);

    // Save order into volume
    fs.appendFileSync(DATA_FILE, orderId + "\n");

    // Read all orders
    let orders = "";
    if (fs.existsSync(DATA_FILE)) {
        orders = fs.readFileSync(DATA_FILE, "utf-8");
    }

    res.send(`
        <h1>Order Placed Successfully</h1>
        <p>New Order ID: <b>${orderId}</b></p>
        <h3>All Orders (Persistent)</h3>
        <pre>${orders}</pre>
    `);
});

app.listen(3000, () => {
    console.log("Order tracking app running on port 3000");
});
