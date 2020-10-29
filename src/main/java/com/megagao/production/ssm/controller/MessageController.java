package com.megagao.production.ssm.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.megagao.production.ssm.domain.Message;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.service.MessageService;

@Controller
@RequestMapping("/message")
public class MessageController {
	@Autowired
	private MessageService messageService;

	@RequestMapping("/find")
	public String find() throws Exception {
		System.out.println(11111);
		return "message_list";
	}

	@RequestMapping("/list")
	@ResponseBody
	public EUDataGridResult getList(Integer page, Integer rows)
			throws Exception {
		System.out.println(messageService);
		EUDataGridResult result = messageService.getList(page, rows);
		return result;
	}
	
	@RequestMapping("/getMessage")
	@ResponseBody
	public List<Message> getMessage(){
		return this.messageService.find();
	}

	// 根据机构id查找
	@RequestMapping("/search_message_by_id")
	@ResponseBody
	public EUDataGridResult searchMessageById(Integer page, Integer rows,
			String searchValue) throws Exception {
		EUDataGridResult result = messageService.searchMessageById(page, rows,
				searchValue);
		return result;
	}
//
//	// 根据机构name查找
	@RequestMapping("/search_message_by_title")
	@ResponseBody
	public EUDataGridResult searchMessageByTitle(Integer page, Integer rows,
			String searchValue) throws Exception {
		searchValue = new String(searchValue.getBytes("ISO-8859-1"), "UTF-8");
		EUDataGridResult result = messageService.searchMessageByTitle(page, rows,
				searchValue);
		return result;
	}
//
	//跳转添加页面
	@RequestMapping("/add")
	public String add() throws Exception{
		return "message_add";
	}
//
	@RequestMapping(value="/insert", method= RequestMethod.POST)
	@ResponseBody
	private CustomResult insert(@Valid Message message, BindingResult bindingResult) throws Exception {
		CustomResult result;
		if(bindingResult.hasErrors()){
			FieldError fieldError = bindingResult.getFieldError();
			System.out.println(fieldError.getDefaultMessage());
			return CustomResult.build(100, fieldError.getDefaultMessage());
		}
		if(messageService.get(message.getId()) != null){
			result = new CustomResult(0, "该消息编号已经存在，请更换消息编号！", null);
		}else{
			result = messageService.insert(message);
		}
		return result;
	}
//
	@RequestMapping(value="/delete")
	@ResponseBody
	private CustomResult delete(String messageId) throws Exception {
		CustomResult result = messageService.delete(messageId);
		return result;
	}
//
	@RequestMapping(value="/delete_batch")
	@ResponseBody
	private CustomResult deleteBatch(String[] ids) throws Exception {
		CustomResult result = messageService.deleteBatch(ids);
		return result;
	}
//
	@RequestMapping("/edit")
	public String edit() throws Exception{
		return "message_edit";
	}

	@RequestMapping(value="/update_all")
	@ResponseBody
	private CustomResult updateAll(@Valid Message message, BindingResult bindingResult) throws Exception {
		if(bindingResult.hasErrors()){
			FieldError fieldError = bindingResult.getFieldError();
			return CustomResult.build(100, fieldError.getDefaultMessage());
		}
		return messageService.updateAll(message);
	}
	
}
