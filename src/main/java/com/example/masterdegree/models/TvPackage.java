package com.example.masterdegree.models;

import java.util.List;

public class TvPackage {

    private String name;
    private Double price;
    private String type;
    private String link;
    private String term;
    private String status;
    private List<ChannelsGroupByCategory> channelsGroupByCategory;

    public TvPackage(String name, Double price, String type, String link, String term, String status, List<ChannelsGroupByCategory> channelsGroupByCategory) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.link = link;
        this.term = term;
        this.status = status;
        this.channelsGroupByCategory = channelsGroupByCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ChannelsGroupByCategory> getChannelsGroupByCategory() {
        return channelsGroupByCategory;
    }

    public void setChannelsGroupByCategory(List<ChannelsGroupByCategory> channelsGroupByCategory) {
        this.channelsGroupByCategory = channelsGroupByCategory;
    }
}
