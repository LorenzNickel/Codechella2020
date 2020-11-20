const express = require("express");
const bodyParser = require("body-parser");

// Create a new Express app
const app = express();

//conf
app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());
const PORT = process.env.PORT || 8081;
const router = express.Router();

const checkJwt = require("./controllers/auth-controller");

// Define an endpoint that must be called with an access token
app.get("/api/external", checkJwt, (req, res) => {
  res.send({
    msg: "Your Access Token was successfully validated!"
  });
});

// Start the app
app.listen(PORT, () => {
  console.log("Port listening in " + PORT);
});