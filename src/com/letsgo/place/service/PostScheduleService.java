package com.letsgo.place.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.letsgo.place.model.DBCP;
import com.letsgo.place.model.MapScheduleVO;
import com.letsgo.place.model.PostScheduleDAO;
import com.letsgo.place.model.PostScheduleVO;
import com.letsgo.place.model.RouteScheduleVO;


public class PostScheduleService {

	private PostScheduleDAO dao;
	private Connection conn;


	public PostScheduleService() throws ClassNotFoundException, SQLException {
		conn = DBCP.getConnection();
		dao = new PostScheduleDAO(conn);
	}
	
	public boolean deletePostSchedule(String scheduleId) {
		boolean result = false;
		try {
			conn.setAutoCommit(false);
			result = dao.deletePostSchedule(scheduleId);
			if (result)
				conn.commit();
			else
				conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
	public List<PostScheduleVO> getPostScheduleList(String keyword, String sortType) {
		return dao.getPostScheduleList(keyword, sortType);
	}
	
	public List<PostScheduleVO> getUserPostScheduleList(String userId, String keyword, String sortType) {
		return dao.getUserPostScheduleList(userId, keyword, sortType);
	}
	
	public String getBudgetDetail(String postId) {
		return dao.getBudgetDetail(postId);
	}
	
	public String getTodoDetail(String postId) {
		return dao.getTodoDetail(postId);
	}
	
	public List<RouteScheduleVO> getScheduleRoute(String postId) {
		return dao.getScheduleRoute(postId);
	}
	
	public List<MapScheduleVO> getMapSchedule(String postId) {
		return dao.getMapSchedule(postId);
	}
	
	public String getScheduleTitle (String postId) {
		return dao.getScheduleTitle(postId);
	}
	
	public int getLikeCount(String postId) {
		return dao.getLikeCount(postId);
	}
	
	public int getViewCount(String postId) {
		return dao.getViewCount(postId);
	}
	
	public boolean plusLike(String postId) {
		return dao.plusLike(postId);
	}
	
	public boolean plusView(String postId) {
		return dao.plusView(postId);
	}
	
	public String getUserId(String postId) {
		return dao.getUserId(postId);
	}
	
	public boolean addToMySchedule(String postId, String userId) {
		List<RouteScheduleVO> routes = dao.getScheduleRoute(postId);
		String title = dao.getScheduleTitle(postId);
		String budgetDetail = dao.getBudgetDetail(postId);
		String todoDetail = dao.getTodoDetail(postId);
	    
		try {
	        conn.setAutoCommit(false);
	        String MyScheduleId = dao.copyToMySchedule(title, budgetDetail, todoDetail, userId);
	        if (routes != null) {
	            for (RouteScheduleVO route : routes) {
	                dao.copyToVisitItem(MyScheduleId, route);
	            }
	        }

	        conn.commit();
	        return true;

	    } catch (Exception e) {
	        try { if (conn != null) conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
	        e.printStackTrace();
	        return false;
	    } finally {
	        try { if (conn != null) conn.setAutoCommit(true); } catch (SQLException e) { e.printStackTrace(); }
	    }
	}
}
