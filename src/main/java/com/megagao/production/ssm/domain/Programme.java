package com.megagao.production.ssm.domain;

import com.megagao.production.ssm.domain.authority.SysUser;

import java.util.Date;

public class Programme {
    private int id;

    private String title;

    private String address;

    private Meeting meeting;

    private Date begin_time;

    private Date end_time;

    private String content;

    private SysUser user;

    private Date create_date;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Meeting getMeeting() {
        return this.meeting;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }

    public Date getBegin_time() {
        return this.begin_time;
    }

    public void setBegin_time(Date begin_time) {
        this.begin_time = begin_time;
    }

    public Date getEnd_time() {
        return this.end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }

    public Programme(int id, String title, String address, Meeting meeting, Date begin_time, Date end_time, String content, SysUser user, Date create_date) {
        this.id = id;
        this.title = title;
        this.address = address;
        this.meeting = meeting;
        this.begin_time = begin_time;
        this.end_time = end_time;
        this.content = content;
        this.user = user;
        this.create_date = create_date;
    }

    public Date getCreate_date() {
        return this.create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public Programme() {
    }
}
