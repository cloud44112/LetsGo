package com.letsgo.place.model;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.letsgo.place.service.MyScheduleService;

public class DeleteScheduleAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ClassNotFoundException, SQLException{
		String scheduleId = request.getParameter("myScheduleId");
		request.setAttribute("result", new MyScheduleService().deleteMySchedule(scheduleId));
		return "index.jsp";
	}

}
