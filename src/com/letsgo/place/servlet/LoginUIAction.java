package com.letsgo.place.servlet;

import javax.servlet.http.HttpServletRequest;

public class LoginUIAction implements Action {

	@Override
	public String execute(HttpServletRequest request) {
		return "login.jsp";
	}
}
