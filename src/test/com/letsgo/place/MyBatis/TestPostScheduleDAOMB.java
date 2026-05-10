package test.com.letsgo.place.MyBatis;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.letsgo.place.DBCPMybatis.DBCPMybatis;
import com.letsgo.place.MyBatis.PostScheduleDAOMB;
import com.letsgo.place.model.MapScheduleVO;
import com.letsgo.place.model.PostScheduleVO;
import com.letsgo.place.model.RouteScheduleVO;

public class TestPostScheduleDAOMB {

	private SqlSession session;
	private PostScheduleDAOMB dao;
	
	
	@Before
	public void setup() throws Exception {
		session = DBCPMybatis.getSqlSession();
		dao = new PostScheduleDAOMB(session);
	}
	
	
	@After
	public void tearDown() throws Exception {
		if (session != null) {
			session.rollback();
			session.close();
		}
	}
	
	@Test
	public void getPostScheduleList() {
		List<PostScheduleVO> list;
		assertNotNull(dao.getPostScheduleListLike()); //게시물 일정 전체 불러오기
		assertNotNull(dao.getPostScheduleListView()); //게시물 일정 전체 불러오기
		assertNotNull(dao.getPostScheduleListTitle()); //게시물 일정 전체 불러오기
		assertNotNull(dao.getPostScheduleListLatest()); //게시물 일정 전체 불러오기
		assertNotNull(dao.getPostScheduleListLike("여의도")); ///게시물 일정 이름으로 검색하기
		assertNotNull(dao.getPostScheduleListView("여의도")); //게시물 일정 상세 플레이스명(장소)으로 검색하기
		assertNotNull(dao.getPostScheduleListTitle("여의도")); //게시물 이름으로 정렬하기
		assertNotNull(dao.getPostScheduleListLatest("여의도")); //게시물 조회순으로 정렬하기
		
		System.out.println(list = dao.getPostScheduleListLike());
		System.out.println(list = dao.getPostScheduleListView());
		System.out.println(list = dao.getPostScheduleListTitle());
		System.out.println(list = dao.getPostScheduleListLatest());
		System.out.println(list = dao.getPostScheduleListLike("여의도"));
		System.out.println(list = dao.getPostScheduleListView("여의도"));
		System.out.println(list = dao.getPostScheduleListTitle("여의도"));
		System.out.println(list = dao.getPostScheduleListLatest("여의도"));

		}
	@Test
	public void getUserPostScheduleList() {
		List<PostScheduleVO> list;
		assertNotNull(dao.getUserPostScheduleListLike("user01")); //내가 올린 게시물 일정 전체 불러오기
		assertNotNull(dao.getUserPostScheduleListView("user01")); //내가 올린 게시물 일정 전체 불러오기
		assertNotNull(dao.getUserPostScheduleListTitle("user01")); //내가 올린 게시물 일정 전체 불러오기
		assertNotNull(dao.getUserPostScheduleListLatest("user01")); //내가 올린 게시물 일정 전체 불러오기
		assertNotNull(dao.getUserPostScheduleListLike("user01", "여의도")); //내가 올린 게시물 일정 이름으로 검색하기
		assertNotNull(dao.getUserPostScheduleListView("user01", "여의도")); //내가 올린 게시물 일정 상세 플레이스명(장소)으로 검색하기
		assertNotNull(dao.getUserPostScheduleListTitle("user01", "여의도")); //내가 올린 게시물 이름으로 정렬하기
		assertNotNull(dao.getUserPostScheduleListLatest("user01", "여의도")); //내가 올린 게시물 조회순으로 정렬하기
		
		System.out.println(dao.getUserPostScheduleListLike("user01"));
		System.out.println(dao.getUserPostScheduleListView("user01"));
		System.out.println(dao.getUserPostScheduleListTitle("user01"));
		System.out.println(dao.getUserPostScheduleListLatest("user01"));
		System.out.println(dao.getUserPostScheduleListLike("user01", "여의도"));
		System.out.println(dao.getUserPostScheduleListView("user01", "여의도"));
		System.out.println(dao.getUserPostScheduleListTitle("user01", "여의도"));
		System.out.println(dao.getUserPostScheduleListLatest("user01", "여의도"));
	}
	
	@Test
	public void getBudgetDetailTest() throws Exception {
		assertEquals("삼겹살 마구 먹기", dao.getBudgetDetail("P027"));
		//assertNotEquals("삼겹살 마구 먹기", dao.getBudgetDetail("P023"));
	}
	
	@Test
	public void getTodoDetailTest() throws Exception {
		assertEquals("햄부기 사냥함부기", dao.getTodoDetail("P027"));
		//assertNotEquals("햄부기 사냥햄부기", dao.getTodoDetail("P027"));
	}

	@Test
	public void getScheduleRouteTest() throws Exception {
		//assertEquals(new ArrayList<RouteScheduleVO>(), dao.getScheduleRoute("P091"));
		
		assertNotNull(dao.getScheduleRoute("P091"));
	}

	@Test
	public void getMapScheduleVOTest() throws Exception {
		List<MapScheduleVO> mapList = dao.getMapSchedule("P091");
		assertNotNull(mapList);
	}
	
	@Test
	public void getScheduleTitle() {
		assertNotNull(dao.getScheduleTitle("P012"));
	}
	
	@Test
	public void deletePostSchedule() {
	assertTrue(dao.deletePostSchedule("P012"));
	}
	
	@Test
	public void getLikeCount() {
		assertNotNull(dao.getLikeCount("P091"));
	}
	
	@Test
	public void getViewCount() {
		assertNotNull(dao.getViewCount("P091"));
	}
	
	@Test
	public void plusLike() {
		assertTrue(dao.plusLike("P091"));
	}
	
	@Test
	public void plusView() {
		assertTrue(dao.plusView("P091"));
	}
	
	@Test
	public void getUserId() {
		assertNotNull(dao.getUserId("P091"));
	}
	
	@Test
	public void copyToMySchedule()  {
		assertNotNull(dao.copyToMySchedule("여의도 대탐방", "삼겹살 마구 먹기", "햄부기 사냥함부기", "user01"));
	}
	
	@Test
	public void copyToVisitItem() {
	    String myScheduleId = dao.copyToMySchedule("테스트", "", "", "user01");
	    List<RouteScheduleVO> rt = dao.getScheduleRoute("P091");
	    for (RouteScheduleVO route : rt) {
	        assertTrue(dao.copyToVisitItem(myScheduleId, route));
	    }
		
	}

}
