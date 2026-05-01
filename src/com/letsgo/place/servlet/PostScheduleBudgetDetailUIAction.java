package com.letsgo.place.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.letsgo.place.model.RouteScheduleVO;
import com.letsgo.place.service.MyScheduleService;
import com.letsgo.place.service.PostScheduleService;

public class PostScheduleBudgetDetailUIAction implements Action{

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException, ClassNotFoundException, SQLException {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("loginOK");
		
		if (userId == null) {
			return "login.jsp";
		}

		String postId = (String) session.getAttribute("currentPostId");
		PostScheduleService service = new PostScheduleService();
		ArrayList<RouteScheduleVO> list = (ArrayList<RouteScheduleVO>) service.getScheduleRoute(postId);
		String budgetDetail = service.getBudgetDetail(postId);

		request.setAttribute("postScheduleRoute", list);
		request.setAttribute("budgetDetail", budgetDetail);
		return "postScheduleBudgetDetail.jsp";
	}
}
