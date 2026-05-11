package test.com.letsgo.place.MyBatis;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.letsgo.place.MyBatis.PlaceDAOMB;
import com.letsgo.place.model.PlaceDAOInterface;
import com.letsgo.place.model.PlaceVO;
import com.letsgo.place.model.VisitItemVO;

public class PlaceDAOMBTest {

	private final PlaceDAOInterface dao = new PlaceDAOMB();

	// 제목으로 장소 조회
	@Test
	public void getPlaceByTitle() {
		List<PlaceVO> list = dao.getPlaceByTitle("LEISURE", "롤파크");
		assertNotNull(list);
		assertEquals(1, list.size());
		assertRollpark(list.get(0));
		assertTrue(dao.getPlaceByTitle("LEISURE", "디즈니").isEmpty());
	}

	// 카테고리로 장소 조회
	@Ignore
	@Test
	public void getPlaceByCategory() {
		List<PlaceVO> list = dao.getPlaceByCategory("LEISURE", "VE100100");
		assertNotNull(list);
		assertEquals(3, list.size());
		assertContainsPlaceIds(list, "6", "7", "9");
		assertTrue(dao.getPlaceByCategory("LEISURE", "NO_SUCH_CATEGORY").isEmpty());
	}

	// 좋아요순 조회
	@Ignore
	@Test
	public void getPlaceOrderByLike() {
		List<PlaceVO> list = dao.getPlaceOrderByLike("LEISURE");
		assertNotNull(list);
		assertEquals(20, list.size());
		assertOrderByLikeDesc(list);
		assertTrue(dao.getPlaceOrderByLike("TRIP").isEmpty());
	}

	// 제목순 조회
	@Ignore
	@Test
	public void getPlaceOrderByTitle() {
		List<PlaceVO> list = dao.getPlaceOrderByTitle("LEISURE");
		assertNotNull(list);
		assertEquals(20, list.size());
		assertOrderByTitleAsc(list);
		assertTrue(dao.getPlaceOrderByTitle("TRIP").isEmpty());
	}

	// 지역(주소)으로 장소 조회
	@Ignore
	@Test
	public void getPlaceByAddr() {
		List<PlaceVO> list = dao.getPlaceByAddr("LEISURE", "종로구");
		assertNotNull(list);
		assertEquals(1, list.size());
		assertRollpark(list.get(0));
		assertTrue(dao.getPlaceByAddr("LEISURE", "도쿄").isEmpty());
	}

	// 장소 ID로 담기용 조회
	@Ignore
	@Test
	public void getPlace() {
		List<PlaceVO> list = dao.getPlace("9");
		assertNotNull(list);
		assertEquals(1, list.size());
		assertEquals("9", list.get(0).getPlaceId());
		assertEquals("롤파크", list.get(0).getTitle());
		assertTrue(dao.getPlace("100").isEmpty());
	}

	// 타입별 장소 개수 조회
	@Ignore
	@Test
	public void getPlaceCount() {
		assertEquals(20, dao.getPlaceCount("LEISURE"));
		assertEquals(20, dao.getPlaceCount("RESTAURANT"));
		assertEquals(20, dao.getPlaceCount("STAY"));
		assertEquals(0, dao.getPlaceCount("TRIP"));
	}

	// 장소 좋아요 +1 갱신
	@Ignore
	@Test
	public void setPlaceLikeCount() {
		int beforeLikeCount = dao.getPlaceLikeCount("LEISURE", "9");
		assertTrue(dao.setPlaceLikeCount("9"));
		assertEquals(beforeLikeCount + 1, dao.getPlaceLikeCount("LEISURE", "9"));
		assertFalse(dao.setPlaceLikeCount("100"));
	}

	// 좋아요 수 조회
	@Ignore
	@Test
	public void getPlaceLikeCount() {
		assertTrue(dao.getPlaceLikeCount("LEISURE", "9") >= 0);
		assertEquals(0, dao.getPlaceLikeCount("LEISURE", "100"));
	}

	// 전체 장소 좌표 목록 조회
	@Ignore
	@Test
	public void getPlaces() {
		List<PlaceVO> places = dao.getPlaces();
		assertNotNull(places);
		assertEquals(60, places.size());
	}

	// 레저 장소 좋아요순 조회
	@Ignore
	@Test
	public void getLeisurePlacesOrderByLikeDesc() {
		List<PlaceVO> list = dao.getLeisurePlacesOrderByLikeDesc();
		assertNotNull(list);
		assertEquals(20, list.size());
		assertOrderByLikeDesc(list);
	}

	// 레저 장소 목록 조회
	@Ignore
	@Test
	public void getLeisurePlaces() {
		List<PlaceVO> list = dao.getLeisurePlaces();
		assertNotNull(list);
		assertEquals(20, list.size());
		assertContainsPlaceIds(list, "1", "9", "20");
	}

	// placeId 단건 조회 (getPlaceById)
	@Ignore
	@Test
	public void getPlaceById() {
		PlaceVO place = dao.getPlaceById("9");
		assertNotNull(place);
		assertEquals("9", place.getPlaceId());
		assertEquals("롤파크", place.getTitle());
	}

	// placeId 단건 조회 (getPlaceByPlaceId)
	@Ignore
	@Test
	public void getPlaceByPlaceId() {
		PlaceVO vo = dao.getPlaceByPlaceId("9");
		assertNotNull(vo);
		assertEquals("9", vo.getPlaceId());
		assertEquals("롤파크", vo.getTitle());
	}

	// 일정별 방문지 목록 조회
	@Ignore
	@Test
	public void getVisitItemsByScheduleId() {
		List<VisitItemVO> items = dao.getVisitItemsByScheduleId("SCH001");
		assertNotNull(items);
		assertEquals(3, items.size());
		assertVisitItem(items.get(0), 1, 5.0, "1");
		assertVisitItem(items.get(1), 2, 8.0, "21");
		assertVisitItem(items.get(2), 3, 0.0, "51");

		List<VisitItemVO> postItems = dao.getVisitItemsByScheduleId("P001");
		assertNotNull(postItems);
		assertEquals(3, postItems.size());
		assertVisitItem(postItems.get(0), 1, 5.0, "1");
		assertVisitItem(postItems.get(1), 2, 8.0, "21");
		assertVisitItem(postItems.get(2), 3, 0.0, "51");

		assertTrue(dao.getVisitItemsByScheduleId("NO_SUCH_SCHEDULE").isEmpty());
	}

	@Ignore
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
	@Ignore
	@Test
	public void searchPlacesOrderByTitle() {
		List<PlaceVO> list = dao.searchPlacesOrderByTitle("LEISURE");
		assertNotNull(list);
		assertEquals(20, list.size());
		assertOrderByTitleAsc(list);
	}

	// 전체 장소 좋아요순 검색
	@Ignore
	@Test
	public void searchPlacesOrderByLike() {
		List<PlaceVO> list = dao.searchPlacesOrderByLike("LEISURE");
		assertNotNull(list);
		assertEquals(20, list.size());
		assertOrderByLikeDesc(list);
	}

	// 카테고리 조건 제목순 검색
	@Ignore
	@Test
	public void searchPlacesByCategoryOrderByTitle() {
		List<PlaceVO> list = dao.searchPlacesByCategoryOrderByTitle("LEISURE", "VE100100");
		assertNotNull(list);
		assertEquals(3, list.size());
		assertContainsPlaceIds(list, "6", "7", "9");
		assertOrderByTitleAsc(list);
	}

	// 카테고리 조건 좋아요순 검색
	@Ignore
	@Test
	public void searchPlacesByCategoryOrderByLike() {
		List<PlaceVO> list = dao.searchPlacesByCategoryOrderByLike("LEISURE", "VE100100");
		assertNotNull(list);
		assertEquals(3, list.size());
		assertContainsPlaceIds(list, "6", "7", "9");
		assertOrderByLikeDesc(list);
	}

	// 키워드 조건 제목순 검색
	@Ignore
	@Test
	public void searchPlacesByKeywordOrderByTitle() {
		List<PlaceVO> list = dao.searchPlacesByKeywordOrderByTitle("LEISURE", "롤파크");
		assertNotNull(list);
		assertEquals(1, list.size());
		assertRollpark(list.get(0));
		assertOrderByTitleAsc(list);
	}

	// 키워드 조건 좋아요순 검색
	@Ignore
	@Test
	public void searchPlacesByKeywordOrderByLike() {
		List<PlaceVO> list = dao.searchPlacesByKeywordOrderByLike("LEISURE", "롤파크");
		assertNotNull(list);
		assertEquals(1, list.size());
		assertRollpark(list.get(0));
		assertOrderByLikeDesc(list);
	}

	// 카테고리 + 키워드 조건 제목순 검색
	@Ignore
	@Test
	public void searchPlacesByCategoryAndKeywordOrderByTitle() {
		List<PlaceVO> list = dao.searchPlacesByCategoryAndKeywordOrderByTitle("LEISURE", "VE100100", "롤파크");
		assertNotNull(list);
		assertEquals(1, list.size());
		assertRollpark(list.get(0));
		assertOrderByTitleAsc(list);
	}

	// 카테고리 + 키워드 조건 좋아요순 검색
	@Ignore
	@Test
	public void searchPlacesByCategoryAndKeywordOrderByLike() {
		List<PlaceVO> list = dao.searchPlacesByCategoryAndKeywordOrderByLike("LEISURE", "VE100100", "롤파크");
		assertNotNull(list);
		assertEquals(1, list.size());
		assertRollpark(list.get(0));
		assertOrderByLikeDesc(list);
	}

	private void assertRollpark(PlaceVO place) {
		assertEquals("9", place.getPlaceId());
		assertEquals("롤파크", place.getTitle());
		assertEquals("서울특별시 종로구 종로 33", place.getAddr1());
		assertEquals("126.9814539585", place.getMapx());
		assertEquals("37.5710121125", place.getMapy());
	}

	private void assertContainsPlaceIds(List<PlaceVO> list, String... expectedPlaceIds) {
		for (String expectedPlaceId : expectedPlaceIds) {
			assertTrue(containsPlaceId(list, expectedPlaceId));
		}
	}

	private boolean containsPlaceId(List<PlaceVO> list, String placeId) {
		for (PlaceVO place : list) {
			if (placeId.equals(place.getPlaceId())) {
				return true;
			}
		}
		return false;
	}

	private void assertVisitItem(VisitItemVO item, int visitOrder, double distanceToNext, String placeId) {
		assertEquals(visitOrder, item.getVisitOrder());
		assertEquals(distanceToNext, item.getDistanceToNext(), 0.0);
		assertEquals(placeId, item.getPlaceId());
	}
}
