package com.letsgo.place.servlet;


import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;

import java.sql.SQLException;


import javax.servlet.http.HttpServletRequest;

import com.letsgo.place.service.PostScheduleService;

public class DeletePostScheduleAction implements Action {

	@Override

	public String execute(HttpServletRequest request) throws ClassNotFoundException, SQLException {
		String scheduleId = request.getParameter("postScheduleId");
		request.setAttribute("result", new PostScheduleService().deletePostSchedule(scheduleId));
		return "index.jsp";
	}

}
