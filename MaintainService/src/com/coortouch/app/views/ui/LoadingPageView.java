package com.coortouch.app.views.ui;

import com.coortouch.maintainservice.activity.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

public class LoadingPageView {
	private ViewGroup loadingPager;
	private ProgressBar bar;
//	private Handler mHandler;
//	private boolean isBreak=false;
//	private List<Image> images;
//	private Context context;
	private boolean isClicked=false;
//	private AnimTask task;
	public void	setIsClicked(boolean canClick){
		this.isClicked=canClick;
		bar.setVisibility(View.INVISIBLE);
	}
	public LoadingPageView(Context context,ViewGroup view) {
		// TODO Auto-generated constructor stub
//		this.context=context;
		this.loadingPager = view;
		this.bar=(ProgressBar)((View)view.getParent()).findViewById(R.id.progressBar1);
		loadingPager.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (isClicked) {
					dismiss();
				}
			}
		});
//		if (getImages()!=null) {
//			setLoadingPagerList(images);
//		}

	}

//	public void setLoadingPagerList(List<Image> list) {
//		loadingPager.removeAllViews();
//		for (Image image : list) {
//			ImageView imageView = new ImageView(loadingPager.getContext());
//			imageView.setLayoutParams(new RelativeLayout.LayoutParams(
//					RelativeLayout.LayoutParams.FILL_PARENT,
//					RelativeLayout.LayoutParams.FILL_PARENT));
//			imageView.setScaleType(ScaleType.FIT_XY);
//			mImageWork.loadImage(image.getImageURL(), imageView);
//			loadingPager.addView(imageView);
//		}
//		images=list;
////		playAnimation();
//	}

	public void dismiss() {
		loadingPager.setVisibility(View.INVISIBLE);
		bar.setVisibility(View.INVISIBLE);
//		isBreak=true;
//		if (task!=null) {
//			task.cancel(true);
//			task=null;
//		}
	}

//	private void playAnimation() {
//		// handler接受消息进行变化
//		mHandler = new Handler() {
//
//			@Override
//			public void handleMessage(Message msg) {
//				for (int i=0;i<loadingPager.getChildCount();i++) {
//					loadingPager.getChildAt(i).setVisibility(View.INVISIBLE);
//				}
//				loadingPager.getChildAt(msg.what).setVisibility(View.VISIBLE);
//
//				if (msg.what==loadingPager.getChildCount()-1||isClicked) {
//					isBreak=true;
//					new Handler().postDelayed(new Runnable() {
//						
//						@Override
//						public void run() {
//							// TODO Auto-generated method stub
//							dismiss();
//						}
//					}, 2000);
//				}
//			};
//
//		};
//		
//		if (task==null) {
//			task=new AnimTask();
//			task.execute(0);
//		}
//	}
//	
//	private List<Image> getImages(){
//		Type type=new TypeToken<List<Image>>(){}.getType();
//		Gson gson=new Gson();
//		String valueString=SharePreferenceHelper.getStringValue(context, "LoadingPage");
//		images=gson.fromJson(valueString, type);
//		return images;
//	}
//	
//	public void saveImages(List<Image> list){
//		Gson gson=new Gson();
//		SharePreferenceHelper.setStringValue(context, "LoadingPage", gson.toJson(list));
//	}
//	
//	class AnimTask extends AsyncTask<Integer, Integer, Integer>{
//
//		@Override
//		protected Integer doInBackground(Integer... params) {
//			// TODO Auto-generated method stub
//			
//			while (!isBreak) {
//				for (int i = 0; i < loadingPager.getChildCount(); i++) {
//					if (mHandler==null) {
//						break;
//					}
//					mHandler.sendEmptyMessage(i);
//
//					try {
//						Thread.sleep(2000);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
//			}
//			
//			return null;
//		}
//
//		
//
//		
//		
//	}
}
