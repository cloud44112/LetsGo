package com.letsgo.place.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

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

	public List<RouteScheduleVO> getScheduleRoute(String userId, String scheduleId) {
		return dao.getScheduleRoute(userId, scheduleId);
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
		String result = "";
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
}
