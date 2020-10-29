package com.megagao.production.ssm.domain;

public class Staff {
	private int id;
	private String name;
	private String password;
	private String sex;
	/**
	 * 0 为禁用 1为可用
	 */
	private int status;
	private int dept_id;
	private String role_id;
	private String name1;
	private String role_name;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getDept_id() {
		return dept_id;
	}
	public void setDept_id(int dept_id) {
		this.dept_id = dept_id;
	}
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	public Staff(int id, String name, String password, String sex, int status,
			int dept_id, String role_id) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.sex = sex;
		this.status = status;
		this.dept_id = dept_id;
		this.role_id = role_id;
	}
	public Staff() {
		super();
	}
	
	
}
