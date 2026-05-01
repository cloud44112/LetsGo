package com.letsgo.place.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.letsgo.place.model.ColleagueVO;
import com.letsgo.place.service.MyScheduleService;

public class MyScheduleShareUIAction implements Action {

	@Override
	public String execute(HttpServletRequest request)
			throws ServletException, IOException, ClassNotFoundException, SQLException {
		HttpSession session = request.getSession();
		String sharedUserId = (String) request.getAttribute("sharedUserId");
		String myScheduleId = (String) session.getAttribute("currentScheduleId");
		
		List<ColleagueVO> list = new MyScheduleService().getCompanionList(myScheduleId);
		request.setAttribute("colleagueList", list);
		
		return "myScheduleShare.jsp";
	}

}


