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
}
