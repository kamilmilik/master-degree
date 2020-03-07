package com.example.masterdegree.services;

import com.example.masterdegree.models.Operator;
import com.example.masterdegree.models.TvPackage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OperatorsServiceImpl implements OperatorsService {

    private List<Operator> fetchedOperators;

    public OperatorsServiceImpl(){
        this.fetchedOperators = new ArrayList<>();
    }

    @Override
    public void addFetchedOperatorResourceToFetchedList(Operator operator) {
        fetchedOperators.add(operator);
        System.out.println("Dodaje operatora");
    }

    @Override
    public void removeFetchedOperatorResourceFromList(Operator operator){
        fetchedOperators.remove(operator);
    }

    @Override
    public List<Operator> getFetchedOperators() {
        return fetchedOperators;
    }


    @Override
    public List<TvPackage> getTvPackagesByOperator(Operator operator){
        return operator.getTvPackages();
    }
}
