package com.megagao.production.ssm.service;

import java.util.List;

import com.megagao.production.ssm.domain.Dept;
import com.megagao.production.ssm.domain.Staff;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;

public interface StaffService {
	public List<Staff> find();

	EUDataGridResult getList(int page, int rows) throws Exception;

	EUDataGridResult searchStaffById(int page, int rows, int id)
			throws Exception;

	CustomResult insert(Staff staff) throws Exception;

	Staff get(int id)throws Exception;

	CustomResult updateAll(Staff staff);

	CustomResult deleteBatch(int[] ids);

	CustomResult delete(int id);

	CustomResult changeStatus(String[] ids) throws Exception;
}