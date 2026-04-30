package com.letsgo.place.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.letsgo.place.model.PlaceVO;
import com.letsgo.place.service.PlaceService;

public class IndexUIAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException, ClassNotFoundException, SQLException {
		String sort = request.getParameter("sortOrder");
		if (sort == null || sort.trim().isEmpty()) {
			sort = "distance";
		}
		PlaceService placeService = new PlaceService();
		List<PlaceVO> leisurePlaceList;
		if ("like".equals(sort)) {
			leisurePlaceList = placeService.getLeisurePlacesOrderByLikeDesc();
		} else {
			leisurePlaceList = placeService.getLeisurePlaces();
		}
		request.setAttribute("leisurePlaceList", leisurePlaceList);
		request.setAttribute("sortOrder", sort);

		return "index.jsp";
	}

}	
