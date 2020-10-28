package com.megagao.production.ssm.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.domain.vo.RoleVO;
import com.megagao.production.ssm.service.OperationLogService;

@Controller
@RequestMapping("/operationLog")
public class OperationLogController {

	@Autowired
	private OperationLogService operationLogService;

	@RequestMapping("/find")
	public String find() throws Exception {
		return "operationLog_list";
	}

	@RequestMapping("/list")
	@ResponseBody
	public EUDataGridResult getItemList(Integer page, Integer rows, RoleVO role)
			throws Exception {
		EUDataGridResult result = operationLogService.getList(page, rows);
		return result;
	}

	@RequestMapping(value = "/delete")
	@ResponseBody
	private CustomResult delete(String id) throws Exception {
		CustomResult result = operationLogService.delete(id);
		return result;
	}

	@RequestMapping(value = "/delete_batch")
	@ResponseBody
	private CustomResult deleteBatch(String[] ids) throws Exception {
		CustomResult result = operationLogService.deleteBatch(ids);
		return result;
	}

	// 根据角色id查找
	@RequestMapping("/search_operationLog_by_operationLogId")
	@ResponseBody
	public EUDataGridResult searchRoleByRoleId(Integer page, Integer rows,
			String searchValue) throws Exception {
		EUDataGridResult result = operationLogService
				.searchLoginLogBySysOperationLogId(page, rows, searchValue);
		return result;
	}

	// 根据角色名查找
	@RequestMapping("/search_operationLog_by_operationLogName")
	@ResponseBody
	public EUDataGridResult searchRoleByRoleName(Integer page, Integer rows,
			String searchValue) throws Exception {
		EUDataGridResult result = operationLogService
				.searchLoginLogBySysOperationLogName(page, rows, searchValue);
		return result;
	}
}
