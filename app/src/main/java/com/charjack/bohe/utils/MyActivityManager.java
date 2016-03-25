package com.charjack.bohe.utils;


import android.app.Activity;
import android.content.Context;

import java.util.Iterator;
import java.util.Stack;

/**
 * @说 明：activity堆栈式管理
 * @项目名称：com.chajack.bohe
 * @类 名：AppManager
 * @创 建人：charjack
 * @创建时间：2015-09-24 17 : 00
 * @版 本：v1.0
 * @修 改人：
 * @修改时间：
 * @修改备注：
 */

public class MyActivityManager {
    private static Stack<Activity> activityStack;
    private static MyActivityManager instance;

    private MyActivityManager() {
    }

    public static Stack<Activity> getActivityStack() {
        return activityStack;
    }

    /**
     * 单一实例
     */
    public static MyActivityManager getActivityManager() {
        if (instance == null) {
            instance = new MyActivityManager();
        }
        return instance;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack();
        }
        activityStack.add(activity);
        if (activityStack.size() > 10) {  //当打开层数大于10的时候，将先前打开的关闭
            Activity a = activityStack.get(1);
            if (a != null && !a.isFinishing()) {
                a.finish();
                a = null;
            }
        }
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void removeActivity(Activity activity) {
        activityStack.remove(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null && !activity.isFinishing()) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类名的单个Activity
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
                break;
            }
        }
    }

    /**
     * 结束指定类名的所有Activity
     */
    public void finishActivitys(Class<?> cls) {
        Iterator<Activity> iterator = activityStack.iterator();
        while (iterator.hasNext()) {
            Activity activity = iterator.next();
            if (activity.getClass().equals(cls)) {
                iterator.remove();
                activity.finish();
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                finishActivity(activityStack.get(i));
                break;
            }
        }
        activityStack.clear();
    }

    /**
     * 获取指定的Activity
     */
    public Activity getActivity(Class<?> cls) {
        if (activityStack != null)
            for (Activity activity : activityStack) {
                if (activity.getClass().equals(cls)) {
                    return activity;
                }
            }
        return null;
    }

    /**
     * 退出应用程序
     */
    public void appExit(Context context) {
        try {
            finishAllActivity();
            // 杀死该应用进程
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        } catch (Exception e) {
        }
    }
}