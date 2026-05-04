package com.letsgo.place.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.letsgo.place.model.ColleagueVO;
import com.letsgo.place.service.MyScheduleService;

public class ShareToPostAction implements Action {

	@Override
	public String execute(HttpServletRequest request)
			throws ServletException, IOException, ClassNotFoundException, SQLException {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("loginOK");
		if (userId == null) {
            return "controller?cmd=LoginUIAction"; 
        }
		String isAnonParam = request.getParameter("isAnonymous");
		int isAnonymous = (isAnonParam != null && isAnonParam.equals("1")) ? 1 : 0;
		String myScheduleId = (String) session.getAttribute("currentScheduleId");
		boolean result;
		MyScheduleService service = new MyScheduleService();
		String strResult = service.shareToPost(myScheduleId, userId, isAnonymous);

		result = (strResult == null) ? false : true;
				
		request.setAttribute("result", result);
		
		
		return "jsonResult.jsp";
	}

}
