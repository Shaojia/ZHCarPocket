package com.coortouch.maintainservice.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.coortouch.adnroid.helper.ScreenManager;
import com.coortouch.android.categoryview.SelectCateView;
import com.coortouch.android.listview.pulltorefershview.PullToRefershListView;
import com.coortouch.app.adapter.BnuzCateAdapter;
import com.coortouch.app.adapter.BnuzGuideAdapter;
import com.coortouch.app.adapter.BnuzGuideTelAdapter;
import com.coortouch.app.entity.*;
import com.coortouch.app.helper.SharePreferenceHelper;
import com.coortouch.app.helper.WIFIHelper;
import com.coortouch.app.wcf.BnuzCategoryService;
import com.coortouch.app.wcf.BnuzGuideService;
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

public class Bnuz_GuideActivity extends BaseActivity{
	
	BnuzCateAdapter cateAdapter;
	
	BnuzGuideAdapter adapter;
	BnuzGuideTelAdapter telAdapter;

	PullToRefershListView listView;
	
	int cateId=-1;    
	
	int pageIndex=1;
	
	int pageCount=10;
	

	BnuzGuideService wcfService;
	
	HashMap<String, List<BnuzTGuide>> hashMap;
	SelectCateView cateView;
	Button btnSearch;
	EditText txtSearch;
	
	InputMethodManager inputMethodManager;
	
	String firstKey="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.bnuz_guide);
		

		ScreenManager.getScreenManager().removeActivity(this);
		
		wcfService=new BnuzGuideService(this);
		
		hashMap=new HashMap<String, List<BnuzTGuide>>();
		
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
					Bnuz_GuideActivity.this
					.getCurrentFocus()
					.getWindowToken(),
					InputMethodManager.HIDE_NOT_ALWAYS);
					txtSearch.setVisibility(View.INVISIBLE);
					
					telAdapter.setData(hashMap.get("K"+cateId));
				}
			}
		});
		
		txtSearch.setOnKeyListener(new View.OnKeyListener() {
			
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// TODO Auto-generated method stub
				
				if (keyCode==KeyEvent.KEYCODE_ENTER) {
					
					List<BnuzTGuide> searchList=new ArrayList<BnuzTGuide>();
					
					for (String key : hashMap.keySet()) {
						if (key.equals(firstKey)) {
							continue;
						}
						for (BnuzTGuide item : hashMap.get(key)) {
							if(item.getTitle().contains(txtSearch.getText().toString())){
								searchList.add(item);
							}
						}
					}
					
					telAdapter.setData(searchList);
					
					listView.setAdapter(telAdapter);
				}
				
				return false;
			}
		});
		
	}
	
	private void setCategoryData(){
		
		
		cateAdapter=new BnuzCateAdapter(this, new ArrayList<BnuzGuideCate>());
		
		cateView.setAdapter(cateAdapter);
		
		cateView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				cateId=cateAdapter.getData().get(arg2).getId();
				cateAdapter.setSelectedIndex(arg2);
				
				adapter.setData(new ArrayList<BnuzTGuide>());
				
				getData();
				
				if (arg2==0) {
					btnSearch.setVisibility(View.INVISIBLE);
				}else {
					btnSearch.setVisibility(View.VISIBLE);
				}
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		BnuzCategoryService categoryWCF=new BnuzCategoryService(this);
		
		categoryWCF.getGuideCateData(new GetPageListener<BnuzGuideCate>() {
			
			@Override
			public void onFailed(String Message) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), Message, Toast.LENGTH_SHORT).show();
				
				cateAdapter.setData(SharePreferenceHelper.getGuideCateCacheData(getApplicationContext()));
				

				setListData();
			}

			@Override
			public void onFinish(List<BnuzGuideCate> data) {
				// TODO Auto-generated method stub
				if (data.size()>0) {
					firstKey="K"+data.get(0).getId();
				}
				cateAdapter.setData(data);
				
				SharePreferenceHelper.saveCacheData(getApplicationContext(), SharePreferenceHelper.Cache_GuideCate, data);

				setListData();
			}
		});
		
	}
	
	
	private void setListData() {
		
		
		listView=(PullToRefershListView)findViewById(R.id.list);
		
		adapter=new BnuzGuideAdapter(this, new ArrayList<BnuzTGuide>());
		telAdapter=new BnuzGuideTelAdapter(this,  new ArrayList<BnuzTGuide>());
        
		listView.setAdapter(adapter);
		
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				
					if (arg2>=1) {
						Intent intent=new Intent();
						if (cateView.getSelectedItemPosition()==0) {
							intent.putExtra("Guide", adapter.getData().get(--arg2));
							intent.setClass(getApplicationContext(), Bnuz_GuideDetailActivity.class);
						}else {
							intent.putExtra("Guide", telAdapter.getData().get(--arg2));
							intent.setClass(getApplicationContext(), Bnuz_GuideDetail2Activity.class);
						}
						
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
			if (cateView.getSelectedItemPosition()==0) {
				adapter.setData(hashMap.get("K"+cateId));
				listView.setAdapter(adapter);
			}else {

				telAdapter.setData(hashMap.get("K"+cateId));
				listView.setAdapter(telAdapter);
			}
			listView.onRefreshComplete();
		}else {
			listView.initLoading();
			pullToRefersh(true);
		}
		
	}
	
	
	private void getCacheData(){
		
		List<BnuzTGuide> list=SharePreferenceHelper.getBnuzGuideCacheData(getApplicationContext(),  cateId);
		
		adapter.setData(list);
		
	}
	
	private void pullToRefersh(final boolean isPush){
		
		if (WIFIHelper.checkWIFI(getApplicationContext())==2) {
			getCacheData();
			listView.onRefreshComplete();
			
		}else {
			wcfService.getGuidePage(cateId, new GetPageListener<BnuzTGuide>(){
				
				@Override
				public void onFinish(List<BnuzTGuide> data) {
					// TODO Auto-generated method stub

					listView.onRefreshComplete();
					
					hashMap.put("K"+cateId, data);
					

					if (cateView.getSelectedItemPosition()==0) {
						adapter.setData(data);
						listView.setAdapter(adapter);
					}else {

						telAdapter.setData(data);
						listView.setAdapter(telAdapter);
					}
					

					SharePreferenceHelper.saveCacheData(getApplicationContext(),  SharePreferenceHelper.Cache_Guide+cateId, data);
					
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
