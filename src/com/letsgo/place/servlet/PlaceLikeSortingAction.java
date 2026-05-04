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
        String redirectCmd = request.getParameter("redirectCmd");
        
        if (sort == null || sort.trim().isEmpty()) {
            sort = "distance";
        }
        
        PlaceService placeService = new PlaceService();//else if가 너무 많아서 리팩토링 생각해보기.
        
        if ("restaurantUI".equals(redirectCmd)) {
            if ("like".equals(sort)) {
                request.setAttribute("restaurantPlaceList", placeService.getPlaceOrderByLike("RESTAURANT"));
            } else {
                request.setAttribute("restaurantPlaceList", placeService.getPlaceOrderByTitle("RESTAURANT"));
            }
            request.setAttribute("sortOrder", sort);
            return "restaurant.jsp";
            
        } else if ("stayUI".equals(redirectCmd)) {
            if ("like".equals(sort)) {
                request.setAttribute("stayPlaceList", placeService.getPlaceOrderByLike("STAY"));
            } else {
                request.setAttribute("stayPlaceList", placeService.getPlaceOrderByTitle("STAY"));
            }
            request.setAttribute("sortOrder", sort);
            return "stay.jsp";
            
        } else if ("leisureUI".equals(redirectCmd)) {
            if ("like".equals(sort)) {
                request.setAttribute("leisurePlaceList", placeService.getPlaceOrderByLike("LEISURE"));
            } else {
                request.setAttribute("leisurePlaceList", placeService.getLeisurePlaces());
            }
            request.setAttribute("sortOrder", sort);
            return "leisure.jsp";
        }
        
        if ("like".equals(sort)) {
            request.setAttribute("leisurePlaceList", placeService.getLeisurePlacesOrderByLikeDesc());
        } else {
            request.setAttribute("leisurePlaceList", placeService.getLeisurePlaces());
        }
        request.setAttribute("sortOrder", sort);
        return "index.jsp";
    }
}