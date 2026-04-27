package com.letsgo.place.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PostScheduleDAO {
	
	public void getPostScheduleList(String userId, String keyword, String sortType, String sharedFilter) {
//		//List<PostScheduleVO> tmp = new ArrayList<>();
//
//		StringBuilder sql = new StringBuilder();
//		sql.append("SELECT s.MY_SCHEDULE_ID, s.TITLE AS SCH_TITLE, s.START_AT, s.IS_SHARED, ");
//		sql.append("       p.TITLE AS PLACE_TITLE, p.ADDR1, p.FIRST_IMAGE, p.PLACE_TYPE ");
//		sql.append("FROM MY_SCHEDULE s ");
//		sql.append("JOIN VISIT_ITEM v ON s.MY_SCHEDULE_ID = v.SCHEDULE_ID ");
//		sql.append("JOIN PLACE p ON v.PLACE_ID = p.PLACE_ID ");
//		sql.append("WHERE s.USER_ID = ? ");
//
//		if ("shared".equals(sharedFilter)) {
//			sql.append("AND s.IS_SHARED = 1 ");
//		}
//
//		if (keyword != null && !keyword.trim().isEmpty()) {
//			sql.append("AND (s.TITLE LIKE ? OR p.TITLE LIKE ?) ");
//		}
//
//		if ("title".equals(sortType)) {
//			sql.append("ORDER BY s.TITLE ASC, v.VISIT_ORDER ASC");
//		} else {
//			sql.append("ORDER BY s.START_AT DESC, v.VISIT_ORDER ASC");
//		}
//
//		try {
//			Connection conn = DBCP.getConnection();
//			PreparedStatement stmt = conn.prepareStatement(sql.toString());
//
//			int idx = 1;
//			stmt.setString(idx++, userId);
//
//			if (keyword != null && !keyword.trim().isEmpty()) {
//				String searchKey = "%" + keyword + "%";
//				stmt.setString(idx++, searchKey);
//				stmt.setString(idx++, searchKey);
//			}
//			ResultSet rs = stmt.executeQuery();
//			while (rs.next()) {
//				tmp.add(new MyScheduleVO(rs.getString("MY_SCHEDULE_ID"), rs.getString("SCH_TITLE"),
//						rs.getString("START_AT"), rs.getString("IS_SHARED"), rs.getString("placeId"),
//						rs.getString("PLACE_TITLE"), rs.getString("ADDR1"), rs.getString("FIRST_IMAGE"),
//						rs.getString("PLACE_TYPE")));
//			}
//			stmt.close();
//			conn.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return tmp;
//	}
	}
	}
