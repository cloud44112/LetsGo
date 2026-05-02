package com.letsgo.place.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.letsgo.place.model.PlaceVO;
import com.letsgo.place.service.PlaceService;

public class AddCartAction implements Action {

	@Override
	public String execute(HttpServletRequest request)
			throws ServletException, IOException, ClassNotFoundException, SQLException {
		HttpSession session = request.getSession();
		String placeId = request.getParameter("placeId");
		String placeType = request.getParameter("placeType");

		if (placeId == null || placeId.trim().isEmpty()) {
			request.setAttribute("cartMessage", "플레이스가 없습니다.");
			return new IndexUIAction().execute(request);
		}

		@SuppressWarnings("unchecked")
		List<PlaceVO> cartList = (List<PlaceVO>) session.getAttribute("placeCartList");
		if (cartList == null) {
			cartList = new ArrayList<>();
			session.setAttribute("placeCartList", cartList);
		}

		if (isAlreadyInCart(cartList, placeId)) {
			request.setAttribute("cartMessage", "이미 담긴 플레이스입니다.");
			return new IndexUIAction().execute(request);
		}

		if ("LEISURE".equals(placeType) && hasLeisurePlace(cartList)) {
			request.setAttribute("cartMessage", "레포츠는 1개만 담을 수 있습니다.");
			return new IndexUIAction().execute(request);
		}

		if ("STAY".equals(placeType) && hasStayPlace(cartList)) {
			request.setAttribute("cartMessage", "숙박은 1개만 담을 수 있습니다.");
			return new IndexUIAction().execute(request);
		}
	
		PlaceService service = new PlaceService();
		PlaceVO place = service.getPlaceById(placeId);
		if (place != null) {
			place.setPlaceType(placeType);
			cartList.add(place);
			request.setAttribute("cartMessage", "바구니에 담았습니다.");
		}

		return new IndexUIAction().execute(request);
	}

	private boolean isAlreadyInCart(List<PlaceVO> cartList, String placeId) {
		for (PlaceVO place : cartList) {
			if (placeId.equals(place.getPlaceId())) {
				return true;
			}
		}
		return false;
	}

	private boolean hasLeisurePlace(List<PlaceVO> cartList) {
		for (PlaceVO place : cartList) {
			if ("LEISURE".equals(place.getPlaceType())) {
				return true;
			}
		}
		return false;
	}

	private boolean hasStayPlace(List<PlaceVO> cartList) {
		for (PlaceVO place : cartList) {
			if ("STAY".equals(place.getPlaceType())) {
				return true;
			}
		}
		return false;
	}
}
