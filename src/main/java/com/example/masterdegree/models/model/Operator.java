package com.example.masterdegree.models.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collections;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Document(collection = "operator")
public class Operator {

    @Id
    @NonNull
    private String id;
    @NonNull
    private String name;
    private String imgSrc;
    @NonNull
    @Getter(AccessLevel.NONE)
    private List<MainTvPackage> tvPackages;

    public List<MainTvPackage> getTvPackages() {
        return Collections.unmodifiableList(tvPackages);
    }

}
