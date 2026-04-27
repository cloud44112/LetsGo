package com.letsgo.place.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

	public UserVO login(String userID, String password) {

		UserVO user = null;

		String sql = "SELECT NAME, EMAIL FROM USERS WHERE USER_ID = ? AND PASSWORD = ?";

		try (Connection conn = DBCP.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, userID);
			pstmt.setString(2, password);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					String name = rs.getString("NAME");
					String email = rs.getString("EMAIL");
					user = new UserVO(userID, email, name, password);
				}
			}
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return user; 

	}


	public boolean signup(String userID, String email, String name, String password) {

	    String sql = "INSERT INTO USERS (USER_ID, EMAIL, NAME, PASSWORD) VALUES (?,?,?,?)";

	    try (Connection conn = DBCP.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        pstmt.setString(1, userID);
	        pstmt.setString(2, email);
	        pstmt.setString(3, name);
	        pstmt.setString(4, password);

	        int result = pstmt.executeUpdate();  
	        return result > 0;  

	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}
	
	
	
	
}

