package com.epam.conference.connection;

import com.epam.conference.connection.exception.ConnectionPoolException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private final String url;
    private final String user;
    private final String pass;

    public ConnectionFactory() {
        try (InputStream input = ConnectionFactory.class.getClassLoader().getResourceAsStream("database.properties")) {
            Properties properties = new Properties();
            properties.load(input);
            url = properties.getProperty("db.url");
            user = properties.getProperty("db.user");
            pass = properties.getProperty("db.password");
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        } catch (SQLException | IOException e) {
            throw new ConnectionPoolException(e);
        }
    }

    public Connection create() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }

}