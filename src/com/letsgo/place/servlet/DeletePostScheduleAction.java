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
		String postId = request.getParameter("postId");
		
		PostScheduleService service = new PostScheduleService();
		boolean result = service.deletePostSchedule(postId);
		
		request.setAttribute("result", result);
		return "controller?cmd=postScheduleMyListUI";
	}

}
