package com.letsgo.place.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.letsgo.place.model.RouteScheduleVO;
import com.letsgo.place.service.MyScheduleService;

public class MyScheduleBudgetDetailUIAction implements Action {

	@Override
	public String execute(HttpServletRequest request)
			throws ServletException, IOException, ClassNotFoundException, SQLException {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("loginOK");

		if (userId == null) {
			return "login.jsp";
		}

		String myScheduleId = (String) session.getAttribute("currentScheduleId");
		MyScheduleService service = new MyScheduleService();
		ArrayList<RouteScheduleVO> list = (ArrayList<RouteScheduleVO>) service.getScheduleRoute(myScheduleId);
		String budgetDetail = service.getBudgetDetail(myScheduleId);
		String scheduleTitle = service.getScheduleTitle(myScheduleId);

		request.setAttribute("ScheduleRoute", list);
		request.setAttribute("budgetDetail", budgetDetail);
		request.setAttribute("scheduleTitle", scheduleTitle);
		return "myScheduleBudgetDetail.jsp";
	}

}
