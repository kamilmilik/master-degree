package com.example.masterdegree.models.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
@AllArgsConstructor
public class ChannelsGroupByCategory {

    @Id
    @NonNull
    private ObjectId id;
    @NonNull
    private String categoryName;
    @NonNull
    private List<Channel> channels;

}
