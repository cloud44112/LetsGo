package test.com.letsgo.place.model;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.letsgo.place.model.DBCP;
import com.letsgo.place.model.PostScheduleDAO;
import com.letsgo.place.model.PostScheduleVO;

public class TestPostScheduleDAO {
	private PostScheduleDAO dao;
	private Connection conn;
	
	@Before
	public void setup() throws Exception {
		conn = DBCP.getConnection();
		conn.setAutoCommit(false);
		dao = new PostScheduleDAO(conn);
	}
	
	@After
	public void tearDown() throws Exception {
		if (conn != null) {
			conn.rollback();
			conn.close();
		}
	}
	
	@Test
	public void getPostScheduleList() {
		List<PostScheduleVO> list;
		assertNotNull(dao.getPostScheduleList("", "", "latest")); //게시물 일정 전체 불러오기
		assertNotNull(dao.getPostScheduleList("서울", "schedule", "like")); ///게시물 일정 이름으로 검색하기
		assertNotNull(dao.getPostScheduleList("다래", "place", "view")); //게시물 일정 상세 플레이스명(장소)으로 검색하기
		assertNotNull(dao.getPostScheduleList("", "", "title")); //게시물 이름으로 정렬하기
		assertNotNull(dao.getPostScheduleList("", "", "view")); //게시물 조회순으로 정렬하기
		assertNotNull(dao.getPostScheduleList("", "", "like")); //게시물 좋아요순으로 정렬하기
		assertNotNull(dao.getPostScheduleList("", "", "latest")); //게시물 등록순으로 정렬하기
		System.out.println(list = dao.getPostScheduleList("", "", "latest"));
		System.out.println(list = dao.getPostScheduleList("서울", "schedule", "like"));
		System.out.println(list = dao.getPostScheduleList("다래", "place", "view"));
		System.out.println(list = dao.getPostScheduleList("", "", "title"));
		System.out.println(list = dao.getPostScheduleList("", "", "view"));
		System.out.println(list = dao.getPostScheduleList("", "", "like"));
		System.out.println(list = dao.getPostScheduleList("", "", "latest"));
	}
	
	@Test
	public void getUserPostScheduleList() {
		List<PostScheduleVO> list;
		assertNotNull(dao.getUserPostScheduleList("user01", "", "", "latest")); //내가 올린 게시물 일정 전체 불러오기
		assertNotNull(dao.getUserPostScheduleList("user01", "서울", "schedule", "title")); //내가 올린 게시물 일정 이름으로 검색하기
		assertNotNull(dao.getUserPostScheduleList("user01", "다래", "place", "title")); //내가 올린 게시물 일정 상세 플레이스명(장소)으로 검색하기
		assertNotNull(dao.getUserPostScheduleList("user01", "", "", "title")); //내가 올린 게시물 이름으로 정렬하기
		assertNotNull(dao.getUserPostScheduleList("user01", "", "", "view")); //내가 올린 게시물 조회순으로 정렬하기
		assertNotNull(dao.getUserPostScheduleList("user01", "", "", "like")); //내가 올린 게시물 좋아요순으로 정렬하기
		assertNotNull(dao.getUserPostScheduleList("user01", "", "", "latest")); //내가 올린 게시물 등록순으로 정렬하기
		System.out.println(dao.getUserPostScheduleList("user01", "", "", "latest"));
		System.out.println(dao.getUserPostScheduleList("user01", "서울", "schedule", "title"));
		System.out.println(dao.getUserPostScheduleList("user01", "다래", "place", "title"));
		System.out.println(dao.getUserPostScheduleList("user01", "", "", "title"));
		System.out.println(dao.getUserPostScheduleList("user01", "", "", "view"));
		System.out.println(dao.getUserPostScheduleList("user01", "", "", "like"));
		System.out.println(dao.getUserPostScheduleList("user01", "", "", "latest"));
	}
	
	
//	
////	@Test
////	public void deletePostSchedule() {
////		assertTrue(dao.deletePostSchedule("P002"));
////	}
	
	@Test
	public void plusLike() {
		assertTrue(dao.plusLike("P003"));
	}


}
