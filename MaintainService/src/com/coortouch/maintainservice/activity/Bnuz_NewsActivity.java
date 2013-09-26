package com.coortouch.maintainservice.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.coortouch.adnroid.helper.ScreenManager;
import com.coortouch.android.categoryview.SelectCateView;
import com.coortouch.android.listview.pulltorefershview.PullToRefershListView;
import com.coortouch.app.adapter.BnuzNewsAdapter;
import com.coortouch.app.adapter.BnuzNewsCateAdapter;
import com.coortouch.app.entity.*;
import com.coortouch.app.helper.SharePreferenceHelper;
import com.coortouch.app.helper.WIFIHelper;
import com.coortouch.app.wcf.BnuzCategoryService;
import com.coortouch.app.wcf.BnuzNewsService;
import com.coortouch.app.wcf.listener.GetPageListener;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Bnuz_NewsActivity extends BaseActivity{
	

	BnuzNewsCateAdapter cateAdapter;
	
	BnuzNewsAdapter adapter;

	PullToRefershListView listView;
	
	int cateId=-1;    
	
	int pageIndex=1;
	
	int pageCount=10;
	

	BnuzNewsService wcfService;
	
	HashMap<String, List<BnuzTNews>> hashMap;
	SelectCateView cateView;
	Button btnSearch;
	EditText txtSearch;
	
	InputMethodManager inputMethodManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		ScreenManager.getScreenManager().removeActivity(this);
		
		setContentView(R.layout.bnuz_news);
		
		wcfService=new BnuzNewsService(this);
		
		hashMap=new HashMap<String, List<BnuzTNews>>();
		
		initalView();
		
		setCategoryData();
		
		
		
	}
	
	private void initalView(){
		
		inputMethodManager=((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE));
		
		cateView=(SelectCateView)findViewById(R.id.selectCategory);
		txtSearch=(EditText)findViewById(R.id.txtSearch);
		Button btnHome=(Button)findViewById(R.id.btnBack);
		btnHome.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onBackPressed();
			}
		});
		
		btnSearch =(Button)findViewById(R.id.btnSearch);
		
		btnSearch.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (txtSearch.getVisibility()==View.INVISIBLE) {
					txtSearch.setText("");
					txtSearch.setVisibility(View.VISIBLE);
					txtSearch.requestFocus();
					cateView.setVisibility(View.INVISIBLE);
					
					inputMethodManager.showSoftInput(txtSearch, 0);
				}else {
					txtSearch.clearFocus();
					cateView.setVisibility(View.VISIBLE);
					inputMethodManager.hideSoftInputFromWindow(
					Bnuz_NewsActivity.this
					.getCurrentFocus()
					.getWindowToken(),
					InputMethodManager.HIDE_NOT_ALWAYS);
					txtSearch.setVisibility(View.INVISIBLE);
					

					adapter.setData(hashMap.get("K"+cateId));
				}
			}
		});

		
		txtSearch.setOnKeyListener(new View.OnKeyListener() {
			
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// TODO Auto-generated method stub
				
				if (keyCode==KeyEvent.KEYCODE_ENTER) {
					
					List<BnuzTNews> searchList=new ArrayList<BnuzTNews>();
					
					for (String key : hashMap.keySet()) {
						for (BnuzTNews item : hashMap.get(key)) {
							if(item.getTitle().contains(txtSearch.getText().toString())){
								searchList.add(item);
							}
						}
					}
					
					adapter.setData(searchList);
				}
				
				return false;
			}
		});
	}
	
	private void setCategoryData(){
		
		cateAdapter=new BnuzNewsCateAdapter(getApplicationContext(), new ArrayList<BnuzNewsCate>());
		
		cateView.setAdapter(cateAdapter);
		
		cateView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				cateId=cateAdapter.getData().get(arg2).getId();
				cateAdapter.setSelectedIndex(arg2);
				
				adapter.setData(new ArrayList<BnuzTNews>());
				
				getData();
				
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		BnuzCategoryService categoryWCF=new BnuzCategoryService(this);
		
		categoryWCF.getNewsCateData(new GetPageListener<BnuzNewsCate>() {
			
			@Override
			public void onFailed(String Message) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), Message, Toast.LENGTH_SHORT).show();
				
				cateAdapter.setData(SharePreferenceHelper.getNewsCateCacheData(getApplicationContext()));
				

				setListData();
			}

			@Override
			public void onFinish(List<BnuzNewsCate> data) {
				// TODO Auto-generated method stub
				
				cateAdapter.setData(data);
				
				SharePreferenceHelper.saveCacheData(getApplicationContext(), SharePreferenceHelper.Cache_NewsCate, data);

				setListData();
			}
		});
		
	}
	
	
	private void setListData() {
		
		
		listView=(PullToRefershListView)findViewById(R.id.list);
		
		adapter=new BnuzNewsAdapter(getApplicationContext(), new ArrayList<BnuzTNews>());
		
        
		listView.setAdapter(adapter);
		
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				
					if (arg2>=1) {
						Intent intent=new Intent();
						intent.putExtra("News", adapter.getData().get(--arg2));
						intent.setClass(getApplicationContext(), Bnuz_NewsDetailActivity.class);
						startActivity(intent);
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
	}
	
	
	private void getData(){
		if (hashMap.containsKey("K"+cateId)) {
			adapter.setData(hashMap.get("K"+cateId));
			listView.setAdapter(adapter);
			
			listView.onRefreshComplete();
		}else {
			listView.initLoading();
			pullToRefersh(true);
		}
		
	}
	
	private void getCacheData(){
		
		List<BnuzTNews> list=SharePreferenceHelper.getBnuzNewsCacheData(getApplicationContext(),  cateId);
		
		adapter.setData(list);
		
	}
	
	private void pullToRefersh(final boolean isPush){
		
		if (WIFIHelper.checkWIFI(getApplicationContext())==2) {
			getCacheData();
			listView.onRefreshComplete();
			
		}else {
			wcfService.getNewsPage(cateId, new GetPageListener<BnuzTNews>(){
				
				@Override
				public void onFinish(List<BnuzTNews> data) {
					// TODO Auto-generated method stub

					listView.onRefreshComplete();
					
					hashMap.put("K"+cateId, data);
					

					adapter.setData(data);
					listView.setAdapter(adapter);
					
					

					SharePreferenceHelper.saveCacheData(getApplicationContext(),  SharePreferenceHelper.Cache_BnuzNews+cateId, data);
					
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
