package com.coortouch.android.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class CTHAdapter<T> extends BaseAdapter{

	public List<T> data;

	public Context context;
	
	public LayoutInflater mInflater;
	
	
	public CTHAdapter(Context context, List<T> data) {
		// TODO Auto-generated constructor stub
		this.data=data;
		this.context=context;
		mInflater=LayoutInflater.from(context);
	}
	
	public void setData(List<T> data) {
		this.data=data;
		notifyDataSetChanged();
	}
	public List<T> getData() {
		return data;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	
	public abstract View getView(int position, View convertView, ViewGroup parent);

	

}
