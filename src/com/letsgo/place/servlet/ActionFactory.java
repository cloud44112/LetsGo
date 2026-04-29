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
		case "deletePostSchedule":
			tmp = new DeletePostScheduleAction();
			break;
		case "myScheduleRouteManageUI":
			tmp = new myScheduleRouteManageUIAction();
			break;
		default:
			tmp = new IndexUIAction();
			break;
		}

		return tmp;
	}

}
