package com.coortouch.app.wcf.listener;


public interface GetOneRecordListener<T> {
	void onFinish(T data);
	void onFailed(String Message);
}
