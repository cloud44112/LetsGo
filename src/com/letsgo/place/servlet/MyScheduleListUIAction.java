package com.letsgo.place.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.letsgo.place.model.MyScheduleVO;
import com.letsgo.place.model.PostScheduleVO;
import com.letsgo.place.service.MyScheduleService;

public class MyScheduleListUIAction implements Action {

	@Override
	public String execute(HttpServletRequest request)
			throws ServletException, IOException, ClassNotFoundException, SQLException {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("loginOK");
		String title = (String) request.getParameter("searchTitle");
		String sortOrder = (String) request.getParameter("sortOrder");
		if (userId == null) {
			return "login.jsp";
		}

		MyScheduleService service = new MyScheduleService();
		List<MyScheduleVO> list = null;

		list = service.getMyScheduleList(userId, title, sortOrder, false);


		Map<String, MyScheduleVO> uniqueMap = new LinkedHashMap<>();
		for (MyScheduleVO vo : list) {
			if (!uniqueMap.containsKey(vo.getMyScheduleId())) {
				uniqueMap.put(vo.getMyScheduleId(), vo);
			} else {
				MyScheduleVO existingVO = uniqueMap.get(vo.getMyScheduleId());
				if (existingVO.getFirstImage() == null) {
					existingVO.setFirstImage(vo.getFirstImage());
				}
		        String combinedPlaces = existingVO.getPlaceTitle() + " / " + vo.getPlaceTitle();
		        existingVO.setPlaceTitle(combinedPlaces);
			}
//			if (!uniqueMap.containsKey(vo.getMyScheduleId())) {
//				uniqueMap.put(vo.getMyScheduleId(), vo);
//			}
		}
		request.setAttribute("myScheduleList", new ArrayList<>(uniqueMap.values()));

		return "myScheduleList.jsp";
	}

}
