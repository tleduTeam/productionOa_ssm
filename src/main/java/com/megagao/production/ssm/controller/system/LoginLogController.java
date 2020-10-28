package com.megagao.production.ssm.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.megagao.production.ssm.domain.LoginLog;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.service.LoginLogService;

@Controller
@RequestMapping("/loginLog")
public class LoginLogController {

	@Autowired
	private LoginLogService LoginLogService;

	@RequestMapping("/find")
	public String find() throws Exception {
		return "loginLog_list";
	}

	@RequestMapping("/list")
	@ResponseBody
	public EUDataGridResult getItemList(Integer page, Integer rows,
			LoginLog LoginLog) throws Exception {
		EUDataGridResult result = LoginLogService.getList(page, rows);
		return result;
	}

	@RequestMapping(value = "/delete")
	@ResponseBody
	private CustomResult delete(String id) throws Exception {
		CustomResult result = LoginLogService.delete(id);
		return result;
	}

	@RequestMapping(value = "/delete_batch")
	@ResponseBody
	private CustomResult deleteBatch(String[] ids) throws Exception {
		CustomResult result = LoginLogService.deleteBatch(ids);
		return result;
	}

	// 根据角色id查找
	@RequestMapping("/search_loginLog_by_loginLogId")
	@ResponseBody
	public EUDataGridResult searchLoginLogByLoginLogId(Integer page,
			Integer rows, String searchValue) throws Exception {
		EUDataGridResult result = LoginLogService.searchLoginLogByLoginLogId(
				page, rows, searchValue);
		return result;
	}

	// 根据角色名查找
	@RequestMapping("/search_loginLog_by_loginLogName")
	@ResponseBody
	public EUDataGridResult searchLoginLogByLoginLogName(Integer page,
			Integer rows, String searchValue) throws Exception {
		EUDataGridResult result = LoginLogService.searchLoginLogByLoginLogName(
				page, rows, searchValue);
		return result;
	}
}
