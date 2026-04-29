package test.com.letsgo.place.model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.letsgo.place.model.PlaceVO;
import com.letsgo.place.service.PlaceService;
import com.letsgo.place.service.PostScheduleService;

public class TestPlaceService {
	
	private PlaceService service;

	@Before
	public void setup() throws Exception{
		service = new PlaceService();
	}
	
	@Test
	public void getPlaceByTitleTest() {
		assertNotNull(service.getPlaceByTitle("LEISURE", "롤파크"));
		assertFalse(service.getPlaceByTitle("LEISURE", "롤파크").isEmpty());
		assertEquals(new ArrayList<PlaceVO>(), service.getPlaceByTitle("LEISURE", "디즈니"));
	}

	@Test
	public void getPlaceByCategoryTest() {
		assertNotNull(service.getPlaceByCategory("LEISURE", "VE100100"));
		assertFalse(service.getPlaceByCategory("LEISURE", "VE100100").isEmpty());
		assertEquals(new ArrayList<PlaceVO>(), service.getPlaceByCategory("12", "N"));
	}

	@Test
	public void getPlaceOrderByLikeTest() {
		assertNotNull(service.getPlaceOrderByLike("LEISURE"));
		assertFalse(service.getPlaceOrderByLike("LEISURE").isEmpty());
		assertEquals(new ArrayList<PlaceVO>(), service.getPlaceOrderByLike("trip"));
	}

	@Test
	public void getPlaceOrderByTitleTest() {
		assertNotNull(service.getPlaceOrderByTitle("LEISURE"));
		assertFalse(service.getPlaceOrderByTitle("LEISURE").isEmpty());
		assertEquals(new ArrayList<PlaceVO>(), service.getPlaceOrderByTitle("trip"));
	}

	@Test
	public void getPlaceByAddrTest() {
		assertNotNull(service.getPlaceByAddr("LEISURE", "서울"));
		assertFalse(service.getPlaceByAddr("LEISURE", "서울").isEmpty());
		assertEquals(new ArrayList<PlaceVO>(), service.getPlaceByAddr("LEISURE", "도쿄"));
	}

	@Test
	public void getPlaceTest() {
		assertNotNull(service.getPlace("9"));
		assertEquals(new ArrayList<PlaceVO>(), service.getPlace("100"));
	}

	@Test
	public void getPlaceCountTest() {
		assertTrue(service.getPlaceCount("LEISURE") > 0);
		assertEquals(0, service.getPlaceCount("trip"));
	}

	@Test
	public void setPlaceLikeCountTest() {
		int beforeLikeCount = service.getPlaceLikeCount("LEISURE", "9");
		assertTrue(service.setPlaceLikeCount("9"));
		assertEquals(beforeLikeCount + 1, service.getPlaceLikeCount("LEISURE", "9"));
		assertFalse(service.setPlaceLikeCount("100"));
	}

	@Test
	public void getPlaceLikeCountTest() {
		assertTrue(service.getPlaceLikeCount("LEISURE", "9") >= 0);
		assertEquals(0, service.getPlaceLikeCount("LEISURE", "100"));
	}
	

}
