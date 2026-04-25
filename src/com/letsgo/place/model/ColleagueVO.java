package com.letsgo.place.model;

public class ColleagueVO {

	private String userId;
	private String name;
	private String email;
	private String permission;

	public ColleagueVO(String userId, String name, String email, String permission) {
		setUserId(userId);
		setName(name);
		setEmail(email);
		setPermission(permission);
	}

	public String getUserId() {
		return userId;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPermission() {
		return permission;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}
}