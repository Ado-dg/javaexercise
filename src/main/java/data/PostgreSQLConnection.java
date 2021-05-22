package main.java.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import main.java.util.JAXBPostgresConfig;


public class PostgreSQLConnection {
	private static String url;
	private static String username;
	private static String password;
	private static Logger logger = LogManager.getLogger(PostgreSQLConnection.class);

	
	static {
		JAXBPostgresConfig config = JAXBPostgresConfig.getConfig();
		url = config.getUrl();
		username = config.getUsername();
		password = config.getPassword();
	}
	
	
	
	
	
	public static Connection getConnection() throws SQLException {
		Properties props = new Properties();
		props.setProperty("user", username);
		props.setProperty("password", password);
		return DriverManager.getConnection(url, props);
	}
}
