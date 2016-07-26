package com.example.refreshlistmodule.util;

public class SaveUserInfoUtil1 {
	/*private SharedPreferences sp;

	public SaveUserInfoUtil1(SharedPreferences sp) {
		super();
		this.sp = sp;
	}
	
	public User getUserInfo(int drawId,String userName,String passWord){
		return new User(,userName,passWord);
		
	}
	
	
	public void insertUserInfos(User info){
		ArrayList<User> userInfos=getUserInfoListFromSp();
		userInfos.add(info);
		saveUserInfoToSp(userInfos);
		
		
	}
	
	
	public void saveUserInfoToSp(ArrayList<User> userInfo){
		Editor editor=sp.edit();
		Gson gson=new Gson();
		String infos=gson.toJson(userInfo);
		editor.putString("userInfos", infos);
		editor.commit();
		
	}
	
	
	public ArrayList<User> getUserInfoListFromSp(){
		
		String userInfo=sp.getString("userInfos", "");
		if(!"".equals(userInfo)){
			Gson gson=new Gson();
			ArrayList<User> userInfos=gson.fromJson(userInfo, new TypeToken<List<User>>(){}.getType());
			return userInfos;
			
		}else {
			return new ArrayList<User>();
		}
		
		
	}
	
	
	
	
	
	/*public class UserInfo{
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
	*/
}
