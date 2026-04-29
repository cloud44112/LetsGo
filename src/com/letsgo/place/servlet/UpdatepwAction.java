package com.letsgo.place.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.letsgo.place.model.UserDAO;

public class UpdatepwAction implements Action{

	@Override
	public String execute(HttpServletRequest request)
			throws ServletException, IOException, ClassNotFoundException, SQLException {
		String userId = request.getParameter("USER_ID");
		String email = request.getParameter("EMAIL");
		String newPassword = request.getParameter("NEW_PASSWORD");
		String newPasswordConfirm = request.getParameter("NEW_PASSWORD_CONFIRM");

		if (userId == null || email == null || newPassword == null || newPasswordConfirm == null
				|| userId.trim().isEmpty() || email.trim().isEmpty()
				|| newPassword.trim().isEmpty() || newPasswordConfirm.trim().isEmpty()) {
			request.setAttribute("errorMessage", "필수 항목을 모두 입력해주세요.");
			return "updatepw.jsp";
		}

		if (!newPassword.equals(newPasswordConfirm)) {
			request.setAttribute("errorMessage", "새 비밀번호 확인이 일치하지 않습니다.");
			return "updatepw.jsp";
		}

		try {
			boolean updated = new UserDAO().updatePassword(userId, email, newPassword);
			if (!updated) {
				request.setAttribute("errorMessage", "아이디 또는 이메일이 일치하지 않습니다.");
				return "updatepw.jsp";
			}

			request.setAttribute("errorMessage", "비밀번호가 변경되었습니다. 로그인 해주세요.");
			return "login.jsp";
		} catch (Exception e) {
			request.setAttribute("errorMessage", "비밀번호 변경 중 오류가 발생했습니다.");
			return "updatepw.jsp";
		}
	}

}
