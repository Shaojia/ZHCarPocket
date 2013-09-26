package com.coortouch.maintainservice.activity;

import cn.jpush.android.api.JPushInterface;
import android.app.Application;

public class BaseApplication extends Application{
	@Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.init(this);
    }
}
