package com.jkmalan.adoptafriend.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database {

    public Database() {

    }

    public void connect() {

    }

    public void disconnect() {

    }

    public Connection getConnection() {
        return null;
    }

    public void setConnection(Connection conn) {

    }

    public void executeStatement(String query) {

    }

    public Statement getStatement(String query) {
        return null;
    }

    public PreparedStatement getPreparedStatement(String query) {
        return null;
    }

    public void closeStatement(Statement statement) {

    }

    public void closeResultSet(ResultSet result) {

    }

}
