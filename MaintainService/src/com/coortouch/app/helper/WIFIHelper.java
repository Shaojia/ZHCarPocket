package com.coortouch.app.helper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
//import android.net.wifi.WifiManager;

/**
 * 
 * @author www.coortouch.com
 * 
 */
public class WIFIHelper {
	Context context;

	public WIFIHelper(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
	}

	public static int checkWIFI(Context context) {
//		WifiManager wifi = (WifiManager) context
//				.getSystemService(Context.WIFI_SERVICE);
		ConnectivityManager connManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		 NetworkInfo info=connManager.getActiveNetworkInfo();
//		 if(wifi.isWifiEnabled()&&wifi.getWifiState()==WifiManager.WIFI_STATE_ENABLED
//		 &&connManager.getActiveNetworkInfo()!=null&&connManager.getActiveNetworkInfo().isAvailable()
//		 &&connManager.getActiveNetworkInfo().isConnected()&&!connManager.getActiveNetworkInfo().isFailover()
//		 ){
			if(info!=null){
			 if(info.getType()==ConnectivityManager.TYPE_WIFI){
				 return 0;  //??ifiè¿??
			 }else if(info.getType()==ConnectivityManager.TYPE_MOBILE){
				 return 1;  //??G/3Gè¿??
			 }else{
				 return 2;
			 }
			}else{
				return 2;
			}
		
//		 }else{
//			  if(info.getType()==ConnectivityManager.TYPE_MOBILE){
//				 return 1;  //??G/3Gè¿??
//			 }else{
//				 return 2;
//			 }
//		 }
		
	}
}
