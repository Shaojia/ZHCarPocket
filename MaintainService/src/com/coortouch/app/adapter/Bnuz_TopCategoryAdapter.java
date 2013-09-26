package com.coortouch.app.adapter;

import java.util.List;

import com.coortouch.android.adapter.CTHAdapter;
import com.coortouch.maintainservice.activity.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class Bnuz_TopCategoryAdapter extends CTHAdapter<String>{
	
	private int[] resourceIds={R.drawable.bnuz_cate_0,R.drawable.bnuz_cate_1,R.drawable.bnuz_cate_2,R.drawable.bnuz_cate_3,R.drawable.bnuz_cate_4};
	
	public Bnuz_TopCategoryAdapter(Context context, List<String> data) {
		super(context, data);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewContent viewContent = new ViewContent();

		String entity = data.get(position);

		if (convertView == null) {

			convertView = mInflater.inflate(R.layout.bnuz_topcategory_list_item,
					null);
			viewContent.titleTextView = (TextView) convertView
					.findViewById(R.id.item_Title);
			viewContent.imageView = (ImageView) convertView
					.findViewById(R.id.item_Image);

			convertView.setTag(viewContent);
		} else {
			viewContent = (ViewContent) convertView.getTag();
		}

		if (entity!=null) {
			
			viewContent.titleTextView.setText(entity);
			
			viewContent.imageView.setImageResource(resourceIds[position]);
			//UrlImageViewHelper.setUrlDrawable(viewContent.imageView, entity.getImageURL(),R.drawable.bi_top_item);
		}
		


		return convertView;
	}

	static class ViewContent {
		TextView titleTextView;

		ImageView imageView;
	}


}
