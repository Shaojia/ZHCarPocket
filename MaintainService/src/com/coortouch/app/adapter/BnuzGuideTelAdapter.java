package com.coortouch.app.adapter;

import java.util.List;

import com.coortouch.android.adapter.CTHAdapter;
import com.coortouch.app.entity.BnuzTGuide;
import com.coortouch.maintainservice.activity.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class BnuzGuideTelAdapter extends CTHAdapter<BnuzTGuide> {

	public BnuzGuideTelAdapter(Context context, List<BnuzTGuide> data) {
		super(context, data);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		
		ViewContent viewContent=new ViewContent();
		
		BnuzTGuide entity=data.get(position);
		
		if (convertView==null) {
			
			convertView=mInflater.inflate(R.layout.bnuz_guide_tel_list_item, null);
			

			viewContent.titleTextView=(TextView)convertView.findViewById(R.id.item_Title);
			viewContent.telTextView=(TextView)convertView.findViewById(R.id.item_Tel);
			
			viewContent.imageView=(ImageView)convertView.findViewById(R.id.item_Image);
			
			convertView.setTag(viewContent);
		}
		else {
			viewContent=(ViewContent)convertView.getTag();
		}
		
		if (entity!=null) {
			
			viewContent.titleTextView.setText(entity.getTitle());
			
			viewContent.telTextView.setText(entity.getPhone());
		}
		
		return convertView;
	}

	static class ViewContent{
		TextView titleTextView;
		TextView telTextView;

		ImageView imageView;
	}
	
}
