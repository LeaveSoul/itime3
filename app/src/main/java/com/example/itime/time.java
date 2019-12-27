package com.example.itime;

import java.io.Serializable;

public class time implements Serializable {
    private String name;

    public time(String name, int coverRecourceID, String text) {
        this.setName(name);
        this.setCoverRecourceID(coverRecourceID);
        this.setText(text);
    }

    private int coverRecourceID;
    private String text;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCoverRecourceID() {
        return coverRecourceID;
    }

    public void setCoverRecourceID(int coverRecourceID) {
        this.coverRecourceID = coverRecourceID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
