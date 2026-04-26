package com.letsgo.place.model;

public class PlaceVO {
	private String placeId;
	private String title;
	private String addr1;
	private String firstImage;
	private String likeCount;
	private String scheduleType;

	public PlaceVO(String placeId, String title, String addr1, String firstImage, String likeCount,
			String scheduleType) {
		setPlaceId(placeId);
		setTitle(title);
		setAddr1(addr1);
		setFirstImage(firstImage);
		setLikeCount(likeCount);
		setScheduleType(scheduleType);
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

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getFirstImage() {
		return firstImage;
	}

	public void setFirstImage(String firstImage) {
		this.firstImage = firstImage;
	}

	public String getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(String likeCount) {
		this.likeCount = likeCount;
	}

	public String getScheduleType() {
		return scheduleType;
	}

	public void setScheduleType(String scheduleType) {
		this.scheduleType = scheduleType;
	}

}
