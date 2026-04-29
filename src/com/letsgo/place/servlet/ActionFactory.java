package com.letsgo.place.servlet;

public class ActionFactory {

	public static Action getAction(String cmd) {
		Action tmp = null;
		
		switch (cmd) {
		case "deleteSchedule":
			tmp = new DeleteScheduleAction();
			break;
		default:
			tmp = new IndexUIAction();
		}
		
		
		
		
		return tmp;
	}

}
