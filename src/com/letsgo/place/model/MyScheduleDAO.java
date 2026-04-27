package com.letsgo.place.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MyScheduleDAO {

	public List<MyScheduleVO> getMyScheduleList(String userId, String keyword, String sortType, String sharedFilter) {
		List<MyScheduleVO> tmp = new ArrayList<>();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT s.MY_SCHEDULE_ID, s.TITLE AS SCH_TITLE, s.START_AT, s.IS_SHARED, p.TITLE AS PLACE_TITLE, p.ADDR1, p.FIRST_IMAGE, p.PLACE_TYPE ");
		sql.append("FROM MY_SCHEDULE s ");
		sql.append("JOIN VISIT_ITEM v ON s.MY_SCHEDULE_ID = v.SCHEDULE_ID ");
		sql.append("JOIN PLACE p ON v.PLACE_ID = p.PLACE_ID ");
		sql.append("WHERE s.USER_ID = ? ");

		if ("shared".equals(sharedFilter)) {
			sql.append("AND s.IS_SHARED = 1 ");
		}

		if (keyword != null && !keyword.trim().isEmpty()) {
			sql.append("AND (s.TITLE LIKE ? OR p.TITLE LIKE ?) ");
		}

		if ("title".equals(sortType)) {
			sql.append("ORDER BY s.TITLE ASC, v.VISIT_ORDER ASC");
		} else {
			sql.append("ORDER BY s.START_AT DESC, v.VISIT_ORDER ASC");
		}

		try {
			Connection conn = DBCP.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql.toString());

			int idx = 1;
			stmt.setString(idx++, userId);

			if (keyword != null && !keyword.trim().isEmpty()) {
				String searchKey = "%" + keyword + "%";
				stmt.setString(idx++, searchKey);
				stmt.setString(idx++, searchKey);
			}
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				tmp.add(new MyScheduleVO(rs.getString("MY_SCHEDULE_ID"), rs.getString("SCH_TITLE"),
						rs.getString("START_AT"), rs.getString("IS_SHARED"), rs.getString("placeId"),
						rs.getString("PLACE_TITLE"), rs.getString("ADDR1"), rs.getString("FIRST_IMAGE"),
						rs.getString("PLACE_TYPE")));
			}
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tmp;
	}

	public boolean deleteMySchedule(String userId, String scheduleId) {
		boolean flag = false;
		try {
			Connection conn = DBCP.getConnection();

			String sql = "DELETE FROM VISIT_ITEM WHERE MY_SCHEDULE_ID = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, scheduleId);
			stmt.executeUpdate();

			sql = "DELETE FROM MY_SCHEDULE WHERE MY_SCHEDULE_ID = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, scheduleId);
			stmt.executeUpdate();

			stmt.close();
			conn.close();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;

	}

	public boolean deleteMyScheduleList(String userId, String[] scheduleId) {
		boolean flag = false;
		String sql = "";
		try {
			Connection conn = DBCP.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			for (String tmp : scheduleId) {
				sql = "DELETE FROM VISIT_ITEM WHERE MY_SCHEDULE_ID = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, tmp);
				stmt.executeUpdate();

				sql = "DELETE FROM MY_SCHEDULE WHERE MY_SCHEDULE_ID = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, tmp);
				stmt.executeUpdate();
			}
			stmt.close();
			conn.close();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	public boolean setMySchedule(String[] visitItemId, String[] visitOrder, String[] distanceToNext, String scheduleId,
			String title, String startAt, String budgetDetail, String todoDetail, String userId, int isShared) {
		boolean flag = false;
		try {
			String sql = "";
			Connection conn = DBCP.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			for (int i = 0; i < visitItemId.length; i++) {

				sql = "UPDATE VISIT_ITEM SET VISIT_ORDER = ?, DISTANCE_TO_NEXT = ? WHERE VISIT_ITEM_ID = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, visitItemId[i]);
				stmt.setString(2, visitOrder[i]);
				stmt.setString(3, distanceToNext[i]);
				stmt.executeUpdate();
			}
			sql = "UPDATE MY_SCHEDULE SET TITLE = ?, START_AT = ?, BUDGET_DETAILS = ?, TODO_DETAILS = ?, IS_SHARED = ? WHERE MY_SCHEDULE_ID = ?' AND USER_ID = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, title);
			stmt.setString(2, startAt);
			stmt.setString(3, budgetDetail);
			stmt.setString(4, todoDetail);
			stmt.setLong(5, isShared);
			stmt.setString(6, scheduleId);
			stmt.setString(7, userId);
			flag = (stmt.executeUpdate() == 1);

			stmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public boolean setMyScheduleTitle(String title, String myScheduleId, String userId) {
		boolean flag = false;

		try {
			Connection conn = DBCP.getConnection();

			String sql = "UPDATE MY_SCHEDULE SET TITLE = ? WHERE MY_SCHEDULE_ID = ? AND USER_ID = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, title);
			stmt.setString(2, myScheduleId);
			stmt.setString(3, userId);

			flag = (stmt.executeUpdate() == 1);
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public boolean setTodoDetail(String scheduleId, String todoDetail) {
		boolean flag = false;
		try {
			Connection conn = DBCP.getConnection();
			String sql = "UPDATE MY_SCHEDULE SET TODO_DETAILS = ? WHERE MY_SCHEDULE_ID = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, todoDetail);
			stmt.setString(2, scheduleId);

			flag = (stmt.executeUpdate() == 1);
			stmt.close();
			conn.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return flag;

	}

	public String getTodoDetail(String scheduleId) {
		String str = "";
		try {
			Connection conn = DBCP.getConnection();
			String sql = "SELECT TODO_DETAILS FROM MY_SCHEDULE WHERE MY_SCHEDULE_ID = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, scheduleId);
			ResultSet rs = stmt.executeQuery();
			str = rs.getString(1);
			
			stmt.close();
			conn.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return str;

	}
	
	public List<ScheduleRouteVO> getScheduleRoute(String userId, String scheduleId) {
		List<ScheduleRouteVO> list;
		list = new ArrayList<ScheduleRouteVO>();
		try {
			Connection conn = DBCP.getConnection();
			String sql = "SELECT s.MY_SCHEDULE_ID, v.VISIT_ORDER, p.TITLE FROM MY_SCHEDULE s JOIN VISIT_ITEM v ON s.MY_SCHEDULE_ID = v.SCHEDULE_ID JOIN PLACE p ON v.PLACE_ID = p.PLACE_ID WHERE s.USER_ID = ? AND s.MY_SCHEDULE_ID = ? ORDER BY v.VISIT_ORDER ASC";
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, userId);
			stmt.setString(2, scheduleId);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				list.add(new ScheduleRouteVO(rs.getString("MY_SCHEDULE_ID"), rs.getString("VISIT_ORDER"), rs.getString("TITLE")));
			}
			
			
			stmt.close();
			conn.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}
	

}
