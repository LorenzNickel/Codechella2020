const express = require("express");
const { Result } = require("express-validator");
var mysql  = require('mysql');
// const auth_controller = require("../controllers/auth-controller")

var router = express.Router();

router.get('/', (req, res) => res.send('Hello API codechellera!'))

router
    .route("/save")
    .post(async (req, res) => {
        //get all variables apart
        const amzn_user_id = req.body.amzn_user_id
        const amzn_name = req.body.amzn_name
        const amzn_email = req.body.amzn_email
        const twitter_accessToken =  req.body.twitter_accessToken
        const twitter_secretToken = req.body.twitter_secretToken
        
        //build query
        let query = "INSERT INTO `credentials` (amzn_user_id, amzn_name, amzn_email, twitter_accessToken, twitter_secretToken) VALUES ('" +
                            amzn_user_id + "', '" + amzn_name + "', '" + amzn_email + "', '" + twitter_accessToken + "', '" + twitter_secretToken + "')";
        db.query(query, (err, res) => {
            if (err) {
                console.log(err)
            }
            console.log(res)
        })

        res.status(200).send('Credentials saved')
    }
        
)

module.exports = router;

