package com.coortouch.app.wcf;

import java.lang.reflect.Type;
import java.util.List;

import org.json.JSONObject;

import android.content.Context;

import com.coortouch.app.constant.Constant;
import com.coortouch.app.entity.BnuzTSystem;
import com.coortouch.app.wcf.listener.GetPageListener;
import com.coortouch.maintainservice.activity.R;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class BnuzSystemService extends BaseService{
	
	private String SystemURL=Constant.BaseUrl+"index.php/System/index";
	
	public BnuzSystemService(Context context) {
		// TODO Auto-generated constructor stub
		
		super(context);
		
	}

	
	public void getSystemData(final GetPageListener<BnuzTSystem> listener) {
		
		isInterrupt=false;
		
		client=new AsyncHttpClient();
		RequestParams params=new RequestParams();
		
		client.get(SystemURL,params, new AsyncHttpResponseHandler(){
			
			@Override
			public void onSuccess(String content) {
				// TODO Auto-generated method stub
				super.onSuccess(content);
				try {
					
					JSONObject jsonArray = new JSONObject(content);
					
					if (jsonArray.getInt("code")==0) {
						Type type = new TypeToken<List<BnuzTSystem>>() {}.getType();
						
						List<BnuzTSystem> list = gson.fromJson(jsonArray.getJSONArray("result").toString(),type);
						
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

