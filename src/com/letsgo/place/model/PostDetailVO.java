package com.letsgo.place.model;

public class PostDetailVO {

	private String likeCount;
	private String visitItemId;
	private String visitOrder;
	private String placeId;
	private String leisureSportsNo;
	private String placeTitle;
	private String restaurantNo;
	private String myScheduleId;
	private String budgetDetails;
	private String todoDetails;

	public PostDetailVO(String likeCount, String visitItemId, String visitOrder, String placeId, String leisureSportsNo,
			String placeTitle, String restaurantNo, String myScheduleId, String budgetDetails, String todoDetails) {
		setLikeCount(likeCount);
		setVisitItemId(visitItemId);
		setVisitOrder(visitOrder);
		setPlaceId(placeId);
		setLeisureSportsNo(leisureSportsNo);
		setPlaceTitle(placeTitle);
		setRestaurantNo(restaurantNo);
		setMyScheduleId(myScheduleId);
		setBudgetDetails(budgetDetails);
		setTodoDetails(todoDetails);
	}

	public String getLikeCount() {
		return likeCount;
	}

	public String getVisitItemId() {
		return visitItemId;
	}

	public String getVisitOrder() {
		return visitOrder;
	}

	public String getPlaceId() {
		return placeId;
	}

	public String getLeisureSportsNo() {
		return leisureSportsNo;
	}

	public String getPlaceTitle() {
		return placeTitle;
	}

	public String getRestaurantNo() {
		return restaurantNo;
	}

	public String getMyScheduleId() {
		return myScheduleId;
	}

	public String getBudgetDetails() {
		return budgetDetails;
	}

	public String getTodoDetails() {
		return todoDetails;
	}

	public void setLikeCount(String likeCount) {
		this.likeCount = likeCount;
	}

	public void setVisitItemId(String visitItemId) {
		this.visitItemId = visitItemId;
	}

	public void setVisitOrder(String visitOrder) {
		this.visitOrder = visitOrder;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}

	public void setLeisureSportsNo(String leisureSportsNo) {
		this.leisureSportsNo = leisureSportsNo;
	}

	public void setPlaceTitle(String placeTitle) {
		this.placeTitle = placeTitle;
	}

	public void setRestaurantNo(String restaurantNo) {
		this.restaurantNo = restaurantNo;
	}

	public void setMyScheduleId(String myScheduleId) {
		this.myScheduleId = myScheduleId;
	}

	public void setBudgetDetails(String budgetDetails) {
		this.budgetDetails = budgetDetails;
	}

	public void setTodoDetails(String todoDetails) {
		this.todoDetails = todoDetails;
	}
}