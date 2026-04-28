package com.letsgo.place.model;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/controller")
public class FrontControllerSevlet extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String cmd = request.getParameter("cmd");
		System.out.println(cmd);

		Action a = ActionFactory.getAction(cmd);
		String url = "controller";
		try {
			url = a.execute(request);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher("/view/" + url).forward(request, response);
	}

}
