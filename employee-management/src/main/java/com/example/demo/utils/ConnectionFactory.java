package com.example.demo.utils;

import java.sql.*;
import java.util.ResourceBundle;
public class ConnectionFactory {
	public static Connection getMySqlConnection() {
		Connection con = null;
		try {
			ResourceBundle bundle = ResourceBundle.getBundle("application");
			String url = bundle.getString("datasource.url");
			String username = bundle.getString("datasource.username");
			String password = bundle.getString("datasource.password");
			con = DriverManager.getConnection(url,username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

}
