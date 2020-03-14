package com.example.masterdegree.models.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Operator {

    @Id
    @NonNull
    private ObjectId id;

    @NonNull
    private String name;

    private String imgSrc;

    @NonNull
    private List<MainTvPackage> tvPackages;

}
