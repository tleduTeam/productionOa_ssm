package com.megagao.production.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.megagao.production.ssm.domain.Message;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.mapper.MessageMapper;
import com.megagao.production.ssm.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {
	@Autowired
	private MessageMapper messageMapper;

	@Override
	public List<Message> find() {
		return messageMapper.find();
	}

	@Override
	public EUDataGridResult getList(int page, int rows) throws Exception {
		// 分页处理
		PageHelper.startPage(page, rows);
		List<Message> list = messageMapper.find();
		// 创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		// 取记录总条数
		PageInfo<Message> pageInfo = new PageInfo<Message>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public EUDataGridResult searchMessageById(int page, int rows, String id)
			throws Exception {
		// 分页处理
		PageHelper.startPage(page, rows);
		List<Message> list = messageMapper.searchMessageById(id);
		// 创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		// 取记录总条数
		PageInfo<Message> pageInfo = new PageInfo<Message>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public EUDataGridResult searchMessageByTitle(int page, int rows, String title) throws Exception {
		// 分页处理
		PageHelper.startPage(page, rows);
		List<Message> list = messageMapper.searchMessageByTitle(title);
		// 创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		// 取记录总条数
		PageInfo<Message> pageInfo = new PageInfo<Message>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}
//
//
	@Override
	public CustomResult insert(Message message) throws Exception {
		int i = messageMapper.insert(message);
		if(i>0){
			return CustomResult.ok();
		}else{
			return CustomResult.build(101, "新增订单失败");
		}
	}
//
	@Override
	public Message get(String messageId) throws Exception {
		return messageMapper.selectByPrimaryKey(messageId);
	}
//
	@Override
	public CustomResult updateAll(Message message) {
		int i = messageMapper.update(message);
		if(i>0){
			return CustomResult.ok();
		}else{
			return CustomResult.build(101, "修改消息失败");
		}
	}
//
	@Override
	public CustomResult delete(String messageId) {
		int i = messageMapper.deleteByPrimaryKey(messageId);
		if(i>0){
			return CustomResult.ok();
		}else{
			return null;
		}
	}
//
	@Override
	public CustomResult deleteBatch(String[] ids) {
		int i = messageMapper.deleteBatch(ids);
		if(i>0){
			return CustomResult.ok();
		}else{
			return null;
		}
	}
}
