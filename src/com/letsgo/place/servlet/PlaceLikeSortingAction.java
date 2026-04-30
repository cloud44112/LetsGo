package com.letsgo.place.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.letsgo.place.service.PlaceService;

public class PlaceLikeSortingAction implements Action {
    @Override
    public String execute(HttpServletRequest request)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        
        String sort = request.getParameter("sortOrder");
        if (sort == null || sort.trim().isEmpty()) {
            sort = "distance";
        }
        
        PlaceService placeService = new PlaceService();
        if ("like".equals(sort)) {
            request.setAttribute("leisurePlaceList", placeService.getLeisurePlacesOrderByLikeDesc());
        } else {
            request.setAttribute("leisurePlaceList", placeService.getLeisurePlaces());
        }
        request.setAttribute("sortOrder", sort);
        return "index.jsp";
    }
}