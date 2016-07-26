package com.example.refreshlistmodule.entity;



public class User {
	private String pic_url;
	private String name;
	private String phone;
	
	
	public User(String pic_url, String name, String phone) {
		super();
		this.pic_url = pic_url;
		this.name = name;
		this.phone = phone;
	}
	public String getPic_url() {
		return pic_url;
	}
	public void setPic_url(String pic_url) {
		this.pic_url = pic_url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	
	
}
