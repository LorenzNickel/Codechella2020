// Auth0 config
const jwt = require("express-jwt");
const jwksRsa = require("jwks-rsa");

const authConfig = {
  domain: "dev-qxonqcx5.us.auth0.com",
  audience: "http://localhost:8081",
  sso: false,
};

// Define middleware that validates incoming bearer tokens
// using JWKS from dev-hmnnmmhr.auth0.com
const checkJwt = jwt({
  secret: jwksRsa.expressJwtSecret({
    cache: true,
    rateLimit: true,
    jwksRequestsPerMinute: 5,
    jwksUri: `https://${authConfig.domain}/.well-known/jwks.json`,
  }),

  audience: authConfig.audience,
  issuer: `https://${authConfig.domain}/`,
  algorithm: ["RS256"],
});

module.exports = checkJwt;