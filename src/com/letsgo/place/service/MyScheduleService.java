package com.letsgo.place.service;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;

import com.letsgo.place.model.DBCP;
import com.letsgo.place.model.MyScheduleDAO;

import oracle.net.aso.f;

public class MyScheduleService {

	private MyScheduleDAO dao;

	private Connection conn;

	public MyScheduleService() throws Exception {
		conn = DBCP.getConnection();
		dao = new MyScheduleDAO(conn);
	}

	// JTA - Spring AOP 개념
	public boolean deleteMySchedule(String scheduleId) {
		boolean result = false;
		try {
			conn.setAutoCommit(false);
			result = dao.deleteMySchedule(scheduleId);
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;

	}

	public boolean deleteMyScheduleList(String userId, String[] scheduleIds) {
		boolean result = false;
		try{
			conn.setAutoCommit(false);
			result=dao.deleteMyScheduleList(userId, scheduleIds);
			if(result) conn.commit();
			else 
				conn.rollback();
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

}
