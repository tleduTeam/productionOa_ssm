package com.megagao.production.ssm.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.service.MessageService;

@Controller
@RequestMapping("/notice")
public class NoticeController {
	@Autowired
	private MessageService messageService;

	@RequestMapping("/find")
	public String find() throws Exception {
		System.out.println(11111);
		return "notice_list";
	}

	@RequestMapping("/list")
	@ResponseBody
	public EUDataGridResult getList(Integer page, Integer rows)
			throws Exception {
		System.out.println(messageService);
		EUDataGridResult result = messageService.getList(page, rows);
		return result;
	}

	
}
