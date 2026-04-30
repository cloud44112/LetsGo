package com.letsgo.place.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.letsgo.place.service.MyScheduleService;

public class searchMyScheduleTitleAction implements Action {

	@Override
	public String execute(HttpServletRequest request)
			throws ServletException, IOException, ClassNotFoundException, SQLException {
		String title = request.getParameter("searchTitle");
		String userId = request.getParameter("loginOK");
		request.setAttribute("result", new MyScheduleService().getMyScheduleList(userId, title, "TITLE", false));
		return "jsonResult.jsp";
	}

}
