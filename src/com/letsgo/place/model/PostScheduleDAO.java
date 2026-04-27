package com.letsgo.place.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PostScheduleDAO {
	//keyword 검색어
	//sortType 정렬종류
	
	
	public void getPostScheduleList(String userId, String keyword, String sortType, String sharedFilter) {
		List<PostScheduleVO> tmp = new ArrayList<>();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT p.POST_ID, p.TITLE AS POST_TITLE, p.LIKE_COUNT, p.VIEW_COUNT, p.IS_ANONYMOUS, p.USER_ID, ");
		sql.append("v.VISIT_ITEM_ID, v.VISIT_ORDER, v.SCHEDULE_TYPE, ");
		sql.append("pl.TITLE AS PLACE_TITLE, pl.FIRST_IMAGE, pl.PLACE_TYPE ");
		sql.append("FROM SCHEDULE_POST p ");
		sql.append("JOIN VISIT_ITEM v ON p.POST_ID = v.SCHEDULE_ID ");
		sql.append("JOIN PLACE pl ON v.PLACE_ID = pl.PLACE_ID ");

		if ("shared".equals(sharedFilter)) {
			sql.append("AND s.IS_SHARED = 1 ");
		}
		
		//게시물 이름으로 검색하기
		if (keyword != null && !keyword.trim().isEmpty()) {
			sql.append("WHERE p.TITLE LIKE ? ");
		}

		//정렬(좋아요, 조회, 게시일 순) AND VISIT_ORDER(기본값)
		if ("like".equals(sortType)) {
			sql.append("ORDER BY p.like_count ASC, v.VISIT_ORDER ASC");
			}else if("view".equals(sortType)){
				sql.append("ORDER BY p.view_count ASC, v.VISIT_ORDER ASC");
			}else if(("latest".equals(sortType))) {
				sql.append("ORDER BY p.posted_at ASC, v.VISIT_ORDER ASC");
			}
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
		
		public boolean deletePostSchedule(String userId, String scheduleId) {
			boolean flag = false;
			try {
				Connection conn = DBCP.getConnection();

				String sql = "DELETE FROM visit_item WHERE schedule_id = 'P004'";
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

	}
	}
