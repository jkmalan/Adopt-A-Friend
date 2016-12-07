package com.jkmalan.adoptafriend.database;

import java.io.File;
import java.sql.*;

public class Database {

    private final String SQLDRIVER = "org.sqlite.JDBC";
    private final File file;

    private Connection connection = null;

    public Database(File file) {
        try {
            Class d = Class.forName(SQLDRIVER);
            Object o = d.newInstance();
            if (!(o instanceof Driver)) {
                // TODO Handle errors
            } else {
                Driver driver = (Driver) o;
                DriverManager.registerDriver(driver);
            }
        } catch (Exception e) {
            // TODO Handle errors
        }
        file.getParentFile().mkdirs();
        this.file = file;
    }

    public void connect() throws SQLException {
        setConnection(DriverManager.getConnection("jdbc:sqlite://" + file.getAbsolutePath()));
    }

    public void disconnect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connect();
        }
        return connection;
    }

    public Statement getStatement() throws SQLException {
        return getConnection().createStatement();
    }

    public PreparedStatement getPreparedStatement(String query) throws SQLException {
        return getConnection().prepareStatement(query);
    }

    public void closeStatement(Statement statement) throws SQLException {
        if (statement != null) {
            statement.close();
        }
    }

    public void closeResultSet(ResultSet result) throws SQLException {
        if (result != null) {
            result.close();
        }
    }

}
