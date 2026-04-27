package test.com.letsgo.place.model;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.letsgo.place.model.ColleagueVO;
import com.letsgo.place.model.DBCP;
import com.letsgo.place.model.MapScheduleVO;
import com.letsgo.place.model.MyScheduleDAO;
import com.letsgo.place.model.RouteScheduleVO;

public class TestMyScheduleDAO {
	private MyScheduleDAO dao;
	private Connection conn;

	@Before
	public void setup() throws Exception {
		conn = DBCP.getConnection();
		conn.setAutoCommit(false);
		dao = new MyScheduleDAO(conn);
	}

	@After
	public void tearDown() throws Exception {
		if (conn != null) {
			conn.rollback();
			conn.close();
		}
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
		conn.rollback();
		// assertFalse(dao.deleteMySchedule("user01", "SCH001"));

	}
	
	@Test
    public void deleteMyScheduleListTest() {
        String[] ids = {"SCH001", "SCH002"};
        assertTrue(dao.deleteMyScheduleList("user01", ids));
    }
	
	// @Test
	public void setMyScheduleTest() throws Exception {

		String[] visitItemIds = { "4", "5", "6" };
		int[] visitOrders = { 3, 2, 1 };
		String[] distanceToNexts = { "64", "23", "35" };
		assertTrue(dao.setMySchedule(visitItemIds, visitOrders, distanceToNexts, "SCH002", "여의도 대탐방", "26/05/15",
				"삼겹살 마구 먹기", "햄부기 사냥하기", "user01", 1));

	}

	// @Test
	public void setTodoDetailTest() throws Exception {
		assertTrue(dao.setTodoDetail("SCH002", "햄부기 사냥함부기"));
	}

	// @Test
	public void getTodoDetailTest() throws Exception {
		assertEquals("햄부기 사냥", dao.getTodoDetail("SCH002"));

	}

	@Test
	public void getScheduleRouteTest() throws Exception {
		assertEquals(new ArrayList<RouteScheduleVO>(), dao.getScheduleRoute("user01", "SCH001"));
	}

	@Test
	public void getMapScheduleVOTest() throws Exception {

		List<MapScheduleVO> mapList = dao.getMapScheduleVO("SCH001");

		assertNotNull(mapList);
		assertNotNull(mapList.get(0).getTitle());
		assertNotNull(mapList.get(0).getMapX());
	}
	@Test
    public void addVisitItemtest() throws Exception {
        assertNotNull(dao.addVisitItem(5, "SCH002", "23"));
    }
	
	@Test
    public void deleteVisitItemByIdTest() {
        assertTrue(dao.deleteVisitItemById("VISIT001"));

    }

    @Test
    public void addCompanionTest() {
        assertTrue(dao.addCompanion("SCH001", "user02"));
    }

    @Test
    public void setCompanionPermissionTest() {
        assertTrue(dao.setCompanionPermission("SCH001", "user02", "W"));
    }

    @Test
    public void getCompanionListTest() {
        List<ColleagueVO> list = dao.getCompanionList("SCH001");
        assertNotNull(list);
    }

    @Test
    public void shareToPostTest() {
        String result = dao.shareToPost("SCH001", "user01", 0);
        assertEquals("", result);
    }
	
	
	
	
	
	
}
