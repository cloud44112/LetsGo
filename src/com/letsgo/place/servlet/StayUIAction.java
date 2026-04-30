package com.letsgo.place.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.letsgo.place.model.PlaceVO;
import com.letsgo.place.service.PlaceService;

public class StayUIAction implements Action {

	@Override
	public String execute(HttpServletRequest request)
			throws ServletException, IOException, ClassNotFoundException, SQLException {
		List<PlaceVO> stayPlaceList = new PlaceService().getPlaceOrderByLike("STAY");
        request.setAttribute("stayPlaceList", stayPlaceList);
        
		return "stay.jsp";
	}

}
