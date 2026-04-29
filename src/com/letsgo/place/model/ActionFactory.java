package com.letsgo.place.model;

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
		default:
			tmp = new IndexUIAction();
		}

		return tmp;
	}

}
