package com.megagao.production.ssm.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Message {
	// 唯一标识，消息id
	private String id;
	// 消息标题
	private String title;
	// 消息内容
	private String content;
	private String messageType_id;
	public String getMessageType_id() {
		return messageType_id;
	}
	public void setMessageType_id(String messageType_id) {
		this.messageType_id = messageType_id;
	}
	// 消息类型，指向 t_messageType
	private String type;
	// 消息发布者，指向 t_user
	private String user_id;
	// 消息发布时间
	@JsonFormat(pattern = "yyyy-MM-dd kk:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date record_time;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public Date getRecord_time() {
		return record_time;
	}
	public void setRecord_time(Date record_time) {
		this.record_time = record_time;
	}
	
	
}
