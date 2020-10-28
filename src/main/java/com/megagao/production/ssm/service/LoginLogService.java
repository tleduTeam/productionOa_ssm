package com.megagao.production.ssm.service;

import java.util.List;

import com.megagao.production.ssm.domain.LoginLog;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;

public interface LoginLogService {

	List<LoginLog> find() throws Exception;

	void insert(LoginLog loginLog);

	EUDataGridResult getList(int page, int rows) throws Exception;

	EUDataGridResult searchLoginLogByLoginLogId(Integer page, Integer rows,
			String id) throws Exception;

	EUDataGridResult searchLoginLogByLoginLogName(Integer page, Integer rows,
			String name) throws Exception;

	CustomResult delete(String string) throws Exception;

	CustomResult deleteBatch(String[] ids) throws Exception;
}
