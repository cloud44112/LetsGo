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
		assertEquals(예상_개수, list.size())
		assertNotNull(dao.getPostScheduleList("다래", "place", "view"));
	}
	
	@Test
	public void deletePostSchedule() {
		assertTrue(dao.deletePostSchedule("P002"));
	}


}
