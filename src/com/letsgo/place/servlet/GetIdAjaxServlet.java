package com.letsgo.place.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.letsgo.place.model.UserDAO;
import com.letsgo.place.util.JsonStrings;

@WebServlet("/getIdAjax")
public class GetIdAjaxServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=UTF-8");

		String name = request.getParameter("NAME");
		String email = request.getParameter("EMAIL");

		if (name == null || email == null || name.trim().isEmpty() || email.trim().isEmpty()) {
			response.getWriter().print("{\"result\":\"fail\",\"message\":\"이름과 이메일을 입력해주세요.\"}");
			return;
		}

		try {
			String userId = new UserDAO().findUserIdByNameAndEmail(name.trim(), email.trim());
			if (userId == null) {
				response.getWriter().print("{\"result\":\"fail\",\"message\":\"일치하는 회원 정보가 없습니다.\"}");
				return;
			}

			response.getWriter().print("{\"result\":\"success\",\"userId\":" + JsonStrings.quotedValue(userId) + "}");
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().print("{\"result\":\"fail\",\"message\":\"조회 중 오류가 발생했습니다.\"}");
		}
	}
}
