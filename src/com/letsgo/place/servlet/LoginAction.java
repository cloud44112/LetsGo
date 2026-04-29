package com.letsgo.place.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.letsgo.place.model.UserDAO;
import com.letsgo.place.model.UserVO;

public class LoginAction implements Action {

	@Override
	public String execute(HttpServletRequest request)
			throws ServletException, IOException, ClassNotFoundException, SQLException {
		String userId = request.getParameter("USER_ID");
		String password = request.getParameter("PASSWORD");

		if (userId == null || password == null || userId.trim().isEmpty() || password.trim().isEmpty()) {
			request.setAttribute("errorMessage", "아이디/비밀번호를 입력해주세요.");
			return "login.jsp";
		}

		try {
			UserVO user = new UserDAO().login(userId, password);
			if (user == null) {
				request.setAttribute("errorMessage", "아이디/비밀번호를 다시입력하세요.");
				return "login.jsp";
			}

			HttpSession session = request.getSession();
			session.setAttribute("loginOK", userId);
			session.setAttribute("info", user);
			return "myScheduleBudgetDetail.jsp";
		} catch (Exception e) {
			request.setAttribute("errorMessage", "로그인 중 오류가 발생했습니다.");
			return "login.jsp";
		}
	}
}
