package com.example.masterdegree.models.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@Document(collection = "channelsgroupbycategory")
public class ChannelsGroupByCategory {

    @Id
    @NonNull
    private String id;
    @NonNull
    private String categoryName;
    @NonNull
    private List<Channel> channels;

}
