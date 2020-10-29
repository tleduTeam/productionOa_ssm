package com.megagao.production.ssm.service;

import java.util.List;

import com.megagao.production.ssm.domain.Branch;
import com.megagao.production.ssm.domain.Message;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;

public interface MessageService {
	public List<Message> find();

	/**
	 * 分页查询插件
	 * 
	 * @param page
	 * @param rows
	 * @return
	 * @throws Exception
	 */
	EUDataGridResult getList(int page, int rows) throws Exception;

	EUDataGridResult searchMessageById(int page, int rows, String id)
			throws Exception;
	EUDataGridResult searchMessageByTitle(int page, int rows, String title)
			throws Exception;
	CustomResult insert(Message message) throws Exception;
	
	Message get(String messageId)throws Exception;
	
	CustomResult updateAll(Message message);

	CustomResult delete(String id);

	CustomResult deleteBatch(String[] ids);
}
