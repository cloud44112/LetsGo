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

import com.letsgo.place.model.PostScheduleVO;
import com.letsgo.place.service.PostScheduleService;

public class postScheduleListUIAction implements Action {

	@Override
	public String execute(HttpServletRequest request)
			throws ServletException, IOException, ClassNotFoundException, SQLException {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("loginOK");
		
		if (userId == null) {
			return "login.jsp";
		}
	
		PostScheduleService service = new PostScheduleService();
		List<PostScheduleVO> list = service.getPostScheduleList("", "", "");
		
		Map<String, PostScheduleVO> uniqueMap = new LinkedHashMap<>();
		for (PostScheduleVO vo : list) {

			if (!uniqueMap.containsKey(vo.getPostId())) {
				uniqueMap.put(vo.getPostId(), vo);
			} else {
				PostScheduleVO existingVO = uniqueMap.get(vo.getPostId());
		        String combinedPlaces = existingVO.getPlaceTitle() + " / " + vo.getPlaceTitle();
		        existingVO.setPlaceTitle(combinedPlaces);
			}
		}

		request.setAttribute("postScheduleList", new ArrayList<>(uniqueMap.values()));
		
					
		return "postScheduleList.jsp";
	}	
}
