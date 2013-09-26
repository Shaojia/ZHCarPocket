package com.coortouch.maintainservice.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.coortouch.app.activityenum.ActivityEnum;
import com.coortouch.app.wcf.BnuzCustomerService;
import com.coortouch.app.wcf.listener.PostRecordResponseListener;
import com.coortouch.maintainservice.activity.R;

public class Bnuz_FindPwdActivity extends BaseActivity implements OnClickListener {

	private EditText nameText;

	private EditText codeText;

	private Button nextButton;
	
	private Button backButton;

	private ProgressDialog progressDialog;

	private BnuzCustomerService customerService;
	
	private int ActivityEnumValue;
	
	private ViewGroup viewGroup;
	
	private View firstView;
	private View secondView;
	
	private TextView pwdTextView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.bnuz_findpwd);
		ActivityEnumValue=getIntent().getIntExtra("ActivityEnumValue",-1);
	
		initView();
		ActivityEnum.TopCateActivity.getValue();
	}

	private void initView() {
		viewGroup=(ViewGroup)findViewById(R.id.container);
		firstView=findViewById(R.id.firstView);
		secondView=findViewById(R.id.seocondView);
		pwdTextView=(TextView)findViewById(R.id.txtPwd);
		
		viewGroup.removeView(secondView);
		
		nameText = (EditText) findViewById(R.id.login_username_input);
		codeText = (EditText) findViewById(R.id.login_password_input);

		nextButton = (Button) findViewById(R.id.login_button);

		backButton=(Button)findViewById(R.id.btnBack);
		

		nextButton.setOnClickListener(this);
		
		backButton.setOnClickListener(this);
		

		customerService = new BnuzCustomerService(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.login_getpwd:
			

			break;

		case R.id.login_button:

			if (nameText.getText().toString().trim().length() == 0) {
				Toast.makeText(Bnuz_FindPwdActivity.this, "请输入学号！",
						Toast.LENGTH_SHORT).show();
				return;
			} else if (codeText.getText().toString().trim().length() == 0) {
				Toast.makeText(Bnuz_FindPwdActivity.this, "请输入身份证号！", Toast.LENGTH_SHORT)
						.show();
				return;
			}
			if (progressDialog == null) {
				progressDialog = new ProgressDialog(this);
				progressDialog.setIndeterminate(false);
				progressDialog.setCancelable(true);
			}
			
			Login(codeText.getText().toString().trim(), nameText.getText()
					.toString().trim());
			break;
		case R.id.btnBack:
			finish();
			overridePendingTransition(0, R.anim.activity_pushtobottom);
			break;
		default:
			break;
		}

	}

	private void Login(final String PassWord, String UserName) {

		progressDialog.setMessage("正在登录...");
		progressDialog.show();

		customerService.findPwd(UserName, PassWord,
				new PostRecordResponseListener() {

					@Override
					public void onFinish(String succString) {
						// TODO Auto-generated method stub
						
						if (progressDialog != null) {
							progressDialog.dismiss();
						}
						
						viewGroup.removeView(firstView);
						viewGroup.addView(secondView);
						pwdTextView.setText(succString);
						
						backButton.setBackgroundResource(R.drawable.bnuz_cate_login);
					}

					@Override
					public void onFailed(String failString) {
						// TODO Auto-generated method stub
						if (progressDialog != null) {
							progressDialog.hide();
						}
					}
				});

	}
	
	private void jumpToActivity(){
	
		Intent intent=new Intent();
		if(ActivityEnumValue==ActivityEnum.TopCateActivity.getValue()){
//			intent.setClass(getApplicationContext(), TactivityActivity.class);
			setResult(1);
			this.finish();
			overridePendingTransition(0, R.anim.activity_pushtobottom);
			return;
		}else if(ActivityEnumValue==ActivityEnum.MoreActivity.getValue()){
			intent.setClass(getApplicationContext(), Bnuz_MoreActivity.class);
		}
		this.finish();
		overridePendingTransition(0, R.anim.activity_pushtobottom);
		startActivity(intent);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		if(resultCode==1){
			jumpToActivity();
		}
	}
	

}
