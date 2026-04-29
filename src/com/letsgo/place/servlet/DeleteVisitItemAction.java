package com.letsgo.place.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.letsgo.place.service.MyScheduleService;

public class DeleteVisitItemAction implements Action {

	@Override
	public String execute(HttpServletRequest request)
			throws ServletException, IOException, ClassNotFoundException, SQLException {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("loginOK");

		if (userId == null) {
			return "login.jsp";
		}

		String visitItemId = request.getParameter("visitItemId");
		
		MyScheduleService service = new MyScheduleService();
		boolean result = service.deleteVisitItemById(visitItemId);
		
		request.setAttribute("deleteVisitItemResult", result);
		
		// Refresh data and return to the route manage page
		return new myScheduleRouteManageUIAction().execute(request);
	}

}
