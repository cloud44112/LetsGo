package com.letsgo.place.model;

public class MapScheduleVO {
	private String placeId;
	private String title;
	private String mapX;
	private String mapY;
	private String distanceToNext;
	
	
	public MapScheduleVO(String placeId, String title, String mapX, String mapY, String distanceToNext) {
		this.placeId = placeId;
		this.title = title;
		this.mapX = mapX;
		this.mapY = mapY;
		this.distanceToNext = distanceToNext;
	}
	public String getPlaceId() {
		return placeId;
	}
	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMapX() {
		return mapX;
	}
	public void setMapX(String mapX) {
		this.mapX = mapX;
	}
	public String getMapY() {
		return mapY;
	}
	public void setMapY(String mapY) {
		this.mapY = mapY;
	}
	public String getDistanceToNext() {
		return distanceToNext;
	}
	public void setDistanceToNext(String distanceToNext) {
		this.distanceToNext = distanceToNext;
	}
	
	

	
	
}
