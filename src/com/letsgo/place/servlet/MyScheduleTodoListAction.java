package com.letsgo.place.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.letsgo.place.model.RouteScheduleVO;
import com.letsgo.place.service.MyScheduleService;

public class MyScheduleTodoListAction implements Action {

	@Override
	public String execute(HttpServletRequest request)
			throws ServletException, IOException, ClassNotFoundException, SQLException {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("loginOK");

		if (userId == null) {
			return "login.jsp";
		}

		String myScheduleId = (String) session.getAttribute("currentScheduleId");
		String todoDetail = request.getParameter("todoDetail");
		MyScheduleService service = new MyScheduleService();
		ArrayList<RouteScheduleVO> list = (ArrayList<RouteScheduleVO>) service.getScheduleRoute(userId, myScheduleId);

		boolean flag = service.setTodoDetail(myScheduleId, todoDetail);

		request.setAttribute("ScheduleRoute", list);
		request.setAttribute("todoDetail", todoDetail);
		request.setAttribute("TodoDetailResult", flag);

		return "myScheduleTodoList.jsp";
	}

}
