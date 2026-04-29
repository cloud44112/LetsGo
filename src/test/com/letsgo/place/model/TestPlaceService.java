package test.com.letsgo.place.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.letsgo.place.model.PlaceVO;
import com.letsgo.place.model.VisitItemVO;
import com.letsgo.place.service.PlaceService;
import com.letsgo.place.service.PostScheduleService;

public class TestPlaceService {
	
	private PlaceService service;

	@Before
	public void setup() throws Exception{
		service = new PlaceService();
	}
	
	//@Test
	public void getPlaceByTitleTest() {
		assertNotNull(service.getPlaceByTitle("LEISURE", "롤파크"));
		assertFalse(service.getPlaceByTitle("LEISURE", "롤파크").isEmpty());
		assertEquals(new ArrayList<PlaceVO>(), service.getPlaceByTitle("LEISURE", "디즈니"));
	}

	//@Test
	public void getPlaceByCategoryTest() {
		assertNotNull(service.getPlaceByCategory("LEISURE", "VE100100"));
		assertFalse(service.getPlaceByCategory("LEISURE", "VE100100").isEmpty());
		assertEquals(new ArrayList<PlaceVO>(), service.getPlaceByCategory("12", "N"));
	}

	//@Test
	public void getPlaceOrderByLikeTest() {
		assertNotNull(service.getPlaceOrderByLike("LEISURE"));
		assertFalse(service.getPlaceOrderByLike("LEISURE").isEmpty());
		assertEquals(new ArrayList<PlaceVO>(), service.getPlaceOrderByLike("trip"));
	}

	//@Test
	public void getPlaceOrderByTitleTest() {
		assertNotNull(service.getPlaceOrderByTitle("LEISURE"));
		assertFalse(service.getPlaceOrderByTitle("LEISURE").isEmpty());
		assertEquals(new ArrayList<PlaceVO>(), service.getPlaceOrderByTitle("trip"));
	}

	//@Test
	public void getPlaceByAddrTest() {
		assertNotNull(service.getPlaceByAddr("LEISURE", "서울"));
		assertFalse(service.getPlaceByAddr("LEISURE", "서울").isEmpty());
		assertEquals(new ArrayList<PlaceVO>(), service.getPlaceByAddr("LEISURE", "도쿄"));
	}

	//@Test
	public void getPlaceTest() {
		assertNotNull(service.getPlace("9"));
		assertEquals(new ArrayList<PlaceVO>(), service.getPlace("100"));
	}

	//@Test
	public void getPlaceCountTest() {
		assertTrue(service.getPlaceCount("LEISURE") > 0);
		assertEquals(0, service.getPlaceCount("trip"));
	}

	//@Test
	public void setPlaceLikeCountTest() {
		int beforeLikeCount = service.getPlaceLikeCount("LEISURE", "9");
		assertTrue(service.setPlaceLikeCount("9"));
		assertEquals(beforeLikeCount + 1, service.getPlaceLikeCount("LEISURE", "9"));
		assertFalse(service.setPlaceLikeCount("100"));
	}

	//@Test
	public void getPlaceLikeCountTest() {
		assertTrue(service.getPlaceLikeCount("LEISURE", "9") >= 0);
		assertEquals(0, service.getPlaceLikeCount("LEISURE", "100"));
	}
	
	//@Test
	public void getPlacesTest() {
		List<PlaceVO> places = service.getPlaces();
		assertNotNull(places);
		assertFalse(places.isEmpty());
		PlaceVO first = places.get(0);
		assertNotNull(first.getTitle());
		assertNotNull(first.getAddr1());
		assertNotNull(first.getMapx());
		assertNotNull(first.getMapy());
		System.out.println("첫 번째: " + first.getTitle() + " / " + first.getAddr1());
	}

	@Test
	public void insertVisitItemTest() {
		assertTrue(service.insertVisitItem(1, 500, "9", "SCH007", "LEISURE"));
		List<VisitItemVO> list = service.getVisitItemsByScheduleId("SCH007");
		assertNotNull(list);
		assertFalse(list.isEmpty());
	}
	
	//@Test
	public void getLeisurePlacesOrderByLikeDescTest() {
		assertNotNull(service.getLeisurePlacesOrderByLikeDesc());
	    assertFalse(service.getLeisurePlacesOrderByLikeDesc().isEmpty());
	    List<PlaceVO> list = service.getLeisurePlacesOrderByLikeDesc();
	    for (int i = 0; i < list.size() - 1; i++) {
	        assertTrue(list.get(i).getLikeCount() >= list.get(i + 1).getLikeCount());
	    }
	}
	
	//@Test
	public void setCountingTest() {
		boolean result = service.setCounting("9");
		assertTrue(result);
		System.out.println("좋아요 카운트 결과: " + result);
	}
	
	//@Test
	public void getPlaceByIdTest() {
		PlaceVO place = service.getPlaceById("1");
		assertNotNull(place);
		assertNotNull(place.getTitle());
		System.out.println("제목: " + place.getTitle());
		System.out.println("주소: " + place.getAddr1());
		System.out.println("좌표: " + place.getMapx() + ", " + place.getMapy());
	}
	
	//@Test
	public void getVisitItemsByScheduleIdTest() {
		List<VisitItemVO> list = service.getVisitItemsByScheduleId("P023");
		assertNotNull(list);
		assertFalse(list.isEmpty());
		for (VisitItemVO vo : list) {
			System.out.println("순서: " + vo.getVisitOrder());
			System.out.println("장소ID: " + vo.getPlaceId());
			System.out.println("다음까지 거리: " + vo.getDistanceToNext());
		}
	}

	//@Test
	public void getPlaceByPlaceIdTest() {
		assertNotNull(service.getPlaceByPlaceId("9"));
	    assertNotNull(service.getPlaceByPlaceId("9").getTitle());
	    assertNull(service.getPlaceByPlaceId("100"));
	}
	
	//@Test
	public void searchPlacesTest() {
		assertNotNull(service.searchPlaces("LEISURE", null, "서울", "title"));
	    assertFalse(service.searchPlaces("LEISURE", null, "서울", "title").isEmpty());
	    assertEquals(new ArrayList<PlaceVO>(), service.searchPlaces("LEISURE", null, "도쿄", "title"));
	}
}
