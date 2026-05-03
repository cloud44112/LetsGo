package com.letsgo.place.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.letsgo.place.model.PlaceVO;
import com.letsgo.place.model.RouteScheduleVO;
import com.letsgo.place.service.MyScheduleService;
import com.letsgo.place.service.PlaceService;

public class LeisureUIAction implements Action {

	@Override
	public String execute(HttpServletRequest request)
			throws ServletException, IOException, ClassNotFoundException, SQLException {
		List<PlaceVO> leisurePlaceList = new PlaceService().getPlaceOrderByLike("LEISURE");
        request.setAttribute("leisurePlaceList", leisurePlaceList);
        request.setAttribute("totalCount", leisurePlaceList.size());

        String fromSchedule = request.getParameter("fromSchedule");
        HttpSession session = request.getSession();
        if ("true".equals(fromSchedule)) {
            String scheduleId = (String) session.getAttribute("currentScheduleId");
            String userId = (String) session.getAttribute("loginOK");
            if (scheduleId != null && userId != null) {
                MyScheduleService svc = new MyScheduleService();
                List<RouteScheduleVO> routes = svc.getScheduleRoute(scheduleId);
                session.setAttribute("lockedCartItems", routes);
                session.setAttribute("fromScheduleMode", Boolean.TRUE);
            }
        }

		return "leisure.jsp";
	}

}
