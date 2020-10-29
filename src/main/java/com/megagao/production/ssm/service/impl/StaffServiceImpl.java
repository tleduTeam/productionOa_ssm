package com.megagao.production.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.megagao.production.ssm.domain.Branch;
import com.megagao.production.ssm.domain.Dept;
import com.megagao.production.ssm.domain.Staff;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.mapper.StaffMapper;
import com.megagao.production.ssm.service.StaffService;

@Service
public class StaffServiceImpl implements StaffService{

	@Autowired
	private StaffMapper staffMapper;
	
	@Override
	public List<Staff> find() {
		return staffMapper.find();
	}
	
	@Override
	public EUDataGridResult getList(int page, int rows) throws Exception {
		// 分页处理
		PageHelper.startPage(page, rows);
		List<Staff> list = staffMapper.find();
		// 创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		// 取记录总条数
		PageInfo<Staff> pageInfo = new PageInfo<Staff>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}
	
	@Override
	public EUDataGridResult searchStaffById(int page, int rows, int id)
			throws Exception {
		PageHelper.startPage(page, rows);
		List<Staff> list = staffMapper.searchStaffById(id);
		// 创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		// 取记录总条数
		PageInfo<Dept> pageInfo = new PageInfo<Dept>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	
	@Override
	public CustomResult insert(Staff staff) throws Exception {
		int i = staffMapper.insert(staff);
		if(i>0){
			return CustomResult.ok();
		}else{
			return CustomResult.build(101, "新增订单失败");
		}
	}
	
	@Override
	public Staff get(int id) throws Exception {
		return staffMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public CustomResult updateAll(Staff staff) {
		int i = staffMapper.update(staff);
		if(i>0){
			return CustomResult.ok();
		}else{
			return CustomResult.build(101, "修改订单失败");
		}
	}
	
	@Override
	public CustomResult delete(int id) {
		int i = staffMapper.deleteByPrimaryKey(id);
		if(i>0){
			return CustomResult.ok();
		}else{
			return null;
		}
	}

	@Override
	public CustomResult deleteBatch(int[] ids) {
		int i = staffMapper.deleteBatch(ids);
		if(i>0){
			return CustomResult.ok();
		}else{
			return null;
		}
	}
	
	@Override
	public CustomResult changeStatus(String[] ids) throws Exception{
		staffMapper.changeStatus(ids);
		return CustomResult.ok();
	}

}
