package com.letsgo.place.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.letsgo.place.service.MyScheduleService;

public class AddCompanionAction implements Action {

	@Override
	public String execute(HttpServletRequest request)
			throws ServletException, IOException, ClassNotFoundException, SQLException {
		HttpSession session = request.getSession();
		String sharedUserId = (String) request.getParameter("sharedUserId");
		String myScheduleId = (String) session.getAttribute("currentScheduleId");
		
		MyScheduleService service = new MyScheduleService();
		boolean flag = service.addCompanion(myScheduleId, sharedUserId);
		
		return "controller?cmd=myScheduleShareUI";
	}

}
