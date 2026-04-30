package com.letsgo.place.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.letsgo.place.service.MyScheduleService;

public class AddVisitItemAction implements Action {

	@Override
	public String execute(HttpServletRequest request)
			throws ServletException, IOException, ClassNotFoundException, SQLException {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("loginOK");

		if (userId == null) {
			return "login.jsp";
		}

		String myScheduleId = (String) session.getAttribute("currentScheduleId");
		String placeId = request.getParameter("placeId");
		String visitOrderStr = request.getParameter("visitOrder");
		int visitOrder = (visitOrderStr != null) ? Integer.parseInt(visitOrderStr) : 1;
		
		MyScheduleService service = new MyScheduleService();
		boolean result = service.addVisitItem(visitOrder, placeId, myScheduleId);
		
		request.setAttribute("addVisitItemResult", result);
		
		return new MyScheduleRouteManageUIAction().execute(request);
	}

}
