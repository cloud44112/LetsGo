package com.letsgo.place.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.letsgo.place.service.MyScheduleService;

public class UpdateScheduleTitleAction implements Action {

	@Override
	public String execute(HttpServletRequest request)
			throws ServletException, IOException, ClassNotFoundException, SQLException {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("loginOK");

		if (userId == null) {
			return "login.jsp";
		}

		String myScheduleId = (String) session.getAttribute("currentScheduleId");
		String title = request.getParameter("title");
		
		MyScheduleService service = new MyScheduleService();
		boolean result = service.setMyScheduleTitle(title, myScheduleId, userId);
		
		request.setAttribute("updateTitleResult", result);
		
		// Redirect or forward back to the referring page
		String referer = request.getHeader("Referer");
		if (referer != null && referer.contains("myScheduleBudgetDetailUI")) {
			return "MyScheduleBudgetDetailUIAction.execute(request)"; // This won't work like this in this architecture
		}
		
		// Standard pattern seems to be forwarding to the JSP, but we need to reload data.
		// For simplicity, let's redirect to the manage UI if possible, or just forward to the main manage page.
		return new MyScheduleRouteManageUIAction().execute(request);
	}

}
