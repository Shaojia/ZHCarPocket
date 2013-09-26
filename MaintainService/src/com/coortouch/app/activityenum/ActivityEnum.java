package com.coortouch.app.activityenum;


//登录成功后需要跳转到对应的Activity
public enum ActivityEnum {
	//TopCate
	TopCateActivity(1),
	//More
	MoreActivity(2),
	
	//预订
	ProductActivity(3),
	//个人中心
	AccountActivity(4),
	//活动详细
	TactivityDetailActivity(5);
	
	private int value;
	private  ActivityEnum(int value){
		this.value=value;
	}
	
	public int getValue(){
		return this.value;
	}
}
