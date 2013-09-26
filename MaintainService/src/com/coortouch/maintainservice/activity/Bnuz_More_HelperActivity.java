package com.coortouch.maintainservice.activity;

import com.coortouch.app.entity.*;
import com.coortouch.app.wcf.BnuzHelperService;
import com.coortouch.app.wcf.listener.GetOneRecordListener;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;

public class Bnuz_More_HelperActivity extends BaseActivity implements OnClickListener{
	
	private WebView txtContent;
	private Button btnBack;

	String html="<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\"\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><html xmlns=\"http://www.w3.org/1999/xhtml\"><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"/><meta name = \"viewport\" content = \"initial-scale = 1.0,user-scalable = no, width = 249\"/><style>@media screen and (max-device-width: 320px){body{-webkit-text-size-adjust:auto;font-family:Helvetica, Arial, Verdana, sans-serif;} div{clear:both!important;display:block!important;width:100%%!important;float:none!important;margin:0!important;padding:0!important;} img{width:100%% !important;height:auto;} table{width:100%% !important;height:auto;}}</style></head><body style=\"margin-top:0px;margin-left:0px;\"><div> %s </div></body></html>";
	
	String contentText="";
	
	BnuzHelperService wcfService;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bnuz_more_helper);

		initView();

		setData();
	}

	private void initView() {
		txtContent = (WebView) findViewById(R.id.txtNewsDetContent);
		txtContent.setBackgroundColor(0);
		
		btnBack = (Button) findViewById(R.id.btnBack);
		
		
		btnBack.setOnClickListener(this);
		
		wcfService=new BnuzHelperService(this);

	}

	private void setData() {

		wcfService.getHelper(new GetOneRecordListener<BnuzTHelper>() {
			
			@Override
			public void onFinish(BnuzTHelper data) {
				// TODO Auto-generated method stub
				
				txtContent.loadDataWithBaseURL("",String.format(html,data.getContent() ), "text/html", "utf-8","");
				
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
			onBackPressed();
			break;
		
		default:
			break;
		}
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
}
