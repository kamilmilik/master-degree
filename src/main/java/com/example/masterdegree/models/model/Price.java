package com.example.masterdegree.models.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Value;


@Value
public class Price {
    double maxPrice;

    public Price(double price){
        this.maxPrice = price;
    }

    public boolean isGreaterThan(double price){
        return getMaxPrice() >= price;
    }

}
