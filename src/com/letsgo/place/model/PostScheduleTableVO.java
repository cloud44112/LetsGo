package com.letsgo.place.model;

public class PostScheduleTableVO {
	private String budgetDetails;
	private String todoDetails;
	
	public PostScheduleTableVO(String postId, String budgetDetails, String todoDetails) {
		setBudgetDetails(budgetDetails);
		setTodoDetails(todoDetails);

	}

	public String getBudgetDetails() {
		return budgetDetails;
	}

	public void setBudgetDetails(String budgetDetails) {
		this.budgetDetails = budgetDetails;
	}

	public String getTodoDetails() {
		return todoDetails;
	}

	public void setTodoDetails(String todoDetails) {
		this.todoDetails = todoDetails;
	}
	
	
}
