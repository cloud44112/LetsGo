package com.letsgo.place.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.letsgo.place.model.MapScheduleVO;
import com.letsgo.place.model.RouteScheduleVO;
import com.letsgo.place.service.PostScheduleService;

public class PostScheduleDetailAction implements Action {

	@Override
	public String execute(HttpServletRequest request)
			throws ServletException, IOException, ClassNotFoundException, SQLException {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("loginOK");
		String postId = request.getParameter("postId");
		boolean flag = false;
		int viewCount = 0;
		PostScheduleService service = new PostScheduleService();
		
		if (userId == null) {
			return "login.jsp";
		}
		
		if (postId != null) {
			session.setAttribute("currentPostScheduleId", postId);
		} else {
			postId = (String) session.getAttribute("currentPostScheduleId");
		}
		
		flag = service.plusView(postId);
		if(flag) {
			viewCount = service.getViewCount(postId);
		}
		
		ArrayList<RouteScheduleVO> list = (ArrayList<RouteScheduleVO>) service.getScheduleRoute(postId);
		List<MapScheduleVO> mapList = service.getMapSchedule(postId);
		String scheduleTitle = service.getScheduleTitle(postId);
		int likeCount = service.getLikeCount(postId); 
		
		
		request.setAttribute("postId", postId);
		request.setAttribute("count", likeCount);
		request.setAttribute("ScheduleRoute", list);
		request.setAttribute("MapSchedule", mapList);
		request.setAttribute("scheduleTitle", scheduleTitle);
		request.setAttribute("count", viewCount);
		request.setAttribute("flag", flag);
		
		return "postScheduleRouteManage.jsp";
	}
	
}
