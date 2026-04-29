
package com.letsgo.place.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.letsgo.place.service.PlaceService;

public class PlaceLikeAction implements Action {

	@Override
	public String execute(HttpServletRequest request)
			throws ServletException, IOException, ClassNotFoundException, SQLException {
		String placeId = request.getParameter("placeId");
		PlaceService placeService = new PlaceService();

		if (placeId != null && !placeId.trim().isEmpty()) {
			placeService.setPlaceLikeCount(placeId);
		}

		request.setAttribute("leisurePlaceList", placeService.getLeisurePlacesOrderByLikeDesc());
		return "index.jsp";
	}
}
