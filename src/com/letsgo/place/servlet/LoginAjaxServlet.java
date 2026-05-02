package com.letsgo.place.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.letsgo.place.model.UserDAO;
import com.letsgo.place.model.UserVO;

@WebServlet("/loginAjax")
public class LoginAjaxServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=UTF-8");

		String userId = request.getParameter("USER_ID");
		String password = request.getParameter("PASSWORD");

		if (userId == null || password == null || userId.trim().isEmpty() || password.trim().isEmpty()) {
			response.getWriter().print("{\"result\":\"fail\",\"message\":\"아이디/비밀번호를 입력해주세요.\"}");
			return;
		}

		try {
			UserVO user = new UserDAO().login(userId, password);
			if (user == null) {
				response.getWriter().print("{\"result\":\"fail\",\"message\":\"아이디/비밀번호를 다시 입력하세요.\"}");
				return;
			}

			HttpSession session = request.getSession();
			session.setAttribute("loginOK", userId);
			session.setAttribute("info", user);

			response.getWriter().print("{\"result\":\"success\",\"url\":\""
					+ request.getContextPath() + "/controller\"}");
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().print("{\"result\":\"fail\",\"message\":\"로그인 중 오류가 발생했습니다.\"}");
		}
	}
}
