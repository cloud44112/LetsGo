package UserTest;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.letsgo.place.model.DBCP;
import com.letsgo.place.model.PlaceDAO;
import com.letsgo.place.model.PlaceVO;
import com.letsgo.place.model.VisitItemVO;

public class 플레이스Test {


	private Connection conn;

	private PlaceDAO placeDAO;


	public void 바구니에_담긴_플레이스_상세_정보() throws Exception {

		PlaceDAO dao = new PlaceDAO();
		List<PlaceVO> places = dao.getPlaces();
		assertNotNull(places);
		assertFalse("플레이스 목록이 없으면 안됨.", places.isEmpty());
		PlaceVO first = places.get(0);
		assertNotNull(first.getTitle());
		assertNotNull(first.getAddr1());
		assertNotNull(first.getMapx());
		assertNotNull(first.getMapy());

		System.out.println("첫 번째: " + first.getTitle() + " / " + first.getAddr1());
	}


	public void 플레이스_상세조회() throws Exception {

		PlaceDAO dao = new PlaceDAO();
		PlaceVO place = dao.getPlaceById("1");
		assertNotNull(place);
		assertNotNull(place.getTitle());
		System.out.println("제목: " + place.getTitle());
		System.out.println("주소: " + place.getAddr1());
		System.out.println("좌표: " + place.getMapx() + ", " + place.getMapy());

	}

	
	public void 방문지_조회() throws Exception {
	    PlaceDAO dao = new PlaceDAO();
	    List<VisitItemVO> list = dao.getVisitItemsByScheduleId("P023"); 
	    assertNotNull(list);
	    assertFalse(list.isEmpty());
	    for (VisitItemVO vo : list) {
	        System.out.println("순서: " + vo.getVisitOrder());
	        System.out.println("장소ID: " + vo.getPlaceId());
	        System.out.println("다음까지 거리: " + vo.getDistanceToNext());
	    }
	}

	@Test
	public void 좋아요_카운트() throws Exception {
	    PlaceDAO dao = new PlaceDAO();
	    boolean result = dao.setCounting("P001"); 
	    assertTrue(result);
	    System.out.println("좋아요 카운트 결과: " + result);
	}
   

}






