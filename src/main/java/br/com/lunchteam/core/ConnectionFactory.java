package br.com.lunchteam.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionFactory {

	public static String status = "";

	public Connection getConnection() {
		Connection conn = null;
		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();
							
			String url = "jdbc:mysql://dtstsim.cqgeooahpw7f.eu-west-1.rds.amazonaws.com?user=dfclient&password=92ja64sb";
			conn = DriverManager.getConnection(url);

			status = "Connection opened";
		} catch (SQLException e) {
			status = e.getMessage();
		} catch (ClassNotFoundException e) {
			status = e.getMessage();

		} catch (Exception e) {
			status = e.getMessage();
		}
		return conn;
	}

	public void closeConnection(ResultSet rs) {

		closeConnection(rs);

		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException ex) {
			
			ex.printStackTrace();
		}
		

	}

//	public static Object prepareStatement(String sql) {
//
//		return null;
//	}
}
