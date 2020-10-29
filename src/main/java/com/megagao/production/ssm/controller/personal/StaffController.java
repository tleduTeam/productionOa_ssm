package com.megagao.production.ssm.controller.personal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.megagao.production.ssm.domain.Dept;
import com.megagao.production.ssm.domain.Staff;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.service.StaffService;

@Controller
@RequestMapping("/staff")
public class StaffController {
	
	@Autowired
	private StaffService staffService;
	
	@RequestMapping("/find")
	public String find(){
		return "staff_list";
		
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public EUDataGridResult getList(Integer page, Integer rows)
			throws Exception {
		EUDataGridResult result = staffService.getList(page, rows);
		System.out.println(result);
		return result;
	}
	
	@RequestMapping("/search_staff_by_id")
	@ResponseBody
	public EUDataGridResult searchBranchById(Integer page, Integer rows,
			int searchValue) throws Exception {
		EUDataGridResult result = staffService.searchDeptById(page, rows,
				searchValue);
		return result;
	}
	
	//跳转添加页面
			@RequestMapping("/add")
			public String add() throws Exception{
				return "staff_add";
			}

			@RequestMapping(value="/insert", method= RequestMethod.POST)
			@ResponseBody
			private CustomResult insert(@Valid Staff staff, BindingResult bindingResult) throws Exception {
				System.out.println(staff);
				CustomResult result;
				if(bindingResult.hasErrors()){
					FieldError fieldError = bindingResult.getFieldError();
					System.out.println(fieldError.getDefaultMessage());
					return CustomResult.build(100, fieldError.getDefaultMessage());
				}
				if(staffService.get(staff.getId()) != null){
					result = new CustomResult(0, "该机构编号已经存在，请更换机构编号！", null);
				}else{
					result = staffService.insert(staff);
				}
				return result;
			}
			
			@RequestMapping("/edit")
			public String edit() throws Exception{
				return "staff_edit";
			}

			@RequestMapping(value="/update_all")
			@ResponseBody
			private CustomResult updateAll(@Valid Staff staff, BindingResult bindingResult) throws Exception {
				if(bindingResult.hasErrors()){
					FieldError fieldError = bindingResult.getFieldError();
					return CustomResult.build(100, fieldError.getDefaultMessage());
				}
				return staffService.updateAll(staff);
			}
			
			@RequestMapping(value="/delete")
			@ResponseBody
			private CustomResult delete(int id) throws Exception {
				CustomResult result = staffService.delete(id);
				return result;
			}

			@RequestMapping(value="/delete_batch")
			@ResponseBody
			private CustomResult deleteBatch(int[] ids) throws Exception {
				CustomResult result = staffService.deleteBatch(ids);
				return result;
			}
			
			@RequestMapping(value="/change_status")
			@ResponseBody
			public CustomResult changeStatus(String[] ids) throws Exception{
				CustomResult result = staffService.changeStatus(ids);
				return result;
			}
}
