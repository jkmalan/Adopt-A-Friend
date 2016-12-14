package com.jkmalan.adoptafriend.database;

import java.io.File;
import java.sql.*;

public class Database {

    private final String SQLDRIVER = "org.sqlite.JDBC";
    private final File file;

    private Connection connection = null;

    // 1
    public Database(File file) {
        try {
            Class d = Class.forName(SQLDRIVER);
            Object o = d.newInstance();
            if (!(o instanceof Driver)) {
               
            } else {
                Driver driver = (Driver) o;
                DriverManager.registerDriver(driver);
            }
        } catch (Exception e) {
            // Error message
        }
        file.getParentFile().mkdirs();
        this.file = file;
    }

    // Making a connection to the database file

    public void connect() throws SQLException {
        setConnection(DriverManager.getConnection("jdbc:sqlite://" + file.getAbsolutePath()));
    }

    public void disconnect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    // gets the connection

    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connect();
        }
        return connection;
    }

    // reactivates the connection

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    // creates a new Statement and executes it

    public void executeStatement(String query) throws SQLException {
        Statement statement = getStatement();
        statement.execute(query);
        closeStatement(statement);
    }

    public Statement getStatement() throws SQLException {
        return getConnection().createStatement();
    }

    // creates a new PreparedStatement

    public PreparedStatement getPreparedStatement(String query) throws SQLException {
        return getConnection().prepareStatement(query);

        // return null;
    }

    // closes a Statement

    public void closeStatement(Statement statement) throws SQLException {
        if (statement != null) {
            statement.close();
        }
    }

    // closes a ResultSet

    public void closeResultSet(ResultSet result) throws SQLException {
        if (result != null) {
            result.close();
        }
    }

}
