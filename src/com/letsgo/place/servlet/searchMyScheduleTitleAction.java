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
import com.letsgo.place.service.MyScheduleService;

public class searchMyScheduleTitleAction implements Action {

	@Override
	public String execute(HttpServletRequest request)
			throws ServletException, IOException, ClassNotFoundException, SQLException {
		HttpSession session = request.getSession();
		String title = request.getParameter("searchTitle");
		System.out.println(title);
		String userId = (String) session.getAttribute("loginOK");
		System.out.println(userId);
		MyScheduleService service = new MyScheduleService();
		List<MyScheduleVO> list = service.getMyScheduleList(userId, title, "title", false);
//		System.out.println(list);

		Map<String, MyScheduleVO> uniqueMap = new LinkedHashMap<>();
		for (MyScheduleVO vo : list) {

			if (!uniqueMap.containsKey(vo.getMyScheduleId())) {
				uniqueMap.put(vo.getMyScheduleId(), vo);
			}
		}
		//System.out.println(new ArrayList<>(uniqueMap.values()));
		request.setAttribute("result", new ArrayList<>(uniqueMap.values()));
		System.out.println(request.getAttribute("result"));
		return "jsonResult.jsp";
	}

}
