package com.megagao.production.ssm.controller.personal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.megagao.production.ssm.domain.Branch;
import com.megagao.production.ssm.domain.Dept;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.service.DeptService;

@Controller
@RequestMapping("/dept")
public class DeptController {
	@Autowired
	private DeptService deptService;
	
	@RequestMapping("/find")
	public String find(){
		return "dept_list";
		
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public EUDataGridResult getList(Integer page, Integer rows)
			throws Exception {
		EUDataGridResult result = deptService.getList(page, rows);
		System.out.println(result);
		return result;
	}
	
	@RequestMapping("/search_dept_by_id")
	@ResponseBody
	public EUDataGridResult searchBranchById(Integer page, Integer rows,
			String searchValue) throws Exception {
		EUDataGridResult result = deptService.searchDeptById(page, rows,
				searchValue);
		return result;
	}
	
		
		//跳转添加页面
		@RequestMapping("/add")
		public String add() throws Exception{
			return "dept_add";
		}

		@RequestMapping(value="/insert", method= RequestMethod.POST)
		@ResponseBody
		private CustomResult insert(@Valid Dept dept, BindingResult bindingResult) throws Exception {
			System.out.println(dept);
			CustomResult result;
			if(bindingResult.hasErrors()){
				FieldError fieldError = bindingResult.getFieldError();
				System.out.println(fieldError.getDefaultMessage());
				return CustomResult.build(100, fieldError.getDefaultMessage());
			}
			if(deptService.get(dept.getId()) != null){
				result = new CustomResult(0, "该机构编号已经存在，请更换机构编号！", null);
			}else{
				result = deptService.insert(dept);
			}
			return result;
		}
		
		@RequestMapping("/edit")
		public String edit() throws Exception{
			return "dept_edit";
		}

		@RequestMapping(value="/update_all")
		@ResponseBody
		private CustomResult updateAll(@Valid Dept dept, BindingResult bindingResult) throws Exception {
			if(bindingResult.hasErrors()){
				FieldError fieldError = bindingResult.getFieldError();
				return CustomResult.build(100, fieldError.getDefaultMessage());
			}
			return deptService.updateAll(dept);
		}
		
		@RequestMapping(value="/delete")
		@ResponseBody
		private CustomResult delete(int id) throws Exception {
			CustomResult result = deptService.delete(id);
			return result;
		}

		@RequestMapping(value="/delete_batch")
		@ResponseBody
		private CustomResult deleteBatch(int[] ids) throws Exception {
			CustomResult result = deptService.deleteBatch(ids);
			return result;
		}



}
