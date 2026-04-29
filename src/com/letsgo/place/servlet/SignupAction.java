package com.letsgo.place.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.letsgo.place.model.UserDAO;

public class SignupAction implements Action {

	@Override
	public String execute(HttpServletRequest request)
			throws ServletException, IOException, ClassNotFoundException, SQLException {
		String userId = request.getParameter("USER_ID");
		String name = request.getParameter("NAME");
		String email = request.getParameter("EMAIL");
		String password = request.getParameter("PASSWORD");
		String passwordConfirm = request.getParameter("PASSWORD_CONFIRM");

		if (userId == null || name == null || email == null || password == null
				|| userId.trim().isEmpty() || name.trim().isEmpty() || email.trim().isEmpty() || password.trim().isEmpty()) {
			request.setAttribute("errorMessage", "필수 항목을 모두 입력해주세요.");
			return "signup.jsp";
		}

		if (!password.equals(passwordConfirm)) {
			request.setAttribute("errorMessage", "비밀번호 확인이 일치하지 않습니다.");
			return "signup.jsp";
		}

		try {
			UserDAO dao = new UserDAO();

			if (dao.idcheck(userId)) {
				request.setAttribute("errorMessage", "이미 사용 중인 아이디입니다.");
				return "signup.jsp";
			}

			boolean result = dao.signup(userId, email, name, password);
			if (!result) {
				request.setAttribute("errorMessage", "회원가입에 실패했습니다.");
				return "signup.jsp";
			}

			request.setAttribute("errorMessage", "회원가입 완료. 로그인 해주세요.");
			return "login.jsp";
		} catch (Exception e) {
			request.setAttribute("errorMessage", "회원가입 중 오류가 발생했습니다.");
			return "signup.jsp";
		}
	}
}
