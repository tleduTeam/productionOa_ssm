package com.megagao.production.ssm.domain;

public class Branch {
	private String id;
	private String name;
	private String short_name;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShort_name() {
		return short_name;
	}
	public void setShort_name(String short_name) {
		this.short_name = short_name;
	}
	@Override
	public String toString() {
		return "Branch [id=" + id + ", name=" + name + ", short_name="
				+ short_name + "]";
	}
	public Branch(String id, String name, String short_name) {
		super();
		this.id = id;
		this.name = name;
		this.short_name = short_name;
	}
	public Branch() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
