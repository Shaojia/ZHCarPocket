package com.coortouch.app.wcf;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import android.content.Context;

import com.coortouch.app.constant.Constant;
import com.coortouch.app.entity.BnuzTGuide;
import com.coortouch.app.wcf.listener.GetOneRecordListener;
import com.coortouch.app.wcf.listener.GetPageListener;
import com.coortouch.maintainservice.activity.R;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class BnuzGuideService extends BaseService{
	
	private String GuideURL=Constant.BaseUrl+"index.php?m=Article&a=index&pid=";
	
	private String FecthGuideURL=Constant.BaseUrl+"index.php?m=Article&a=read&pid=%s&id=%s";
	
	
	public BnuzGuideService(Context context) {
		// TODO Auto-generated constructor stub
		super(context);
		
	}
	public void getGuidePage(int cateId,final GetPageListener<BnuzTGuide> listener) {

		isInterrupt=false;
		
		client = new AsyncHttpClient();
		
		RequestParams params = new RequestParams();

		client.post(GuideURL+cateId, params,new AsyncHttpResponseHandler(){

			@Override
			public void onSuccess(String content) {
				// TODO Auto-generated method stub
				super.onSuccess(content);
				try {
					
					JSONObject jsonArray = new JSONObject(content);
					
					if (jsonArray.getInt("code")==0) {
						Type type = new TypeToken<List<BnuzTGuide>>() {
						}.getType();
						
						
						List<BnuzTGuide> list = gson.fromJson(jsonArray.getJSONArray("result").toString(),type);
						
						if (listener!=null&&!isInterrupt) {
							listener.onFinish(list);
						}
					}else {
						if (listener!=null&&!isInterrupt) {
							listener.onFinish(new ArrayList<BnuzTGuide>());
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
	
	public void getGuideID(int cateId,int id,final GetOneRecordListener<BnuzTGuide> listener) {
		

		isInterrupt=false;
		
		client = new AsyncHttpClient();

		client.post(String.format(FecthGuideURL,""+cateId,""+id),new AsyncHttpResponseHandler(){

			@Override
			public void onSuccess(String content) {
				// TODO Auto-generated method stub
				super.onSuccess(content);
				
				try {
					JSONObject jsonArray = new JSONObject(content);
					
					if (jsonArray.getInt("code")==0) {
						
						BnuzTGuide entity=gson.fromJson(jsonArray.getJSONObject("result").toString(), BnuzTGuide.class);
						
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

