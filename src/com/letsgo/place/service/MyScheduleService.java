package com.letsgo.place.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.letsgo.place.model.ColleagueVO;
import com.letsgo.place.model.DBCP;
import com.letsgo.place.model.MapScheduleVO;
import com.letsgo.place.model.MyScheduleDAO;
import com.letsgo.place.model.MyScheduleVO;
import com.letsgo.place.model.RouteScheduleVO;

public class MyScheduleService {

	private MyScheduleDAO dao;

	private Connection conn;

	public MyScheduleService() throws ClassNotFoundException, SQLException {
		conn = DBCP.getConnection();
		dao = new MyScheduleDAO(conn);
	}

	// JTA - Spring AOP 개념
	public boolean deleteMySchedule(String scheduleId) {
		boolean result = false;
		try {
			conn.setAutoCommit(false);
			result = dao.deleteMySchedule(scheduleId);
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

	public boolean deleteMyScheduleList(String userId, String[] scheduleIds) {
		boolean result = false;
		try {
			conn.setAutoCommit(false);
			result = dao.deleteMyScheduleList(userId, scheduleIds);
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

	public List<MyScheduleVO> getMyScheduleList(String userId, String keyword, String sortType, boolean sharedFilter) {
		return dao.getMyScheduleList(userId, keyword, sortType, sharedFilter);
	}

	public boolean setMySchedule(String[] visitItemId, int[] visitOrder, String[] distanceToNext, String scheduleId,
			String scheduleTitle, String startAt, String budgetDetail, String todoDetail, String userId, int isShared) {
		boolean result = false;
		try {
			conn.setAutoCommit(false);
			result = dao.setMySchedule(visitItemId, visitOrder, distanceToNext, scheduleId, scheduleTitle, startAt,
					budgetDetail, todoDetail, userId, isShared);
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

	public boolean updateVisitOrders(String[] visitItemIds, int[] visitOrders, String[] distances) {
		boolean result = false;
		try {
			conn.setAutoCommit(false);
			result = dao.updateVisitOrders(visitItemIds, visitOrders, distances);
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

	public boolean setMyScheduleTitle(String title, String myScheduleId, String userId) {
		boolean result = false;
		try {
			conn.setAutoCommit(false);
			result = dao.setMyScheduleTitle(title, myScheduleId, userId);
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

	public String getStartAt(String scheduleId) {
		return dao.getStartAt(scheduleId);
	}

	public boolean setStartAt(String scheduleId, String startAt, String userId) {
		boolean result = false;
		try {
			conn.setAutoCommit(false);
			result = dao.setStartAt(scheduleId, startAt, userId);
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

	public boolean setTodoDetail(String scheduleId, String todoDetail) {
		boolean result = false;
		try {
			conn.setAutoCommit(false);
			result = dao.setTodoDetail(scheduleId, todoDetail);
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

	public String getTodoDetail(String scheduleId) {
		return dao.getTodoDetail(scheduleId);
	}

	public List<RouteScheduleVO> getScheduleRoute(String scheduleId) {
		return dao.getScheduleRoute(scheduleId);
	}

	public List<MapScheduleVO> getMapSchedule(String scheduleId) {
		return dao.getMapSchedule(scheduleId);
	}

	public boolean addVisitItem(int visitOrder, String placeId, String scheduleId) {
		boolean result = false;
		try {
			conn.setAutoCommit(false);
			result = dao.addVisitItem(visitOrder, placeId, scheduleId);
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

	public boolean deleteVisitItemById(String visitItemId) {
		boolean result = false;
		try {
			conn.setAutoCommit(false);
			result = dao.deleteVisitItemById(visitItemId);
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

	public boolean addCompanion(String myScheduleId, String sharedUserId) {
		boolean result = false;
		try {
			conn.setAutoCommit(false);
			result = dao.addCompanion(myScheduleId, sharedUserId);
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

	public boolean setCompanionPermission(String myScheduleId, String sharedUserId, String permission) {
		boolean result = false;
		try {
			conn.setAutoCommit(false);
			result = dao.setCompanionPermission(myScheduleId, sharedUserId, permission);
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

	public List<ColleagueVO> getCompanionList(String myScheduleId) {
		return dao.getCompanionList(myScheduleId);
	}

	public String shareToPost(String myScheduleId, String userId, int isAnonymous) {
		String result = null;
		try {
			conn.setAutoCommit(false);
			result = dao.shareToPost(myScheduleId, userId, isAnonymous);
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
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

	public String getBudgetDetail(String scheduleId) {
		String result = dao.getBudgetDetail(scheduleId);
		return result;
	}
	
	public boolean setBudgetDetail(String scheduleId, String budgetDetail) {
		boolean flag = dao.setBudgetDetail(scheduleId, budgetDetail);
		return flag;
	}

	public String getScheduleTitle(String scheduleId) {
		return dao.getScheduleTitle(scheduleId);
	}

	public List<String[]> listMyScheduleIdAndTitle(String userId) {
		return dao.listMyScheduleIdAndTitle(userId);
	}

	public static class NewScheduleFromCartResult {
		private final String scheduleId;
		private final List<String> addedPlaceIds;

		public NewScheduleFromCartResult(String scheduleId, List<String> addedPlaceIds) {
			this.scheduleId = scheduleId;
			this.addedPlaceIds = addedPlaceIds;
		}

		public String getScheduleId() {
			return scheduleId;
		}

		public List<String> getAddedPlaceIds() {
			return addedPlaceIds;
		}
	}

	public NewScheduleFromCartResult createNewScheduleFromCartPlaces(String userId, List<String> placeIds, String title) {
		List<String> ordered = new ArrayList<>();
		if (placeIds != null) {
			for (String pid : placeIds) {
				if (pid != null) {
					String t = pid.trim();
					if (!t.isEmpty()) {
						ordered.add(t);
					}
				}
			}
		}
		if (ordered.isEmpty()) {
			return null;
		}
		String scheduleTitle = (title != null && !title.trim().isEmpty()) ? title.trim() : "카트에서 만든 여행";
		try {
			conn.setAutoCommit(false);
			String newId = dao.allocateNextMyScheduleId();
			if (newId == null || !dao.insertMyScheduleRow(newId, scheduleTitle, userId)) {
				conn.rollback();
				return null;
			}
			int visitOrder = 0;
			for (String placeId : ordered) {
				visitOrder++;
				if (!dao.addVisitItem(visitOrder, placeId, newId)) {
					conn.rollback();
					return null;
				}
			}
			conn.commit();
			return new NewScheduleFromCartResult(newId, new ArrayList<>(ordered));
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			e.printStackTrace();
			return null;
		} finally {
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	
	public List<String> addCartPlacesToSchedule(String userId, String scheduleId, List<String> placeIds) {
		List<String> addedIds = new ArrayList<>();
		if (scheduleId == null || scheduleId.trim().isEmpty() || placeIds == null || placeIds.isEmpty()) {
			return addedIds;
		}
		try {
			conn.setAutoCommit(false);
			if (!dao.isScheduleOwnedByUser(scheduleId, userId)) {
				conn.rollback();
				return null;
			}
			List<RouteScheduleVO> route = dao.getScheduleRoute(scheduleId);
			Set<String> existing = new HashSet<>();
			int maxOrder = 0;
			if (route != null) {
				for (RouteScheduleVO r : route) {
					if (r.getPlaceId() != null) {
						existing.add(r.getPlaceId());
					}
					try {
						int o = Integer.parseInt(r.getVisitOrder());
						if (o > maxOrder) {
							maxOrder = o;
						}
					} catch (NumberFormatException ignored) {
					}
				}
			}
			int nextOrder = maxOrder;
			for (String placeId : placeIds) {
				if (placeId == null || placeId.trim().isEmpty()) {
					continue;
				}
				if (existing.contains(placeId)) {
					continue;
				}
				nextOrder++;
				if (!dao.addVisitItem(nextOrder, placeId, scheduleId)) {
					conn.rollback();
					return null;
				}
				existing.add(placeId);
				addedIds.add(placeId);
			}
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			e.printStackTrace();
			return null;
		} finally {
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return addedIds;
	}
	
}
