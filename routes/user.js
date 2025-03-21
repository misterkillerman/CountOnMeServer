const express = require("express");
const router = express.Router();
const User = require("../models/user");

// Get all users
router.get("/user/:email/", async (req, res) => {
  try {
    const { email, password } = req.params;
    // Find the user by email and password
    const user = await User.findOne({ email: email });

    if (!user) {
        console.log("User not found")
        return res.status(404).json({ message: "User not found" });
    }
    else {
        console.log("User found: " + user);
    }
    res.json(user);

  } catch (err) {
    res.status(500).json({ message: err.message });
  }
});

module.exports = router;