package com.example.masterdegree.models.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@EqualsAndHashCode(of = {"_id"})
@Document(collection = "operator")
public class Operator {

    @Id
    @NonNull
    private ObjectId _id;

    @NonNull
    private String name;

    private String imgSrc;

    @NonNull
    private List<MainTvPackage> tvPackages;

}
