package com.megagao.production.ssm.domain;

import com.megagao.production.ssm.domain.customize.CustomResult;

public class Dept {
	private int id;
	private String name;
	private String phone;
	private String tel;
	private String user_id;
	private String branch_id;
	private String name1;
	private String username;
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getBranch_id() {
		return branch_id;
	}
	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getName1() {
		return name1;
	}
	public void setName1(String name1) {
		this.name1 = name1;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Dept(int id, String name, String phone, String tel, String name1,
			String username) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.tel = tel;
		this.name1 = name1;
		this.username = username;
	}
	public Dept() {
		super();
	}
	@Override
	public String toString() {
		return "Dept [id=" + id + ", name=" + name + ", phone=" + phone
				+ ", tel=" + tel + ", name1=" + name1 + ", username="
				+ username + "]";
	}
	
	
	
}
