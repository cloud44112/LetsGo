package com.letsgo.place.model;

public class MyScheduleVO {

	private String myScheduleId;
	private String myScheduleTitle;
	private String startAt;
	private String isShared;
	private String placeTitle;
	private String addr1;
	private String firstImage;

	public MyScheduleVO(String myScheduleId, String myScheduleTitle, String startAt, String isShared,
			String placeTitle, String addr1, String firstImage) {
		setMyScheduleId(myScheduleId);
		setMyScheduleTitle(myScheduleTitle);
		setStartAt(startAt);
		setIsShared(isShared);
		setPlaceTitle(placeTitle);
		setAddr1(addr1);
		setFirstImage(firstImage);
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


	public String getPlaceTitle() {
		return placeTitle;
	}

	public String getAddr1() {
		return addr1;
	}

	public String getFirstImage() {
		return firstImage;
	}

	private void setMyScheduleId(String myScheduleId) {
		this.myScheduleId = myScheduleId;
	}

	public void setMyScheduleTitle(String myScheduleTitle) {
		this.myScheduleTitle = myScheduleTitle;
	}

	private void setStartAt(String startAt) {
		this.startAt = startAt;
	}

	public void setIsShared(String isShared) {
		this.isShared = isShared;
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

	@Override
	public String toString() {
		return "{"
		        + "\"myScheduleId\":\"" + myScheduleId + "\", "
		        + "\"myScheduleTitle\":\"" + myScheduleTitle + "\", "
		        + "\"startAt\":\"" + startAt + "\", "
		        + "\"isShared\":" + isShared + ", "
		        + "\"placeTitle\":\"" + placeTitle + "\", "
		        + "\"addr1\":\"" + addr1 + "\", "
		        + "\"firstImage\":\"" + firstImage + "\""
		        + "}";
		}
	

}