package com.example.masterdegree.services;

import com.example.masterdegree.models.ChannelsGroupByCategory;
import com.example.masterdegree.repositories.ChannelsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChannelsServiceImpl implements ChannelsService{
    @Autowired
    private ChannelsRepository channelsRepository;

    @Override
    public List<ChannelsGroupByCategory> findAll() {
        return channelsRepository.findAll();
    }

    @Override
    public ChannelsGroupByCategory save(ChannelsGroupByCategory var1) {
        return channelsRepository.save(var1);
    }
}
