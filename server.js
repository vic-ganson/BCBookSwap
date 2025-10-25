const express = require('express');
const bodyParser = require('body-parser');
const cors = require('cors');
const path = require('path');

const app = express();
const PORT = process.env.PORT || 3000;

app.use(cors());
app.use(bodyParser.json());

// ----------------------
// Serve static frontend
// ----------------------
app.use(express.static(path.join(__dirname, 'frontend')));

// ----------------------
// API endpoints
// ----------------------

// Save user (POST)
let users = [];
app.post('/save-user', (req, res) => {
  const { username, password } = req.body;
  if (!username || !password) return res.status(400).send("Username and password required");

  users.push({ username, password });
  console.log("Saved user:", { username, password });
  res.send("User saved successfully!");
});

// View saved users (GET) - for testing
app.get('/users', (req, res) => {
  res.json(users);
});

// Fallback route for unmatched paths (optional)
app.get('*', (req, res) => {
  res.sendFile(path.join(__dirname, 'frontend', 'login.html'));
});

// ----------------------
// Start server
// ----------------------
app.listen(PORT, () => {
  console.log(`Server running on port ${PORT}`);
});
