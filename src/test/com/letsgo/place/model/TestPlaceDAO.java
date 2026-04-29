package test.com.letsgo.place.model;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.letsgo.place.model.DBCP;
import com.letsgo.place.model.PlaceDAO;
import com.letsgo.place.model.PlaceVO;

public class TestPlaceDAO {
	private PlaceDAO dao;
	private Connection conn;
	
	@Before
	public void setup() throws Exception {
		conn = DBCP.getConnection();
		conn.setAutoCommit(false);
		dao = new PlaceDAO(conn);
	}

	@After
	public void tearDown() throws Exception {
		if (conn != null) {
			conn.rollback();
			conn.close();
		}
	}
	
	@Test
	public void getPlaceByTitleTest() {
		assertNotNull(dao.getPlaceByTitle("LEISURE", "롤파크"));
		assertFalse(dao.getPlaceByTitle("LEISURE", "롤파크").isEmpty());
		assertEquals(new ArrayList<PlaceVO>(), dao.getPlaceByTitle("LEISURE", "디즈니"));
	}

	@Test
	public void getPlaceByCategoryTest() {
		assertNotNull(dao.getPlaceByCategory("LEISURE", "VE100100"));
		assertFalse(dao.getPlaceByCategory("LEISURE", "VE100100").isEmpty());
		assertEquals(new ArrayList<PlaceVO>(), dao.getPlaceByCategory("12", "N"));
	}

	@Test
	public void getPlaceOrderByLikeTest() {
		assertNotNull(dao.getPlaceOrderByLike("LEISURE"));
		assertFalse(dao.getPlaceOrderByLike("LEISURE").isEmpty());
		assertEquals(new ArrayList<PlaceVO>(), dao.getPlaceOrderByLike("trip"));
	}

	@Test
	public void getPlaceOrderByTitleTest() {
		assertNotNull(dao.getPlaceOrderByTitle("LEISURE"));
		assertFalse(dao.getPlaceOrderByTitle("LEISURE").isEmpty());
		assertEquals(new ArrayList<PlaceVO>(), dao.getPlaceOrderByTitle("trip"));
	}

	@Test
	public void getPlaceByAddrTest() {
		assertNotNull(dao.getPlaceByAddr("LEISURE", "서울"));
		assertFalse(dao.getPlaceByAddr("LEISURE", "서울").isEmpty());
		assertEquals(new ArrayList<PlaceVO>(), dao.getPlaceByAddr("LEISURE", "도쿄"));
	}

	@Test
	public void getPlaceTest() {
		assertNotNull(dao.getPlace("9"));
		assertEquals(new ArrayList<PlaceVO>(), dao.getPlace("100"));
	}

	@Test
	public void getPlaceCountTest() {
		assertTrue(dao.getPlaceCount("LEISURE") > 0);
		assertEquals(0, dao.getPlaceCount("trip"));
	}

	@Test
	public void setPlaceLikeCountTest() {
		int beforeLikeCount = dao.getPlaceLikeCount("LEISURE", "9");
		assertTrue(dao.setPlaceLikeCount("9"));
		assertEquals(beforeLikeCount + 1, dao.getPlaceLikeCount("LEISURE", "9"));
		assertFalse(dao.setPlaceLikeCount("100"));
	}

	@Test
	public void getPlaceLikeCountTest() {
		assertTrue(dao.getPlaceLikeCount("LEISURE", "9") >= 0);
		assertEquals(0, dao.getPlaceLikeCount("LEISURE", "100"));
	}

}
