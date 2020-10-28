package com.megagao.production.ssm.domain.authority;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SysOperationLog {
	private int id;

	// 指定时间格式向前端传递数据
	@JsonFormat(pattern = "yyyy-MM-dd kk:mm:ss", timezone = "GMT+8")
	private Date come_time;

	private SysUser sysUser;

	private String l_desc;

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

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public String getL_desc() {
		return l_desc;
	}

	public void setL_desc(String l_desc) {
		this.l_desc = l_desc;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public SysOperationLog() {
		super();
	}

	public SysOperationLog(int id, Date come_time, SysUser sysUser,
			String l_desc, String username) {
		super();
		this.id = id;
		this.come_time = come_time;
		this.sysUser = sysUser;
		this.l_desc = l_desc;
		this.username = username;
	}

	public SysOperationLog(Date come_time, SysUser sysUser, String l_desc,
			String username) {
		super();
		this.come_time = come_time;
		this.sysUser = sysUser;
		this.l_desc = l_desc;
		this.username = username;
	}

	public SysOperationLog(Date come_time, SysUser sysUser, String username) {
		super();
		this.come_time = come_time;
		this.sysUser = sysUser;
		this.username = username;
	}

	public SysOperationLog(Date come_time, SysUser sysUser) {
		super();
		this.come_time = come_time;
		this.sysUser = sysUser;
	}

	@Override
	public String toString() {
		return "SysOperationLog [id=" + id + ", come_time=" + come_time
				+ ", sysUser=" + sysUser + ", l_desc=" + l_desc + ", username="
				+ username + "]";
	}

}
