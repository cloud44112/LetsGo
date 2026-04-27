package com.letsgo.place.model;

public class PlaceVO {
	private String placeId;
	private String title;
	private String addr1;
	private String mapx;
	private String mapy;
	private String firstImage;
	private int likeCount;
	private String placeType;
	
	public PlaceVO(String placeId, String title, String addr1, String mapx, String mapy, String firstImage,
			int likeCount, String placeType) {
		setPlaceId(placeId);
		setTitle(title);
		setAddr1(addr1);
		setMapx(mapx);
		setMapy(mapy);
		setFirstImage(firstImage);
		setLikeCount(likeCount);
		setPlaceType(placeType);
	}
	
	public PlaceVO(String placeId, String title, String addr1, String mapx, String mapy) {
		setPlaceId(placeId);
		setTitle(title);
		setAddr1(addr1);
		setMapx(mapx);
		setMapy(mapy);
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
	
	public String getMapx() {
		return mapx;
	}

	public void setMapx(String mapx) {
		this.mapx = mapx;
	}

	public String getMapy() {
		return mapy;
	}

	public void setMapy(String mapy) {
		this.mapy = mapy;
	}

	public String getFirstImage() {
		return firstImage;
	}

	public void setFirstImage(String firstImage) {
		this.firstImage = firstImage;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public String getPlaceType() {
		return placeType;
	}

	public void setPlaceType(String placeType) {
		this.placeType = placeType;
	}

	
}
