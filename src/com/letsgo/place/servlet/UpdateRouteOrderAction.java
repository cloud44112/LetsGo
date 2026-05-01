package com.letsgo.place.servlet;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.letsgo.place.service.MyScheduleService;

public class UpdateRouteOrderAction implements Action {

	@Override
	public String execute(HttpServletRequest request)
			throws ServletException, IOException, ClassNotFoundException, SQLException {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("loginOK");

		if (userId == null) {
			return "login.jsp";
		}

		String myScheduleId = (String) session.getAttribute("currentScheduleId");
		
		String[] visitItemIds = request.getParameterValues("visitItemId");
		String[] visitOrdersStr = request.getParameterValues("visitOrder");
		int[] visitOrders = null;
		if (visitOrdersStr != null) {
			visitOrders = new int[visitOrdersStr.length];
			for (int i = 0; i < visitOrdersStr.length; i++) {
				visitOrders[i] = Integer.parseInt(visitOrdersStr[i]);
			}
		}
		
		MyScheduleService service = new MyScheduleService();

		if (visitItemIds != null && visitOrders != null) {
			String[] distances = new String[visitItemIds.length];
			for (int i = 0; i < distances.length; i++) distances[i] = "0";

			boolean result = service.updateVisitOrders(visitItemIds, visitOrders, distances);
			request.setAttribute("updateRouteResult", result);
		}

		return new MyScheduleRouteManageUIAction().execute(request);
	}

}
