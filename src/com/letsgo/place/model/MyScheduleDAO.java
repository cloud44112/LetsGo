package com.letsgo.place.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class MyScheduleDAO {
	public MyScheduleDAO(){}


	public List<MyScheduleVO> getMySchedule(String userId){
		List<MyScheduleVO> tmp = new ArrayList<MyScheduleVO>();
		try (Connection conn = DBCP.getConnection()) {
			List<MyScheduleVO> schedules = getScheduleHeaders(conn, userId);
			for (MyScheduleVO s : schedules) {
				List<String[]> visitItems = getVisitItems(conn, s.getMyScheduleId());
				for (String[] vi : visitItems) {
					String[] place = getPlaceInfo(conn, vi[1]);
					if (place != null) {
						tmp.add(new MyScheduleVO(
							s.getMyScheduleId(), s.getMyScheduleTitle(), s.getStartAt(), s.getIsShared(),
							vi[0], vi[1], vi[2],
							place[0], place[1], place[2]
						));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tmp;
	}

	private List<MyScheduleVO> getScheduleHeaders(Connection conn, String userId) throws Exception {
		List<MyScheduleVO> list = new ArrayList<MyScheduleVO>();
		String sql = "SELECT MY_SCHEDULE_ID, TITLE, START_AT, IS_SHARED "
				   + "FROM MY_SCHEDULE WHERE USER_ID = ? ORDER BY START_AT DESC";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, userId);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			list.add(new MyScheduleVO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
		}
		rs.close();
		stmt.close();
		return list;
	}

	private List<String[]> getVisitItems(Connection conn, String scheduleId) throws Exception {
		List<String[]> list = new ArrayList<String[]>();
		String sql = "SELECT VISIT_ITEM_ID, PLACE_ID, PLACE_TYPE "
				   + "FROM VISIT_ITEM WHERE SCHEDULE_ID = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, scheduleId);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			list.add(new String[]{ rs.getString(1), rs.getString(2), rs.getString(3) });
		}
		rs.close();
		stmt.close();
		return list;
	}

	private String[] getPlaceInfo(Connection conn, String placeId) throws Exception {
		String sql = "SELECT TITLE, ADDR1, FIRST_IMAGE "
				   + "FROM LEISURE_SPORTS WHERE LEISURE_SPORTS_NO = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, placeId);
		ResultSet rs = stmt.executeQuery();
		String[] result = null;
		if (rs.next()) {
			result = new String[]{ rs.getString(1), rs.getString(2), rs.getString(3) };
		}
		rs.close();
		stmt.close();
		return result;
	}
}
