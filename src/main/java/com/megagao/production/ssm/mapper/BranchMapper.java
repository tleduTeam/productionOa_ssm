package com.megagao.production.ssm.mapper;

import java.util.List;

import com.megagao.production.ssm.domain.Branch;

public interface BranchMapper {
	public List<Branch> find();
	public List<Branch> searchBranchById(String id);
	public List<Branch> searchBranchByShortName(String short_name);
	public List<Branch> searchBranchByName(String name);
	int insert(Branch branch);
	Branch selectByPrimaryKey(String branchId);
	int update(Branch branch);
	int deleteByPrimaryKey(String branchId);
	int deleteBatch(String[] ids);

}
