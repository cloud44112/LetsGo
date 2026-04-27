package com.letsgo.place.model;

public class ScheduleMapVO {
	private String placeId;
	private String placeType;
	private String title;
	private String mapX;
	private String mapY;
	private String addr1;

	public ScheduleMapVO(String placeId, String placeType, String title, String mapX, String mapY, String addr1) {
        setPlaceId(placeId);
        setPlaceType(placeType);
        setTitle(title);
        setMapX(mapX);
        setMapY(mapY);
        setAddr1(addr1);
	}

	public String getPlaceId() {
		return placeId;
	}

	public String getPlaceType() {
		return placeType;
	}

	public String getTitle() {
		return title;
	}

	public String getMapX() {
		return mapX;
	}

	public String getMapY() {
		return mapY;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}

	public void setPlaceType(String placeType) {
		this.placeType = placeType;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setMapX(String mapX) {
		this.mapX = mapX;
	}

	public void setMapY(String mapY) {
		this.mapY = mapY;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
}
