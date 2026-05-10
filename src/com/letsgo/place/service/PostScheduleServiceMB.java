package com.letsgo.place.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.letsgo.place.DBCPMybatis.DBCPMybatis;
import com.letsgo.place.MyBatis.PostScheduleDAOMB;
import com.letsgo.place.model.MapScheduleVO;
import com.letsgo.place.model.PostScheduleDAOInterface;
import com.letsgo.place.model.PostScheduleVO;
import com.letsgo.place.model.RouteScheduleVO;

public class PostScheduleServiceMB {
	private PostScheduleDAOInterface dao;
	private SqlSession session;


	public PostScheduleServiceMB() throws Exception {
		session = DBCPMybatis.getSqlSession();
		dao = new PostScheduleDAOMB(session);
	}
	
    public void close() {
    	if (session != null) session.close();
    }

	public boolean deletePostScheduleAndVisitItem(String scheduleId) {
		try {
			dao.deleteVisitItem(scheduleId);
			dao.deletePostSchedule(scheduleId);
			session.commit();
			return true;
		} catch (Exception e) {
			session.rollback();
			return false;
		}
	}
	
	public List<PostScheduleVO> getPostScheduleList(String sortOrder, String keyword) {
		if (keyword == null || keyword.trim().isEmpty()) {
			switch (sortOrder) {
				case "like":  return getPostScheduleListLike();
				case "view":  return getPostScheduleListView();
				case "title": return getPostScheduleListTitle();
				default:      return getPostScheduleListLatest();
			}
		} else {
			switch (sortOrder) {
				case "like":  return getPostScheduleListLike(keyword);
				case "view":  return getPostScheduleListView(keyword);
				case "title": return getPostScheduleListTitle(keyword);
				default:      return getPostScheduleListLatest(keyword);
			}
		}
	}

	public List<PostScheduleVO> getUserPostScheduleList(String userId, String sortOrder, String keyword) {
		if (keyword == null || keyword.trim().isEmpty()) {
			switch (sortOrder) {
				case "like":  return getUserPostScheduleListLike(userId);
				case "view":  return getUserPostScheduleListView(userId);
				case "title": return getUserPostScheduleListTitle(userId);
				default:      return getUserPostScheduleListLatest(userId);
			}
		} else {
			switch (sortOrder) {
				case "like":  return getUserPostScheduleListLike(userId, keyword);
				case "view":  return getUserPostScheduleListView(userId, keyword);
				case "title": return getUserPostScheduleListTitle(userId, keyword);
				default:      return getUserPostScheduleListLatest(userId, keyword);
			}
		}
	}
	
	public List<PostScheduleVO> getPostScheduleListLike() {
		return dao.getPostScheduleListLike();
	}
	
	public List<PostScheduleVO> getPostScheduleListView() {
		return dao.getPostScheduleListView();
	}
	
	public List<PostScheduleVO> getPostScheduleListTitle() {
		return dao.getPostScheduleListTitle();
	}
	
	public List<PostScheduleVO> getPostScheduleListLatest() {
		return dao.getPostScheduleListLatest();
	}
	
	public List<PostScheduleVO> getPostScheduleListLike(String keyword) {
		return dao.getPostScheduleListLike(keyword);
	}
	
	public List<PostScheduleVO> getPostScheduleListView(String keyword) {
		return dao.getPostScheduleListView(keyword);
	}
	
	public List<PostScheduleVO> getPostScheduleListTitle(String keyword) {
		return dao.getPostScheduleListTitle(keyword);
	}
	
	public List<PostScheduleVO> getPostScheduleListLatest(String keyword) {
		return dao.getPostScheduleListLatest(keyword);
	}
	
	public List<PostScheduleVO> getUserPostScheduleListLike(String userId) {
		return dao.getUserPostScheduleListLike(userId);
	}
	
	public List<PostScheduleVO> getUserPostScheduleListView(String userId) {
		return dao.getUserPostScheduleListView(userId);
	}
	
	public List<PostScheduleVO> getUserPostScheduleListTitle(String userId) {
		return dao.getUserPostScheduleListTitle(userId);
	}
	
	public List<PostScheduleVO> getUserPostScheduleListLatest(String userId) {
		return dao.getUserPostScheduleListLatest(userId);
	}
	
	public List<PostScheduleVO> getUserPostScheduleListLike(String userId, String keyword) {
		return dao.getUserPostScheduleListLike(userId, keyword);
	}
	
	public List<PostScheduleVO> getUserPostScheduleListView(String userId, String keyword) {
		return dao.getUserPostScheduleListView(userId, keyword);
	}
	
	public List<PostScheduleVO> getUserPostScheduleListTitle(String userId, String keyword) {
		return dao.getUserPostScheduleListTitle(userId, keyword);
	}
	
	public List<PostScheduleVO> getUserPostScheduleListLatest(String userId, String keyword) {
		return dao.getUserPostScheduleListLatest(userId, keyword);
	}
	
	public String getBudgetDetail(String postId) {
		return dao.getBudgetDetail(postId);
	}
	
	public String getTodoDetail(String postId) {
		return dao.getTodoDetail(postId);
	}
	
	public List<RouteScheduleVO> getScheduleRoute(String postId) {
		return dao.getScheduleRoute(postId);
	}
	
	public List<MapScheduleVO> getMapSchedule(String postId) {
		return dao.getMapSchedule(postId);
	}
	
	public String getScheduleTitle (String postId) {
		return dao.getScheduleTitle(postId);
	}
	
	public int getLikeCount(String postId) {
		return dao.getLikeCount(postId);
	}
	
	public int getViewCount(String postId) {
		return dao.getViewCount(postId);
	}
	
	public boolean plusLike(String postId) {
		try {
	        boolean result = dao.plusLike(postId);
	        session.commit();
	        return result;
	    } catch (Exception e) {
	        session.rollback();
	        return false;
	    }
	}
	
	public boolean plusView(String postId) {
		try {
	        boolean result = dao.plusView(postId);
	        session.commit();
	        return result;
	    } catch (Exception e) {
	        session.rollback();
	        return false;
	    }
	}
	
	public String getUserId(String postId) {
		return dao.getUserId(postId);
	}
	

	public boolean addToMySchedule(String postId, String userId) {
		try {
			String title        = dao.getScheduleTitle(postId);
			String budgetDetail = dao.getBudgetDetail(postId);
			String todoDetail   = dao.getTodoDetail(postId);
			List<RouteScheduleVO> routes = dao.getScheduleRoute(postId);

			String myScheduleId = dao.copyToMySchedule(title, budgetDetail, todoDetail, userId);
			for (RouteScheduleVO route : routes) {
				dao.copyToVisitItem(myScheduleId, route);
			}
			session.commit();
			return true;
		} catch (Exception e) {
			session.rollback();
			return false;
		}
	}
}
