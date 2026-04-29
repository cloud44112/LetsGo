package com.letsgo.place.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class postScheduleBudgetDetailUIAction implements Action{

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException, ClassNotFoundException, SQLException {
		return "postScheduleBudgetDetail";
	}
}
