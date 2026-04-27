package com.letsgo.place.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostScheduleDAO {
	private Connection conn;

	public PostScheduleDAO() throws Exception{
		conn = DBCP.getConnection();
	}
	
	public PostScheduleDAO(Connection conn) throws Exception{
		this.conn = conn;
	}
	
	
	public List<PostScheduleVO> getPostScheduleList(String keyword, String searchType, String sortType) {
		List<PostScheduleVO> tmp = new ArrayList<>();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT p.POST_ID, p.TITLE AS POST_TITLE, p.LIKE_COUNT, p.VIEW_COUNT, p.IS_ANONYMOUS, ");
		sql.append("u.NAME, ");
		sql.append("v.VISIT_ITEM_ID, v.VISIT_ORDER, v.SCHEDULE_TYPE, ");
		sql.append("pl.TITLE AS PLACE_TITLE, pl.addr1,pl.FIRST_IMAGE, pl.PLACE_TYPE ");
		sql.append("FROM SCHEDULE_POST p ");
		sql.append("JOIN VISIT_ITEM v ON p.POST_ID = v.SCHEDULE_ID ");
		sql.append("JOIN PLACE pl ON v.PLACE_ID = pl.PLACE_ID ");
		sql.append("JOIN USERS u ON u.user_id = p.user_id ");

		
		//게시물 일정 이름 or 플레이스 이름으로 검색하기
		if (keyword != null && !keyword.trim().isEmpty()) {
			if ("schedule".equals(searchType)) {
				sql.append("WHERE p.TITLE LIKE ? ");
			}else if("place".equals(searchType)){
				sql.append("WHERE p.POST_ID IN (");
				sql.append("SELECT v2.SCHEDULE_ID ");
				sql.append("FROM VISIT_ITEM v2 ");
				sql.append("JOIN PLACE pl2 ON v2.PLACE_ID = pl2.PLACE_ID ");
				sql.append("WHERE pl2.TITLE LIKE ? ");
				sql.append(")");
			}
		}
		
		//정렬(좋아요, 조회, 게시일) AND VISIT_ORDER(기본값)
		String standard = "p.posted_at ASC"; //게시일
		
		if ("like".equals(sortType)) { //좋아요
			standard = "p.like_count ASC";
//			sql.append("ORDER BY p.like_count ASC, v.VISIT_ORDER ASC");
		}else if("view".equals(sortType)){ //조회
			standard = "p.view_count ASC";
		}else if("latest".equals(sortType)){
			standard = "p.posted_at ASC";
		}
//			sql.append("ORDER BY p.view_count ASC, v.VISIT_ORDER ASC");
//		}else if(("latest".equals(sortType))) {
//			sql.append("ORDER BY p.posted_at ASC, v.VISIT_ORDER ASC");
//		}
		sql.append(" ORDER BY ");
		sql.append(standard);
		sql.append(", p.post_id ASC");
		sql.append(", v.VISIT_ORDER ASC");

		try {
			PreparedStatement stmt = conn.prepareStatement(sql.toString());
			int idx = 1;

			if (keyword != null && !keyword.trim().isEmpty()) {
				String searchKey = "%" + keyword + "%";
				stmt.setString(idx++, searchKey);
			}
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				tmp.add(new PostScheduleVO(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13)));
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tmp;
		}
	
		public List<PostScheduleVO> getUserPostScheduleList(String userId, String keyword, String searchType, String sortType) {
			List<PostScheduleVO> tmp = new ArrayList<>();

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT p.POST_ID, p.TITLE AS POST_TITLE, p.LIKE_COUNT, p.VIEW_COUNT, p.IS_ANONYMOUS, ");
			sql.append("v.VISIT_ITEM_ID, v.VISIT_ORDER, v.SCHEDULE_TYPE, ");
			sql.append("pl.TITLE AS PLACE_TITLE, pl.addr1, pl.FIRST_IMAGE, pl.PLACE_TYPE ");
			sql.append("FROM SCHEDULE_POST p ");
			sql.append("JOIN VISIT_ITEM v ON p.POST_ID = v.SCHEDULE_ID ");
			sql.append("JOIN PLACE pl ON v.PLACE_ID = pl.PLACE_ID ");
			sql.append("WHERE p.USER_ID = ? ");
			
			//게시물 일정 이름 or 플레이스 이름으로 검색하기
			if (keyword != null && !keyword.trim().isEmpty()) {
				if ("schedule".equals(searchType)) {
					sql.append("AND p.TITLE LIKE ? ");
				}else if("place".equals(searchType)){
					sql.append("AND p.POST_ID IN (");
					sql.append("SELECT v2.SCHEDULE_ID ");
					sql.append("FROM VISIT_ITEM v2 ");
					sql.append("JOIN PLACE pl2 ON v2.PLACE_ID = pl2.PLACE_ID ");
					sql.append("WHERE pl2.TITLE LIKE ? ");
					sql.append(")");
				}
			}
			
			//정렬(좋아요, 조회, 게시일) AND VISIT_ORDER(기본값)
			String standard = "p.posted_at ASC"; //게시일
			
			if ("like".equals(sortType)) { //좋아요
				standard = "p.like_count ASC";
//				sql.append("ORDER BY p.like_count ASC, v.VISIT_ORDER ASC");
			}else if("view".equals(sortType)){ //조회
				standard = "p.view_count ASC";
			}	
//				sql.append("ORDER BY p.view_count ASC, v.VISIT_ORDER ASC");
//			}else if(("latest".equals(sortType))) {
//				sql.append("ORDER BY p.posted_at ASC, v.VISIT_ORDER ASC");
//			}
			sql.append(" ORDER BY ");
			sql.append(standard);
			sql.append(", p.post_id ASC");
			sql.append(", v.VISIT_ORDER ASC");

			try {
				PreparedStatement stmt = conn.prepareStatement(sql.toString());
				int idx = 1;
				stmt.setString(idx++, userId);

				if (keyword != null && !keyword.trim().isEmpty()) {
					String searchKey = "%" + keyword + "%";
					stmt.setString(idx++, searchKey);
				}
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					tmp.add(new PostScheduleVO(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), "나", rs.getInt(6), rs.getInt(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12)));
				}
				rs.close();
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return tmp;
			}
		
		
	
		

		
		public boolean deletePostSchedule(String scheduleId) {
			boolean flag = false;
			try {
				Connection conn = DBCP.getConnection();

				String sql = "DELETE FROM visit_item WHERE schedule_id = ?";
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, scheduleId);
				stmt.executeUpdate();

				sql = "DELETE FROM schedule_post WHERE post_ID = ?";
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
		
		public boolean plusLike(String postId) {
			boolean flag = false;
			try {
				String sql = "UPDATE SCHEDULE_POST SET LIKE_COUNT = LIKE_COUNT + 1 WHERE POST_ID = ?";
				PreparedStatement stmt = conn.prepareStatement(sql);
				
				stmt.setString(1, postId);

				flag = (stmt.executeUpdate() == 1);

				stmt.close();
			} catch (Exception e) {

				e.printStackTrace();
			}

			return flag;
		}

	}

