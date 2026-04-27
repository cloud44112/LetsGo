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
	public void daoImport(){
		dao = new MyScheduleDAO();
	}
	
	//@Test
	public void getMyScheduleListTest(){
		assertNotNull(dao.getMyScheduleList("user01","서대문" , "title", false));
		assertNotNull(dao.getMyScheduleList("user01","" , "title", false));
		assertNotEquals(new ArrayList<MyScheduleDAO>(), dao.getMyScheduleList("user0241","" , "title", false));
		
	}
	
	
	@Test
	public void deleteMyScheduleTest() throws Exception{
		Connection conn = DBCP.getConnection();
		conn.setAutoCommit(false);
		assertTrue(dao.deleteMySchedule("user01", "SCH001"));
//		assertFalse(dao.deleteMySchedule("user01", "SCH001"));
		conn.rollback();
		conn.setAutoCommit(true);
	}
}
