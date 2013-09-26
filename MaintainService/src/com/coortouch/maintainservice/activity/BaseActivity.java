package com.coortouch.maintainservice.activity;


import com.coortouch.adnroid.helper.ScreenManager;


import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;

public abstract class BaseActivity extends Activity {
	
//	private String language="";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		

		ScreenManager.getScreenManager().pushActivity(this);
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	    
//	    if (!LanguageHelper.checkLanguage(this, language)) {
//	    	language=LanguageHelper.languaneString;
//			reloadData();
//		}

	}
	
	@Override
	public void finish() {
		// TODO Auto-generated method stub
		ScreenManager.getScreenManager().removeActivity(this);
		super.finish();
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
	}
	
//	abstract void reloadData();
	
	@Override
	public void onPause() {
	    super.onPause();
	}
	
}
