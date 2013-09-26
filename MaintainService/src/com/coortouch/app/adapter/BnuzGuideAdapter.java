package com.coortouch.app.adapter;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import com.coortouch.android.adapter.CTHAdapter;
import com.coortouch.android.urlimagehelper.UrlImageViewHelper;
import com.coortouch.app.entity.BnuzTGuide;
import com.coortouch.maintainservice.activity.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class BnuzGuideAdapter extends CTHAdapter<BnuzTGuide> {

	public BnuzGuideAdapter(Context context, List<BnuzTGuide> data) {
		super(context, data);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		
		ViewContent viewContent=new ViewContent();
		
		BnuzTGuide entity=data.get(position);
		
		if (convertView==null) {
			
			convertView=mInflater.inflate(R.layout.bnuz_guide_item_view, null);
			

			viewContent.titleTextView=(TextView)convertView.findViewById(R.id.item_Title);
			viewContent.timeTextView=(TextView)convertView.findViewById(R.id.item_Time);
			
			viewContent.imageView=(ImageView)convertView.findViewById(R.id.item_Image);
			
			convertView.setTag(viewContent);
		}
		else {
			viewContent=(ViewContent)convertView.getTag();
		}
		
		if (entity!=null) {
			
			viewContent.titleTextView.setText(entity.getTitle());
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
			
			viewContent.timeTextView.setText(formatter.format(new Timestamp(entity.getCreate_time()*1000)));
			
			if (entity.getSmall_img()!=null&&entity.getSmall_img().length()>0) {

				UrlImageViewHelper.setUrlDrawable(viewContent.imageView, entity.getSmall_img());
				
			}
			
		}
		
		return convertView;
	}

	static class ViewContent{
		TextView titleTextView;
		TextView timeTextView;

		ImageView imageView;
	}
	
}
