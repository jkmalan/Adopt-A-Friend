package com.jkmalan.adoptafriend.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

	private Connection connect = null;

	public Database() {

	}

	// tests the connection

	public boolean connect() {

		try {
			getConnection();
			return true;
		} catch (SQLException ex) {
			return false;
		}
	}

	public void disconnect() {

	}

	// gets the connection

	public Connection getConnection() throws SQLException {
		if (connect == null || connect.isClosed()) {
			getConnection();
		}

		return null;
	}

	// reactivates the connection

	public void setConnection(Connection connect) {

		this.connect = connect;
	}

	// creates a new Statement and executes it

	public void executeStatement(String query) throws SQLException {

		Statement statement = getStatement(query);
		statement.execute(query);
		closeStatement(statement);

	}

	public Statement getStatement(String query) {
		return null;
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
