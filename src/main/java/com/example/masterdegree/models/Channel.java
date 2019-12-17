package com.example.masterdegree.models;

public class Channel {

    private String name;
    private String desc;
    private String imgSrc;

    public Channel(String name, String desc, String imgSrc) {
        this.name = name;
        this.desc = desc;
        this.imgSrc = imgSrc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }
}
