package com.letsgo.place.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.letsgo.place.model.ColleagueVO;
import com.letsgo.place.service.MyScheduleService;

public class EditCompanionPermissionAction implements Action {

	@Override
	public String execute(HttpServletRequest request)
			throws ServletException, IOException, ClassNotFoundException, SQLException {
		HttpSession session = request.getSession();
		String sharedUserId = (String) request.getParameter("sharedUserId");
		String permission = (String) request.getParameter("permission");
		String myScheduleId = (String) session.getAttribute("currentScheduleId");

		
		request.setAttribute("result", new MyScheduleService().setCompanionPermission(myScheduleId, sharedUserId, permission));
		System.out.println(request.getAttribute("result"));
		return "jsonResult.jsp";
	}

}
