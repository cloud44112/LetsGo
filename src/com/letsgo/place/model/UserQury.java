package com.letsgo.place.model;

public interface UserQury {
	String LOGIN_SQL = "SELECT NAME, EMAIL FROM USERS WHERE USER_ID = ? AND PASSWORD = ?";
	String SIGNUP_SQL = "INSERT INTO USERS (USER_ID, EMAIL, NAME, PASSWORD) VALUES (?,?,?,?)";
	String IDCHECK_SQL = "SELECT 1 FROM USERS WHERE USER_ID = ?";
	
}
