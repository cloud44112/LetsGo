package com.letsgo.place.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class PostScheduleDAO {
	private Connection conn;

	
	public PostScheduleDAO(Connection conn){
		this.conn = conn;
	}
	
	
	public List<PostScheduleVO> getPostScheduleList(String keyword, String searchType, String sortType) {
		List<PostScheduleVO> tmp = null;
		
		StringBuilder sql = new StringBuilder();
		sql.append(PostScheduleQuery.GET_POST_SCHEDULE_LIST);
		
		if (keyword != null && !keyword.trim().isEmpty()) {
			if ("schedule".equals(searchType)) {
				sql.append(PostScheduleQuery.SEARCH_TYPE_SCHEDULE);
			}else if("place".equals(searchType)){
				sql.append(PostScheduleQuery.SEARCH_TYPE_PLACE);
			}
		}
		
		if ("like".equals(sortType)) {
			sql.append(PostScheduleQuery.ORDER_BY_LIKE);
		}else if("view".equals(sortType)){
			sql.append(PostScheduleQuery.ORDER_BY_VIEW);
		}else if("title".equals(sortType)) {
			sql.append(PostScheduleQuery.ORDER_BY_TITLE);
		}else if("latest".equals(sortType)) {
			sql.append(PostScheduleQuery.ORDER_BY_TITLE);
		}else {
			sql.append(PostScheduleQuery.ORDER_BY_LATEST);
		}


		try {
			PreparedStatement stmt = conn.prepareStatement(sql.toString());
			int idx = 1;

			if (keyword != null && !keyword.trim().isEmpty()) {
				String searchKey = "%" + keyword + "%";
				stmt.setString(idx++, searchKey);
			}
			ResultSet rs = stmt.executeQuery();
			tmp = new ArrayList<>();
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
			List<PostScheduleVO> tmp = null;

			StringBuilder sql = new StringBuilder();
			sql.append(PostScheduleQuery.GET_USER_POST_SCHEDULE_LIST);
		
			if (keyword != null && !keyword.trim().isEmpty()) {
				if ("schedule".equals(searchType)) {
					sql.append(PostScheduleQuery.SEARCH_TYPE_USER_SCHEDULE);
				}else if("place".equals(searchType)){
					sql.append(PostScheduleQuery.SEARCH_TYPE_USER_PLACE);
				}
			}
			
			
			if ("like".equals(sortType)) {
				sql.append(PostScheduleQuery.ORDER_BY_LIKE);
			}else if("view".equals(sortType)){
				sql.append(PostScheduleQuery.ORDER_BY_VIEW);
			}else if("latest".equals(sortType)){
				sql.append(PostScheduleQuery.ORDER_BY_LATEST);
			}else if("title".equals(sortType)) {
				sql.append(PostScheduleQuery.ORDER_BY_TITLE);
			}

			try {
				PreparedStatement stmt = conn.prepareStatement(sql.toString());
				int idx = 1;
				stmt.setString(idx++, userId);

				if (keyword != null && !keyword.trim().isEmpty()) {
					String searchKey = "%" + keyword + "%";
					stmt.setString(idx++, searchKey);
				}
				ResultSet rs = stmt.executeQuery();
				tmp = new ArrayList<>();
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
		
		public String getBudgetDetail(String postId) {
			String str = null;
			try {
				PreparedStatement stmt = conn.prepareStatement(PostScheduleQuery.GET_BUDGET_DETAIL);

				stmt.setString(1, postId);
				ResultSet rs = stmt.executeQuery();
				if (rs.next())
					str = rs.getString(1);

				stmt.close();
			} catch (Exception e) {

				e.printStackTrace();
			}

			return str;

		}
		public String getTodoDetail(String postId) {
			String str = null;
			try {
				PreparedStatement stmt = conn.prepareStatement(PostScheduleQuery.GET_TODO_DETAIL);

				stmt.setString(1, postId);
				ResultSet rs = stmt.executeQuery();
				if (rs.next())
					str = rs.getString(1);

				stmt.close();
			} catch (Exception e) {

				e.printStackTrace();
			}

			return str;

		}

		public List<RouteScheduleVO> getScheduleRoute(String postId) {
			List<RouteScheduleVO> list = null;
			
			try {
				PreparedStatement stmt = conn.prepareStatement(PostScheduleQuery.GET_SCHEDULE_ROUTE);

				stmt.setString(1, postId);
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

		public List<MapScheduleVO> getMapSchedule(String postId) {
			List<MapScheduleVO> list = null;
			
			try {
				PreparedStatement stmt = conn.prepareStatement(PostScheduleQuery.GET_MAP_SCHEDULE);

				stmt.setString(1, postId);
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
		
		public boolean deletePostSchedule(String scheduleId) {
			boolean flag = false;
			try {

				PreparedStatement stmtVisitItem = conn.prepareStatement(PostScheduleQuery.DELETE_VISIT_ITEM);
				PreparedStatement stmtSchedulePost = conn.prepareStatement(PostScheduleQuery.DELETE_SCHEDULE_POST);
				stmtVisitItem.setString(1, scheduleId);
				stmtVisitItem.executeUpdate();

				stmtSchedulePost.setString(1, scheduleId);
				stmtSchedulePost.executeUpdate();
		
				stmtVisitItem.close();
				stmtSchedulePost.close();
				flag = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return flag;

		}
		
		public boolean plusLike(String postId) {
			boolean flag = false;
			try {
				
				PreparedStatement stmt = conn.prepareStatement(PostScheduleQuery.INCREAMENT_LIKE);
				
				stmt.setString(1, postId);
				flag = (stmt.executeUpdate() == 1);
				stmt.close();
			} catch (Exception e) {

				e.printStackTrace();
			}

			return flag;
		}

	}

