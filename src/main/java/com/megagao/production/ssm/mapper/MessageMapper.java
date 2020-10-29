package com.megagao.production.ssm.mapper;

import java.util.List;

import com.megagao.production.ssm.domain.Branch;
import com.megagao.production.ssm.domain.Message;

public interface MessageMapper {
	public List<Message> find();
	public List<Message> searchMessageById(String id);
	public List<Message> searchMessageByTitle(String title);
	int insert(Message message);
	Message selectByPrimaryKey(String messageId);
	int update(Message message);
	int deleteByPrimaryKey(String messageId);
	int deleteBatch(String[] ids);
}
