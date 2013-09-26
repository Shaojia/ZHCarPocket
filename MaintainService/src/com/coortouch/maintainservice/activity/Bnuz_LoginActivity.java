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

import com.coortouch.app.activityenum.ActivityEnum;
import com.coortouch.app.wcf.BnuzCustomerService;
import com.coortouch.app.wcf.listener.PostRecordResponseListener;
import com.coortouch.maintainservice.activity.R;

public class Bnuz_LoginActivity extends BaseActivity implements OnClickListener {

	private EditText nameText;

	private EditText pwdText;

	private TextView getPwdButton;

	private Button loginButton;
	
	private Button backButton;

	private ProgressDialog progressDialog;

	private BnuzCustomerService customerService;
	
	private int ActivityEnumValue;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.bnuz_login);
		ActivityEnumValue=getIntent().getIntExtra("ActivityEnumValue",-1);
	
		initView();
	}

	private void initView() {
		nameText = (EditText) findViewById(R.id.login_username_input);
		pwdText = (EditText) findViewById(R.id.login_password_input);

		getPwdButton = (TextView) findViewById(R.id.login_getpwd);
		loginButton = (Button) findViewById(R.id.login_button);

		backButton=(Button)findViewById(R.id.btnBack);
		
		getPwdButton.setOnClickListener(this);

		loginButton.setOnClickListener(this);
		
		backButton.setOnClickListener(this);
		
		customerService = new BnuzCustomerService(this);
		

		if (progressDialog == null) {
			progressDialog = new ProgressDialog(this);
			progressDialog.setIndeterminate(false);
			progressDialog.setCancelable(true);
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.login_getpwd:
			Intent intent = new Intent();
			intent.setClass(getApplicationContext(), Bnuz_FindPwdActivity.class);
			startActivityForResult(intent, 1);
			overridePendingTransition(R.anim.activity_pushtotop,
					R.anim.activity_noanim);

			break;

		case R.id.login_button:
			if (nameText.getText().toString().trim().length() == 0) {
				Toast.makeText(Bnuz_LoginActivity.this, "«Î ‰»Î—ß∫≈£°",
						Toast.LENGTH_SHORT).show();
				return;
			} else if (pwdText.getText().toString().trim().length() == 0) {
				Toast.makeText(Bnuz_LoginActivity.this, "«Î ‰»Î√‹¬Î£°", Toast.LENGTH_SHORT)
						.show();
				return;
			}
			
			Login(pwdText.getText().toString().trim(), nameText.getText()
					.toString().trim());
			break;
		case R.id.btnBack:
			setResult(0);
			finish();
			break;
		default:
			break;
		}

	}

	private void Login(final String PassWord, String UserName) {

		progressDialog.setMessage("’˝‘⁄µ«¬º...");
		progressDialog.show();

		customerService.loginIn(UserName, PassWord,
				new PostRecordResponseListener() {

					@Override
					public void onFinish(String succString) {
						// TODO Auto-generated method stub
						
						if (progressDialog != null) {
							progressDialog.dismiss();
						}
						jumpToActivity();
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
		if(ActivityEnumValue==ActivityEnum.TopCateActivity.getValue()){
			setResult(1);
			this.finish();
			return;
		}else if(ActivityEnumValue==ActivityEnum.MoreActivity.getValue()){

			setResult(1);
			this.finish();
			return;
			
		}else if(ActivityEnumValue==-1){
			this.finish();
			return;
		}
		this.finish();
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		if(resultCode==1){
			jumpToActivity();
		}
	}
	
	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
		progressDialog.dismiss();
		overridePendingTransition(0, R.anim.activity_pushtobottom);
	}
}
