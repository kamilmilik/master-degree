package com.example.masterdegree;

import com.example.masterdegree.models.entity.Channel;
import com.example.masterdegree.models.entity.Operator;
import com.example.masterdegree.models.entity.MainTvPackage;
import org.bson.types.ObjectId;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TestOperatorModel {

    @Test
    public void testEqualsOperators(){
        ObjectId objectId = ObjectId.get();
        List<MainTvPackage> tvPackageList = new ArrayList<>();
        List<Channel> channels = new ArrayList<>();
        tvPackageList.add(new MainTvPackage("null", 4.0,"null", null, "null", "null", "null", channels, null));
        Operator operator1 = new Operator(objectId, "null", "null", tvPackageList);
        Operator operator2 = new Operator(objectId, "null23", "null", tvPackageList);
        Operator operator3 = new Operator(objectId, "null", null, tvPackageList);
        Operator operator4 = new Operator(objectId, "null", "null2", tvPackageList);
        Operator operator5 = new Operator(objectId, "nulasdasdl", "nullasdad", tvPackageList);
        Operator operator6 = new Operator(ObjectId.get(), "null", "null", tvPackageList);
        assertThat(operator1.equals(operator2)).isEqualTo(true);
        assertThat(operator1.equals(operator3)).isEqualTo(true);
        assertThat(operator1.equals(operator4)).isEqualTo(true);
        assertThat(operator1.equals(operator5)).isEqualTo(true);
        assertThat(operator1.equals(operator6)).isEqualTo(false);
    }
}
