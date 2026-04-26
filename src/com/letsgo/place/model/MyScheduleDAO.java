package com.letsgo.place.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class MyScheduleDAO {
	public MyScheduleDAO(){}
	
	public String getMySchedule(String userId){
		String scheduleId = null;
		try {
			Connection conn = DBCP.getConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT MY_SCHEDULE_ID, TITLE, START_AT, IS_SHARED FROM MY_SCHEDULE WHERE USER_ID = ? ORDER BY START_AT DESC");
			stmt.setString(1, userId);
			
			ResultSet rs = stmt.executeQuery();
			if (rs.next()){
				
			}
			
			
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return scheduleId;
	}
}
