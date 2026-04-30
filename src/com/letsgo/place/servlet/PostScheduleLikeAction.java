package com.letsgo.place.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.letsgo.place.service.PostScheduleService;


public class PostScheduleLikeAction implements Action {

	@Override
	public String execute(HttpServletRequest request)
			throws ServletException, IOException, ClassNotFoundException, SQLException {
		HttpSession session = request.getSession();
		String postId = request.getParameter("postId");
		PostScheduleService service = new PostScheduleService();
		boolean flag;

		if (postId != null && !postId.trim().isEmpty()) {
			flag = service.plusLike(postId);
		}
		
		session.setAttribute("result", flag);

		return "jsonResult.jsp";
	}
}
