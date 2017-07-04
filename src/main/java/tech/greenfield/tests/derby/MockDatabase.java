package tech.greenfield.tests.derby;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.derby.jdbc.EmbeddedDataSource;

public class MockDatabase {
	public EmbeddedDataSource ds = new EmbeddedDataSource();
	
	public MockDatabase() throws SQLException {
		ds.setDatabaseName(getUrl());
	}

	public void createTable() throws SQLException {
		Connection con = ds.getConnection();
		con.prepareStatement("CREATE TABLE names (id INT PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY,"
				+ "name VARCHAR(255))").execute();
	}

	public String getUrl() {
		return "memory:test;create=true";
	}

}
