package com.letsgo.place.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.letsgo.place.model.ColleagueVO;
import com.letsgo.place.service.MyScheduleService;

public class ShareToPostAction implements Action {

	@Override
	public String execute(HttpServletRequest request)
			throws ServletException, IOException, ClassNotFoundException, SQLException {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("loginOK");
		int isAnonymous = (int)request.getAttribute("isAnonymous");
		String myScheduleId = (String) session.getAttribute("currentScheduleId");
		
		String list = new MyScheduleService().shareToPost(myScheduleId, userId, isAnonymous);
		request.setAttribute("colleagueList", list);
		
		return null;
	}

}
