package com.example.masterdegree.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class ChannelsGroupByCategory {

    @Id
    private ObjectId id;
    private String categoryName;
    private List<Channel> channels;

    public ChannelsGroupByCategory(ObjectId id, String categoryName, List<Channel> channels) {
        this.id = id;
        this.categoryName = categoryName;
        this.channels = channels;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
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
