const express = require('express');
const bodyParser = require('body-parser');
const cors = require('cors');

const app = express();
const PORT = process.env.PORT || 3000;

app.use(cors());
app.use(bodyParser.json());

let users = [];

app.post('/save-user', (req, res) => {
    const { username, password } = req.body;
    if (!username || !password) return res.status(400).send("Username and password required");

    users.push({ username, password });
    console.log("Saved user:", { username, password });
    res.send("User saved!");
});

app.get('/users', (req, res) => {
    res.json(users);
});

app.listen(PORT, () => console.log(`Server running on port ${PORT}`));
