package com.letsgo.place.model;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class IndexUIAction implements Action {

	@Override
	public String execute(HttpServletRequest request){

		return "index.jsp";
	}

}	
