package com.megagao.production.ssm.mapper.authority;

import java.util.List;

import com.megagao.production.ssm.domain.LoginLog;

public interface SysLoginLogMapper {
	
	public List<LoginLog> find();

	public void insert(LoginLog loginLog);
	
	public List<LoginLog> searchLoginLogById(String id);
	
	public List<LoginLog> searchLoginLogByName(String name);
	
	int deleteBatch(String[] ids);
	
	int deleteByLoginLogId(String loginLogId);
	
}