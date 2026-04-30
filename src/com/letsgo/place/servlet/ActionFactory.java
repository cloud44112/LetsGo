package com.letsgo.place.servlet;

public class ActionFactory {
    public static Action getAction(String cmd) {
        Action tmp = null;
        if (cmd == null) {
            return new IndexUIAction();
        }
        switch (cmd) {
        case "deleteSchedule":
            tmp = new DeleteScheduleAction();
            break;
        case "updateScheduleTitle":
            tmp = new UpdateScheduleTitleAction();
            break;
        case "deleteVisitItem":
            tmp = new DeleteVisitItemAction();
            break;
        case "addVisitItem":
            tmp = new AddVisitItemAction();
            break;
        case "updateRouteOrder":
            tmp = new UpdateRouteOrderAction();
            break;
        case "loginUI":
            tmp = new LoginUIAction();
            break;
        case "loginAction":
            tmp = new LoginAction();
            break;
        case "logoutAction":
            tmp = new LogoutAction();
            break;
        case "signupUI":
            tmp = new SignupUIAction();
            break;
        case "signupAction":
            tmp = new SignupAction();
            break;
        case "deletePostSchedule":
            tmp = new DeletePostScheduleAction();
            break;
        case "myScheduleRouteManageUI":
            tmp = new myScheduleRouteManageUIAction();
            break;
        case "myScheduleBudgetDetailUI":
            tmp = new MyScheduleBudgetDetailUIAction();
            break;
        case "myScheduleBudgetDetailAction":
            tmp = new MyScheduleBudgetDetailAction();
            break;
        case "MyScheduleTodoListUI":
            tmp = new MyScheduleTodoListUIAction();
            break;
        case "myScheduleTodoListAction":
            tmp = new MyScheduleTodoListAction();
            break;
        case "myScheduleListUI":
            tmp = new MyScheduleListUIAction();
            break;
        case "updatepwUI":
            tmp = new UpdatepwUIAction();
            break;
        case "updatepwAction":
            tmp = new UpdatepwAction();
            break;
        case "LeisureUI":
            tmp = new LeisureUIAction();
            break;
        case "restaurantUI":
            tmp = new RestaurantUIAction();
            break;
        case "stayUI":
            tmp = new StayUIAction();
            break;
        case "getIdUI":
            tmp = new GetIdUIAction();
            break;
        case "getIdAction":
            tmp = new GetIdAction();
            break;
        case "postScheduleMyListUI":
            tmp = new postScheduleMyListUIAction();
            break;
        case "postScheduleRouteManageUI":
            tmp = new postScheduleRouteManageUIAction();
            break;
        case "postScheduleBudgetDetailUI":
            tmp = new postScheduleBudgetDetailUIAction();
            break;
        case "postScheduleTodoListUI":
            tmp = new postScheduleTodoListUIAction();
            break;
        case "postScheduleListUI":
            tmp = new postScheduleListUIAction();
            break;
        default:
            tmp = new IndexUIAction();
            break;
        }
        return tmp;
    }
}