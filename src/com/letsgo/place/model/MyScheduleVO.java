package com.letsgo.place.model;

public class MyScheduleVO {

	private String myScheduleId;
	private String myScheduleTitle;
	private String startAt;
	private String isShared;
	private String visitItemId;
	private String placeId;
	private String visitItem;
	private String placeTitle;
	private String addr1;
	private String firstImage;

	public MyScheduleVO(String myScheduleId, String myScheduleTitle, String startAt, String isShared,
			String visitItemId, String placeId, String visitItem, String placeTitle, String addr1, String firstImage) {
		setMyScheduleId(myScheduleId);
		setMyScheduleTitle(myScheduleTitle);
		setStartAt(startAt);
		setIsShared(isShared);
		setVisitItemId(visitItemId);
		setPlaceId(placeId);
		setVisitItem(visitItem);
		setPlaceTitle(placeTitle);
		setAddr1(addr1);
		setFirstImage(firstImage);
	}
	
	public MyScheduleVO(String myScheduleId, String myScheduleTitle, String startAt, String isShared) {
		this(myScheduleId, myScheduleTitle, startAt ,isShared, null, null, null, null, null, null);
	}

	public String getMyScheduleId() {
		return myScheduleId;
	}

	public String getMyScheduleTitle() {
		return myScheduleTitle;
	}

	public String getStartAt() {
		return startAt;
	}

	public String getIsShared() {
		return isShared;
	}

	public String getVisitItemId() {
		return visitItemId;
	}

	public String getPlaceId() {
		return placeId;
	}

	public String getVisitItem() {
		return visitItem;
	}

	public String getPlaceTitle() {
		return placeTitle;
	}

	public String getAddr1() {
		return addr1;
	}

	public String getFirstImage() {
		return firstImage;
	}

	public void setMyScheduleId(String myScheduleId) {
		this.myScheduleId = myScheduleId;
	}

	public void setMyScheduleTitle(String myScheduleTitle) {
		this.myScheduleTitle = myScheduleTitle;
	}

	public void setStartAt(String startAt) {
		this.startAt = startAt;
	}

	public void setIsShared(String isShared) {
		this.isShared = isShared;
	}

	public void setVisitItemId(String visitItemId) {
		this.visitItemId = visitItemId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}

	public void setVisitItem(String visitItem) {
		this.visitItem = visitItem;
	}

	public void setPlaceTitle(String placeTitle) {
		this.placeTitle = placeTitle;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public void setFirstImage(String firstImage) {
		this.firstImage = firstImage;
	}
}