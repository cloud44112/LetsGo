package com.letsgo.place.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.letsgo.place.model.PlaceVO;
import com.letsgo.place.service.PlaceService;

public class SearchPlaceAction implements Action {

	@Override
	public String execute(HttpServletRequest request)
			throws ServletException, IOException, ClassNotFoundException, SQLException {
		
        String placeType = request.getParameter("placeType"); 
        String keyword = request.getParameter("keyword");     
        String category = request.getParameter("category");   
        String sortOrder = request.getParameter("sortOrder"); 
        
        if (placeType == null || placeType.trim().isEmpty()) {
            placeType = "LEISURE"; 
        }

        PlaceService placeService = new PlaceService();
        
        List<PlaceVO> placeList = placeService.searchPlaces(placeType, category, keyword, sortOrder);
        
        int totalCount = placeList.size(); 
        request.setAttribute("totalCount", totalCount);
        
        if ("RESTAURANT".equals(placeType)) {
            request.setAttribute("restaurantPlaceList", placeList);
            return "restaurant.jsp";
            
        } else if ("STAY".equals(placeType)) {
            request.setAttribute("stayPlaceList", placeList);
            return "stay.jsp";
            
        } else {
            request.setAttribute("leisurePlaceList", placeList);
            return "leisure.jsp";
        }
	}
}
