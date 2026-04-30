package com.letsgo.place.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.letsgo.place.model.DBCP;
import com.letsgo.place.model.PlaceDAO;
import com.letsgo.place.model.PlaceVO;
import com.letsgo.place.model.VisitItemVO;

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
	
	public List<PlaceVO> getPlace(String placeId) {
		return dao.getPlace(placeId);
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
	
	public List<PlaceVO> getPlaces() {
		return dao.getPlaces();
	}
	
	public boolean insertVisitItem(int visitOrder, int distanceToNext,
            String placeId, String scheduleId, String scheduleType) {
		boolean result = false;
		try {
			conn.setAutoCommit(false);
			result = dao.insertVisitItem(visitOrder, distanceToNext, placeId, scheduleId, scheduleType);
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
	//좋아요 정렬
	public List<PlaceVO> getLeisurePlacesOrderByLikeDesc() {
		return dao.getLeisurePlacesOrderByLikeDesc();
	}
	//레저만 보여주기
	public List<PlaceVO> getLeisurePlaces(){
		return dao.getLeisurePlaces();
	}
	
	public boolean setCounting(String postId) {
		boolean result = false;
		try {
			conn.setAutoCommit(false);
			result = dao.setCounting(postId);
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
	
	public PlaceVO getPlaceById(String placeId) {
		return dao.getPlaceById(placeId);
	}
	
	public List<VisitItemVO> getVisitItemsByScheduleId(String scheduleId) {
		return dao.getVisitItemsByScheduleId(scheduleId);
	}
	
	public PlaceVO getPlaceByPlaceId(String placeId) {
		return dao.getPlaceByPlaceId(placeId);
	}
	
	public List<PlaceVO> searchPlaces(String placeType, String category, String keyword, String sortType) {
		return dao.searchPlaces(placeType, category, keyword, sortType);
	}
	
	

}
