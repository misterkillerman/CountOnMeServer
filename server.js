const mongoose = require("mongoose");
const express = require("express");
const cors = require("cors");
const app = express();


const uri = "mongodb+srv://phuasiqi:13122001@countonme.no73z.mongodb.net/CountOnMe?retryWrites=true&w=majority&appName=CountOnMe";
mongoose.connect(uri)
    .then(() => console.log("Connected to MongoDB"))
    .catch((err) => console.error("MongoDB connection error:", err));

// Middleware
app.use(cors());
app.use(express.json());

// Routes
const userRoutes = require("./routes/user.js");
app.use("/api", userRoutes);

// Start Server
const PORT = 8000;
app.listen(PORT, () => console.log(`🚀 Server running on http://localhost:${PORT}`));


