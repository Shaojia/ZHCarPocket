package com.coortouch.maintainservice.activity;

import java.util.ArrayList;
import java.util.List;

import com.coortouch.maintainservice.activity.R;
import com.coortouch.adnroid.helper.ScreenManager;
import com.coortouch.app.adapter.MoreAdapter;
import com.coortouch.app.entity.BnuzNewsCate;
import com.coortouch.app.entity.BnuzTGuide;
import com.coortouch.app.entity.BnuzTGuideDepartment;
import com.coortouch.app.entity.BnuzTNews;
import com.coortouch.app.entity.BnuzTSystem;
import com.coortouch.app.helper.AnimCommon;
import com.coortouch.app.helper.SharePreferenceHelper;
//import com.coortouch.app.helper.UpdateVersionHelper;



import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class Bnuz_MoreActivity extends BaseActivity {

	
	
	int selectIndex=0;
	
	AlertDialog updateDialog;
	ProgressDialog progressDialog;
	private ListView listView;
	MoreAdapter adapter;
	List<String> lists=new ArrayList<String>();
	Button btnExit;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.bnuz_more);

		ScreenManager.getScreenManager().removeActivity(this);
		initView();
	}
	
	private void initView(){
		Button btnHome=(Button)findViewById(R.id.btnBack);
		btnHome.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onBackPressed();
			}
		});
		listView=(ListView)findViewById(R.id.list_more);
		String[] moreArray=getResources().getStringArray(R.array.bnuz_morelist);
	
		for (String string : moreArray) {
			lists.add(string);
		}
		adapter=new MoreAdapter(getApplicationContext(), lists);
		
		
		listView.setAdapter(adapter);
		
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				
				if(!SharePreferenceHelper.isLogin(getApplicationContext())){
					position+=2;
				}
				
				switch (position) {
				case 0:
					
					
					intent.setClass(getApplicationContext(), Bnuz_ModifyInfoActivity.class);
					startActivity(intent);
					AnimCommon.set(R.anim.activity_pushtotop,
							R.anim.activity_noanim);
					
					break;
				case 1:
					
					logout();
					
					break;
				case 2:
					
					clearCache();
					
					break;
				case 3:
//					Uri uri = Uri.parse(entity.getWeibo());  
//				    startActivity(new Intent(Intent.ACTION_VIEW,uri));  
					

					intent.setClass(getApplicationContext(), Bnuz_More_HelperActivity.class);
					startActivity(intent);	
					
					break;
				case 4:
					intent.setClass(getApplicationContext(), Bnuz_More_AboutActivity.class);
					startActivity(intent);	
					break;
				default:
					break;
				}
			}
		});
		

	}
	
	private void clearCache(){
		
		Dialog dialog = new AlertDialog.Builder(Bnuz_MoreActivity.this)
		.setTitle("确定要清除缓存?")
		
		.setPositiveButton("确定",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog,
							int which) {
						// TODO Auto-generated method stub
						
						SharePreferenceHelper.saveCacheData(getApplicationContext(), SharePreferenceHelper.Cache_BnuzNews, new ArrayList<BnuzTNews>());
						SharePreferenceHelper.saveCacheData(getApplicationContext(), SharePreferenceHelper.Cache_BnuzSystem, new ArrayList<BnuzTSystem>());
						SharePreferenceHelper.saveCacheData(getApplicationContext(), SharePreferenceHelper.Cache_Guide, new ArrayList<BnuzTGuide>());
						SharePreferenceHelper.saveCacheData(getApplicationContext(), SharePreferenceHelper.Cache_NewsCate, new ArrayList<BnuzNewsCate>());
						SharePreferenceHelper.saveCacheData(getApplicationContext(), SharePreferenceHelper.Cache_GuideCate, new ArrayList<BnuzTNews>());
						SharePreferenceHelper.saveCacheData(getApplicationContext(), SharePreferenceHelper.Cache_GuideDepartment, new ArrayList<BnuzTGuideDepartment>());
					}
				})
		.setNegativeButton("取消",
				null).create();

		dialog.show();
		
	}
	
	
	private void logout(){
		Dialog dialog = new AlertDialog.Builder(Bnuz_MoreActivity.this)
		.setTitle("确定要退出当前账号吗?")
		
		.setPositiveButton("确定",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog,
							int which) {
						// TODO Auto-generated method stub
						SharePreferenceHelper.saveCustomerData(getApplicationContext(), null);
						Toast.makeText(getApplicationContext(), "退出当前账号成功", Toast.LENGTH_SHORT).show();
						
						adapter.notifyDataSetChanged();
					}
				})
		.setNegativeButton("取消",
				null).create();

		dialog.show();
		
	}


	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		
	}
}
