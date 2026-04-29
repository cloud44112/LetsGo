package com.letsgo.place.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.letsgo.place.model.UserDAO;

public class GetIdAction implements Action {

	@Override
	public String execute(HttpServletRequest request)
			throws ServletException, IOException, ClassNotFoundException, SQLException {
		String name = request.getParameter("NAME");
		String email = request.getParameter("EMAIL");

		if (name == null || email == null || name.trim().isEmpty() || email.trim().isEmpty()) {
			request.setAttribute("errorMessage", "이름과 이메일을 입력해주세요.");
			request.removeAttribute("foundUserId");
			return "getid.jsp";
		}

		try {
			String userId = new UserDAO().findUserIdByNameAndEmail(name.trim(), email.trim());
			if (userId == null) {
				request.setAttribute("errorMessage", "일치하는 회원 정보가 없습니다.");
				request.removeAttribute("foundUserId");
				return "getid.jsp";
			}
			request.removeAttribute("errorMessage");
			request.setAttribute("foundUserId", userId);
			return "getid.jsp";
		} catch (Exception e) {
			request.setAttribute("errorMessage", "조회 중 오류가 발생했습니다.");
			request.removeAttribute("foundUserId");
			return "getid.jsp";
		}
	}
}
