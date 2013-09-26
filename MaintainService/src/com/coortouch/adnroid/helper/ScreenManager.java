package com.coortouch.adnroid.helper;

import java.util.Stack;

import android.app.Activity;

public class ScreenManager {
	private static Stack<Activity> activityStack;
	private static ScreenManager instance;

	private ScreenManager() {
	}

	public static ScreenManager getScreenManager() {
		if (instance == null) {
			instance = new ScreenManager();
		}
		return instance;
	}
	
	public boolean hasActivity() {
		if (activityStack!=null&&activityStack.size()>0) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isTop() {
		if (activityStack==null||activityStack.size()==1) {
			return true;
		}else {
			return false;
		}
	}
	
	public void removeActivity(Activity activity) {
		if (activityStack!=null) {
			activityStack.remove(activity);
		}
		
	}
	
	public void removeAllActivity(){
		if (activityStack!=null) {
			activityStack.clear();
		}
		
	}

	public void popActivity() {
		Activity activity = activityStack.lastElement();
		if (activity != null) {
			activity.finish();
			activity = null;
		}
	}

	public void popActivity(Activity activity) {
		if (activity != null) {
			activity.finish();
			activityStack.remove(activity);
			activity = null;
		}
	}

	public Activity currentActivity() {
		Activity activity = activityStack.lastElement();
		return activity;
	}

	public void pushActivity(Activity activity) {
		if (activityStack == null) {
			activityStack = new Stack<Activity>();
		}
		activityStack.add(activity);
	}

	public void popAllActivityExceptOne(Class<?> cls) {
		while (true) {
			Activity activity = currentActivity();
			if (activity == null) {
				break;
			}
			if (activity.getClass().equals(cls)) {
				break;
			}
			popActivity(activity);
		}
	}
}
