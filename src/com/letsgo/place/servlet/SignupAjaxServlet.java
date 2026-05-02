package com.letsgo.place.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.letsgo.place.model.UserDAO;

@WebServlet("/signupAjax")
public class SignupAjaxServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=UTF-8");

		String userId = request.getParameter("USER_ID");
		String name = request.getParameter("NAME");
		String email = request.getParameter("EMAIL");
		String password = request.getParameter("PASSWORD");
		String passwordConfirm = request.getParameter("PASSWORD_CONFIRM");

		if (userId == null || name == null || email == null || password == null || passwordConfirm == null
				|| userId.trim().isEmpty() || name.trim().isEmpty()
				|| email.trim().isEmpty() || password.trim().isEmpty() || passwordConfirm.trim().isEmpty()) {
			response.getWriter().print("{\"result\":\"fail\",\"message\":\"필수 항목을 모두 입력해주세요.\"}");
			return;
		}

		if (!password.equals(passwordConfirm)) {
			response.getWriter().print("{\"result\":\"fail\",\"message\":\"비밀번호 확인이 일치하지 않습니다.\"}");
			return;
		}

		try {
			UserDAO dao = new UserDAO();
			if (dao.idcheck(userId)) {
				response.getWriter().print("{\"result\":\"fail\",\"message\":\"이미 사용 중인 아이디입니다.\"}");
				return;
			}

			boolean result = dao.signup(userId, email, name, password);
			if (!result) {
				response.getWriter().print("{\"result\":\"fail\",\"message\":\"회원가입에 실패했습니다.\"}");
				return;
			}

			response.getWriter().print("{\"result\":\"success\",\"message\":\"회원가입 완료. 로그인 해주세요.\",\"url\":\""
					+ request.getContextPath() + "/controller?cmd=loginUI\"}");
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().print("{\"result\":\"fail\",\"message\":\"회원가입 중 오류가 발생했습니다.\"}");
		}
	}
}
