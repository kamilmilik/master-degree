package com.example.masterdegree.models.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Document(collection = "operator")
public class Operator {

    @Id
    @Getter
    @NonNull
    private String id;
    @NonNull
    @Getter
    private String name;
    @Getter
    private String imgSrc;
    @NonNull
    @Getter
    private List<MainTvPackage> tvPackages;

}
