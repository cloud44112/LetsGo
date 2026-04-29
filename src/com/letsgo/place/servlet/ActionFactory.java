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
		case "loginUI":
			tmp = new LoginUIAction();
			break;
		case "loginAction":
			tmp = new LoginAction();
			break;
		case "logoutAction":
			tmp = new LogoutAction();
			break;
		case "deletePostSchedule":
			tmp = new DeletePostScheduleAction();
			break;
		case "myScheduleRouteManageUI":
			tmp = new myScheduleRouteManageUIAction();
			break;
		case "myScheduleBudgetDetailUI":
			tmp = new myScheduleBudgetDetailUIAction();
			break;
			
		case "MyScheduleTodoListUI":
			tmp = new MyScheduleTodoListUIAction();
			break;
			
		case "myScheduleListUI":
			tmp = new myScheduleListUIAction();
			break;
		default:
			tmp = new IndexUIAction();
			break;
		}

		return tmp;
	}

}
