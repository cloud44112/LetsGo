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
		String postId = request.getParameter("postId");
		PostScheduleService service = new PostScheduleService();
		int likeCount = 0;
		boolean flag = false;

		if (postId != null && !postId.trim().isEmpty()) {
			flag = service.plusLike(postId);
			if(flag) {
				likeCount = service.getLikeCount(postId);
			}
		}
		request.setAttribute("result", flag);
		request.setAttribute("postId", postId);
		request.setAttribute("count", likeCount);

		return "jsonResultCount.jsp";
	}
}
