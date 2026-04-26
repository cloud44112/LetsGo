package com.letsgo.place.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MyScheduleDAO2 {
	
	public MyScheduleDAO2() {
	}

	public List<MyScheduleVO> getMyScheduleList(String userId, String keyword, String sortType, String filterType) {
        List<MyScheduleVO> list = new ArrayList<>();


        StringBuilder sql = new StringBuilder();
        sql.append("SELECT s.MY_SCHEDULE_ID, s.TITLE AS SCH_TITLE, s.START_AT, s.IS_SHARED, ");
        sql.append("       v.VISIT_ITEM_ID, v.PLACE_ID, v.PLACE_TYPE, ");
        sql.append("       COALESCE(l.TITLE, r.TITLE, st.TITLE) AS PLACE_TITLE, ");
        sql.append("       COALESCE(l.ADDR1, r.ADDR1, st.ADDR1) AS PLACE_ADDR, ");
        sql.append("       COALESCE(l.FIRST_IMAGE, r.FIRST_IMAGE, st.FIRST_IMAGE) AS PLACE_IMG ");
        sql.append("FROM MY_SCHEDULE s ");
        sql.append("JOIN VISIT_ITEM v ON s.MY_SCHEDULE_ID = v.SCHEDULE_ID ");
        sql.append("LEFT JOIN LEISURE_SPORTS l ON v.PLACE_ID = l.LEISURE_SPORTS_NO AND v.PLACE_TYPE = 'L' ");
        sql.append("LEFT JOIN RESTAURANT r ON v.PLACE_ID = r.RESTAURANT_NO AND v.PLACE_TYPE = 'R' ");
        sql.append("LEFT JOIN STAY st ON v.PLACE_ID = st.STAY_NO AND v.PLACE_TYPE = 'S' ");
        sql.append("WHERE s.USER_ID = ? ");

        if ("shared".equals(filterType)) {
            sql.append("AND s.IS_SHARED = 1 ");
        }

        if (keyword != null && !keyword.trim().isEmpty()) {
            sql.append("AND (s.TITLE LIKE ? OR l.TITLE LIKE ? OR r.TITLE LIKE ? OR st.TITLE LIKE ?) ");
        }

        if ("name".equals(sortType)) {
            sql.append("ORDER BY s.TITLE ASC, v.VISIT_ORDER ASC");
        } else {
            sql.append("ORDER BY s.START_AT DESC, v.VISIT_ORDER ASC");
        }

        try (Connection conn = DBCP.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            int idx = 1;
            stmt.setString(idx++, userId);

            if (keyword != null && !keyword.trim().isEmpty()) {
                String searchKey = "%" + keyword + "%";
                stmt.setString(idx++, searchKey);
                stmt.setString(idx++, searchKey);
                stmt.setString(idx++, searchKey);
                stmt.setString(idx++, searchKey);
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(new MyScheduleVO(
                        rs.getString("MY_SCHEDULE_ID"),
                        rs.getString("SCH_TITLE"),
                        rs.getString("START_AT"),
                        rs.getString("IS_SHARED"),
                        rs.getString("VISIT_ITEM_ID"),
                        rs.getString("PLACE_ID"),
                        rs.getString("PLACE_TYPE"),
                        rs.getString("PLACE_TITLE"),
                        rs.getString("PLACE_ADDR"),
                        rs.getString("PLACE_IMG")
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
