package com.letsgo.place.model;

public class RouteScheduleVO {

	private String visitId;
	private String visitOrder;
	private String placeId;
	private String title;
	private double distanceToNext;
	private String scheduleType;

	public RouteScheduleVO(String visitId, String visitOrder, String placeId, String title) {

		this.visitId = visitId;
		this.visitOrder = visitOrder;
		this.placeId = placeId;
		this.title = title;
	}

	public RouteScheduleVO(String visitId, String visitOrder, String placeId, String title, double distanceToNext, String scheduleType) {

		setVisitId(visitId);
		setVisitOrder(visitOrder);
		setPlaceId(placeId);
		setTitle(title);
		setDistanceToNext(distanceToNext);
		setScheduleType(scheduleType);
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

	public double getDistanceToNext() {
		return distanceToNext;
	}

	public void setDistanceToNext(double distanceToNext) {
		this.distanceToNext = distanceToNext;
	}


	public String getScheduleType() {
		return scheduleType;
	}

	public void setScheduleType(String scheduleType) {
		this.scheduleType = scheduleType;
	}

	@Override
	public String toString() {
		return "RouteScheduleVO [visitId=" + visitId + ", visitOrder=" + visitOrder + ", placeId=" + placeId
				+ ", title=" + title + "]";
	}
	
	
}
