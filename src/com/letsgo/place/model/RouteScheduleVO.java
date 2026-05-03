package com.letsgo.place.model;

public class RouteScheduleVO {

	private String visitId;
	private String visitOrder;
	private String placeId;
	private String title;

	public RouteScheduleVO(String visitId, String visitOrder, String placeId, String title) {

		this.visitId = visitId;
		this.visitOrder = visitOrder;
		this.placeId = placeId;
		this.title = title;
	}
	

	public String getVisitId() {
		return visitId;
	}

	public void setVisitId(String visitId) {
		this.visitId = visitId;
	}

	public String getVisitOrder() {
		return visitOrder;
	}

	public void setVisitOrder(String visitOrder) {
		this.visitOrder = visitOrder;
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


	@Override
	public String toString() {
		return "RouteScheduleVO [visitId=" + visitId + ", visitOrder=" + visitOrder + ", placeId=" + placeId
				+ ", title=" + title + "]";
	}
	
	
}
