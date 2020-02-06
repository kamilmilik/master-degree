package com.example.masterdegree.services;

import com.example.masterdegree.models.ChannelsGroupByCategory;

import java.util.List;

public interface ChannelsService {
    List<ChannelsGroupByCategory> findAll();

    ChannelsGroupByCategory save(ChannelsGroupByCategory var1);

}
