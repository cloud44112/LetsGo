package com.letsgo.place.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.letsgo.place.service.MyScheduleService;
import com.letsgo.place.util.JsonStrings;

@WebServlet("/myScheduleListAjax")
public class MyScheduleListAjaxServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=UTF-8");

		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("loginOK");
		PrintWriter out = response.getWriter();

		if (userId == null) {
			out.print("{\"schedules\":[],\"currentScheduleId\":null}");
			return;
		}

		try {
			MyScheduleService service = new MyScheduleService();
			List<String[]> rows = service.listMyScheduleIdAndTitle(userId);
			StringBuilder json = new StringBuilder("{\"schedules\":[");
			for (int i = 0; i < rows.size(); i++) {
				String[] row = rows.get(i);
				if (i > 0) {
					json.append(',');
				}
				json.append("{\"id\":").append(JsonStrings.quotedValue(row[0])).append(",\"title\":").append(JsonStrings.quotedValue(row[1]))
						.append("}");
			}
			json.append("],\"currentScheduleId\":");
			String currentId = (String) session.getAttribute("currentScheduleId");
			boolean currentOk = false;
			if (currentId != null && !currentId.trim().isEmpty()) {
				for (String[] row : rows) {
					if (row[0] != null && row[0].equals(currentId)) {
						currentOk = true;
						break;
					}
				}
			}
			if (currentOk) {
				json.append(JsonStrings.quotedValue(currentId));
			} else {
				json.append("null");
			}
			json.append('}');
			out.print(json.toString());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			out.print("{\"schedules\":[],\"currentScheduleId\":null}");
		}
	}
}
