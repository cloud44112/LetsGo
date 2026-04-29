package com.letsgo.place.servlet;

import javax.servlet.http.HttpServletRequest;

public class SignupUIAction implements Action {

	@Override
	public String execute(HttpServletRequest request) {
		return "signup.jsp";
	}
}
