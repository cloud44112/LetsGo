package com.letsgo.place.model;

public class VisitItemVO {
    private long visitItemId;
    private int visitOrder;
    private double distanceToNext;
    private String scheduleType;
    private String scheduleId;
    private String placeId;

    public VisitItemVO() {}

    public VisitItemVO(long visitItemId, int visitOrder, double distanceToNext,
                       String scheduleType, String scheduleId, String placeId) {
        this.visitItemId = visitItemId;
        this.visitOrder = visitOrder;
        this.distanceToNext = distanceToNext;
        this.scheduleType = scheduleType;
        this.scheduleId = scheduleId;
        this.placeId = placeId;
    }

    public long getVisitItemId() {
        return visitItemId;
    }

    public void setVisitItemId(long visitItemId) {
        this.visitItemId = visitItemId;
    }

    public int getVisitOrder() {
        return visitOrder;
    }

    public void setVisitOrder(int visitOrder) {
        this.visitOrder = visitOrder;
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

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }
}
