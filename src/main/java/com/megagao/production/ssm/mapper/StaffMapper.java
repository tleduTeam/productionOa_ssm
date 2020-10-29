package com.megagao.production.ssm.mapper;

import java.util.List;

import com.megagao.production.ssm.domain.Dept;
import com.megagao.production.ssm.domain.Staff;

public interface StaffMapper {
	public List<Staff> find();
	
	int insert(Staff staff);
	
	Staff selectByPrimaryKey(int id);
	
	int update(Staff staff);
	
	int deleteByPrimaryKey(int id);
	
	int deleteBatch(int[] ids);
	
	int changeStatus(String[] ids);

}
