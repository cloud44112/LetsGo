package com.letsgo.place.model;

public class TodoScheduleVO {

	private String todoDetails;
	private String myScheduleId;

	public TodoScheduleVO(String todoDetails, String myScheduleId) {
		setTodoDetails(todoDetails);
		setMyScheduleId(myScheduleId);
	}

	public String getTodoDetails() {
		return todoDetails;
	}

	public String getMyScheduleId() {
		return myScheduleId;
	}

	public void setTodoDetails(String todoDetails) {
		this.todoDetails = todoDetails;
	}

	public void setMyScheduleId(String myScheduleId) {
		this.myScheduleId = myScheduleId;
	}
}