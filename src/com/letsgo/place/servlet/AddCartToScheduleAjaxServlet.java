package com.letsgo.place.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.letsgo.place.model.PlaceVO;
import com.letsgo.place.service.MyScheduleService;
import com.letsgo.place.util.JsonStrings;

@WebServlet("/addCartToScheduleAjax")
public class AddCartToScheduleAjaxServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=UTF-8");

		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("loginOK");
		if (userId == null) {
			response.getWriter().print("{\"ok\":false,\"message\":\"로그인이 필요합니다.\"}");
			return;
		}

		String placeIdsParam = request.getParameter("placeIds");
		if (placeIdsParam == null || placeIdsParam.trim().isEmpty()) {
			response.getWriter().print("{\"ok\":false,\"message\":\"추가할 장소를 선택해주세요.\"}");
			return;
		}

		List<String> placeIds = new ArrayList<>();
		for (String id : placeIdsParam.split(",")) {
			String t = id.trim();
			if (!t.isEmpty()) {
				placeIds.add(t);
			}
		}
		if (placeIds.isEmpty()) {
			response.getWriter().print("{\"ok\":false,\"message\":\"추가할 장소를 선택해주세요.\"}");
			return;
		}

		String mergeScheduleId = request.getParameter("myScheduleId");
		if (mergeScheduleId != null) {
			mergeScheduleId = mergeScheduleId.trim();
		} else {
			mergeScheduleId = "";
		}

		try {
			MyScheduleService service = new MyScheduleService();
			List<String> addedIds;
			String resultScheduleId;

			if (!mergeScheduleId.isEmpty()) {
				addedIds = service.addCartPlacesToSchedule(userId, mergeScheduleId, placeIds);
				if (addedIds == null) {
					response.getWriter().print("{\"ok\":false,\"message\":\"일정에 추가하지 못했습니다.\"}");
					return;
				}
				resultScheduleId = mergeScheduleId;
			} else {
				String scheduleTitle = request.getParameter("scheduleTitle");
				MyScheduleService.NewScheduleFromCartResult created = service
						.createNewScheduleFromCartPlaces(userId, placeIds, scheduleTitle);
				if (created == null) {
					response.getWriter().print("{\"ok\":false,\"message\":\"새 일정을 만들지 못했습니다.\"}");
					return;
				}
				resultScheduleId = created.getScheduleId();
				addedIds = created.getAddedPlaceIds();
			}

			if (addedIds == null) {
				response.getWriter().print("{\"ok\":false,\"message\":\"일정에 추가하지 못했습니다.\"}");
				return;
			}

			if (!addedIds.isEmpty()) {
				@SuppressWarnings("unchecked")
				List<PlaceVO> cart = (List<PlaceVO>) session.getAttribute("placeCartList");
				if (cart != null) {
					Iterator<PlaceVO> it = cart.iterator();
					while (it.hasNext()) {
						PlaceVO p = it.next();
						if (p != null && p.getPlaceId() != null && addedIds.contains(p.getPlaceId())) {
							it.remove();
						}
					}
				}
			}
			session.setAttribute("currentScheduleId", resultScheduleId);
			StringBuilder idsJson = new StringBuilder("[");
			for (int i = 0; i < addedIds.size(); i++) {
				if (i > 0) {
					idsJson.append(',');
				}
				idsJson.append(JsonStrings.quotedValue(addedIds.get(i)));
			}
			idsJson.append("]");
			response.getWriter().print(
					"{\"ok\":true,\"added\":" + addedIds.size() + ",\"addedPlaceIds\":" + idsJson + "}");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			response.getWriter().print("{\"ok\":false,\"message\":\"서버 오류가 발생했습니다.\"}");
		}
	}
}
