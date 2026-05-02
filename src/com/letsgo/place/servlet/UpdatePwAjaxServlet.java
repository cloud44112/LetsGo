package com.letsgo.place.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.letsgo.place.model.UserDAO;

@WebServlet("/updatePwAjax")
public class UpdatePwAjaxServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=UTF-8");

		String userId = request.getParameter("USER_ID");
		String email = request.getParameter("EMAIL");
		String newPassword = request.getParameter("NEW_PASSWORD");
		String newPasswordConfirm = request.getParameter("NEW_PASSWORD_CONFIRM");

		if (userId == null || email == null || newPassword == null || newPasswordConfirm == null
				|| userId.trim().isEmpty() || email.trim().isEmpty()
				|| newPassword.trim().isEmpty() || newPasswordConfirm.trim().isEmpty()) {
			response.getWriter().print("{\"result\":\"fail\",\"message\":\"필수 항목을 모두 입력해주세요.\"}");
			return;
		}

		if (!newPassword.equals(newPasswordConfirm)) {
			response.getWriter().print("{\"result\":\"fail\",\"message\":\"새 비밀번호 확인이 일치하지 않습니다.\"}");
			return;
		}

		try {
			boolean updated = new UserDAO().updatePassword(userId, email, newPassword);
			if (!updated) {
				response.getWriter().print("{\"result\":\"fail\",\"message\":\"아이디 또는 이메일이 일치하지 않습니다.\"}");
				return;
			}

			response.getWriter().print("{\"result\":\"success\",\"message\":\"비밀번호가 변경되었습니다. 로그인 해주세요.\",\"url\":\""
					+ request.getContextPath() + "/controller?cmd=loginUI\"}");
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().print("{\"result\":\"fail\",\"message\":\"비밀번호 변경 중 오류가 발생했습니다.\"}");
		}
	}
}
