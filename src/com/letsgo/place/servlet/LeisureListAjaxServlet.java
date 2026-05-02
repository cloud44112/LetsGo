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

import com.letsgo.place.model.PlaceVO;
import com.letsgo.place.service.PlaceService;
import com.letsgo.place.util.JsonStrings;


@WebServlet("/leisureListAjax")
public class LeisureListAjaxServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");

		String sort = request.getParameter("sortOrder");
		if (sort == null || sort.trim().isEmpty()) {
			sort = "distance";
		}
		if (!"like".equals(sort) && !"distance".equals(sort)) {
			sort = "distance";
		}

		PrintWriter out = response.getWriter();
		try {
			PlaceService placeService = new PlaceService();
			List<PlaceVO> list = "like".equals(sort)
					? placeService.getLeisurePlacesOrderByLikeDesc()
					: placeService.getLeisurePlaces();

			StringBuilder json = new StringBuilder("[");
			for (int i = 0; i < list.size(); i++) {
				PlaceVO p = list.get(i);
				if (i > 0) {
					json.append(',');
				}
				String placeType = p.getPlaceType();
				if (placeType == null || placeType.trim().isEmpty()) {
					placeType = "LEISURE";
				}
				json.append('{');
				json.append("\"placeId\":").append(JsonStrings.quotedValue(p.getPlaceId()));
				json.append(",\"title\":").append(JsonStrings.quotedValue(p.getTitle()));
				json.append(",\"addr1\":").append(JsonStrings.quotedValue(p.getAddr1()));
				json.append(",\"firstImage\":").append(JsonStrings.quotedValue(p.getFirstImage()));
				json.append(",\"likeCount\":").append(p.getLikeCount());
				json.append(",\"placeType\":").append(JsonStrings.quotedValue(placeType));
				json.append('}');
			}
			json.append(']');
			out.print(json.toString());
		} catch (ClassNotFoundException | SQLException e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			out.print("{\"error\":true,\"message\":\"목록을 불러오지 못했습니다.\"}");
		}
	}
}
