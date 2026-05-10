package com.letsgo.place.model;

public class CopyToMyScheduleParam {
    private String title;
    private String budgetDetail;
    private String todoDetail;
    private String userId;
    private String generatedId;  // selectKey 주입 대상 - setter 필수

    public CopyToMyScheduleParam(String title, String budgetDetail, String todoDetail, String userId) {
        this.title        = title;
        this.budgetDetail = budgetDetail;
        this.todoDetail   = todoDetail;
        this.userId       = userId;
    }

    public String getTitle(){
    	return title; 
    }
    public String getBudgetDetail(){ 
    	return budgetDetail; 
    }
    public String getTodoDetail(){ 
    	return todoDetail;
    }
    public String getUserId(){ 
    	return userId;
    }
    public String getGeneratedId(){ 
    	return generatedId; 
    }

    // MyBatis selectKey 가 keyProperty="generatedId" 로 이 setter를 호출
    public void setGeneratedId(String generatedId) {
        this.generatedId = generatedId;
    }
}
