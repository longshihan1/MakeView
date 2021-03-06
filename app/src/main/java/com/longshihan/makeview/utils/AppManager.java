package com.longshihan.makeview.utils;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Process;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public final class AppManager {
	private static final String LOG_TAG = AppManager.class.getSimpleName();
	private static Stack<Activity> activityStack;
	private static Stack<Service> serviceStack;
	private static AppManager mInstance;

	public static synchronized AppManager getAppManager() {
		return mInstance == null ? (AppManager.mInstance = new AppManager())
				: mInstance;
	}

	public synchronized void addActivity(Activity activity) {
		if (activityStack == null) {
			activityStack = new Stack();
		}
		activityStack.add(activity);
	}

	public synchronized void addService(Service service) {
		if (serviceStack == null) {
			serviceStack = new Stack();
		}
		serviceStack.add(service);
	}

	public Activity currentActivity() {
		return (Activity) activityStack.lastElement();
	}

	public void finishActivity() {
		if(activityStack.isEmpty())
			return;
		Activity activity = (Activity) activityStack.lastElement();
		finishActivity(activity);
	}

	public void finishActivity(Activity activity) {
		if (activity != null) {
			activityStack.remove(activity);
			activity.finish();
			activity = null;
		}
	}

	public void finishActivity(Class<?> cls) {
		List<Activity> removeList = new ArrayList<Activity>();
		for (Activity activity : activityStack) {
			if (activity.getClass().equals(cls)) {
				removeList.add(activity);
			}
		}
		activityStack.removeAll(removeList);
		for (Activity activity : removeList)
			if (activity != null) {
				activity.finish();
				activity = null;
			}
	}

	public void finishWithOut(Activity withOutActivity) {
		List<Activity> removeList = new ArrayList<Activity>();
		for (Activity activity : activityStack) {
			if (activity != withOutActivity) {
				removeList.add(activity);
			}
		}
		activityStack.removeAll(removeList);
		for (Activity activity : removeList)
			if (activity != null) {
				activity.finish();
				activity = null;
			}
	}

	public void finishAllActivity() {
		if (activityStack == null) {
			return;
		}
		int i = 0;
		for (int size = activityStack.size(); i < size; i++) {
			if (activityStack.get(i) != null) {
				((Activity) activityStack.get(i)).finish();
			}
		}
		activityStack.clear();
	}

	public void finishAllService() {
		if (serviceStack == null)
			return;
		int i = 0;
		for (int size = serviceStack.size(); i < size; i++) {
			if (serviceStack.get(i) != null) {
				((Service) serviceStack.get(i)).stopSelf();
			}
		}
		serviceStack.clear();
	}


	/**
	 * 重新登录
	 */
	public void redirectLogin(Context context, Class clazz) {
		List<Activity> removeList = new ArrayList<Activity>();
		for (Activity activity : activityStack) {

			/*
			 * if (!(activity instanceof clazz.newInstance())) {
			 * removeList.add(activity); }
			 */
			if (!(clazz.isInstance(activity))) {
				removeList.add(activity);
			}
		}
		activityStack.removeAll(removeList);
		// 如果没有登录过
		if (activityStack.size() == 0) {
			context.startActivity(new Intent(context, clazz));
		}
		for (Activity activity : removeList)
			if (activity != null) {
				activity.finish();
				activity = null;
			}

	}

	public void AppExit(Context context) {
		try {
			finishAllActivity();
			finishAllService();
			Process.killProcess(Process.myPid());
			System.exit(0);
		} catch (Exception e) {
		}
	}
}