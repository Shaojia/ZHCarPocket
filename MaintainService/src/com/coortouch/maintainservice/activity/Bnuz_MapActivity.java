package com.coortouch.maintainservice.activity;


import com.coortouch.adnroid.helper.ScreenManager;
import com.coortouch.maintainservice.activity.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Bnuz_MapActivity extends BaseActivity implements OnClickListener {

	
	Button btnBack;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		ScreenManager.getScreenManager().removeActivity(this);
		setContentView(R.layout.bnuz_map);

		initView();
	}
	
	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		super.onNewIntent(intent);
		
	}

	private void initView() {
		
		btnBack = (Button) findViewById(R.id.btnBack);
		
		btnBack.setOnClickListener(this);
		
		
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnBack:
		
			onBackPressed();
			break;
		
		default:
			break;
		}
	}
	
	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
	}
	
}
