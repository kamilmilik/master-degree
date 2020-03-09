package com.example.masterdegree;

import com.example.masterdegree.models.Operator;
import com.example.masterdegree.models.TvPackage;
import org.bson.types.ObjectId;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TestOperatorModel {

    @Test
    public void testEqualsOperators(){
        ObjectId objectId = ObjectId.get();
        List<TvPackage> tvPackageList = new ArrayList<>();
        tvPackageList.add(new TvPackage(null, null, null, null, null, null, null));
        Operator operator1 = new Operator(objectId, null, null, null, null);
        Operator operator2 = new Operator(objectId, null, null, null, null);
        Operator operator3 = new Operator(objectId, "null", null, null, null);
        Operator operator4 = new Operator(objectId, "null", "null", null, null);
        Operator operator5 = new Operator(objectId, "null", "null", tvPackageList, null);
        Operator operator6 = new Operator(ObjectId.get(), null, null, null, null);
        assertThat(operator1.equals(operator2)).isEqualTo(true);
        assertThat(operator1.equals(operator3)).isEqualTo(true);
        assertThat(operator1.equals(operator4)).isEqualTo(true);
        assertThat(operator1.equals(operator5)).isEqualTo(true);
        assertThat(operator1.equals(operator6)).isEqualTo(false);
    }
}
