package com.letsgo.place.model;

public class SchedulePostVO {

	private String postId;
	private String title;
	private String likeCount;
	private String viewCount;
	private String isAnonymous;
	private String visitItemId;
	private String placeTitle;
	private String firstImage;
	private String addr1;
	private String userName;

	public SchedulePostVO(String postId, String title, String likeCount, String viewCount, String isAnonymous,
			String visitItemId, String placeTitle, String firstImage, String addr1, String userName) {
		setPostId(postId);
		setTitle(title);
		setLikeCount(likeCount);
		setViewCount(viewCount);
		setIsAnonymous(isAnonymous);
		setVisitItemId(visitItemId);
		setPlaceTitle(placeTitle);
		setFirstImage(firstImage);
		setAddr1(addr1);
		setUserName(userName);
	}

	public String getPostId() {
		return postId;
	}

	public String getTitle() {
		return title;
	}

	public String getLikeCount() {
		return likeCount;
	}

	public String getViewCount() {
		return viewCount;
	}

	public String getIsAnonymous() {
		return isAnonymous;
	}

	public String getVisitItemId() {
		return visitItemId;
	}

	public String getPlaceTitle() {
		return placeTitle;
	}

	public String getFirstImage() {
		return firstImage;
	}

	public String getAddr1() {
		return addr1;
	}

	public String getUserName() {
		return userName;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setLikeCount(String likeCount) {
		this.likeCount = likeCount;
	}

	public void setViewCount(String viewCount) {
		this.viewCount = viewCount;
	}

	public void setIsAnonymous(String isAnonymous) {
		this.isAnonymous = isAnonymous;
	}

	public void setVisitItemId(String visitItemId) {
		this.visitItemId = visitItemId;
	}

	public void setPlaceTitle(String placeTitle) {
		this.placeTitle = placeTitle;
	}

	public void setFirstImage(String firstImage) {
		this.firstImage = firstImage;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}