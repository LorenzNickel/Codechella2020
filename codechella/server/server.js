const express = require("express");
const bodyParser = require("body-parser");
const cors = require("cors");
const path = require("path");

// Create a new Express app
const app = express();

//conf
app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());
const PORT = process.env.PORT || 8081;
const router = express.Router();

//databse
require("./database/database");


const checkJwt = require("./controllers/auth-controller");

var corsOptions = {
  origin: "*",
  opptionsSuccessStatus: 200,
};
app.use(cors(corsOptions));

router.use(function (req, res, next) {
  console.log("Getting in API");
  res.header("Access-Control-Allow-Origin", "*");
  res.header("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE,OPTIONS");
  res.header(
    "Access-Control-Allow-Headers",
    "Content-Type, Authorization, Content-Length, X-Requested-With"
  );
  next();
});

//routes
const indexRoute = require("./routes/index");
app.use("/api", indexRoute);



// Define an endpoint that must be called with an access token
app.get("/api/external", checkJwt, (req, res) => {
  res.send({
    msg: "Your Access Token was successfully validated!"
  });
});

app.disable("etag");

// Start the app
app.listen(PORT, () => {
  console.log("Port listening in " + PORT);
});