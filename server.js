const express = require('express');
const bodyParser = require('body-parser');
const cors = require('cors');

const app = express();

// Use the port Render assigns, or default to 3000 for local testing
const PORT = process.env.PORT || 3000;

// Enable CORS so your frontend can talk to this server
app.use(cors());

// Parse JSON bodies from POST requests
app.use(bodyParser.json());

// Temporary in-memory storage of users
let users = [];

// POST /save-user → Save username/password from frontend
app.post('/save-user', (req, res) => {
    const { username, password } = req.body;

    if (!username || !password) {
        return res.status(400).send("Username and password are required");
    }

    users.push({ username, password });
    console.log("Saved user:", { username, password });

    res.send("User saved successfully!");
});

// GET /users → See all saved users (for testing)
app.get('/users', (req, res) => {
    res.json(users);
});

// Start the server
app.listen(PORT, () => {
    console.log(`Server running on port ${PORT}`);
});
