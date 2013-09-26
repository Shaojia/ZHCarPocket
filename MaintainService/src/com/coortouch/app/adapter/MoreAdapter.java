package com.coortouch.app.adapter;

import java.util.List;

import com.coortouch.android.adapter.CTHAdapter;
import com.coortouch.app.helper.SharePreferenceHelper;
import com.coortouch.maintainservice.activity.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MoreAdapter extends CTHAdapter<String> {



	public MoreAdapter(Context context, List<String> data) {
		super(context, data);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if (!SharePreferenceHelper.isLogin(context)) {
			return data.size()-2;
		}
		return super.getCount();
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

	
		ViewContent viewContent = new ViewContent();

		if (!SharePreferenceHelper.isLogin(context)) {
			position+=2;
		}
		
		String string = data.get(position);

		if (convertView == null) {

			convertView = mInflater.inflate(R.layout.bnuz_more_list_item,
					null);
			viewContent.titleTextView = (TextView) convertView
					.findViewById(R.id.item_Title);
			viewContent.imageView = (ImageView) convertView
					.findViewById(R.id.item_Image);

			convertView.setTag(viewContent);
		} else {
			viewContent = (ViewContent) convertView.getTag();
		}

		
		viewContent.titleTextView.setText(string);
		

		return convertView;
	}

	static class ViewContent {
		TextView titleTextView;

		ImageView imageView;
	}

}
