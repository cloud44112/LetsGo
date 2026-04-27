package test.com.letsgo.place.model;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.letsgo.place.model.DBCP;
import com.letsgo.place.model.MyScheduleDAO;

public class TestMyScheduleDAO {
	private MyScheduleDAO dao;

	@Before
	public void daoImport() throws Exception {
		dao = new MyScheduleDAO();
	}

	// @Test
	public void getMyScheduleListTest() {
		assertNotNull(dao.getMyScheduleList("user01", "서대문", "title", false));
		assertNotNull(dao.getMyScheduleList("user01", "", "title", false));
		assertNotEquals(new ArrayList<MyScheduleDAO>(), dao.getMyScheduleList("user0241", "", "title", false));

	}

	// @Test
	public void deleteMyScheduleTest() throws Exception {

		assertTrue(dao.deleteMySchedule("user01", "SCH001"));
		// assertFalse(dao.deleteMySchedule("user01", "SCH001"));

	}

//	@Test
	public void setMyScheduleTest() throws Exception{

		String [] visitItemIds = {"4","5","6"};
		int [] visitOrders = {3,2,1};
		String [] distanceToNexts = {"64", "23", "35"};
		assertTrue(dao.setMySchedule(visitItemIds, visitOrders, distanceToNexts, "SCH002", "여의도 대탐방" , "26/05/15", "삼겹살 마구 먹기", "햄부기 사냥하기", "user01", 1));

	}
	
	@Test
	public void setTodoDetailTest() throws Exception {
		Connection conn = DBCP.getConnection();
		conn.setAutoCommit(false);
		assertTrue(dao.setTodoDetail("SCH002", "햄부기 사냥함부기"));

		conn.rollback();
		conn.setAutoCommit(true);
	}
//	@Test
	public void getTodoDetailTest() throws Exception {
		assertEquals("햄부기 사냥", dao.getTodoDetail("SCH002"));

	}
}
