package com.coortouch.maintainservice.activity;

import java.util.ArrayList;
import java.util.List;

import com.coortouch.adnroid.helper.ScreenManager;
import com.coortouch.android.listview.pulltorefershview.PullToRefershListView;
import com.coortouch.app.adapter.BnuzSystemAdapter;
import com.coortouch.app.entity.*;
import com.coortouch.app.helper.SharePreferenceHelper;
import com.coortouch.app.helper.WIFIHelper;
import com.coortouch.app.wcf.BnuzSystemService;
import com.coortouch.app.wcf.listener.GetPageListener;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

public class Bnuz_SystemActivity extends BaseActivity{
	
	BnuzSystemAdapter adapter;

	PullToRefershListView listView; 
	
	int pageIndex=1;
	
	int pageCount=10;
	

	BnuzSystemService wcfService;
	
	List<BnuzTSystem> lists;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		ScreenManager.getScreenManager().removeActivity(this);
		
		setContentView(R.layout.bnuz_system);
		
		wcfService=new BnuzSystemService(this);
		
		lists=new ArrayList<BnuzTSystem>();
		
		initView();
	}
	
	
	
	
	private void initView() {
		Button btnHome=(Button)findViewById(R.id.btnBack);
		btnHome.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onBackPressed();
			}
		});
		
		listView=(PullToRefershListView)findViewById(R.id.list);
		
		adapter=new BnuzSystemAdapter(getApplicationContext(), new ArrayList<BnuzTSystem>());
		
        
		listView.setAdapter(adapter);
		
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				
					if (arg2>=1) {
						BnuzTSystem entity=adapter.getData().get(--arg2);
						Uri uri = Uri.parse(entity.getUrl());  
					    startActivity(new Intent(Intent.ACTION_VIEW,uri));  
					}
				
			}
		});
		
		listView.setonRefreshListener(new PullToRefershListView.OnRefreshListener() {
			
			@Override
			public void onRefresh() {
				// TODO Auto-generated method stub
				
				pageIndex=1;
				
				pullToRefersh(true);
			}
		});
		
		getData();
	}
	
	
	private void getData(){
		
		pullToRefersh(true);
		
	}
	
	private void getCacheData(){
		
		List<BnuzTSystem> list=SharePreferenceHelper.getBnuzSystemCacheData(getApplicationContext());
		
		adapter.setData(list);
		
	}
	
	private void pullToRefersh(final boolean isPush){
		
		if (WIFIHelper.checkWIFI(getApplicationContext())==2) {
			getCacheData();
			listView.onRefreshComplete();
			
		}else {
			wcfService.getSystemData(new GetPageListener<BnuzTSystem>(){
				
				@Override
				public void onFinish(List<BnuzTSystem> data) {
					// TODO Auto-generated method stub

					listView.onRefreshComplete();
					

					adapter.setData(data);
					listView.setAdapter(adapter);
					
					

					SharePreferenceHelper.saveCacheData(getApplicationContext(),  SharePreferenceHelper.Cache_BnuzSystem, data);
					
				}
				
				@Override
				public void onFailed(String Message) {
					// TODO Auto-generated method stub
					Toast.makeText(getApplicationContext(), Message, Toast.LENGTH_SHORT).show();
					pageIndex--;
					
					if (isPush) {
						listView.onRefreshComplete();
					}
					
				}
			});
		}
		
		
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
}
