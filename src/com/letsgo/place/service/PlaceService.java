package com.letsgo.place.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.letsgo.place.model.DBCP;
import com.letsgo.place.model.PlaceDAO;
import com.letsgo.place.model.PlaceVO;

public class PlaceService {
	
	private PlaceDAO dao;
	
	private Connection conn;

	public PlaceService() throws ClassNotFoundException, SQLException {
		conn = DBCP.getConnection();
		dao = new PlaceDAO(conn);
	}
	
	public List<PlaceVO> getPlaceByTitle(String placeType, String title) {
		return dao.getPlaceByTitle(placeType, title);
	}
	
	public List<PlaceVO> getPlaceByCategory(String placeType, String lclssystm3) {
		return dao.getPlaceByCategory(placeType, lclssystm3);
	}
	
	public List<PlaceVO> getPlaceOrderByLike(String placeType) {
		return dao.getPlaceOrderByLike(placeType);
	}
	
	public List<PlaceVO> getPlaceOrderByTitle(String placeType) {
		return dao.getPlaceOrderByTitle(placeType);
	}
	
	public List<PlaceVO> getPlaceByAddr(String placeType, String addr) {
		return dao.getPlaceByAddr(placeType, addr);
	}
	
	public List<PlaceVO> getPlace(String placeType, String placeId) {
		return dao.getPlace(placeType, placeId);
	}
	
	public int getPlaceCount(String placeType) {
		return dao.getPlaceCount(placeType);
	}
	
	public boolean setPlaceLikeCount(String placeId) {
		boolean result = false;
		try {
			conn.setAutoCommit(false);
			result = dao.setPlaceLikeCount(placeId);
			if (result)
				conn.commit();
			else
				conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public int getPlaceLikeCount(String placeType, String placeId) {
		return dao.getPlaceLikeCount(placeType, placeId);
	}

}
