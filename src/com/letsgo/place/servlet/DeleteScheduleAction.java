package com.letsgo.place.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.letsgo.place.service.MyScheduleService;

public class DeleteScheduleAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ClassNotFoundException, SQLException{
		HttpSession session = request.getSession();
		String scheduleId = (String) session.getAttribute("currentScheduleId");
		request.setAttribute("result", new MyScheduleService().deleteMySchedule(scheduleId));
		return "controller?cmd=myScheduleListUI";
	}
}
