package com.jkmalan.adoptafriend.database;

import java.io.File;
import java.sql.*;

/**
 * Represents a connection to an SQLite database
 */
public class Database {

    // Holds the database connection
    private Connection connection = null;

    // Holds the local database
    private final File file;

    /**
     * Constructs a new SQLite Database object
     *
     * @param file The SQLite file to connect to
     */
    public Database(File file) {
        try {
            Class d = Class.forName("org.sqlite.JDBC");
            Object o = d.newInstance();
            if (!(o instanceof Driver)) {
                System.err.print("This is not an SQLite driver class!");
            } else {
                Driver driver = (Driver) o;
                DriverManager.registerDriver(driver);
            }
        } catch (Exception e) {
            System.err.print("There was an exception!");
            e.printStackTrace();
        }
        file.getParentFile().mkdirs();
        this.file = file;
    }

    /**
     * Opens a Connection to the database
     *
     * @throws SQLException
     */
    public void connect() throws SQLException {
        setConnection(DriverManager.getConnection("jdbc:sqlite://" + file.getAbsolutePath()));
    }

    /**
     * Closes a Connection to the database
     *
     * @throws SQLException
     */
    public void disconnect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    /**
     * Gets an existing Connection to the database
     *
     * @return An existing connection to the database
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connect();
        }
        return connection;
    }

    /**
     * Sets a new Connection to the database
     *
     * @param connection A new connection to the database
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    /**
     * Gets a new Statement object
     *
     * @return
     * @throws SQLException
     */
    public Statement getStatement() throws SQLException {
        return getConnection().createStatement();
    }

    /**
     * Executes a Statement and then closes the Statement
     *
     * @param query A SQL query to execute
     * @throws SQLException
     */
    public void executeStatement(String query) throws SQLException {
        Statement statement = getStatement();
        statement.execute(query);
        closeStatement(statement);
    }

    /**
     * Gets a new PreparedStatement object
     *
     * @param query A SQL query to prepare
     * @return A PreparedStatement for a SQL query
     * @throws SQLException
     */
    public PreparedStatement getPreparedStatement(String query) throws SQLException {
        return getConnection().prepareStatement(query);
    }

    /**
     * Closes an open Statement
     *
     * @param statement A Statement to be closed
     */
    public void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                System.err.print("There was an exception!");
                e.printStackTrace();
            }
        }
    }

    /**
     * Closes an open ResultSet
     *
     * @param result A ResultSet to be closed
     */
    public void closeResultSet(ResultSet result) {
        if (result != null) {
            try {
                result.close();
            } catch (SQLException e) {
                System.err.print("There was an exception!");
                e.printStackTrace();
            }
        }
    }

}
