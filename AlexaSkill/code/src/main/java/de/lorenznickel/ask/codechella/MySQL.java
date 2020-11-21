package main.java.de.lorenznickel.ask.codechella;

import java.sql.*;
import java.util.Properties;

public class MySQL {

    private static final String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DATABASE_URL = StreamHandler.getCredentials().getDatabaseURL();
    private static final String USERNAME = StreamHandler.getCredentials().getUsername();
    private static final String PASSWORD = StreamHandler.getCredentials().getPassword();
    private static final String MAX_POOL = "250";

    // init connection object
    private Connection connection;
    // init properties object
    private Properties properties;

    // create properties
    private Properties getProperties() {
        if (properties == null) {
            properties = new Properties();
            properties.setProperty("user", USERNAME);
            properties.setProperty("password", PASSWORD);
            properties.setProperty("MaxPooledStatements", MAX_POOL);
        }
        return properties;
    }

    // connect database
    public Connection connect() {
        if (connection == null) {
            try {
                Class.forName(DATABASE_DRIVER);
                connection = DriverManager.getConnection(DATABASE_URL, getProperties());
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    // disconnect database
    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean userExists(final User user) {
        boolean db = false;
        try {
            PreparedStatement statement = this.connect().prepareStatement("SELECT amzn_user_id FROM credentials WHERE amzn_user_id=?");
            statement.setString(1, user.getUser_id());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                db = true;
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.disconnect();
        }
        return db;
    }

    public boolean setUpTwitterCredentials(final User user) {
        boolean db = false;
        try {
            PreparedStatement statement = this.connect().prepareStatement("SELECT twitter_accessToken, twitter_secretToken FROM credentials WHERE amzn_user_id=?");
            statement.setString(1, user.getUser_id());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                db = true;
                user.setAccessToken(rs.getString("twitter_accessToken"));
                user.setSecretToken(rs.getString("twitter_secretToken"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.disconnect();
        }
        return db && user.getAccessToken() != null && !(user.getAccessToken().equalsIgnoreCase(""));
    }

}
