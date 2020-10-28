package com.megagao.production.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.megagao.production.ssm.domain.LoginLog;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.mapper.authority.SysLoginLogMapper;
import com.megagao.production.ssm.service.LoginLogService;

@Service
public class LoginLogServiceImpl implements LoginLogService {

	@Autowired
	SysLoginLogMapper sysLoginLogMapper;

	@Override
	public List<LoginLog> find() {
		return sysLoginLogMapper.find();
	}

	@Override
	public EUDataGridResult getList(int page, int rows) throws Exception {
		// 分页处理
		PageHelper.startPage(page, rows);
		List<LoginLog> list = sysLoginLogMapper.find();
		// 创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		// 取记录总条数
		PageInfo<LoginLog> pageInfo = new PageInfo<LoginLog>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public EUDataGridResult searchLoginLogByLoginLogId(Integer page,
			Integer rows, String id) throws Exception {
		// 分页处理
		PageHelper.startPage(page, rows);
		List<LoginLog> list = sysLoginLogMapper.searchLoginLogById(id);
		// 创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		// 取记录总条数
		PageInfo<LoginLog> pageInfo = new PageInfo<LoginLog>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public EUDataGridResult searchLoginLogByLoginLogName(Integer page,
			Integer rows, String name) throws Exception {
		// 分页处理
		PageHelper.startPage(page, rows);
		List<LoginLog> list = sysLoginLogMapper.searchLoginLogByName(name);
		// 创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		// 取记录总条数
		PageInfo<LoginLog> pageInfo = new PageInfo<LoginLog>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public void insert(LoginLog loginLog) {
		sysLoginLogMapper.insert(loginLog);
	}

	@Override
	public CustomResult delete(String loginLogId) throws Exception {
		int i = sysLoginLogMapper.deleteByLoginLogId(loginLogId);
		if (i > 0) {
			return CustomResult.ok();
		} else {
			return null;
		}
	}

	@Override
	public CustomResult deleteBatch(String[] ids) throws Exception {
		int i = sysLoginLogMapper.deleteBatch(ids);
		if (i > 0) {
			return CustomResult.ok();
		} else {
			return null;
		}
	}

}
