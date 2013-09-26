package com.coortouch.app.wcf.listener;

import java.util.List;


public interface GetPageListener<T> {
	void onFinish(List<T> data);
	void onFailed(String Message);
	
}
