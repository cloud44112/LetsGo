package com.letsgo.place.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.letsgo.place.service.MyScheduleService;

public class postScheduleBudgetDetailUIAction implements Action{

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException, ClassNotFoundException, SQLException {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("loginOK");
		
		if (userId == null) {
			return "login.jsp";
		}
		String myScheduleId = request.getParameter("myScheduleId");
		if (myScheduleId != null) {
			myScheduleId = (String) session.getAttribute("currentScheduleId");
		}
		
		MyScheduleService service = new MyScheduleService();
		String budgetDetail = service.getBudgetDetail(myScheduleId);
		
		
		return "postScheduleBudgetDetail";
	}
}
