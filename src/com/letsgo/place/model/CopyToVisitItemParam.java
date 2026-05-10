package com.letsgo.place.model;

public class CopyToVisitItemParam {
	private String myScheduleId;
    private String visitOrder;
    private double distanceToNext;
    private String placeId;
    private String scheduleType = "MY_SCH";  // 기본값 고정
    
    public CopyToVisitItemParam(String myScheduleId, RouteScheduleVO route) {
        this.myScheduleId    = myScheduleId;
        this.visitOrder      = route.getVisitOrder();
        this.distanceToNext  = route.getDistanceToNext();
        this.placeId         = route.getPlaceId();
    }

	public String getMyScheduleId() {
		return myScheduleId;
	}

	public String getVisitOrder() {
		return visitOrder;
	}

	public double getDistanceToNext() {
		return distanceToNext;
	}

	public String getPlaceId() {
		return placeId;
	}

	public String getScheduleType() {
		return scheduleType;
	}
}
