package test.com.letsgo.place.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

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
}
