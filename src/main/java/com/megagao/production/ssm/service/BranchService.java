package com.megagao.production.ssm.service;

import java.util.List;

import com.megagao.production.ssm.domain.Branch;
import com.megagao.production.ssm.domain.COrder;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;

public interface BranchService {
	public List<Branch> find();

	/**
	 * 分页查询插件
	 * 
	 * @param page
	 * @param rows
	 * @return
	 * @throws Exception
	 */
	EUDataGridResult getList(int page, int rows) throws Exception;

	EUDataGridResult searchBranchById(int page, int rows, String id)
			throws Exception;

	EUDataGridResult searchBranchByShortName(int page, int rows,
			String short_name) throws Exception;

	EUDataGridResult searchBranchByName(int page, int rows, String name)
			throws Exception;
	CustomResult insert(Branch branch) throws Exception;

	Branch get(String branchId)throws Exception;

	CustomResult updateAll(Branch branch);

	CustomResult delete(String id);

	CustomResult deleteBatch(String[] ids);
}
