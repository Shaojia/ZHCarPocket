package com.coortouch.maintainservice.activity;

import java.io.UnsupportedEncodingException;

import com.coortouch.adnroid.helper.ScreenManager;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import cn.jpush.android.api.JPushInterface;


public class MyReceiver extends BroadcastReceiver {
	private static final String TAG = "MyReceiver";
    public static final String NOTIFICATION_SERVICE = "notification";
	private static final int		NOTIF_CONNECTED = 0;	
	public static String NOTIF_TITLE = "æ© ¶º“‘∞"; 	
	NotificationManager mNotifMan;
	@Override
	public void onReceive(Context context, Intent intent) {
		
		if (mNotifMan==null) {
			 mNotifMan = (NotificationManager)context.getSystemService(NOTIFICATION_SERVICE);
		}
		
        Bundle bundle = intent.getExtras();
		Log.d(TAG, "onReceive - " + intent.getAction() + ", extras: " + printBundle(bundle));
		
        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
        	
            //send the Registration Id to your server...
        }else if (JPushInterface.ACTION_UNREGISTER.equals(intent.getAction())){
           
          //send the UnRegistration Id to your server...
        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
        	
        	showNotification(bundle.getString(JPushInterface.EXTRA_MESSAGE), context);
        
        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
          
        	
        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {

            
        	
        	
        } else {
        	Log.d(TAG, "Unhandled intent - " + intent.getAction());
        }
	}
	
	private void showNotification(String text,Context context) {

		Notification n = new Notification();
		
		n.flags |= Notification.FLAG_SHOW_LIGHTS;
      	n.flags |= Notification.FLAG_AUTO_CANCEL;

        n.defaults = Notification.DEFAULT_ALL;
      	
		n.icon = com.coortouch.maintainservice.activity.R.drawable.ic_launcher;
		n.when = System.currentTimeMillis();
		
		String[] message={"0"};
		message=text.split("\\^");
		
		if (message.length<2) {
			return;
		}
		
		// Simply open the parent activity
		Intent intent_D=new Intent();
		
		if (message.length>2) {

			if (!ScreenManager.getScreenManager().hasActivity()) {
				intent_D.setClass(context, Bnuz_NewsDetailActivity.class);
			}else {
				intent_D.setClass(ScreenManager.getScreenManager().currentActivity(), Bnuz_NewsDetailActivity.class);
			}
			intent_D.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
			intent_D.putExtra("pId", message[0]);
			intent_D.putExtra("Id", message[1]);
			PendingIntent pi = PendingIntent.getActivity(context, 0,
			  intent_D,  PendingIntent.FLAG_UPDATE_CURRENT);
			// Change the name of the notification here
			try {
				n.setLatestEventInfo(context, "["+NOTIF_TITLE+"]", new String(message[2].getBytes(),"utf8"), pi);
				
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			
//			Log.i("jiajia","push oooo"+ intent_D.getExtras().getString("pId")+"   id"+intent_D.getExtras().getString("Id"));
			
			mNotifMan.notify(NOTIF_CONNECTED, n);
			
		}
		
	}

	private static String printBundle(Bundle bundle) {
		StringBuilder sb = new StringBuilder();
		for (String key : bundle.keySet()) {
			if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
				sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
			} else {
				sb.append("\nkey:" + key + ", value:" + bundle.getString(key));
			}
		}
		return sb.toString();
	}
	

}
