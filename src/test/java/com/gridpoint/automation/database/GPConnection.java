package com.gridpoint.automation.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

/**
 * @author pchoudhury
 *
 */
public class GPConnection {

	private static Logger logger = Logger.getLogger(GPConnection.class);
	private Connection conn;
		
	
	public Connection getConnection(String hostName, String userName,
			String password) throws SQLException {

		try {
			Class.forName("org.postgresql.Driver");
			logger.debug("Connecting to" +hostName );
			System.out.println("Connecting to" +hostName );
			conn = DriverManager.getConnection(hostName, userName, password);

		} catch (ClassNotFoundException e) {
			logger.info("Connection object got created" + conn);
			e.printStackTrace();
		}
		return conn;
	}
}
