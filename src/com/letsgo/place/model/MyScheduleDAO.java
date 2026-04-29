package com.letsgo.place.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MyScheduleDAO {
	private Connection conn;

	public MyScheduleDAO(Connection conn) {
		this.conn = conn;
	}

	public List<MyScheduleVO> getMyScheduleList(String userId, String keyword, String sortType, boolean sharedFilter) {
		List<MyScheduleVO> tmp = null;

		StringBuilder sql = new StringBuilder();
		sql.append(MyScheduleQuery.GET_MY_SCHEDULE_LIST_SQL);

		if (sharedFilter) {
			sql.append(MyScheduleQuery.SHARED_FILTER);
		}

		if (keyword != null && !keyword.trim().isEmpty()) {
			sql.append(MyScheduleQuery.KEYWORD_FILTER);
		}

		if ("title".equals(sortType)) {
			sql.append(MyScheduleQuery.ORDER_BY_TITLE);
		} else {
			sql.append(MyScheduleQuery.ORDER_BY_START_AT);
		}

		try {
			PreparedStatement stmt = conn.prepareStatement(sql.toString());
			int idx = 1;
			stmt.setString(idx++, userId);

			if (keyword != null && !keyword.trim().isEmpty()) {
				String searchKey = "%" + keyword + "%";
				stmt.setString(idx++, searchKey);
				stmt.setString(idx++, searchKey);
			}
			ResultSet rs = stmt.executeQuery();
			tmp = new ArrayList<MyScheduleVO>();
			while (rs.next()) {
				tmp.add(new MyScheduleVO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7)));
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tmp;
	}

	public boolean deleteMySchedule(String scheduleId) {
		boolean flag = false;
		try {
			PreparedStatement stmtVisit = conn.prepareStatement(MyScheduleQuery.DELETE_VISIT_ITEM_BY_SCHEDULE_ID);
			stmtVisit.setString(1, scheduleId);
			flag = (stmtVisit.executeUpdate() == 1);
			stmtVisit.close();

			PreparedStatement stmtSchedule = conn.prepareStatement(MyScheduleQuery.DELETE_MY_SCHEDULE_BY_SCHEDULE_ID);
			stmtSchedule.setString(1, scheduleId);

			flag = (stmtSchedule.executeUpdate() == 1);
			stmtSchedule.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;

	}

	public boolean deleteMyScheduleList(String userId, String[] scheduleIds) {
		boolean flag = false;
		try {
			PreparedStatement stmtVisit = conn.prepareStatement(MyScheduleQuery.DELETE_VISIT_ITEMS_LIST_BY_SCHEDULE_ID);
			PreparedStatement stmtSch = conn.prepareStatement(MyScheduleQuery.DELETE_MY_SCHEDULES_BY_SCHEDULE_ID);

			for (String schId : scheduleIds) {

				stmtVisit.setString(1, schId);
				stmtVisit.executeUpdate();

				stmtSch.setString(1, schId);
				stmtSch.setString(2, userId);
				flag = (stmtSch.executeUpdate() == 1);
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return flag;
	}

	public boolean setMySchedule(String[] visitItemId, int[] visitOrder, String[] distanceToNext, String scheduleId,
			String scheduleTitle, String startAt, String budgetDetail, String todoDetail, String userId, int isShared) {
		boolean flag = false;
		try {
			PreparedStatement stmt;
			PreparedStatement stmtVisit;
			for (int i = 0; i < visitItemId.length; i++) {
				stmt = conn.prepareStatement(MyScheduleQuery.SET_MY_SCHEDULE);
				stmt.setInt(1, visitOrder[i]);
				stmt.setString(2, distanceToNext[i]);
				stmt.setString(3, visitItemId[i]);
				stmt.executeUpdate();
			}
			stmtVisit = conn.prepareStatement(MyScheduleQuery.SET_VISIT_LIST);
			stmtVisit.setString(1, scheduleTitle);
			stmtVisit.setString(2, startAt);
			stmtVisit.setString(3, budgetDetail);
			stmtVisit.setString(4, todoDetail);
			stmtVisit.setInt(5, isShared);
			stmtVisit.setString(6, scheduleId);
			stmtVisit.setString(7, userId);
			flag = (stmtVisit.executeUpdate() == 1);
			stmtVisit.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public boolean setMyScheduleTitle(String title, String myScheduleId, String userId) {
		boolean flag = false;

		try {
			PreparedStatement stmt = conn.prepareStatement(MyScheduleQuery.SET_MY_SCHEDULE_TITLE);

			stmt.setString(1, title);
			stmt.setString(2, myScheduleId);
			stmt.setString(3, userId);

			flag = (stmt.executeUpdate() == 1);
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public boolean setTodoDetail(String scheduleId, String todoDetail) {
		boolean flag = false;
		try {
			PreparedStatement stmt = conn.prepareStatement(MyScheduleQuery.SET_TODO_DETAIL);

			stmt.setString(1, todoDetail);
			stmt.setString(2, scheduleId);

			flag = (stmt.executeUpdate() == 1);
			stmt.close();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return flag;

	}

	public String getTodoDetail(String scheduleId) {
		String str = null;
		try {
			PreparedStatement stmt = conn.prepareStatement(MyScheduleQuery.GET_TODO_DETAIL);

			stmt.setString(1, scheduleId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next())
				str = rs.getString(1);

			stmt.close();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return str;

	}

	public List<RouteScheduleVO> getScheduleRoute(String userId, String scheduleId) {
		List<RouteScheduleVO> list = null;
		try {
			PreparedStatement stmt = conn.prepareStatement(MyScheduleQuery.GET_SCHEDULE_ROUTE);

			stmt.setString(1, userId);
			stmt.setString(2, scheduleId);
			ResultSet rs = stmt.executeQuery();
			list = new ArrayList<RouteScheduleVO>();
			while (rs.next()) {
				list.add(new RouteScheduleVO(rs.getString("VISIT_ITEM_ID"), rs.getString("VISIT_ORDER"),
						rs.getString("PLACE_ID"), rs.getString("TITLE")));
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	public List<MapScheduleVO> getMapSchedule(String scheduleId) {
		List<MapScheduleVO> list = null;

		try {
			PreparedStatement stmt = conn.prepareStatement(MyScheduleQuery.GET_MAP_SCHEDULE);

			stmt.setString(1, scheduleId);
			ResultSet rs = stmt.executeQuery();
			list = new ArrayList<MapScheduleVO>();
			while (rs.next()) {
				list.add(new MapScheduleVO(rs.getString("TITLE"), rs.getString("VISIT_ORDER"), rs.getString("MAPX"),
						rs.getString("MAPY"), rs.getString("DISTANCE_TO_NEXT")));
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return list;
	}

	public boolean addVisitItem(int visitOrder, String placeId, String scheduleId) {
		boolean flag = false;
		try {
			PreparedStatement stmt = conn.prepareStatement(MyScheduleQuery.ADD_VISIT_ITEM);

			stmt.setInt(1, visitOrder);
			stmt.setString(2, placeId);
			stmt.setString(3, scheduleId);

			flag = (stmt.executeUpdate() == 1);

			stmt.close();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return flag;
	}

	public boolean deleteVisitItemById(String visitItemId) {
		boolean flag = false;
		try {
			PreparedStatement stmt = conn.prepareStatement(MyScheduleQuery.DELETE_VISIT_ITEM_BY_ID);

			stmt.setString(1, visitItemId);

			flag = (stmt.executeUpdate() == 1);

			stmt.close();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return flag;
	}

	public boolean addCompanion(String myScheduleId, String sharedUserId) {
		boolean flag = false;
		try {
			PreparedStatement stmt = conn.prepareStatement(MyScheduleQuery.ADD_COMPANION);

			stmt.setString(1, myScheduleId);
			stmt.setString(2, sharedUserId);

			flag = (stmt.executeUpdate() == 1);

			stmt.close();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return flag;
	}

	public boolean setCompanionPermission(String myScheduleId, String sharedUserId, String permission) {
		boolean flag = false;
		try {
			PreparedStatement stmt = conn.prepareStatement(MyScheduleQuery.SET_COMPANION_PERMISSION);

			stmt.setString(1, permission);
			stmt.setString(2, myScheduleId);
			stmt.setString(3, sharedUserId);

			flag = (stmt.executeUpdate() == 1);

			stmt.close();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return flag;
	}

	public List<ColleagueVO> getCompanionList(String myScheduleId) {
		List<ColleagueVO> list = null;

		try {
			PreparedStatement stmt = conn.prepareStatement(MyScheduleQuery.GET_COMPANION_LIST);

			stmt.setString(1, myScheduleId);
			ResultSet rs = stmt.executeQuery();
			list = new ArrayList<ColleagueVO>();
			while (rs.next()) {
				list.add(new ColleagueVO(rs.getString("USER_ID"), rs.getString("NAME"), rs.getString("EMAIL"),
						rs.getString("PERMISSION")));
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return list;
	}

	public String shareToPost(String myScheduleId, String userId, int isAnonymous) throws SQLException {
		String str = null;
		try {

			PreparedStatement stmtSchedule = conn.prepareStatement(MyScheduleQuery.SHARE_TO_POST_BY_SCHEDULE_ID);

			stmtSchedule.setInt(1, isAnonymous);
			stmtSchedule.setString(2, userId);
			stmtSchedule.setString(3, myScheduleId);

			stmtSchedule.executeUpdate();
			stmtSchedule.close();

			PreparedStatement stmtVisit = conn
					.prepareStatement(MyScheduleQuery.SHARE_TO_VISIT_ITEM_LIST_BY_SCHEDULE_ID);
			stmtVisit.setString(1, myScheduleId);

			stmtVisit.executeUpdate();
			stmtVisit.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return str;
	}
}
