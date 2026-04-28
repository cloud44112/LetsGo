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

	public MyScheduleDAO() throws Exception {
		conn = DBCP.getConnection();
	}

	public MyScheduleDAO(Connection conn) throws Exception {
		this.conn = conn;
	}

	public List<MyScheduleVO> getMyScheduleList(String userId, String keyword, String sortType, boolean sharedFilter) {
		List<MyScheduleVO> tmp = new ArrayList<>();

		StringBuilder sql = new StringBuilder();
		sql.append(
				"SELECT s.MY_SCHEDULE_ID, s.TITLE AS SCH_TITLE, s.START_AT, s.IS_SHARED, p.TITLE AS PLACE_TITLE, p.ADDR1, p.FIRST_IMAGE ");
		sql.append("FROM MY_SCHEDULE s ");
		sql.append("JOIN VISIT_ITEM v ON s.MY_SCHEDULE_ID = v.SCHEDULE_ID ");
		sql.append("JOIN PLACE p ON v.PLACE_ID = p.PLACE_ID ");
		sql.append("WHERE s.USER_ID = ? ");

		if (sharedFilter) {
			sql.append("AND s.IS_SHARED = 1 ");
		}

		if (keyword != null && !keyword.trim().isEmpty()) {
			sql.append("AND (s.TITLE LIKE ? OR p.TITLE LIKE ?) ");
		}

		if ("title".equals(sortType)) {
			sql.append("ORDER BY s.TITLE ASC");
		} else {
			sql.append("ORDER BY s.START_AT DESC");
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
			String sqlVisit = "DELETE FROM VISIT_ITEM WHERE SCHEDULE_ID = ?";
			PreparedStatement stmtVisit = conn.prepareStatement(sqlVisit);
			stmtVisit.setString(1, scheduleId);
			flag = (stmtVisit.executeUpdate() == 1);
			stmtVisit.close();

			String sqlSchedule = "DELETE FROM MY_SCHEDULE WHERE MY_SCHEDULE_ID = ?";
			PreparedStatement stmtSchedule = conn.prepareStatement(sqlSchedule);
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
		String sqlVisit = "DELETE FROM VISIT_ITEM WHERE SCHEDULE_ID = ?";
		String sqlSchedule = "DELETE FROM MY_SCHEDULE WHERE MY_SCHEDULE_ID = ? AND USER_ID = ?";

		try {
			PreparedStatement stmtVisit = conn.prepareStatement(sqlVisit);
			PreparedStatement stmtSch = conn.prepareStatement(sqlSchedule);

			for (String schId : scheduleIds) {

				stmtVisit.setString(1, schId);
				stmtVisit.executeUpdate();

				stmtSch.setString(1, schId);
				stmtSch.setString(2, userId);
				stmtSch.executeUpdate();
			}
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return flag;
	}

	public boolean setMySchedule(String[] visitItemId, int[] visitOrder, String[] distanceToNext, String scheduleId,
			String scheduleTitle, String startAt, String budgetDetail, String todoDetail, String userId, int isShared) {
		boolean flag = false;
		try {
			String sql = "";
			PreparedStatement stmt;
			for (int i = 0; i < visitItemId.length; i++) {

				sql = "UPDATE VISIT_ITEM SET VISIT_ORDER = ?, DISTANCE_TO_NEXT = ? WHERE VISIT_ITEM_ID = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, visitOrder[i]);
				stmt.setString(2, distanceToNext[i]);
				stmt.setString(3, visitItemId[i]);
				stmt.executeUpdate();
			}
			sql = "UPDATE MY_SCHEDULE SET TITLE = ?, START_AT = ?, BUDGET_DETAILS = ?, TODO_DETAILS = ?, IS_SHARED = ? WHERE MY_SCHEDULE_ID = ? AND USER_ID = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, scheduleTitle);
			stmt.setString(2, startAt);
			stmt.setString(3, budgetDetail);
			stmt.setString(4, todoDetail);
			stmt.setInt(5, isShared);
			stmt.setString(6, scheduleId);
			stmt.setString(7, userId);
			flag = (stmt.executeUpdate() == 1);

			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public boolean setMyScheduleTitle(String title, String myScheduleId, String userId) {
		boolean flag = false;

		try {
			String sql = "UPDATE MY_SCHEDULE SET TITLE = ? WHERE MY_SCHEDULE_ID = ? AND USER_ID = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);

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
			String sql = "UPDATE MY_SCHEDULE SET TODO_DETAILS = ? WHERE MY_SCHEDULE_ID = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);

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
		String str = "";
		try {
			String sql = "SELECT TODO_DETAILS FROM MY_SCHEDULE WHERE MY_SCHEDULE_ID = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);

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
		List<RouteScheduleVO> list;
		list = new ArrayList<RouteScheduleVO>();
		try {
			String sql = "SELECT v.VISIT_ITEM_ID, v.VISIT_ORDER, p.PLACE_ID, p.TITLE FROM MY_SCHEDULE s JOIN VISIT_ITEM v ON s.MY_SCHEDULE_ID = v.SCHEDULE_ID JOIN PLACE p ON v.PLACE_ID = p.PLACE_ID WHERE s.USER_ID = ? AND s.MY_SCHEDULE_ID = ? ORDER BY v.VISIT_ORDER ASC";
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, userId);
			stmt.setString(2, scheduleId);
			ResultSet rs = stmt.executeQuery();
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

	public List<MapScheduleVO> getMapScheduleVO(String scheduleId) {
		List<MapScheduleVO> list;
		list = new ArrayList<MapScheduleVO>();
		try {
			String sql = "SELECT p.TITLE, v.VISIT_ORDER, p.MAPX, p.MAPY, v.DISTANCE_TO_NEXT FROM MY_SCHEDULE s JOIN VISIT_ITEM v ON s.MY_SCHEDULE_ID = v.SCHEDULE_ID JOIN PLACE p ON v.PLACE_ID = p.PLACE_ID WHERE s.MY_SCHEDULE_ID = ? ORDER BY v.VISIT_ORDER ASC";
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, scheduleId);
			ResultSet rs = stmt.executeQuery();
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
			String sql = "INSERT INTO VISIT_ITEM (VISIT_ITEM_ID, VISIT_ORDER, SCHEDULE_TYPE, PLACE_ID, SCHEDULE_ID) "
					+ "VALUES (SEQ_VISIT_ITEM.NEXTVAL, ?, 'MY_SCH', ?, ?)";
			PreparedStatement stmt = conn.prepareStatement(sql);

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
			String sql = "DELETE FROM VISIT_ITEM WHERE VISIT_ITEM_ID = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);

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
			String sql = "INSERT INTO SCHEDULE_SHARE_USER (SHARE_ID, PERMISSION, MY_SCHEDULE_ID, SHARED_USER_ID) VALUES (SEQ_SCHEDULE_SHARE_USER.NEXTVAL, 'R', ?, ?)";
			PreparedStatement stmt = conn.prepareStatement(sql);

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
			String sql = "UPDATE SCHEDULE_SHARE_USER SET PERMISSION = ? WHERE MY_SCHEDULE_ID = ? AND SHARED_USER_ID = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);

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
		List<ColleagueVO> list;
		list = new ArrayList<ColleagueVO>();
		try {
			String sql = "SELECT U.USER_ID, U.NAME, U.EMAIL, SSU.PERMISSION FROM SCHEDULE_SHARE_USER SSU JOIN USERS U ON SSU.SHARED_USER_ID = U.USER_ID WHERE SSU.MY_SCHEDULE_ID = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, myScheduleId);
			ResultSet rs = stmt.executeQuery();
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
		String str = "";
		try {
			Statement stmtLock = conn.createStatement();
			stmtLock.execute("LOCK TABLE SCHEDULE_POST IN EXCLUSIVE MODE");

			String sqlSchedule = "INSERT INTO SCHEDULE_POST (POST_ID, TITLE, BUDGET_DETAILS, TODO_DETAILS, IS_ANONYMOUS, VIEW_COUNT, LIKE_COUNT, POSTED_AT, USER_ID) "
					+ "SELECT ('P' || LPAD(SEQ_SCHEDULE_POST.NEXTVAL, 3, '0')), TITLE, BUDGET_DETAILS, TODO_DETAILS, ?, 0, 0, SYSDATE, ? "
					+ "FROM MY_SCHEDULE WHERE MY_SCHEDULE_ID = ?";
			PreparedStatement stmtSchedule = conn.prepareStatement(sqlSchedule);

			stmtSchedule.setInt(1, isAnonymous);
			stmtSchedule.setString(2, userId);
			stmtSchedule.setString(3, myScheduleId);

			stmtSchedule.executeUpdate();
			stmtSchedule.close();

			String sqlVisit = "INSERT INTO VISIT_ITEM (VISIT_ITEM_ID, VISIT_ORDER, DISTANCE_TO_NEXT, SCHEDULE_TYPE, PLACE_ID, SCHEDULE_ID) "
					+ "SELECT SEQ_VISIT_ITEM.NEXTVAL, VISIT_ORDER, DISTANCE_TO_NEXT, 'POST', PLACE_ID, ('P' || LPAD(SEQ_SCHEDULE_POST.CURRVAL, 3, '0')) "
					+ "FROM VISIT_ITEM WHERE SCHEDULE_ID = ?";
			PreparedStatement stmtVisit = conn.prepareStatement(sqlVisit);
			stmtVisit.setString(1, myScheduleId);

			stmtVisit.executeUpdate();
			stmtVisit.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return str;
	}
}
