package com.megagao.production.ssm.mapper.authority;

import java.util.List;

import com.megagao.production.ssm.domain.authority.SysOperationLog;

public interface SysOperationLogMapper {

	public List<SysOperationLog> find();

	public void insert(SysOperationLog loginLog);

	public List<SysOperationLog> searchSysOperationLogById(String id);

	public List<SysOperationLog> searchSysOperationLogByName(String name);

	public void writeLog(SysOperationLog sysOperationLog);

	int deleteBatch(String[] ids);

	int deleteByLoginLogId(String operationLogId);
}