package com.megagao.production.ssm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.megagao.production.ssm.domain.Branch;
import com.megagao.production.ssm.domain.Dept;
import com.megagao.production.ssm.domain.authority.SysUser;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;

public interface DeptMapper {
	/**
	 * 部门遍历
	 * @return
	 */
	public List<Dept> find();
	
	public List<Dept> searchDeptById(String id);

	
	Dept selectByPrimaryKey(int id);
	
	int insert(Dept dept);

	int update(Dept dept);
	
	int deleteByPrimaryKey(int id);
	
	int deleteBatch(int[] ids);

}
