package com.example.refreshlistmodule.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.refreshlistmodule.R;
import com.example.refreshlistmodule.entity.User;
import com.example.refreshlistmodule.util.AsynImageLoader;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

public class UserListAdapter extends BaseAdapter{
		private ArrayList<User> list;
		private LayoutInflater inflater;
		private Context context;
		User user;
	    private ImageLoader imageLoader;
	    private DisplayImageOptions options;
		private AsynImageLoader asynImageLoader;
		
		
	public AsynImageLoader getAsynImageLoader() {
			return asynImageLoader;
		}

		public void setAsynImageLoader(AsynImageLoader asynImageLoader) {
			this.asynImageLoader = asynImageLoader;
		}

	public ImageLoader getImageLoader() {
			return imageLoader;
		}

		public void setImageLoader(ImageLoader imageLoader) {
			this.imageLoader = imageLoader;
		}

		public DisplayImageOptions getOptions() {
			return options;
		}

		public void setOptions(DisplayImageOptions options) {
			this.options = options;
		}

	public ArrayList<User> getList() {
			return list;
		}

		public void setList(ArrayList<User> list) {
			this.list = list;
		}

		public LayoutInflater getInflater() {
			return inflater;
		}

		public void setInflater(LayoutInflater inflater) {
			this.inflater = inflater;
		}

		public Context getContext() {
			return context;
		}

		public void setContext(Context context) {
			this.context = context;
		}

	public UserListAdapter(ArrayList<User> list, Context context,ImageLoader imageLoader,DisplayImageOptions options) {
			super();
			this.list = list;
			this.context = context;
			this.imageLoader=imageLoader;
			this.options=options;
			inflater=LayoutInflater.from(context);
		}

	public UserListAdapter(ArrayList<User> list, Context context,AsynImageLoader asynImageLoader) {
		super();
		this.list = list;
		this.context = context;
		this.asynImageLoader=asynImageLoader;
		inflater=LayoutInflater.from(context);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public User getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder=null;
		if(convertView==null){
			viewHolder=new ViewHolder();
			convertView=inflater.inflate(R.layout.user_item, null);
			
			viewHolder.iv_headimage=(ImageView) convertView.findViewById(R.id.iv_headimage);
			viewHolder.tv_name=(TextView) convertView.findViewById(R.id.tv_name);
			viewHolder.tv_phone=(TextView) convertView.findViewById(R.id.tv_phone);
			viewHolder.bt_delete=(Button) convertView.findViewById(R.id.bt_delete);
			convertView.setTag(viewHolder);
		}else{
			viewHolder=(ViewHolder) convertView.getTag();
		}
	
		
		//imageLoader.displayImage(getItem(position).getPic_url(), viewHolder.iv_headimage,options);
		asynImageLoader.showImageAsyn(viewHolder.iv_headimage,getItem(position).getPic_url() , R.mipmap.ic_launcher);
		viewHolder.tv_name.setText(getItem(position).getName());
		viewHolder.tv_phone.setText(getItem(position).getPhone());
		viewHolder.bt_delete.setOnClickListener(new OnClickListener() {
			
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//deleteUser(position);
				list.remove(position);
				notifyDataSetChanged();
			}
		});
		
		return convertView;
	}

	protected void deleteUser(User user) {
		// TODO Auto-generated method stub
		list.remove(user);
		notifyDataSetChanged();
	}

	class ViewHolder{
		ImageView iv_headimage;
		TextView tv_name;
		TextView tv_phone;
		Button bt_delete;
		
	}
	
}
