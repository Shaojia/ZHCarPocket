package com.coortouch.android.categoryview;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CateSimpleAdapter extends BaseAdapter{

	private Context context;
	private String[] data;
	public CateSimpleAdapter(Context context,String[] data){
		this.context=context;
		this.data=data;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
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
		
		textView.setText(data[position]);
		
		return view;
	}

}
