package com.coortouch.app.wcf;

import java.lang.reflect.Type;
import java.util.List;

import org.json.JSONObject;

import android.content.Context;

import com.coortouch.app.constant.Constant;
import com.coortouch.app.entity.BnuzGuideCate;
import com.coortouch.app.entity.BnuzNewsCate;
import com.coortouch.app.wcf.listener.GetPageListener;
import com.coortouch.maintainservice.activity.R;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class BnuzCategoryService extends BaseService{
	
	private String GuideCateURL=Constant.BaseUrl+"index.php?m=Column&a=index&pid=1";

	private String NewsCateURL=Constant.BaseUrl+"index.php?m=Column&a=index&pid=2";
	
	public BnuzCategoryService(Context context) {
		// TODO Auto-generated constructor stub
		
		super(context);
		
	}

	
	public void getGuideCateData(final GetPageListener<BnuzGuideCate> listener) {
		
		isInterrupt=false;
		
		client=new AsyncHttpClient();
		RequestParams params=new RequestParams();
		
		client.get(GuideCateURL,params, new AsyncHttpResponseHandler(){
			
			@Override
			public void onSuccess(String content) {
				// TODO Auto-generated method stub
				super.onSuccess(content);
				try {
					
					JSONObject jsonArray = new JSONObject(content);
					
					if (jsonArray.getInt("code")==0) {
						Type type = new TypeToken<List<BnuzGuideCate>>() {}.getType();
						
						List<BnuzGuideCate> list = gson.fromJson(jsonArray.getJSONArray("result").toString(),type);
						
						if (listener!=null&&!isInterrupt) {
							listener.onFinish(list);
						}
					}

					

				}catch (Exception e) {
					// TODO: handle exception
				}
				
				
			}

			@Override
			public void onFailure(Throwable error, String content) {
				// TODO Auto-generated method stub
				super.onFailure(error, content);
				
				if (listener!=null&&context!=null) {
					listener.onFailed(context.getResources().getString(R.string.Online_Fail));
				}
				
			}
			
		});
		
	}
	
	
	public void getNewsCateData(final GetPageListener<BnuzNewsCate> listener) {
		
		isInterrupt=false;
		
		client=new AsyncHttpClient();
		RequestParams params=new RequestParams();
		
		client.get(NewsCateURL,params, new AsyncHttpResponseHandler(){
			
			@Override
			public void onSuccess(String content) {
				// TODO Auto-generated method stub
				super.onSuccess(content);
				
				try {
					JSONObject jsonArray = new JSONObject(content);
					
					if (jsonArray.getInt("code")==0) {
						Type type = new TypeToken<List<BnuzNewsCate>>() {}.getType();
						
						List<BnuzNewsCate> list = gson.fromJson(jsonArray.getJSONArray("result").toString(),type);
						
						
						if (listener!=null&&!isInterrupt) {
							listener.onFinish(list);
						}
					}

				}catch (Exception e) {
					// TODO: handle exception
				}
				
				
			}

			@Override
			public void onFailure(Throwable error, String content) {
				// TODO Auto-generated method stub
				super.onFailure(error, content);
				
				if (listener!=null&&context!=null) {
					listener.onFailed(context.getResources().getString(R.string.Online_Fail));
				}
				
			}
			
		});
		
	}
	
}

