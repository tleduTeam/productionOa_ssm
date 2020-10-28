package com.megagao.production.ssm.service;

import java.util.List;

import com.megagao.production.ssm.domain.authority.SysOperationLog;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;

public interface OperationLogService {

	List<SysOperationLog> find() throws Exception;

	void insert(SysOperationLog sysOperationLog);

	EUDataGridResult getList(int page, int rows) throws Exception;

	EUDataGridResult searchLoginLogBySysOperationLogId(Integer page,
			Integer rows, String id) throws Exception;

	EUDataGridResult searchLoginLogBySysOperationLogName(Integer page,
			Integer rows, String name) throws Exception;

	CustomResult delete(String string) throws Exception;

	CustomResult deleteBatch(String[] ids) throws Exception;

}
