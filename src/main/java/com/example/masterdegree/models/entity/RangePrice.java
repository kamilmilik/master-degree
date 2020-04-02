package com.example.masterdegree.models.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Value;


@Value
public class RangePrice {
    private double minPrice;
    private double maxPrice;

    public RangePrice(double[] rangePrice){
        this.minPrice = chooseMinimumPrice(rangePrice);
        this.maxPrice = chooseMaximumPrice(rangePrice);
    }

    private double chooseMinimumPrice(double[] rangePrice) {
        return Math.min(rangePrice[0], rangePrice[1]);
    }

    private double chooseMaximumPrice(double[] rangePrice) {
        return Math.max(rangePrice[0], rangePrice[1]);
    }

    public boolean isBetween(double price){
        return getMinPrice() <= price && price <= getMaxPrice();
    }

}
