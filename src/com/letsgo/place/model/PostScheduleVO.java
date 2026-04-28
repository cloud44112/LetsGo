package com.letsgo.place.model;

public class PostScheduleVO {

	private String postId;
	private String title;
	private int likeCount;
	private int viewCount;
	private int isAnonymous;
	private String userName;
	private int visitItemId;
	private int visitOrder;
	private String scheduleType;
	private String placeTitle;
	private String addr1;
	private String firstImage;
	private String placeType;
	
	public PostScheduleVO(String postId, String title, int likeCount, int viewCount, int isAnonymous,
			String userName, int visitItemId, int visitOrder, String scheduleType, String placeTitle,
			String addr1, String firstImage, String placeType) {
		setPostId(postId);
		setTitle(title);
		setLikeCount(likeCount);
		setViewCount(viewCount);
		setIsAnonymous(isAnonymous);
		setUserName(userName);
		setVisitItemId(visitItemId);
		setVisitOrder(visitOrder);
		setScheduleType(scheduleType);
		setPlaceTitle(placeTitle);
		setAddr1(addr1);
		setFirstImage(firstImage);
		setPlaceType(placeType);
		
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public int getIsAnonymous() {
		return isAnonymous;
	}

	public void setIsAnonymous(int isAnonymous) {
		this.isAnonymous = isAnonymous;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getVisitItemId() {
		return visitItemId;
	}

	public void setVisitItemId(int visitItemId) {
		this.visitItemId = visitItemId;
	}

	public int getVisitOrder() {
		return visitOrder;
	}

	public void setVisitOrder(int visitOrder) {
		this.visitOrder = visitOrder;
	}

	public String getScheduleType() {
		return scheduleType;
	}

	public void setScheduleType(String scheduleType) {
		this.scheduleType = scheduleType;
	}

	public String getPlaceTitle() {
		return placeTitle;
	}

	public void setPlaceTitle(String placeTitle) {
		this.placeTitle = placeTitle;
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

	public String getPlaceType() {
		return placeType;
	}

	public void setPlaceType(String placeType) {
		this.placeType = placeType;
	}

	
}