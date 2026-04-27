package test.com.letsgo.place.model;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.letsgo.place.model.DBCP;
import com.letsgo.place.model.PostScheduleDAO;

public class TestPostScheduleDAO {
	private PostScheduleDAO dao;
	private Connection conn;
	
	@Before
	public void setup() throws Exception {
		conn = DBCP.getConnection();
		conn.setAutoCommit(false);
		dao = new PostScheduleDAO(conn);
	}
	
	@After
	public void tearDown() throws Exception {
		if (conn != null) {
			conn.rollback();
			conn.close();
		}
	}
	
	@Test
	public void getPostScheduleList() {
		assertNotNull(dao.getPostScheduleList("서울", "schedule", "like"));
		assertNotNull(dao.getPostScheduleList("다래", "place", "view"));
		assertNotNull(dao.getPostScheduleList("", "place", "view"));
		assertNotNull(dao.getPostScheduleList("", "schedule", "view"));
		assertNotNull(dao.getPostScheduleList("", "schedule", "latest"));
	}
	
//	@Test
//	public void deletePostSchedule() {
//		assertTrue(dao.deletePostSchedule("P002"));
//	}
	
	@Test
	public void plusLike() {
		assertTrue(dao.plusLike("P003"));
	}


}
