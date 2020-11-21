const mysql = require('mysql')

const con = mysql.createConnection({
    host: 'ip',
    user: 'user_name',
    password: 'user_paswe',
    database: 'database'
});

con.connect((err) => {
    if (err) {
        console.log('Error while connecting to DB')
    }
    console.log('Connection to DB established')
})

global.db = con