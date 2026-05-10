package test.com.letsgo.place.MyBatis;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.letsgo.place.MyBatis.PlaceDAOMB;
import com.letsgo.place.model.PlaceVO;
import com.letsgo.place.model.VisitItemVO;

public class PlaceDAOMBTest {

	private final PlaceDAOMB dao = new PlaceDAOMB();

	// 제목으로 장소 조회
	@Test
	public void getPlaceByTitle() {
		List<PlaceVO> list = dao.getPlaceByTitle("LEISURE", "롤파크");
		assertNotNull(list);
		assertFalse(list.isEmpty());
		assertEquals("9", list.get(0).getPlaceId());
		assertEquals("롤파크", list.get(0).getTitle());
		assertEquals(new ArrayList<PlaceVO>(), dao.getPlaceByTitle("LEISURE", "디즈니"));
	}

	// 카테고리로 장소 조회
	@Test
	public void getPlaceByCategory() {
		List<PlaceVO> list = dao.getPlaceByCategory("LEISURE", "VE100100");
		assertNotNull(list);
		assertFalse(list.isEmpty());
		assertEquals(new ArrayList<PlaceVO>(), dao.getPlaceByCategory("12", "N"));
	}

	// 좋아요순 조회
	@Test
	public void getPlaceOrderByLike() {
		assertNotNull(dao.getPlaceOrderByLike("LEISURE"));
		assertFalse(dao.getPlaceOrderByLike("LEISURE").isEmpty());
		assertEquals(new ArrayList<PlaceVO>(), dao.getPlaceOrderByLike("TRIP"));
	}

	// 제목순 조회
	@Test
	public void getPlaceOrderByTitle() {
		assertNotNull(dao.getPlaceOrderByTitle("LEISURE"));
		assertFalse(dao.getPlaceOrderByTitle("LEISURE").isEmpty());
		assertEquals(new ArrayList<PlaceVO>(), dao.getPlaceOrderByTitle("TRIP"));
	}

	// 지역(주소)으로 장소 조회
	@Test
	public void getPlaceByAddr() {
		assertNotNull(dao.getPlaceByAddr("LEISURE", "서울"));
		assertFalse(dao.getPlaceByAddr("LEISURE", "서울").isEmpty());
		assertEquals(new ArrayList<PlaceVO>(), dao.getPlaceByAddr("LEISURE", "도쿄"));
	}

	// 장소 ID로 담기용 조회
	@Test
	public void getPlace() {
		assertNotNull(dao.getPlace("9"));
		assertEquals(new ArrayList<PlaceVO>(), dao.getPlace("100"));
	}

	// 타입별 장소 개수 조회
	@Test
	public void getPlaceCount() {
		assertEquals(20, dao.getPlaceCount("LEISURE"));
		assertEquals(20, dao.getPlaceCount("RESTAURANT"));
		assertEquals(20, dao.getPlaceCount("STAY"));
		assertEquals(0, dao.getPlaceCount("TRIP"));
	}

	// 장소 좋아요 +1 갱신
	@Test
	public void setPlaceLikeCount() {
		int beforeLikeCount = dao.getPlaceLikeCount("LEISURE", "9");
		assertTrue(dao.setPlaceLikeCount("9"));
		assertEquals(beforeLikeCount + 1, dao.getPlaceLikeCount("LEISURE", "9"));
		assertFalse(dao.setPlaceLikeCount("100"));
	}

	// 좋아요 수 조회
	@Test
	public void getPlaceLikeCount() {
		assertTrue(dao.getPlaceLikeCount("LEISURE", "9") >= 0);
		assertEquals(0, dao.getPlaceLikeCount("LEISURE", "100"));
	}

	// 전체 장소 좌표 목록 조회
	@Test
	public void getPlaces() {
		List<PlaceVO> places = dao.getPlaces();
		assertNotNull(places);
		assertEquals(60, places.size());
	}

	// 레저 장소 좋아요순 조회
	@Test
	public void getLeisurePlacesOrderByLikeDesc() {
		List<PlaceVO> list = dao.getLeisurePlacesOrderByLikeDesc();
		assertNotNull(list);
		assertFalse(list.isEmpty());
		for (int i = 1; i < list.size(); i++) {
			assertTrue(list.get(i - 1).getLikeCount() >= list.get(i).getLikeCount());
		}
	}

	// 레저 장소 목록 조회
	@Test
	public void getLeisurePlaces() {
		List<PlaceVO> list = dao.getLeisurePlaces();
		assertNotNull(list);
		assertFalse(list.isEmpty());
	}

	// placeId 단건 조회 (getPlaceById)
	@Test
	public void getPlaceById() {
		PlaceVO place = dao.getPlaceById("9");
		assertNotNull(place);
		assertEquals("9", place.getPlaceId());
		assertEquals("롤파크", place.getTitle());
	}

	// placeId 단건 조회 (getPlaceByPlaceId)
	@Test
	public void getPlaceByPlaceId() {
		PlaceVO vo = dao.getPlaceByPlaceId("9");
		assertNotNull(vo);
		assertEquals("9", vo.getPlaceId());
		assertEquals("롤파크", vo.getTitle());
	}

	// 일정별 방문지 목록 조회
	@Test
	public void getVisitItemsByScheduleId() {
		List<VisitItemVO> items = dao.getVisitItemsByScheduleId("");
		assertNotNull(items);
		assertTrue(items.isEmpty());
	}

	@Test
	public void setCounting() {
		int before = dao.getPlaceLikeCount("LEISURE", "9");
		assertTrue(dao.setCounting("9"));
		assertEquals(before + 1, dao.getPlaceLikeCount("LEISURE", "9"));
		assertFalse(dao.setCounting("__no_such_place__"));
	}

	private void assertOrderByLikeDesc(List<PlaceVO> list) {
		for (int i = 1; i < list.size(); i++) {
			assertTrue(list.get(i - 1).getLikeCount() >= list.get(i).getLikeCount());
		}
	}

	private void assertOrderByTitleAsc(List<PlaceVO> list) {
		for (int i = 1; i < list.size(); i++) {
			assertTrue(list.get(i - 1).getTitle().compareTo(list.get(i).getTitle()) <= 0);
		}
	}

	// 전체 장소 제목순 검색
	@Test
	public void searchPlacesOrderByTitle() {
		List<PlaceVO> list = dao.searchPlacesOrderByTitle("LEISURE");
		assertNotNull(list);
		assertFalse(list.isEmpty());
		assertOrderByTitleAsc(list);
	}

	// 전체 장소 좋아요순 검색
	@Test
	public void searchPlacesOrderByLike() {
		List<PlaceVO> list = dao.searchPlacesOrderByLike("LEISURE");
		assertNotNull(list);
		assertFalse(list.isEmpty());
		assertOrderByLikeDesc(list);
	}

	// 카테고리 조건 제목순 검색
	@Test
	public void searchPlacesByCategoryOrderByTitle() {
		List<PlaceVO> list = dao.searchPlacesByCategoryOrderByTitle("LEISURE", "VE100100");
		assertNotNull(list);
		assertFalse(list.isEmpty());
		assertOrderByTitleAsc(list);
	}

	// 카테고리 조건 좋아요순 검색
	@Test
	public void searchPlacesByCategoryOrderByLike() {
		List<PlaceVO> list = dao.searchPlacesByCategoryOrderByLike("LEISURE", "VE100100");
		assertNotNull(list);
		assertFalse(list.isEmpty());
		assertOrderByLikeDesc(list);
	}

	// 키워드 조건 제목순 검색
	@Test
	public void searchPlacesByKeywordOrderByTitle() {
		List<PlaceVO> list = dao.searchPlacesByKeywordOrderByTitle("LEISURE", "롤파크");
		assertNotNull(list);
		assertFalse(list.isEmpty());
		assertEquals("9", list.get(0).getPlaceId());
		assertOrderByTitleAsc(list);
	}

	// 키워드 조건 좋아요순 검색
	@Test
	public void searchPlacesByKeywordOrderByLike() {
		List<PlaceVO> list = dao.searchPlacesByKeywordOrderByLike("LEISURE", "롤파크");
		assertNotNull(list);
		assertFalse(list.isEmpty());
		assertEquals("9", list.get(0).getPlaceId());
		assertOrderByLikeDesc(list);
	}

	// 카테고리 + 키워드 조건 제목순 검색
	@Test
	public void searchPlacesByCategoryAndKeywordOrderByTitle() {
		List<PlaceVO> list = dao.searchPlacesByCategoryAndKeywordOrderByTitle("LEISURE", "VE100100", "롤파크");
		assertNotNull(list);
		assertFalse(list.isEmpty());
		assertEquals("9", list.get(0).getPlaceId());
		assertOrderByTitleAsc(list);
	}

	// 카테고리 + 키워드 조건 좋아요순 검색
	@Test
	public void searchPlacesByCategoryAndKeywordOrderByLike() {
		List<PlaceVO> list = dao.searchPlacesByCategoryAndKeywordOrderByLike("LEISURE", "VE100100", "롤파크");
		assertNotNull(list);
		assertFalse(list.isEmpty());
		assertEquals("9", list.get(0).getPlaceId());
		assertOrderByLikeDesc(list);
	}
}
