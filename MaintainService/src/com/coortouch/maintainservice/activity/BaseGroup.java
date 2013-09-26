package com.coortouch.maintainservice.activity;


import com.coortouch.maintainservice.activity.R;
import com.coortouch.app.entity.TabStack;

import android.app.ActivityGroup;
import android.content.Intent;
import android.content.res.Resources.NotFoundException;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.ViewFlipper;
import android.widget.LinearLayout.LayoutParams;

public class BaseGroup extends ActivityGroup  {
	
	private static final String TAG = "BaseGroup";
	protected TabStack stack = new TabStack();
	protected ViewFlipper containerFlipper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	public void switchActivity(String id,Intent	intent,int inAnimation,int outAnimation){
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		Window window = getLocalActivityManager().startActivity(id, intent);
		View v = window.getDecorView();
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		LayoutParams param = new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT);
		v.setLayoutParams(param);
		try {
			containerFlipper.setInAnimation(AnimationUtils.loadAnimation(this,inAnimation));
			containerFlipper.setOutAnimation(AnimationUtils.loadAnimation(this,outAnimation));
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
//		printViewFlipper();
		
		containerFlipper.addView(v);
		containerFlipper.showNext();
		if(inAnimation == R.anim.push_right_in){
			containerFlipper.removeViewAt(stack.size());
		}
		stack.push(id);
		System.out.println("pust:" + id);
	}
	public void back(){
		if(stack.size() > 1){
			containerFlipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.push_right_in));
			containerFlipper.setOutAnimation(AnimationUtils.loadAnimation(this,R.anim.push_right_out));
			containerFlipper.showPrevious();
			containerFlipper.removeViewAt(stack.size() - 1);
			stack.pop();
		}else 
			this.finish();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		Log.i(TAG, "onKeyDown" + stack.toString());
		if(keyCode == KeyEvent.KEYCODE_BACK){
			if(!stack.isEmpty()){
				back();
			}else{
				finish();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	public void popSome(String id){
		int sum = stack.getTheSumToPop(id);
		containerFlipper.removeViews(stack.size() - sum, sum - 1);
		stack.popSome(id);
		containerFlipper.setDisplayedChild(containerFlipper.getChildCount() - 1);
	}
}
