package com.coortouch.app.wcf;

import android.content.Context;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;

public class BaseService {
	public Context context;

	public AsyncHttpClient client;

	public final Gson gson = new Gson();
	
	boolean isInterrupt=false;
	
	public BaseService(Context context) {
		// TODO Auto-generated constructor stub
		this.context=context;
	}
	public void cancel() {
		
		if (client!=null) {
			client.cancelRequests(context, true);
			isInterrupt=true;
		}
		
		client=null;
	}
	
	public void destroy() {
		if (client!=null) {
			client.cancelRequests(context, true);
		}
		context=null;
		
		client=null;
	}
	
}
