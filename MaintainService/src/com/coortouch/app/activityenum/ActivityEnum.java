package com.coortouch.app.activityenum;


//��¼�ɹ�����Ҫ��ת����Ӧ��Activity
public enum ActivityEnum {
	//TopCate
	TopCateActivity(1),
	//More
	MoreActivity(2),
	
	//Ԥ��
	ProductActivity(3),
	//��������
	AccountActivity(4),
	//���ϸ
	TactivityDetailActivity(5);
	
	private int value;
	private  ActivityEnum(int value){
		this.value=value;
	}
	
	public int getValue(){
		return this.value;
	}
}
