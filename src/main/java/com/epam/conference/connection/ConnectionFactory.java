package com.epam.conference.connection;

import com.epam.conference.connection.exception.ConnectionPoolException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private final static String DB_URL_KEY = "db.url";
    private final static String DB_USER_KEY = "db.user";
    private final static String DB_PASSWORD_KEY = "db.password";
    private final static String DATABASE_PROPERTIES_FILE_NAME = "database.properties";

    private final String url;
    private final String user;
    private final String pass;

    public ConnectionFactory() {
        try (InputStream input = ConnectionFactory.class.getClassLoader().getResourceAsStream(DATABASE_PROPERTIES_FILE_NAME)) {
            Properties properties = new Properties();
            properties.load(input);
            url = properties.getProperty(DB_URL_KEY);
            user = properties.getProperty(DB_USER_KEY);
            pass = properties.getProperty(DB_PASSWORD_KEY);
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        } catch (SQLException | IOException e) {
            throw new ConnectionPoolException(e);
        }
    }

    public Connection create() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }

}