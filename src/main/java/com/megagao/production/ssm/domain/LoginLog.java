package com.megagao.production.ssm.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.megagao.production.ssm.domain.authority.SysUser;

public class LoginLog {
	private int id;

	// 指定时间格式向前端传递数据
	@JsonFormat(pattern = "yyyy-MM-dd kk:mm:ss", timezone = "GMT+8")
	private Date come_time;
	private String ip;
	private SysUser sysUser;

	private String username;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCome_time() {
		return come_time;
	}

	public void setCome_time(Date come_time) {
		this.come_time = come_time;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public LoginLog() {
		super();
	}

	public LoginLog(int id, Date come_time, String ip, SysUser sysUser,
			String username) {
		super();
		this.id = id;
		this.come_time = come_time;
		this.ip = ip;
		this.sysUser = sysUser;
		this.username = username;
	}

	public LoginLog(int id, Date come_time, String ip, SysUser sysUser) {
		super();
		this.id = id;
		this.come_time = come_time;
		this.ip = ip;
		this.sysUser = sysUser;
	}

	public LoginLog(Date come_time, String ip, SysUser sysUser) {
		super();
		this.come_time = come_time;
		this.ip = ip;
		this.sysUser = sysUser;
	}

	public LoginLog(Date come_time, String ip, SysUser sysUser, String username) {
		super();
		this.come_time = come_time;
		this.ip = ip;
		this.sysUser = sysUser;
		this.username = username;
	}

	public LoginLog(Date come_time, String ip) {
		super();
		this.come_time = come_time;
		this.ip = ip;
	}

	@Override
	public String toString() {
		return "LoginLog [id=" + id + ", come_time=" + come_time + ", ip=" + ip
				+ ", sysUser=" + sysUser + ", username=" + username + "]";
	}

}
