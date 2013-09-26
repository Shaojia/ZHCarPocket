package com.coortouch.maintainservice.activity;



import com.coortouch.app.entity.BnuzTGuide;
import com.coortouch.app.wcf.BnuzGuideService;
import com.coortouch.app.wcf.listener.GetOneRecordListener;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

public class Bnuz_GuideDetail2Activity extends BaseActivity implements OnClickListener {

	private WebView txtContent;
	private TextView txtTitle; 
	private TextView txtTel; 
	private TextView txtAddress; 
	private Button btnBack;

	private BnuzTGuide entity;
	String html="<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\"\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><html xmlns=\"http://www.w3.org/1999/xhtml\"><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"/><meta name = \"viewport\" content = \"initial-scale = 1.0,user-scalable = no, width = 249\"/><style>@media screen and (max-device-width: 320px){body{-webkit-text-size-adjust:auto;font-family:Helvetica, Arial, Verdana, sans-serif;} div{clear:both!important;display:block!important;width:100%%!important;float:none!important;margin:0!important;padding:0!important;} img{width:100%% !important;height:auto;} table{width:100%% !important;height:auto;}}</style></head><body style=\"margin-top:0px;margin-left:0px;\"><div> %s </div></body></html>";
	
	String contentText="";

	BnuzGuideService wcfService;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bnuz_guide_detail2);

		Intent intent = getIntent();
		entity = (BnuzTGuide) intent.getExtras().get("Guide");
		initView();

		setData();
	}

	private void initView() {
		txtContent = (WebView) findViewById(R.id.txtNewsDetContent);
		txtContent.setBackgroundColor(0);
		txtTitle = (TextView) findViewById(R.id.txtTitle);
		txtTel=(TextView) findViewById(R.id.txtTel);
		txtAddress=(TextView) findViewById(R.id.txtAddress);
		btnBack = (Button) findViewById(R.id.btnBack);
		
		txtTel.setOnClickListener(this);
		btnBack.setOnClickListener(this);
		findViewById(R.id.imgTel).setOnClickListener(this);
		
		wcfService=new BnuzGuideService(this);

	}

	private void setData() {

		if (entity != null) {

			txtTitle.setText(entity.getTitle());
			txtTel.setText(entity.getPhone());
			
			wcfService.getGuideID(entity.getPid(), entity.getId(), new GetOneRecordListener<BnuzTGuide>() {
				
				@Override
				public void onFinish(BnuzTGuide data) {
					// TODO Auto-generated method stub
					String content=data.getContent();
//					if (content.contains("/jscms/")) {
//						content=content.replace("/jscms/",  Constant.BaseUrl);
//					}
					
					txtContent.loadDataWithBaseURL("",String.format(html,content), "text/html", "utf-8","");

					txtAddress.setText(data.getAddress());
				}
				
				@Override
				public void onFailed(String Message) {
					// TODO Auto-generated method stub
					
				}
			});
		}

	}
	
	private void makeTel() {
		if (entity.getPhone()!=null&&entity.getPhone().length()>0) {
			Dialog dialog = new AlertDialog.Builder(this)
			.setTitle("是否拨打该电话？")
			.setMessage(entity.getPhone())
			.setPositiveButton("是",
					new DialogInterface.OnClickListener() {
	
						@Override
						public void onClick(DialogInterface dialog,
								int which) {
							// TODO Auto-generated method stub


							String tel=entity.getPhone().trim();
							if (entity.getPhone().contains("-")) {
								tel=entity.getPhone().trim().replaceAll("-", "");
								
							}
							Intent intent = new Intent(
									Intent.ACTION_CALL,
									Uri.parse("tel:" + tel));

							startActivity(intent);
						}
					})
			.setNegativeButton("否",
					null).create();
	
			dialog.show();
		}
		
		
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnBack:
			finish();
			break;
		case R.id.txtTel:
			makeTel();
			break;
		case R.id.imgTel:
			makeTel();
			break;
		default:
			break;
		}
	}
	
	
}
