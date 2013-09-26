package com.coortouch.maintainservice.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.coortouch.android.switchbutton.SwitchButton;
import com.coortouch.app.activityenum.ActivityEnum;
import com.coortouch.app.entity.BnuzTUser;
import com.coortouch.app.helper.SharePreferenceHelper;
import com.coortouch.app.wcf.BnuzCustomerService;
import com.coortouch.app.wcf.listener.PostRecordResponseListener;
import com.coortouch.maintainservice.activity.R;

public class Bnuz_ModifyInfoActivity extends BaseActivity implements OnClickListener {

	private EditText nameText;

	private EditText pwdText;

	private EditText telText;

	private EditText emailText;

	private EditText qqText;

	private TextView txtNum;

	private TextView txtCode;

	private Button loginButton;
	
	private Button backButton;
	
	private SwitchButton switchView;

	private ProgressDialog progressDialog;

	private BnuzCustomerService customerService;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.bnuz_modifyinfo);
	
		initView();
		ActivityEnum.TopCateActivity.getValue();
	}

	private void initView() {
		switchView=(SwitchButton)findViewById(R.id.switchButton);
		nameText = (EditText) findViewById(R.id.txtName);
		pwdText = (EditText) findViewById(R.id.txtPassword);
		telText = (EditText) findViewById(R.id.txtTel);
		emailText = (EditText) findViewById(R.id.txtEmail);
		qqText = (EditText) findViewById(R.id.txtQQ);

		txtNum = (TextView) findViewById(R.id.txtNum);
		txtCode = (TextView) findViewById(R.id.txtCode);
		
		
		loginButton = (Button) findViewById(R.id.login_button);

		backButton=(Button)findViewById(R.id.btnBack);
		

		loginButton.setOnClickListener(this);
		
		backButton.setOnClickListener(this);
		

		customerService = new BnuzCustomerService(this);
		
		setData();
	}
	
	
	private void setData(){
		
		BnuzTUser entity=SharePreferenceHelper.getCustomer(this);
		if (entity!=null) {
			nameText.setText(entity.getUsername());
			txtNum.setText(entity.getStudent_id());
			txtCode.setText(entity.getId_num());
			if(entity.getGnder()==1){
				switchView.updateSwitchState(false);
			}else {
				switchView.updateSwitchState(true);
			}
			telText.setText(entity.getPhone());
			emailText.setText(entity.getEmail());
			qqText.setText(entity.getQq());
			
		}
		
	}
	

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.login_getpwd:
			Intent intent = new Intent();
			intent.setClass(getApplicationContext(), Bnuz_FindPwdActivity.class);
			overridePendingTransition(R.anim.activity_pushtotop,
					R.anim.activity_noanim);
			startActivityForResult(intent, 1);

			break;

		case R.id.login_button:

			if (nameText.getText().toString().trim().length() == 0) {
				Toast.makeText(Bnuz_ModifyInfoActivity.this, "请输入姓名！",
						Toast.LENGTH_SHORT).show();
				return;
			}
			if (progressDialog == null) {
				progressDialog = new ProgressDialog(this);
				progressDialog.setIndeterminate(false);
				progressDialog.setCancelable(true);
			}
			
			Login(pwdText.getText().toString().trim(), nameText.getText()
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

		progressDialog.setMessage("提交中...");
		progressDialog.show();
		
		BnuzTUser entity=SharePreferenceHelper.getCustomer(this);
		entity.setGnder(switchView.isSwitchOn()?2:1);
		entity.setEmail(emailText.getText().toString());
		entity.setPassword(pwdText.getText().toString());
		entity.setPhone(telText.getText().toString());
		entity.setQq(qqText.getText().toString());
		entity.setUsername(nameText.getText().toString());

		
		customerService.modifyCustomerBaseInfo(entity,new PostRecordResponseListener() {

					@Override
					public void onFinish(String succString) {
						// TODO Auto-generated method stub
						
						if (progressDialog != null) {
							progressDialog.dismiss();
						}
						if(succString.equals("1")){
							
							
						}
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
	
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

	
	}
	

}
