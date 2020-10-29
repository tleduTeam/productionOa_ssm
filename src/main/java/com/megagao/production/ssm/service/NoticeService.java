package com.megagao.production.ssm.service;

import java.util.List;

import com.megagao.production.ssm.domain.Branch;
import com.megagao.production.ssm.domain.Message;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;

public interface NoticeService {
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

	CustomResult updateAll(Message message);

}
