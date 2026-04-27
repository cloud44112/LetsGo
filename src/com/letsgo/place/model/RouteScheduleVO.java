package com.letsgo.place.model;

public class RouteScheduleVO {

	private String placeId;
	private String visitOrder;
	private String title;

	public RouteScheduleVO(String placeId, String visitOrder, String title) {
        setPlaceId(placeId);
        setPlaceType(visitOrder);
        setTitle(title);
	}

	public String getPlaceId() {
		return placeId;
	}

	public String getPlaceType() {
		return visitOrder;
	}

	public String getTitle() {
		return title;
	}


	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}

	public void setPlaceType(String placeType) {
		this.visitOrder = placeType;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
