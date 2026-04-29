package com.letsgo.place.servlet;

import javax.servlet.http.HttpServletRequest;

public class UpdatepwUIAction implements Action {
	
	@Override
	public String execute(HttpServletRequest request) {
		return "updatepw.jsp";
	}
	

}
