package com.letsgo.place.model;

public class SaveScheduleVO {

	private String visitOrder;
	private String distanceToNext;
	private String placeType;
	private String scheduleId;
	private String visitItemId;
	private String mySchedule;
	private String startAt;
	private String budgetDetails;

	public SaveScheduleVO(String visitOrder, String distanceToNext, String placeType, String scheduleId,
			String visitItemId, String mySchedule, String startAt, String budgetDetails) {
		setVisitOrder(visitOrder);
		setDistanceToNext(distanceToNext);
		setPlaceType(placeType);
		setScheduleId(scheduleId);
		setVisitItemId(visitItemId);
		setMySchedule(mySchedule);
		setStartAt(startAt);
		setBudgetDetails(budgetDetails);
	}

	public String getVisitOrder() {
		return visitOrder;
	}

	public String getDistanceToNext() {
		return distanceToNext;
	}

	public String getPlaceType() {
		return placeType;
	}

	public String getScheduleId() {
		return scheduleId;
	}

	public String getVisitItemId() {
		return visitItemId;
	}

	public String getMySchedule() {
		return mySchedule;
	}

	public String getStartAt() {
		return startAt;
	}

	public String getBudgetDetails() {
		return budgetDetails;
	}

	public void setVisitOrder(String visitOrder) {
		this.visitOrder = visitOrder;
	}

	public void setDistanceToNext(String distanceToNext) {
		this.distanceToNext = distanceToNext;
	}

	public void setPlaceType(String placeType) {
		this.placeType = placeType;
	}

	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
	}

	public void setVisitItemId(String visitItemId) {
		this.visitItemId = visitItemId;
	}

	public void setMySchedule(String mySchedule) {
		this.mySchedule = mySchedule;
	}

	public void setStartAt(String startAt) {
		this.startAt = startAt;
	}

	public void setBudgetDetails(String budgetDetails) {
		this.budgetDetails = budgetDetails;
	}
}