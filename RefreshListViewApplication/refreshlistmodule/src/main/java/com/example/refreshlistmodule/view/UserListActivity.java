package com.example.refreshlistmodule.view;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ListView;

import com.example.refreshlistmodule.R;
import com.example.refreshlistmodule.adapter.UserListAdapter;
import com.example.refreshlistmodule.entity.User;
import com.example.refreshlistmodule.util.AsynImageLoader;
import com.example.refreshlistmodule.util.ConstantClient;
import com.example.refreshlistmodule.util.HttpURLConnectionUtil;
import com.example.refreshlistmodule.util.ToastUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserListActivity extends Activity {

	private UserListAdapter adapter;
	private ListView lv_userlist;

	private ArrayList<User> list;

	private ImageLoader imageLoader;
	private DisplayImageOptions options;
	private AsynImageLoader asynImageLoader;
	Gson gson;

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			list = (ArrayList<User>) msg.obj;
			adapter.setList(list);
			Log.i("UserListActivity", "传过来的list大小为" + list.size());
			adapter.notifyDataSetChanged();

		};

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_userlist);
		lv_userlist = (ListView) findViewById(R.id.lv_userlist);
		gson = new Gson();
		list = new ArrayList<User>();
		asynImageLoader = new AsynImageLoader();
		/*
		 * imageLoader = ImageLoader.getInstance(); options = new
		 * DisplayImageOptions.Builder()
		 * .showImageOnLoading(R.drawable.ic_launcher)
		 * .showImageForEmptyUri(R.drawable.ic_launcher)
		 * .showImageOnFail(R.drawable.ic_launcher).cacheInMemory(true)
		 * .cacheOnDisc(true).displayer(new RoundedBitmapDisplayer(20))
		 * .build();
		 */
		// adapter = new UserListAdapter(list,
		// UserListActivity.this,imageLoader,options);
		adapter = new UserListAdapter(list, UserListActivity.this,
				asynImageLoader);
		Log.i("UserListActivity", "一开始的list大小为" + list.size());
		lv_userlist.setAdapter(adapter);

		getUserList();

	}

	private void getUserList() {
		String url = "http://192.168.1.83:8080/Test_Demon_Server/list";
		Map<String, Object> params = new HashMap<String, Object>();
		HttpURLConnectionUtil.asynPost(url, params,
				new HttpURLConnectionUtil.ResultCallback<String>() {

					@Override
					public void onError(String errorMsg) {
						// TODO Auto-generated method stub
						ToastUtil.show(UserListActivity.this,
								ConstantClient.EROORMSG);
					}

					@Override
					public void onSuccess(String returnData_json) {
						// TODO Auto-generated method stub
						ArrayList<User> userlist = gson.fromJson(
								returnData_json,
								new TypeToken<ArrayList<User>>() {
								}.getType());
						Log.i("UserListActivity", "用户数量" + userlist.size());
						Message msg = handler.obtainMessage();
						msg.obj = userlist;
						handler.sendMessage(msg);
					}
				});

	}

}
