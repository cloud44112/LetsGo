package com.letsgo.place.model;

public interface PlaceQuery {
	String GET_PLACE_BY_TITLE_SQL="SELECT place_Id, title, first_image, addr1, mapx, mapy, like_count FROM place WHERE place_type=? AND title=?";
	
	String GET_PLACE_BY_CATEGORY_SQL="SELECT place_Id, title, first_image, addr1, mapx, mapy, like_count FROM place WHERE place_type=? AND lclssystm3=?";
	
	String GET_PLACE_ORDER_BY_LIKE_SQL="SELECT place_Id, title, first_image, addr1, mapx, mapy, like_count FROM place WHERE place_type=? ORDER BY like_count DESC";
	
	String GET_PLACE_ORDER_BY_TITLE_SQL="SELECT place_Id, title, first_image, addr1, mapx, mapy, like_count FROM place WHERE place_type=? ORDER BY title ASC";
	
	String GET_PLACE_BY_ADDR_SQL="SELECT place_Id, title, first_image, addr1, mapx, mapy, like_count FROM place WHERE place_type =? AND addr1 Like ?";
	
	String GET_PLACE_SQL="SELECT title, addr1, mapx, mapy FROM place WHERE place_type=? AND place_Id=?";
	
	String GET_PLACE_COUNT_SQL="SELECT COUNT(place_Id) FROM place WHERE place_type=?";
	
	String GET_PLACE_LIKE_COUNT_SQL="SELECT like_count FROM place WHERE place_type=? AND place_Id=?";
	
	String GET_LEISURE_PLACE_DESC_SQL = "SELECT * FROM place WHERE place_type = 'LEISURE' ORDER BY like_count DESC";
	
	String GET_PLACE_BYID_SQL = "SELECT TITLE, ADDR1, MAPX, MAPY FROM PLACE WHERE PLACE_ID = ?";
	
	String GET_VISIT_ITEM_SCHEDULE_ID_SQL = "SELECT VISIT_ORDER, DISTANCE_TO_NEXT, PLACE_ID FROM VISIT_ITEM WHERE SCHEDULE_ID = ? ORDER BY VISIT_ORDER ASC";
	
	String GET_PLACE_BYPLACEID_SQL = "SELECT PLACE_ID, TITLE, ADDR1, MAPX, MAPY FROM PLACE WHERE PLACE_ID = ?";
	
	String GET_PLACES_SQL = "SELECT place_id, title, addr1, mapx, mapy FROM place";
	
	String SET_COUNTING_SQL = "UPDATE schedule_post SET like_count = like_count + 1 WHERE post_id = ?";

	String INSERT_VISIT_ITEM_SQL ="INSERT INTO VISIT_ITEM (VISIT_ITEM_ID, VISIT_ORDER, DISTANCE_TO_NEXT, PLACE_ID, SCHEDULE_ID, SCHEDULE_TYPE) VALUES (VISIT_ITEM_SEQ.NEXTVAL, ?, ?, ?, ?, ?)";
}
