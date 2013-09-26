package com.coortouch.maintainservice.activity;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.android.api.JPushInterface;

import com.coortouch.adnroid.helper.ScreenManager;
import com.coortouch.app.activityenum.ActivityEnum;
import com.coortouch.app.adapter.Bnuz_TopCategoryAdapter;
import com.coortouch.app.helper.SharePreferenceHelper;
import com.coortouch.app.views.ui.LoadingPageView;
import com.coortouch.maintainservice.activity.R;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

public class Bnuz_TopCategoryActivity extends Activity implements OnClickListener{

	int count=0;
	GridView gridView;
	LoadingPageView loadingPageView;
	
	Bnuz_TopCategoryAdapter adapter;
	Button btnAdmin;
	
	View loadingView;
	
	boolean isLogin=false;

	/** Called when the activity is first created. */
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bnuz_topcategory);

		ScreenManager.getScreenManager().pushActivity(this);
		initView();
		
		loadingPageView=new LoadingPageView(this,(ViewGroup)findViewById(R.id.loadingPage));

		initalSettingData();
		
		JPushInterface.resumePush(getApplicationContext());  
		
	}
    
    private void initView(){
    	loadingView=findViewById(R.id.loadingPage);
    	gridView=(GridView)findViewById(R.id.grid);
    	btnAdmin=(Button)findViewById(R.id.btnAdmin);
    	btnAdmin.setOnClickListener(this);
    	
    	adapter = new Bnuz_TopCategoryAdapter(getApplicationContext(), new ArrayList<String>());
		gridView.setAdapter(adapter);

		gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						// TODO Auto-generated method stub
						Intent intent = new Intent();
						intent.setClass(Bnuz_TopCategoryActivity.this,
								MainActivity.class);
						intent.putExtra("SeletedIndex", position);
						startActivity(intent);
					}

				});
		
		initData();
    }
    
    private void initData(){
    	List<String> list=new ArrayList<String>();
    	for (String item : getResources().getStringArray(R.array.bnuz_topCate)) {
			list.add(item);
		}
    	adapter.setData(list);
   
    }

    
	private void initalSettingData() {
		
		if (!getIntent().getBooleanExtra("FromPush", false)) {
			new Handler().postDelayed(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub

					loadingPageView.setIsClicked(true);
					loadingPageView.dismiss();
				}
			},1000);
		}else {

			loadingPageView.setIsClicked(true);
			loadingPageView.dismiss();
		}
		
		
		
		
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (!isLogin) {
			if (SharePreferenceHelper.isLogin(this)) {
	    		btnAdmin.setBackgroundResource(R.drawable.bnuz_cate_admin);
			}else {
	    		btnAdmin.setBackgroundResource(R.drawable.bnuz_cate_login);
			}
		}
		
	}
	
	


	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
	}

	
	@Override
	public void finish() {
		// TODO Auto-generated method stub
		ScreenManager.getScreenManager().removeAllActivity();
		super.finish();
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		if (loadingPageView!=null) {
			loadingPageView.dismiss();
		}
		super.onDestroy();
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId()==R.id.btnAdmin) {
			Intent intent = new Intent();
			if (SharePreferenceHelper.isLogin(this)) {
				intent.setClass(getApplicationContext(), Bnuz_ModifyInfoActivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.activity_pushtotop,
						R.anim.activity_noanim);
			}else {
				intent.setClass(getApplicationContext(), Bnuz_LoginActivity.class);
				intent.putExtra("ActivityEnumValue", ActivityEnum.TopCateActivity.getValue());
				startActivityForResult(intent,1);
				overridePendingTransition(R.anim.activity_pushtotop,
						R.anim.activity_noanim);
			}
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		if (resultCode==1) {
			btnAdmin.setBackgroundResource(R.drawable.bnuz_cate_admin);
			isLogin=true;
		}
		
	}
	
}