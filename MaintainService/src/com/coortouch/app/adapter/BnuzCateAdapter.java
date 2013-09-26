package com.coortouch.app.adapter;


import java.util.ArrayList;

import com.coortouch.android.adapter.CTHAdapter;
import com.coortouch.app.entity.BnuzGuideCate;
import com.coortouch.maintainservice.activity.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class BnuzCateAdapter extends CTHAdapter<BnuzGuideCate>{
	private int selectedIndex=0;
	public void setSelectedIndex(int index) {
		this.selectedIndex=index;
		notifyDataSetChanged();
	}
	public BnuzCateAdapter(Context context, ArrayList<BnuzGuideCate> data) {
		super(context, data);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view=null;
		if (convertView==null) {
			view=LayoutInflater.from(context).inflate(android.R.layout.simple_gallery_item, null);
		}
		else {
			view=convertView;
		}
		TextView textView=(TextView)view.findViewById(android.R.id.text1);
		
		BnuzGuideCate category=data.get(position);
		
		String cate=category.getTitle();
		
		
		textView.setText(cate);
		if (position==selectedIndex) {
			textView.setTextColor(context.getResources().getColor(R.color.txtword));
		}else {
			textView.setTextColor(context.getResources().getColor(R.color.black));
		}
		
		return view;
	}



}
