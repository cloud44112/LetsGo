package com.letsgo.place.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBCP {
	
	private static DBCP dbcp;
	private DBCP() throws ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
	}
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException{
		if(dbcp == null){
			dbcp = new DBCP();
		}
		String uri = "jdbc:oracle:thin:@192.168.0.233:1521:xe";
		return DriverManager.getConnection(uri, "hr", "hr");
	}
}