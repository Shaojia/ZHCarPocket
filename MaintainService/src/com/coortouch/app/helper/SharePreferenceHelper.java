package com.coortouch.app.helper;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.coortouch.app.entity.BnuzGuideCate;
import com.coortouch.app.entity.BnuzNewsCate;
import com.coortouch.app.entity.BnuzTGuide;
import com.coortouch.app.entity.BnuzTGuideDepartment;
import com.coortouch.app.entity.BnuzTNews;
import com.coortouch.app.entity.BnuzTSystem;
import com.coortouch.app.entity.BnuzTUser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharePreferenceHelper {
	
	public final static String SHAREPREFENCENAME="BnuzPocket";
	
	
	//data bnuz cache
	public final static String Cache_GuideCate="CacheGuideCate";
	public final static String Cache_NewsCate="CacheNewsCate";
	public final static String Cache_BnuzNews="CacheBnuzNews";
	public final static String Cache_Guide="CacheGuideCate";
	public final static String Cache_GuideDepartment="CacheGuideDepartment";
	public final static String Cache_BnuzSystem="CacheBnuzSystem";
	public final static String Cache_Customer="CacheCustomer";
	
	

	public static Boolean setStringValue(Context context,String key, String value){
		SharedPreferences sharedPreferences=context.getSharedPreferences(SHAREPREFENCENAME, 0);
		Editor editor= sharedPreferences.edit();
		editor.putString(key, value);
		return editor.commit();
	}
	
	public static String getStringValue(Context context,String key) {
		SharedPreferences sharedPreferences=context.getSharedPreferences(SHAREPREFENCENAME, 0);
		return sharedPreferences.getString(key, "");
	}

	
	public static Boolean setIntValue(Context context,String key, int value){
		SharedPreferences sharedPreferences=context.getSharedPreferences(SHAREPREFENCENAME, 0);
		Editor editor= sharedPreferences.edit();
		editor.putInt(key, value);
		return editor.commit();
	}
	
	public static int getIntValue(Context context,String key) {
		SharedPreferences sharedPreferences=context.getSharedPreferences(SHAREPREFENCENAME, 0);
		return sharedPreferences.getInt(key, 0);
	}
	
	public static Boolean setLongValue(Context context,String key, Long value){
		SharedPreferences sharedPreferences=context.getSharedPreferences(SHAREPREFENCENAME, 0);
		Editor editor= sharedPreferences.edit();
		editor.putLong(key, value);
		return editor.commit();
	}
	
	public static Long getLongValue(Context context,String key) {
		SharedPreferences sharedPreferences=context.getSharedPreferences(SHAREPREFENCENAME, -1);
		return sharedPreferences.getLong(key, -1);
	}
	
	public static <T> boolean saveCacheData(Context context,String key,List<T> nList) {
//		String vlaueString=SharePreferenceHelper.getStringValue(context,key);
		
//		Type type=new TypeToken<List<T>>(){}.getType();
		
		Gson gson=new Gson();
		
//		List<T> list=gson.fromJson(vlaueString, type);
//		
//		if (list==null) {
//			list=new ArrayList<T>();
//		}
//		
//		if (list.size()>0) {
//			nList.removeAll(list);
//		}
//		list.addAll(nList);
		
		SharePreferenceHelper.setStringValue(context,key, gson.toJson(nList));
		
		return true;
		
	}
	

	public static List<BnuzGuideCate> getGuideCateCacheData(Context context) {
		
		String vlaueString=SharePreferenceHelper.getStringValue(context,Cache_GuideCate);
		
		Type type=new TypeToken<List<BnuzGuideCate>>(){}.getType();
		
		Gson gson=new Gson();
		
		List<BnuzGuideCate> list=gson.fromJson(vlaueString, type);
		
		if (list==null) {
			list=new ArrayList<BnuzGuideCate>();
		}
		
		return list;
	}
	

	public static List<BnuzNewsCate> getNewsCateCacheData(Context context) {
		
		String vlaueString=SharePreferenceHelper.getStringValue(context,Cache_NewsCate);
		
		Type type=new TypeToken<List<BnuzNewsCate>>(){}.getType();
		
		Gson gson=new Gson();
		
		List<BnuzNewsCate> list=gson.fromJson(vlaueString, type);
		
		if (list==null) {
			list=new ArrayList<BnuzNewsCate>();
		}
		
		return list;
	}
	

	public static List<BnuzTGuide> getBnuzGuideCacheData(Context context,int cateId) {
		
		String vlaueString=SharePreferenceHelper.getStringValue(context,Cache_Guide+cateId);
		
		Type type=new TypeToken<List<BnuzTGuide>>(){}.getType();
		
		Gson gson=new Gson();
		
		List<BnuzTGuide> list=gson.fromJson(vlaueString, type);
		
		if (list==null) {
			list=new ArrayList<BnuzTGuide>();
		}
		
		return list;
	}
	

	public static List<BnuzTGuideDepartment> getBnuzGuideDepartmentCacheData(Context context) {
		
		String vlaueString=SharePreferenceHelper.getStringValue(context,Cache_GuideDepartment);
		
		Type type=new TypeToken<List<BnuzTGuideDepartment>>(){}.getType();
		
		Gson gson=new Gson();
		
		List<BnuzTGuideDepartment> list=gson.fromJson(vlaueString, type);
		
		if (list==null) {
			list=new ArrayList<BnuzTGuideDepartment>();
		}
		
		return list;
	}
	

	public static List<BnuzTNews> getBnuzNewsCacheData(Context context,int cateId) {
		
		String vlaueString=SharePreferenceHelper.getStringValue(context,Cache_BnuzNews+cateId);
		
		Type type=new TypeToken<List<BnuzTNews>>(){}.getType();
		
		Gson gson=new Gson();
		
		List<BnuzTNews> list=gson.fromJson(vlaueString, type);
		
		if (list==null) {
			list=new ArrayList<BnuzTNews>();
		}
		
		return list;
	}
	

	public static List<BnuzTSystem> getBnuzSystemCacheData(Context context) {
		
		String vlaueString=SharePreferenceHelper.getStringValue(context,Cache_BnuzSystem);
		
		Type type=new TypeToken<List<BnuzTSystem>>(){}.getType();
		
		Gson gson=new Gson();
		
		List<BnuzTSystem> list=gson.fromJson(vlaueString, type);
		
		if (list==null) {
			list=new ArrayList<BnuzTSystem>();
		}
		
		return list;
	}
	
	public static boolean saveCustomerData(Context context,BnuzTUser entity) {
		
		Gson gson=new Gson();
		
		SharePreferenceHelper.setStringValue(context,Cache_Customer, gson.toJson(entity));
		
		return true;
		
	}
	
	public static BnuzTUser getCustomer(Context context) {
		
		String vlaueString=SharePreferenceHelper.getStringValue(context,Cache_Customer);
		
		BnuzTUser customer=null;
		Gson gson=new Gson();
		
		customer=gson.fromJson(vlaueString, BnuzTUser.class);
		
		return customer;
	}
	
	public static boolean isLogin(Context context){
		BnuzTUser entity = getCustomer(context);
		
		if(entity!=null){
			return true;
		}else{
			return false;
		}
	}
	
	public static String getCustomerId(Context context){
		BnuzTUser entity = getCustomer(context);
		String customerId="";
//		if(entity!=null&&entity.getId()!=null&&entity.getCustomerId().length()>0){
//			customerId=entity.getCustomerId();
//		}
		return customerId;
	}
	
	
	
	
}
