package com.megagao.production.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.megagao.production.ssm.domain.authority.SysOperationLog;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.mapper.authority.SysOperationLogMapper;
import com.megagao.production.ssm.service.OperationLogService;

@Service
public class OperationLogServiceImpl implements OperationLogService {

	@Autowired
	SysOperationLogMapper sysOperationLogMapper;

	@Override
	public List<SysOperationLog> find() {
		return sysOperationLogMapper.find();
	}

	@Override
	public EUDataGridResult getList(int page, int rows) throws Exception {
		// 分页处理
		PageHelper.startPage(page, rows);
		List<SysOperationLog> list = sysOperationLogMapper.find();
		// 创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		// 取记录总条数
		PageInfo<SysOperationLog> pageInfo = new PageInfo<SysOperationLog>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public EUDataGridResult searchLoginLogBySysOperationLogId(Integer page,
			Integer rows, String id) throws Exception {
		// 分页处理
		PageHelper.startPage(page, rows);
		List<SysOperationLog> list = sysOperationLogMapper
				.searchSysOperationLogById(id);
		// 创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		// 取记录总条数
		PageInfo<SysOperationLog> pageInfo = new PageInfo<SysOperationLog>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public EUDataGridResult searchLoginLogBySysOperationLogName(Integer page,
			Integer rows, String name) throws Exception {
		// 分页处理
		PageHelper.startPage(page, rows);
		List<SysOperationLog> list = sysOperationLogMapper
				.searchSysOperationLogByName(name);
		// 创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		// 取记录总条数
		PageInfo<SysOperationLog> pageInfo = new PageInfo<SysOperationLog>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public void insert(SysOperationLog sysOperationLog) {
		sysOperationLogMapper.insert(sysOperationLog);
	}

	@Override
	public CustomResult delete(String string) throws Exception {
		int i = sysOperationLogMapper.deleteByLoginLogId(string);
		if (i > 0) {
			return CustomResult.ok();
		} else {
			return null;
		}
	}

	@Override
	public CustomResult deleteBatch(String[] ids) throws Exception {
		int i = sysOperationLogMapper.deleteBatch(ids);
		if (i > 0) {
			return CustomResult.ok();
		} else {
			return null;
		}
	}

}
