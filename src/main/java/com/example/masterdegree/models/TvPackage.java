package com.example.masterdegree.models;

import java.util.List;

public class TvPackage {

    private String name;
    private Double price;
    private String type;
    private List<ChannelObject> canalObject;

    public TvPackage(String name, Double price, String type, List<ChannelObject> canalObject) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.canalObject = canalObject;
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

    public List<ChannelObject> getCanalObject() {
        return canalObject;
    }

    public void setCanalObject(List<ChannelObject> canalObject) {
        this.canalObject = canalObject;
    }
}
