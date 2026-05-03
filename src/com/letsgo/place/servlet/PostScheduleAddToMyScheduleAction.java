package com.letsgo.place.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.letsgo.place.service.PostScheduleService;

public class PostScheduleAddToMyScheduleAction implements Action {

	@Override
	public String execute(HttpServletRequest request)
			throws ServletException, IOException, ClassNotFoundException, SQLException {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("loginOK");
		String postId = request.getParameter("postId");

		PostScheduleService service = new PostScheduleService();
		
		if (userId == null) {
			return "login.jsp";
		}
		
		if (postId != null) {
			session.setAttribute("currentPostScheduleId", postId);
		} else {
			postId = (String) session.getAttribute("currentPostScheduleId");
		}

		boolean result = service.addToMySchedule(postId, userId);
		request.setAttribute("addResult", result);
		
		
		return "controller?cmd=postScheduleRouteManageUI";
	}
	
	
}
