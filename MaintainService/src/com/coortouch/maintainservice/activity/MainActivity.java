package com.coortouch.maintainservice.activity;



import com.coortouch.adnroid.helper.ScreenManager;
import com.coortouch.app.helper.AnimCommon;
import com.coortouch.maintainservice.activity.R;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
//import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;

public class MainActivity extends TabActivity{

	int index=-1;
	TabHost tabHost;

	/** Called when the activity is first created. */
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		ScreenManager.getScreenManager().pushActivity(this);
		
		index=getIntent().getExtras().getInt("SeletedIndex");
		
		setTabs();	
		
		
	}

    
	private void setTabs() {
		
		addTab("1", R.drawable.tab_home, Bnuz_GuideActivity.class);
		addTab("2", R.drawable.tab_second, Bnuz_NewsActivity.class);
		addTab("3", R.drawable.tab_third, Bnuz_MapActivity.class);
		addTab("4", R.drawable.tab_fourth, Bnuz_SystemActivity.class);


		addTab("5", R.drawable.tab_fifth, Bnuz_MoreActivity.class);
		
		tabHost = getTabHost();
		
		tabHost.setCurrentTab(index);
		
		
	}

	private void addTab(String labelId, int drawableId, Class<?> c) {

		TabHost tabHost = getTabHost();
		Intent intent = new Intent(this, c);
		
		TabHost.TabSpec spec = tabHost.newTabSpec("tab" + labelId);

		View tabIndicator = LayoutInflater.from(this).inflate(
				R.layout.tab_indicator, getTabWidget(), false);


		ImageView icon = (ImageView) tabIndicator.findViewById(R.id.icon);
		icon.setImageDrawable(getResources().getDrawable(drawableId));

		spec.setIndicator(tabIndicator);
		spec.setContent(intent);
		tabHost.addTab(spec);
	}
	
	int[] resId={R.drawable.tab_home,R.drawable.tab_second,R.drawable.tab_third,R.drawable.tab_fourth,R.drawable.tab_fifth};

	public void onRefresh() {
		// TODO Auto-generated method stub
		for (int i = 0; i < resId.length; i++) {
			int j = resId[i];
			ImageView view=(ImageView)tabHost.getTabWidget().getChildAt(i).findViewById(R.id.icon);
			view.setImageDrawable(getResources().getDrawable(j));
		}
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

	}
	
	


	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
		onRefresh();
	}
	
	
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
//		MobclickAgent.onPause(this);
		if (AnimCommon.in != 1 && AnimCommon.out != 1) {
			super.overridePendingTransition(AnimCommon.in, AnimCommon.out);
			AnimCommon.clear();
		}
		
		super.onPause();
		
	}   
	
	@Override
	public void finish() {
		// TODO Auto-generated method stub
		ScreenManager.getScreenManager().removeActivity(this);
		super.finish();
	}
	
	
}