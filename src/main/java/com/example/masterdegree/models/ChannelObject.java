package com.example.masterdegree.models;

import java.util.List;

public class ChannelObject {

    private String categoryName;
    private List<Channel> channels;

    public ChannelObject(String categoryName, List<Channel> channels) {
        this.categoryName = categoryName;
        this.channels = channels;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Channel> getChannels() {
        return channels;
    }

    public void setChannels(List<Channel> channels) {
        this.channels = channels;
    }
}
