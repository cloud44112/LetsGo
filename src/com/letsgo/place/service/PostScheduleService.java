package com.letsgo.place.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.letsgo.place.model.DBCP;
import com.letsgo.place.model.PostScheduleDAO;


public class PostScheduleService {

	private PostScheduleDAO dao;
	private Connection conn;


	public PostScheduleService() throws Exception {
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
	
	
}
