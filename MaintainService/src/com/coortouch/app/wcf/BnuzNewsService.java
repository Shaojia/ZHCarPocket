package com.coortouch.app.wcf;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import android.content.Context;

import com.coortouch.app.constant.Constant;
import com.coortouch.app.entity.BnuzTNews;
import com.coortouch.app.wcf.listener.GetOneRecordListener;
import com.coortouch.app.wcf.listener.GetPageListener;
import com.coortouch.maintainservice.activity.R;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class BnuzNewsService extends BaseService{
	
	private String NewsURL=Constant.BaseUrl+"index.php?m=Article&a=index&pid=";
	
	private String FecthNewsURL=Constant.BaseUrl+"index.php?m=Article&a=read&pid=%s&id=%s";
	
	
	public BnuzNewsService(Context context) {
		// TODO Auto-generated constructor stub
		super(context);
		
	}
	public void getNewsPage(int cateId,final GetPageListener<BnuzTNews> listener) {

		isInterrupt=false;
		
		client = new AsyncHttpClient();
		
		RequestParams params = new RequestParams();

		client.post(NewsURL+cateId, params,new AsyncHttpResponseHandler(){

			@Override
			public void onSuccess(String content) {
				// TODO Auto-generated method stub
				super.onSuccess(content);
				try {
					
					JSONObject jsonArray = new JSONObject(content);
					
					if (jsonArray.getInt("code")==0) {
						Type type = new TypeToken<List<BnuzTNews>>() {
						}.getType();
						
						
						List<BnuzTNews> list = gson.fromJson(jsonArray.getJSONArray("result").toString(),type);
						
						if (listener!=null&&!isInterrupt) {
							listener.onFinish(list);
						}
					}else {
						if (listener!=null&&!isInterrupt) {
							listener.onFinish(new ArrayList<BnuzTNews>());
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
	
	public void getNewsID(String cateId,String id,final GetOneRecordListener<BnuzTNews> listener) {
		

		isInterrupt=false;
		
		client = new AsyncHttpClient();

		client.post(String.format(FecthNewsURL,cateId,id),new AsyncHttpResponseHandler(){

			@Override
			public void onSuccess(String content) {
				// TODO Auto-generated method stub
				super.onSuccess(content);
				try {
					JSONObject jsonArray = new JSONObject(content);
					
					if (jsonArray.getInt("code")==0) {

						BnuzTNews entity=gson.fromJson(jsonArray.getJSONObject("result").toString(), BnuzTNews.class);

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

