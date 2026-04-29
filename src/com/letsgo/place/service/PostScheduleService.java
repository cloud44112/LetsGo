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
	
	
	public List<PostScheduleVO> getPostScheduleList(String keyword, String searchType, String sortType) {
		return dao.getPostScheduleList(keyword, searchType, sortType);
	}
	
	public List<PostScheduleVO> getUserPostScheduleList(String userId, String keyword, String searchType, String sortType) {
		return dao.getUserPostScheduleList(userId, keyword, searchType, sortType);
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
	
	public boolean plusLike(String postId) {
		return dao.plusLike(postId);
	}
	
	
	
	
}
