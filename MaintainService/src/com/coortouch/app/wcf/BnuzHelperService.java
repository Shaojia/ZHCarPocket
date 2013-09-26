package com.coortouch.app.wcf;

import org.json.JSONObject;

import android.content.Context;

import com.coortouch.app.constant.Constant;
import com.coortouch.app.entity.BnuzTHelper;
import com.coortouch.app.wcf.listener.GetOneRecordListener;
import com.coortouch.maintainservice.activity.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class BnuzHelperService extends BaseService{
	
	private String HelperURL=Constant.BaseUrl+"index.php?m=Article&a=help";
	
	private String AboutURL=Constant.BaseUrl+"index.php?m=Article&a=about";
	
	
	public BnuzHelperService(Context context) {
		// TODO Auto-generated constructor stub
		super(context);
		
	}
	public void getHelper(final GetOneRecordListener<BnuzTHelper> listener) {
		

		isInterrupt=false;
		
		client = new AsyncHttpClient();

		client.post(HelperURL,new AsyncHttpResponseHandler(){

			@Override
			public void onSuccess(String content) {
				// TODO Auto-generated method stub
				super.onSuccess(content);
				
				try {
					JSONObject jsonArray = new JSONObject(content);
					
					if (jsonArray.getInt("code")==0) {
						
						BnuzTHelper entity=gson.fromJson(jsonArray.getJSONObject("result").toString(), BnuzTHelper.class);
						
						if (listener!=null&&!isInterrupt) {
							
							listener.onFinish(entity);
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
				
				if (listener!=null) {
					listener.onFailed(context.getResources().getString(R.string.Online_Fail));
				}
				
			}
			
		});
		
	}
	

	public void getAbout(final GetOneRecordListener<BnuzTHelper> listener) {
		

		isInterrupt=false;
		
		client = new AsyncHttpClient();

		client.post(AboutURL,new AsyncHttpResponseHandler(){

			@Override
			public void onSuccess(String content) {
				// TODO Auto-generated method stub
				super.onSuccess(content);
				
				try {
					JSONObject jsonArray = new JSONObject(content);
					
					if (jsonArray.getInt("code")==0) {
						
						BnuzTHelper entity=gson.fromJson(jsonArray.getJSONObject("result").toString(), BnuzTHelper.class);
						
						if (listener!=null&&!isInterrupt) {
							
							listener.onFinish(entity);
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
				
				if (listener!=null) {
					listener.onFailed(context.getResources().getString(R.string.Online_Fail));
				}
				
			}
			
		});
		
	}
}

