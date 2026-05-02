package com.letsgo.place.servlet;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.letsgo.place.model.PlaceVO;

/**
 * 플로팅 카트에서 항목 삭제 시 세션 placeCartList 와 맞춥니다.
 */
@WebServlet("/removeCartItemsAjax")
public class RemoveCartItemsAjaxServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=UTF-8");

		HttpSession session = request.getSession();
		String placeIdsParam = request.getParameter("placeIds");
		if (placeIdsParam == null || placeIdsParam.trim().isEmpty()) {
			response.getWriter().print("{\"ok\":true}");
			return;
		}

		@SuppressWarnings("unchecked")
		List<PlaceVO> cart = (List<PlaceVO>) session.getAttribute("placeCartList");
		if (cart != null) {
			Set<String> remove = new HashSet<>();
			for (String id : placeIdsParam.split(",")) {
				String t = id.trim();
				if (!t.isEmpty()) {
					remove.add(t);
				}
			}
			Iterator<PlaceVO> it = cart.iterator();
			while (it.hasNext()) {
				PlaceVO p = it.next();
				if (p != null && p.getPlaceId() != null && remove.contains(p.getPlaceId())) {
					it.remove();
				}
			}
		}
		response.getWriter().print("{\"ok\":true}");
	}
}
