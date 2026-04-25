package com.letsgo.place.model;

public class MemberVO {
	private String userID;
	private String email;
	private String name;
	private String password;
	
	public MemberVO(String userID, String email, String name, String password) {
		setUserID(userID);
		setEmail(email);
		setName(name);
		setPassword(password);
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
