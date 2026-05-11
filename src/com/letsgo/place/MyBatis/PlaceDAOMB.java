package com.letsgo.place.MyBatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.letsgo.place.DBCPMyBatis.DBCPMybatis;
import com.letsgo.place.model.PlaceDAOInterface;
import com.letsgo.place.model.PlaceVO;
import com.letsgo.place.model.VisitItemVO;

public class PlaceDAOMB implements PlaceDAOInterface {

	@Override
	public List<PlaceVO> getPlaceByTitle(String placeType, String title) {
		try (SqlSession session = DBCPMybatis.getSqlSessionFactory().openSession()) {
			Map<String, Object> params = new HashMap<>();
			params.put("placeType", placeType);
			params.put("title", title);
			return session.selectList("PlaceMapper.getPlaceByTitle", params);
		}
	}

	@Override
	public List<PlaceVO> getPlaceByCategory(String placeType, String lclssystm3) {
		try (SqlSession session = DBCPMybatis.getSqlSessionFactory().openSession()) {
			Map<String, Object> params = new HashMap<>();
			params.put("placeType", placeType);
			params.put("lclssystm3", lclssystm3);
			return session.selectList("PlaceMapper.getPlaceByCategory", params);
		}
	}

	@Override
	public List<PlaceVO> getPlaceOrderByLike(String placeType) {
		try (SqlSession session = DBCPMybatis.getSqlSessionFactory().openSession()) {
			Map<String, Object> params = new HashMap<>();
			params.put("placeType", placeType);
			return session.selectList("PlaceMapper.getPlaceOrderByLike", params);
		}
	}

	@Override
	public List<PlaceVO> getPlaceOrderByTitle(String placeType) {
		try (SqlSession session = DBCPMybatis.getSqlSessionFactory().openSession()) {
			Map<String, Object> params = new HashMap<>();
			params.put("placeType", placeType);
			return session.selectList("PlaceMapper.getPlaceOrderByTitle", params);
		}
	}

	@Override
	public List<PlaceVO> getPlaceByAddr(String placeType, String addr) {
		try (SqlSession session = DBCPMybatis.getSqlSessionFactory().openSession()) {
			Map<String, Object> params = new HashMap<>();
			params.put("placeType", placeType);
			params.put("addrPattern", "%" + addr + "%");
			return session.selectList("PlaceMapper.getPlaceByAddr", params);
		}
	}

	@Override
	public List<PlaceVO> getPlace(String placeId) {
		try (SqlSession session = DBCPMybatis.getSqlSessionFactory().openSession()) {
			return session.selectList("PlaceMapper.getPlaceByPlaceIdList", placeId);
		}
	}

	@Override
	public int getPlaceCount(String placeType) {
		try (SqlSession session = DBCPMybatis.getSqlSessionFactory().openSession()) {
			Integer count = session.selectOne("PlaceMapper.getPlaceCount", placeType);
			return count != null ? count : 0;
		}
	}

	@Override
	public boolean setPlaceLikeCount(String placeId) {
		try (SqlSession session = DBCPMybatis.getSqlSessionFactory().openSession()) {
			int rows = session.update("PlaceMapper.setPlaceLikeCount", placeId);
			if (rows > 0) {
				session.commit();
				return true;
			}
			return false;
		}
	}

	@Override
	public int getPlaceLikeCount(String placeType, String placeId) {
		try (SqlSession session = DBCPMybatis.getSqlSessionFactory().openSession()) {
			Map<String, Object> params = new HashMap<>();
			params.put("placeType", placeType);
			params.put("placeId", placeId);
			Integer count = session.selectOne("PlaceMapper.getPlaceLikeCount", params);
			return count != null ? count : 0;
		}
	}

	@Override
	public List<PlaceVO> getPlaces() {
		try (SqlSession session = DBCPMybatis.getSqlSessionFactory().openSession()) {
			return session.selectList("PlaceMapper.getPlaces");
		}
	}

	@Override
	public boolean insertVisitItem(int visitOrder, int distanceToNext, String placeId, String scheduleId,
			String scheduleType) {
		try (SqlSession session = DBCPMybatis.getSqlSessionFactory().openSession()) {
			Map<String, Object> params = new HashMap<>();
			params.put("visitOrder", visitOrder);
			params.put("distanceToNext", distanceToNext);
			params.put("placeId", placeId);
			params.put("scheduleId", scheduleId);
			params.put("scheduleType", scheduleType);
			int rows = session.insert("PlaceMapper.insertVisitItem", params);
			if (rows > 0) {
				session.commit();
				return true;
			}
			return false;
		}
	}

	@Override
	public List<PlaceVO> getLeisurePlacesOrderByLikeDesc() {
		try (SqlSession session = DBCPMybatis.getSqlSessionFactory().openSession()) {
			return session.selectList("PlaceMapper.getLeisurePlacesOrderByLikeDesc");
		}
	}

	@Override
	public List<PlaceVO> getLeisurePlaces() {
		try (SqlSession session = DBCPMybatis.getSqlSessionFactory().openSession()) {
			return session.selectList("PlaceMapper.getLeisurePlaces");
		}
	}

	@Override
	public boolean setCounting(String placeId) {
		try (SqlSession session = DBCPMybatis.getSqlSessionFactory().openSession()) {
			int rows = session.update("PlaceMapper.setCounting", placeId);
			if (rows > 0) {
				session.commit();
				return true;
			}
			return false;
		}
	}

	@Override
	public PlaceVO getPlaceById(String placeId) {
		try (SqlSession session = DBCPMybatis.getSqlSessionFactory().openSession()) {
			return session.selectOne("PlaceMapper.getPlaceById", placeId);
		}
	}

	@Override
	public List<VisitItemVO> getVisitItemsByScheduleId(String scheduleId) {
		try (SqlSession session = DBCPMybatis.getSqlSessionFactory().openSession()) {
			return session.selectList("PlaceMapper.getVisitItemsByScheduleId", scheduleId);
		}
	}

	@Override
	public List<PlaceVO> searchPlacesOrderByTitle(String placeType) {
		try (SqlSession session = DBCPMybatis.getSqlSessionFactory().openSession()) {
			Map<String, Object> params = new HashMap<>();
			params.put("placeType", placeType);
			return session.selectList("PlaceMapper.searchPlacesOrderByTitle", params);
		}
	}

	@Override
	public List<PlaceVO> searchPlacesOrderByLike(String placeType) {
		try (SqlSession session = DBCPMybatis.getSqlSessionFactory().openSession()) {
			Map<String, Object> params = new HashMap<>();
			params.put("placeType", placeType);
			return session.selectList("PlaceMapper.searchPlacesOrderByLike", params);
		}
	}

	@Override
	public List<PlaceVO> searchPlacesByCategoryOrderByTitle(String placeType, String category) {
		try (SqlSession session = DBCPMybatis.getSqlSessionFactory().openSession()) {
			Map<String, Object> params = new HashMap<>();
			params.put("placeType", placeType);
			params.put("category", category);
			return session.selectList("PlaceMapper.searchPlacesByCategoryOrderByTitle", params);
		}
	}

	@Override
	public List<PlaceVO> searchPlacesByCategoryOrderByLike(String placeType, String category) {
		try (SqlSession session = DBCPMybatis.getSqlSessionFactory().openSession()) {
			Map<String, Object> params = new HashMap<>();
			params.put("placeType", placeType);
			params.put("category", category);
			return session.selectList("PlaceMapper.searchPlacesByCategoryOrderByLike", params);
		}
	}

	@Override
	public List<PlaceVO> searchPlacesByKeywordOrderByTitle(String placeType, String keyword) {
		try (SqlSession session = DBCPMybatis.getSqlSessionFactory().openSession()) {
			Map<String, Object> params = new HashMap<>();
			params.put("placeType", placeType);
			params.put("keywordPattern", "%" + keyword + "%");
			return session.selectList("PlaceMapper.searchPlacesByKeywordOrderByTitle", params);
		}
	}

	@Override
	public List<PlaceVO> searchPlacesByKeywordOrderByLike(String placeType, String keyword) {
		try (SqlSession session = DBCPMybatis.getSqlSessionFactory().openSession()) {
			Map<String, Object> params = new HashMap<>();
			params.put("placeType", placeType);
			params.put("keywordPattern", "%" + keyword + "%");
			return session.selectList("PlaceMapper.searchPlacesByKeywordOrderByLike", params);
		}
	}

	@Override
	public List<PlaceVO> searchPlacesByCategoryAndKeywordOrderByTitle(String placeType, String category, String keyword) {
		try (SqlSession session = DBCPMybatis.getSqlSessionFactory().openSession()) {
			Map<String, Object> params = new HashMap<>();
			params.put("placeType", placeType);
			params.put("category", category);
			params.put("keywordPattern", "%" + keyword + "%");
			return session.selectList("PlaceMapper.searchPlacesByCategoryAndKeywordOrderByTitle", params);
		}
	}

	@Override
	public List<PlaceVO> searchPlacesByCategoryAndKeywordOrderByLike(String placeType, String category, String keyword) {
		try (SqlSession session = DBCPMybatis.getSqlSessionFactory().openSession()) {
			Map<String, Object> params = new HashMap<>();
			params.put("placeType", placeType);
			params.put("category", category);
			params.put("keywordPattern", "%" + keyword + "%");
			return session.selectList("PlaceMapper.searchPlacesByCategoryAndKeywordOrderByLike", params);
		}
	}

	@Override
	public PlaceVO getPlaceByPlaceId(String placeId) {
		try (SqlSession session = DBCPMybatis.getSqlSessionFactory().openSession()) {
			return session.selectOne("PlaceMapper.getPlaceByPlaceId", placeId);
		}
	}

}
