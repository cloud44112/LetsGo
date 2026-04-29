package com.letsgo.place.servlet;

import javax.servlet.http.HttpServletRequest;

public class GetIdUIAction implements Action {

	@Override
	public String execute(HttpServletRequest request) {
		return "getid.jsp";
	}
}
