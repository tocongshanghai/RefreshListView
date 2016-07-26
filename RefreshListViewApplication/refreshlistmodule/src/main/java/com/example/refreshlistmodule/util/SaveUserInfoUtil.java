package com.example.refreshlistmodule.util;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class SaveUserInfoUtil {
	private SharedPreferences sp;

	public SaveUserInfoUtil(SharedPreferences sp) {
		super();
		this.sp = sp;
	}
	
	public UserInfo getUserInfo(String userName,String passWord){
		return new UserInfo(userName,passWord);
		
	}
	
	
	public void insertUserInfos(UserInfo info){
		ArrayList<UserInfo> userInfos=getUserInfoListFromSp();
		userInfos.add(info);
		saveUserInfoToSp(userInfos);
		
		
	}
	
	
	public void saveUserInfoToSp(ArrayList<UserInfo> userInfo){
		Editor editor=sp.edit();
		Gson gson=new Gson();
		String infos=gson.toJson(userInfo);
		editor.putString("userinfos", infos);
		editor.commit();
		
	}
	
	
	public ArrayList<UserInfo> getUserInfoListFromSp(){
		
		String userInfo=sp.getString("userInfos", "");
		if(!"".equals(userInfo)){
			Gson gson=new Gson();
			ArrayList<UserInfo> userInfos=gson.fromJson(userInfo, new TypeToken<List<UserInfo>>(){}.getType());
			return userInfos;
			
		}else {
			return new ArrayList<UserInfo>();
		}
		
		
	}
	
	
	
	
	
	public class UserInfo{
		public String userName;
		public String telephone;
		public UserInfo(String userName, String telephone) {
			super();
			this.userName = userName;
			this.telephone = telephone;
		}
		public UserInfo() {
			super();
		}
		
		
		
		
	}
	
}
