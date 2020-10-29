package com.megagao.production.ssm.service;

import java.util.List;

import com.megagao.production.ssm.domain.Branch;
import com.megagao.production.ssm.domain.Dept;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;

public interface DeptService {
	public List<Dept> find();
	
	EUDataGridResult getList(int page, int rows) throws Exception;

	EUDataGridResult searchDeptById(int page, int rows, int id)
			throws Exception;

	EUDataGridResult searchDeptByName(int page, int rows, String name)
			throws Exception;
	
	CustomResult insert(Dept dept) throws Exception;
	
	Dept get(int id)throws Exception;
	
	CustomResult updateAll(Dept dept);
	

	CustomResult deleteBatch(int[] ids);

	CustomResult delete(int id);
}
