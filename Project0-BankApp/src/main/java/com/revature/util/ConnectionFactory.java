package com.revature.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
	private static ConnectionFactory cf = null;

	private ConnectionFactory() {

	}

	public static synchronized ConnectionFactory getInstance() {
		if (cf == null)
			cf = new ConnectionFactory();
		return cf;
	}

	/*
	 * 
	 */
	public Connection getConnection() {
		Connection conn = null;
		Properties prop = new Properties();
		String path = "src/main/resources/database.properties";
		try {
			prop.load(new FileReader(path));
			Class.forName(prop.getProperty("driver"));
			/*
			 * The DriverManager provides
			 */
			conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("usr"),
					prop.getProperty("pwd"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return conn;
	}
}
