package com.coortouch.android.categoryview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Gallery;

public class SelectCateView extends Gallery{

	public SelectCateView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		setSpacing(35);
//		setUnselectedAlpha((float) 0.2);
		
		
	}
	public boolean onFling (MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
	
		return false;
		
	}

}
