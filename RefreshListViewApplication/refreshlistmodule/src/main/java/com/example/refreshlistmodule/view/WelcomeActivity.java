package com.example.refreshlistmodule.view;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.refreshlistmodule.R;
import com.example.refreshlistmodule.entity.User;
import com.example.refreshlistmodule.util.ConstantClient;
import com.example.refreshlistmodule.util.HttpURLConnectionUtil;
import com.example.refreshlistmodule.util.SaveUserInfoUtil1;
import com.example.refreshlistmodule.util.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WelcomeActivity extends Activity implements OnClickListener{
	private EditText et_name;
	private EditText et_password;
	private Button bt_login;
	private Intent intent;
	private int drawId;
	private ArrayList<User> list;
	SharedPreferences sp;
	private SaveUserInfoUtil1 uiUtil;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		et_name=(EditText) findViewById(R.id.et_name);
		et_password=(EditText) findViewById(R.id.et_password);
		bt_login=(Button) findViewById(R.id.bt_login);
		sp=getSharedPreferences("config",MODE_PRIVATE);
	  //  uiUtil=new SaveUserInfoUtil1(sp);
	  //  list=uiUtil.getUserInfoListFromSp();
	    bt_login.setOnClickListener(this);
	}

	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bt_login:
			String name=et_name.getText().toString();
			String passWord=et_password.getText().toString();
			if(!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(passWord)){
				if(name.trim().length()==0||passWord.trim().length()==0){
					ToastUtil.show(WelcomeActivity.this, "用户名和密码不为空");
				}
				checkLogin(name.trim(), passWord.trim());
				Log.i("WelcomeActivity", name+"  "+passWord );
			}else{
				ToastUtil.show(WelcomeActivity.this, "用户名和密码不为空");
			}
			break;

		
		}
	}
	
	
	private void checkLogin(final String name,final String passWord){
		String url="http://192.168.1.83:8080/Test_Demon_Server/login?";
		Map<String,Object> params=new HashMap<String, Object>();
		
	
		params.put("userName", name);
		params.put("passWord", passWord);
		HttpURLConnectionUtil.sessionID=null;
		HttpURLConnectionUtil.asynPost(url, params,new HttpURLConnectionUtil.ResultCallback<String>() {

			@Override
			public void onError(String errorMsg) {
				// TODO Auto-generated method stub
				ToastUtil.show(WelcomeActivity.this,
						ConstantClient.EROORMSG);
			}

			@Override
			public void onSuccess(String returnData_json) {
				// TODO Auto-generated method stub
			//	HashMap<String, Object> returnData=ParserUtil.parserInfo(returnData_json);
			//	Log.i("WelcomeActivity",  returnData.get(ConstantClient.STATUS)+"");
			if(returnData_json.equals("ok")){
				intent=new Intent(WelcomeActivity.this,UserListActivity.class);
				startActivity(intent);
			}
	
		
			}
		});
	
	}
	
	
	
	
	
	
	
	
	
	
	


	
}
