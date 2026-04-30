package com.letsgo.place.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.letsgo.place.model.RouteScheduleVO;
import com.letsgo.place.service.MyScheduleService;
public class MyScheduleRouteManageUIAction implements Action {

	@Override
	public String execute(HttpServletRequest request)
			throws ServletException, IOException, ClassNotFoundException, SQLException {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("loginOK");
		
		if (userId == null) {
			return "login.jsp";
		}
		String myScheduleId = request.getParameter("myScheduleId");
		if (myScheduleId != null) {
			session.setAttribute("currentScheduleId", myScheduleId);
		} else {
			myScheduleId = (String) session.getAttribute("currentScheduleId");
		}
		MyScheduleService service = new MyScheduleService();
		ArrayList<RouteScheduleVO> list = (ArrayList<RouteScheduleVO>) service.getScheduleRoute(userId, myScheduleId);
		String scheduleTitle = service.getScheduleTitle(myScheduleId);

		request.setAttribute("ScheduleRoute", list);
		request.setAttribute("scheduleTitle", scheduleTitle);
		
		return "myScheduleRouteManage.jsp";
	}

}
