package com.coortouch.app.wcf;


import org.json.JSONObject;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import com.coortouch.app.constant.Constant;
import com.coortouch.app.entity.BnuzTUser;
import com.coortouch.app.helper.SharePreferenceHelper;
import com.coortouch.app.wcf.listener.GetOneRecordListener;
import com.coortouch.app.wcf.listener.PostRecordResponseListener;
import com.google.gson.Gson;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class BnuzCustomerService extends BaseService{
	
	private String LoginURL=Constant.BaseUrl+"index.php?m=Public&a=checkLogin";
	
	private String FindPwdURL=Constant.BaseUrl+"index.php?m=Public&a=lostpassword";
	
	private String ModifyURL=Constant.BaseUrl+"index.php/Public/change";
	
	
	public BnuzCustomerService(Context context) {
		// TODO Auto-generated constructor stub
		super(context);
		
	}
	
	
	public void loginIn(String userName,String pwd,final PostRecordResponseListener listener) {

		isInterrupt=false;
		
		client = new AsyncHttpClient();
		
		RequestParams params = new RequestParams();
	
		params.put("student_id", userName);
		params.put("password", pwd);
		
		client.post(LoginURL, params,new AsyncHttpResponseHandler(){

			@Override
			public void onSuccess(String content) {
				// TODO Auto-generated method stub
				super.onSuccess(content);
				Log.i("jiajia", content);
				try {
					
					JSONObject object=new JSONObject(content);
					int requestStatus=object.getInt("code");
//					Log.i("mylog", "requestStatus"+requestStatus);
					if(requestStatus==0){
						BnuzTUser entity=new Gson().fromJson(object.getJSONObject("result").toString(), BnuzTUser.class);

						SharePreferenceHelper.saveCustomerData(context, entity);
						if(listener!=null&&isInterrupt==false){
							listener.onFinish(""+requestStatus);
						}
					}else{
						Toast.makeText(context, "用户不存在或密码错误。", Toast.LENGTH_SHORT).show();
						if(listener!=null&&isInterrupt==false){
							listener.onFailed("");
						}
					}

				}catch (Exception e) {
					// TODO: handle exception
				}
				
				
			}

			@Override
			public void onFailure(Throwable error, String content) {
				// TODO Auto-generated method stub
				super.onFailure(error, content);
				if(listener!=null&&isInterrupt==false){
					listener.onFailed("");
				}
				Toast.makeText(context, "请检查网络联接。", Toast.LENGTH_SHORT).show();
				
			}
			
		});
		
	}
	public void Register(String userName,String pwd,String tel,String nikeName,String comoany,final GetOneRecordListener<BnuzTUser> listener) {

		isInterrupt=false;
		
		client = new AsyncHttpClient();
		
		RequestParams params = new RequestParams();
	
		params.put("UserName", userName);
		params.put("Password", pwd);
		params.put("NickName", nikeName);
		params.put("ActorURL", "");
		params.put("Tel", tel);
		params.put("Company", comoany);

		client.post("", params,new AsyncHttpResponseHandler(){

			@Override
			public void onSuccess(String content) {
				// TODO Auto-generated method stub
				super.onSuccess(content);

				try {
					
					JSONObject object=new JSONObject(content);
					int requestStatus=object.getInt("RequestStatus");
					if(requestStatus==0){
						Toast.makeText(context, "已存在用户", Toast.LENGTH_SHORT).show();
						
//						Customer entity= 
					}else if(requestStatus==1){
						Toast.makeText(context, "注册成功。", Toast.LENGTH_SHORT).show();
						BnuzTUser entity=new Gson().fromJson(object.getJSONObject("UserInfo").toString(), BnuzTUser.class);
						SharePreferenceHelper.saveCustomerData(context, entity);
						if(listener!=null&&!isInterrupt){
							listener.onFinish(entity);
						}
					}else if(requestStatus==2){
						Toast.makeText(context, "注册失败。", Toast.LENGTH_SHORT).show();
						if(listener!=null&&isInterrupt==false){
							listener.onFailed("");
						}
					}
				}catch (Exception e) {
					// TODO: handle exception
				}
				
				
			}

			@Override
			public void onFailure(Throwable error, String content) {
				// TODO Auto-generated method stub
				super.onFailure(error, content);
				
				Toast.makeText(context, "请检查网络联接。", Toast.LENGTH_SHORT).show();
				
			}
			
		});
		
	}
	public void findPwd(String userName,String cardId,final PostRecordResponseListener listener) {

		isInterrupt=false;
		
		client = new AsyncHttpClient();
		
		RequestParams params = new RequestParams();
	
		params.put("student_id", userName);
		params.put("id_num", cardId);

		
		client.post(FindPwdURL, params,new AsyncHttpResponseHandler(){

			@Override
			public void onSuccess(String content) {
				// TODO Auto-generated method stub
				super.onSuccess(content);
				try {
					
					JSONObject object=new JSONObject(content);
					int requestStatus=object.getInt("code");
					if(requestStatus>1){
						Toast.makeText(context, "学号或身份证号不匹配!", Toast.LENGTH_SHORT).show();
						if(listener!=null&&isInterrupt==false){
							listener.onFailed("");
						}
					}else if(requestStatus==0){
						
						listener.onFinish(object.getJSONObject("result").getString("password"));
					}
				}catch (Exception e) {
					// TODO: handle exception
				}
				
				
			}

			@Override
			public void onFailure(Throwable error, String content) {
				// TODO Auto-generated method stub
				super.onFailure(error, content);
				listener.onFailed("");
				Toast.makeText(context, "请检查网络联接。", Toast.LENGTH_SHORT).show();
				
			}
			
		});
		
	}
	
	public void modifyCustomerBaseInfo(final BnuzTUser customer,final PostRecordResponseListener listener) {

		isInterrupt=false;
		
		client = new AsyncHttpClient();
		
		RequestParams params = new RequestParams();
		params.put("student_id", customer.getStudent_id());
		params.put("id_num", customer.getId_num());
		params.put("username", customer.getUsername());
		params.put("password", customer.getPassword());
		params.put("gnder", customer.getGnder()+"");
		params.put("phone", customer.getPhone());
		params.put("email", customer.getEmail());
		params.put("id", customer.getId()+"");
		params.put("qq", customer.getQq());
		

		
		client.post(ModifyURL, params,new AsyncHttpResponseHandler(){

			@Override
			public void onSuccess(String content) {
				// TODO Auto-generated method stub
				super.onSuccess(content);
				Log.i("jiajia", content);
				try {
					
					JSONObject object=new JSONObject(content);
					int requestStatus=object.getInt("code");
					if(requestStatus>1){
						if(listener!=null&&isInterrupt==false){
							listener.onFailed("");
						}
						Toast.makeText(context, "修改失败", Toast.LENGTH_SHORT).show();
					}else if(requestStatus==0){
						Toast.makeText(context, "修改成功。", Toast.LENGTH_SHORT).show();
						
						SharePreferenceHelper.saveCustomerData(context, customer);
					}
				}catch (Exception e) {
					// TODO: handle exception
				}
				if(listener!=null&&isInterrupt==false){
					listener.onFinish("");
				}
				
				
			}

			@Override
			public void onFailure(Throwable error, String content) {
				// TODO Auto-generated method stub
				super.onFailure(error, content);
				
				Toast.makeText(context, "请检查网络联接。", Toast.LENGTH_SHORT).show();
				if(listener!=null&&isInterrupt==false){
					listener.onFinish("");
				}
			}
			
		});
		
	}
	
}

