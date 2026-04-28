package com.letsgo.place.model;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oopsw.shop.servlet.Action;
import com.oopsw.shop.servlet.ActionFactory;

/**
 * Servlet implementation class FrontControllerSevlet
 */
@WebServlet("/FrontControllerSevlet")
public class FrontControllerSevlet extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String cmd = request.getParameter("cmd");
		System.out.println(cmd);

		Action a = ActionFactory.getAction(cmd);
		String url = a.execute(request);

		request.getRequestDispatcher("/view/" + url).forward(request, response);
	}

}
