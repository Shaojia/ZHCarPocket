package com.coortouch.android.views.ui;

import android.content.Context;
import android.view.View;

public abstract class BaseView{
	public View view;
	public BaseView(Context context) {
		// TODO Auto-generated constructor stub
	}
	
	public void setOnClickListener(View.OnClickListener listener) {
		view.setOnClickListener(listener);
	}
	
	public View getView() {
		return view;
	}
	
	public abstract void setData(Object data);
	public abstract void destroyViews();
	
	
	
}
