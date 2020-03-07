package com.example.masterdegree.config;

import com.example.masterdegree.models.ChannelsGroupByCategory;
import com.example.masterdegree.models.Operator;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

// Thanks to this class ObjectId is sent.
@Configuration
public class RestConfig implements RepositoryRestConfigurer {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Operator.class);
        config.exposeIdsFor(ChannelsGroupByCategory.class);
    }
}