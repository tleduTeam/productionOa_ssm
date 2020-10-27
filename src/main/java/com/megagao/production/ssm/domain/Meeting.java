package com.megagao.production.ssm.domain;

public class Meeting {
    private int id;

    private String type;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Meeting(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public Meeting() {}
}
