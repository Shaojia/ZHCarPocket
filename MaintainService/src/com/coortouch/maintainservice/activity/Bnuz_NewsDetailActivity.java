package com.coortouch.maintainservice.activity;

import com.coortouch.adnroid.helper.ScreenManager;
import com.coortouch.app.entity.BnuzTNews;
import com.coortouch.app.wcf.BnuzNewsService;
import com.coortouch.app.wcf.listener.GetOneRecordListener;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

public class Bnuz_NewsDetailActivity extends BaseActivity implements OnClickListener {

	private WebView txtContent;
	private TextView txtTitle; 
	private Button btnBack;

	private BnuzTNews entity;
	String html="<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\"\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><html xmlns=\"http://www.w3.org/1999/xhtml\"><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"/><meta name = \"viewport\" content = \"initial-scale = 1.0,user-scalable = no, width = 249\"/><style>@media screen and (max-device-width: 320px){body{-webkit-text-size-adjust:auto;font-family:Helvetica, Arial, Verdana, sans-serif;} div{clear:both!important;display:block!important;width:100%%!important;float:none!important;margin:0!important;padding:0!important;} img{width:100%% !important;height:auto;} table{width:100%% !important;height:auto;}}</style></head><body style=\"margin-top:0px;margin-left:0px;\"><div> %s </div></body></html>";
	
	String contentText="";

	BnuzNewsService wcfService;

	String pId="-1";
	String Id="-1";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bnuz_news_detail);

		Intent intent = getIntent();
		entity = (BnuzTNews) intent.getExtras().get("News");
		initView();
		
		
		pId=intent.getExtras().getString("pId");
		Id=intent.getExtras().getString("Id");
		

		if (pId!=null&&pId.length()>0) {
			requestData();
		}else {
			setData();
		}
	}
	

	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		super.onNewIntent(intent);

		pId=intent.getExtras().getString("pId");
		Id=intent.getExtras().getString("Id");

		if (pId!=null&&pId.length()>0) {
			requestData();
		}
	}

	private void initView() {
		txtContent = (WebView) findViewById(R.id.txtNewsDetContent);
		txtContent.setBackgroundColor(0);
		txtTitle = (TextView) findViewById(R.id.txtTitle);
		
		btnBack = (Button) findViewById(R.id.btnBack);
		
		
		btnBack.setOnClickListener(this);

		
		wcfService=new BnuzNewsService(this);

	}

	private void setData() {

		if (entity != null) {

			txtTitle.setText(entity.getTitle());

			pId=entity.getPid()+"";
			Id=entity.getId()+"";
			
			requestData();
		}

	}
	
	private void requestData() {
		wcfService.getNewsID(pId, Id, new GetOneRecordListener<BnuzTNews>() {
			
			@Override
			public void onFinish(BnuzTNews data) {
				// TODO Auto-generated method stub

				String content=data.getContent();

				Log.i("jiajia", content);
//				if (content.contains("/jscms/")) {
//					content=content.replace("/jscms/",  Constant.BaseUrl);
//				}
//				Log.i("jiajia", "adsadsadsa");
				txtTitle.setText(data.getTitle());
				txtContent.loadDataWithBaseURL("",String.format(html,content), "text/html", "utf-8","");

			}
			
			@Override
			public void onFailed(String Message) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnBack:
			finish();
			break;
		
		default:
			break;
		}
	}
	
	@Override
	public void finish() {
		// TODO Auto-generated method stub
		
		if (pId!=null&&pId.length()>0&&ScreenManager.getScreenManager().isTop()) {
			Intent intent=new Intent();
			intent.setClass(this, Bnuz_TopCategoryActivity.class);
			intent.putExtra("FromPush", true);
			startActivity(intent);
		}
		super.finish();
		
	}
}
