package com.coortouch.app.adapter;

import java.util.List;

import com.coortouch.android.adapter.CTHAdapter;
import com.coortouch.app.entity.BnuzTSystem;
import com.coortouch.maintainservice.activity.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class BnuzSystemAdapter extends CTHAdapter<BnuzTSystem> {

	public BnuzSystemAdapter(Context context, List<BnuzTSystem> data) {
		super(context, data);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		
		ViewContent viewContent=new ViewContent();
		
		BnuzTSystem entity=data.get(position);
		
		if (convertView==null) {
			
			convertView=mInflater.inflate(R.layout.bnuz_systeml_list_item, null);
			

			viewContent.titleTextView=(TextView)convertView.findViewById(R.id.item_Title);
			
			convertView.setTag(viewContent);
		}
		else {
			viewContent=(ViewContent)convertView.getTag();
		}
		
		if (entity!=null) {
			
			viewContent.titleTextView.setText(entity.getName());
			
			
			
		}
		
		return convertView;
	}

	static class ViewContent{
		TextView titleTextView;
	}
	
}
