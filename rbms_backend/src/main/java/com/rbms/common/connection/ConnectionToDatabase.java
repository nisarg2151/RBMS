package com.rbms.common.connection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import oracle.jdbc.pool.OracleDataSource;

public class ConnectionToDatabase {
	public static Connection makeConnection() throws SQLException {
		PropertyFileReader pfr = new PropertyFileReader();
		Properties prop = pfr.getApplicationProperties();
		OracleDataSource ds = new oracle.jdbc.pool.OracleDataSource();
		ds.setURL(prop.getProperty("jdbc.url"));
		Connection conn = ds.getConnection(prop.getProperty("jdbc.username"), prop.getProperty("jdbc.password"));
		return conn;
	}
}
