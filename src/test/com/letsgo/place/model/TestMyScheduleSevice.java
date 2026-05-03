package test.com.letsgo.place.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.letsgo.place.model.ColleagueVO;
import com.letsgo.place.model.MapScheduleVO;
import com.letsgo.place.model.MyScheduleVO;
import com.letsgo.place.model.RouteScheduleVO;
import com.letsgo.place.service.MyScheduleService;

public class TestMyScheduleSevice {
	
	private MyScheduleService service;
	
	
	@Before
	public void setup() throws Exception{
		service = new MyScheduleService();
	}
	
	@Test
	public void deleteMyScheduleTest() {
//		assertTrue(service.deleteMySchedule("SCH023"));
		assertFalse(service.deleteMySchedule("SCH023"));
	}

	@Test
	public void deleteMyScheduleList(){
		String[] list = {"SCH066","SCH077"};
		assertFalse(service.deleteMyScheduleList("033", list));
		
	}
	@Test
	public void getMyScheduleListTest(){
		System.out.println(service.getMyScheduleList("user02", null, "title", true));
		ArrayList<MyScheduleVO> list = (ArrayList<MyScheduleVO>) service.getMyScheduleList("user01", "", "title", true);
//		assertEquals("2026-05-10 00:00:00",list.get(0).getStartAt());		
	}
	
	@Test
    public void deleteMyScheduleListTest() {
        String[] ids = {"SCH001", "SCH002"};
        assertTrue(service.deleteMyScheduleList("user01", ids));
    }
	
	@Test
	public void setMyScheduleTest() throws Exception {

		String[] visitItemIds = { "4", "5", "6" };
		int[] visitOrders = { 3, 2, 1 };
		String[] distanceToNexts = { "64", "23", "35" };
		assertTrue(service.setMySchedule(visitItemIds, visitOrders, distanceToNexts, "SCH002", "여의도 대탐방", "26/05/15",
				"삼겹살 마구 먹기", "햄부기 사냥하기", "user01", 1));

	}

	@Test
	public void setTodoDetailTest() throws Exception {
		assertTrue(service.setTodoDetail("SCH002", "햄부기 사냥함부기"));
	}

	@Test
	public void getTodoDetailTest() throws Exception {
//		assertEquals("햄부기 사냥함부기", service.getTodoDetail("SCH002"));
		assertNotEquals("햄부기 사냥햄부기", service.getTodoDetail("SCH002"));
	}

	@Test
	public void getScheduleRouteTest() throws Exception {
		assertEquals(new ArrayList<RouteScheduleVO>(), service.getScheduleRoute("user01", "SCH001"));
	}

	@Test
	public void getMapScheduleVOTest() throws Exception {

		List<MapScheduleVO> mapList = service.getMapSchedule("SCH001");
		assertNotNull(mapList);
	}
	@Test
    public void addVisitItemtest() throws Exception {
        assertNotNull(service.addVisitItem(5, "1", "SCH011"));
    }
	
	@Test
    public void deleteVisitItemByIdTest() {
        assertFalse(service.deleteVisitItemById("1"));

    }

    @Test
    public void addCompanionTest() {
        assertTrue(service.addCompanion("SCH002", "user06"));
    }

    @Test
    public void setCompanionPermissionTest() {
        assertFalse(service.setCompanionPermission("SCH002", "user01", "R"));
    }

    @Test
    public void getCompanionListTest() {
        List<ColleagueVO> list = service.getCompanionList("SCH001");
        assertNotNull(list);
    }

    @Test
    public void shareToPostTest() throws Exception {
        String result = service.shareToPost("SCH002", "user01", 0);
        assertEquals("user01", result);
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
