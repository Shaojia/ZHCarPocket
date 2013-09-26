package com.coortouch.app.wcf.listener;

public interface PostRecordResponseListener {
	void onFinish(String succString);
	void onFailed(String failString);
}
