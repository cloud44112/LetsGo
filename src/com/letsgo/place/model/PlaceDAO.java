package com.letsgo.place.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PlaceDAO {
	private Connection conn;
	 
	
	public PlaceDAO() throws Exception{
		conn = DBCP.getConnection();
	}
	
	public PlaceDAO(Connection conn) throws Exception{
		this.conn = conn;
	}
	
	
	public List<PlaceVO> getPlaceByTitle(String placeType, String title){
		List<PlaceVO> list = new ArrayList<>();
		
		try {
			String sql = "SELECT place_Id, title, first_image, addr1, mapx, mapy, like_count FROM place WHERE place_type=? AND title=?";
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, placeType);
			stmt.setString(2, title);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(new PlaceVO(rs.getString("place_id"), rs.getString("title"), rs.getString("addr1"), 
						rs.getString("mapx"), rs.getString("mapy"), rs.getString("first_image"), rs.getInt("like_count"), placeType));
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return list;
	}
	
	public List<PlaceVO> getPlaceByCategory(String placeType, String lclssystm3){
		List<PlaceVO> list = new ArrayList<>();
		
		try {
			String sql = "SELECT place_Id, title, first_image, addr1, mapx, mapy, like_count FROM place WHERE place_type=? AND lclssystm3=?";
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, placeType);
			stmt.setString(2, lclssystm3);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(new PlaceVO(rs.getString("place_id"), rs.getString("title"), rs.getString("addr1"), 
						rs.getString("mapx"), rs.getString("mapy"), rs.getString("first_image"), rs.getInt("like_count"), placeType));
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return list;
	}
	
	public List<PlaceVO> getPlaceOrderByLike(String placeType){
		List<PlaceVO> list = new ArrayList<>();
		
		try {
			String sql = "SELECT place_Id, title, first_image, addr1, mapx, mapy, like_count FROM place WHERE place_type=? ORDER BY like_count DESC";
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, placeType);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(new PlaceVO(rs.getString("place_id"), rs.getString("title"), rs.getString("addr1"), 
						rs.getString("mapx"), rs.getString("mapy"), rs.getString("first_image"), rs.getInt("like_count"), placeType));
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return list;
	}
	
	public List<PlaceVO> getPlaceOrderByTitle(String placeType){
		List<PlaceVO> list = new ArrayList<>();
		
		try {
			String sql = "SELECT place_Id, title, first_image, addr1, mapx, mapy, like_count FROM place WHERE place_type=? ORDER BY title ASC";
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, placeType);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(new PlaceVO(rs.getString("place_id"), rs.getString("title"), rs.getString("addr1"), 
						rs.getString("mapx"), rs.getString("mapy"), rs.getString("first_image"), rs.getInt("like_count"), placeType));
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return list;
	}
	
	public List<PlaceVO> getPlaceByAddr(String placeType, String addr){
		List<PlaceVO> list = new ArrayList<>();
		
		try {
			String sql = "SELECT place_Id, title, first_image, addr1, mapx, mapy, like_count FROM place WHERE place_type =? AND addr1 Like ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			String searchKeyword = "%" + addr + "%";
			stmt.setString(1, placeType);
			stmt.setString(2, searchKeyword);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(new PlaceVO(rs.getString("place_id"), rs.getString("title"), rs.getString("addr1"), 
						rs.getString("mapx"), rs.getString("mapy"), rs.getString("first_image"), rs.getInt("like_count"), placeType));
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return list;
	}
	
	public List<PlaceVO> getPlace(String placeType, String placeId){
		List<PlaceVO> list = new ArrayList<>();
		
		try {
			String sql = "SELECT title, addr1, mapx, mapy FROM place WHERE place_type=? AND place_Id=?";
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, placeType);
			stmt.setString(2, placeId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(new PlaceVO(placeId, rs.getString("title"), rs.getString("addr1"), 
						rs.getString("mapx"), rs.getString("mapy")));
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return list;
	}
	
	public int getPlaceCount(String placeType){
		int count=0;
		
		try {
			String sql = "SELECT COUNT(place_Id) FROM place WHERE place_type=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, placeType);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return count;
	}
	
	public int getPlaceLikeCount(String placeType, String placeId){
		int count=0;
		
		try {
			String sql = "SELECT like_count FROM place WHERE place_type=? AND place_Id=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, placeType);
			stmt.setString(2, placeId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt("like_count");
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return count;
	}
	
}
