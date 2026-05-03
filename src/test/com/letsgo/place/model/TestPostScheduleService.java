package test.com.letsgo.place.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.letsgo.place.model.MapScheduleVO;
import com.letsgo.place.model.RouteScheduleVO;
import com.letsgo.place.service.PostScheduleService;


public class TestPostScheduleService {

	private PostScheduleService service;
	
	
	@Before
	public void setup() throws Exception{
		service = new PostScheduleService();
	}
	
	@Test
	public void deletePostSchedule() {
		assertTrue(service.deletePostSchedule("P002"));
	}
	
	@Test
	public void getPostScheduleList() {
		assertNotNull(service.getPostScheduleList("", "latest"));
	}
	
	@Test
	public void getUserPostScheduleList() {
		assertNotNull(service.getUserPostScheduleList("user01","", "latest"));
	}
	
	@Test
	public void getBudgetDetail() {
		assertEquals("삼겹살 마구 먹기", service.getBudgetDetail("P023"));
		//assertNotEquals("삼겹살 마구 먹기", service.getBudgetDetail("P023"));
	}
	
	@Test
	public void getTodoDetail() {
		assertEquals("햄부기 사냥함부기", service.getTodoDetail("P023"));
		//assertNotEquals("햄부기 사냥햄부기", service.getTodoDetail("P023"));
	}
	
	@Test
	public void getScheduleRoute() {
		//assertEquals(new ArrayList<RouteScheduleVO>(), service.getScheduleRoute("P023"));
		System.out.println(service.getScheduleRoute("P023"));
			
	}
	
	@Test
	public void getMapSchedule() {
		List<MapScheduleVO> mapList = service.getMapSchedule("P023");
		assertNotNull(mapList);
	}
	
	@Test
	public void plusLike() {
		assertTrue(service.plusLike("P023"));
	}

}
