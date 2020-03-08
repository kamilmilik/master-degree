package com.example.masterdegree.services;

import com.example.masterdegree.models.Operator;
import com.example.masterdegree.models.ResultTvPackage;
import com.example.masterdegree.models.TvPackage;
import com.example.masterdegree.repositories.OperatorsRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OperatorsServiceImpl implements OperatorsService {

    private List<Operator> fetchedOperators;

    private OperatorsRepository operatorsRepository;


    @Autowired
    public OperatorsServiceImpl(OperatorsRepository operatorsRepository) {
        this.operatorsRepository = operatorsRepository;
        this.fetchedOperators = new ArrayList<>();
    }

    @Override
    public void addFetchedOperatorResourceToFetchedList(Operator operator) {
        fetchedOperators.add(operator);
        System.out.println("Dodaje operatora");
    }

    @Override
    public void removeFetchedOperatorResourceFromList(Operator operator) {
        fetchedOperators.remove(operator);
    }

    @Override
    public List<Operator> getFetchedOperators() {
        return fetchedOperators;
    }

    @Override
    public boolean isAnyOperatorSelected() {
        return getFetchedOperators().size() != 0;
    }

    @Override
    public List<Operator> getAllOperatorsFromDb() {
        return operatorsRepository.findAll();
    }

    @Override
    public List<ResultTvPackage> getResultByFetchedOperators(List<ResultTvPackage> resultTvPackages) {
//         Now this is very expensive because you create a stream for each instance in the data ResultTvPackage stream pipeline.
/*        resultTvPackages = resultTvPackages.stream().
                filter(resultTvPackage -> getFetchedOperators().stream()
                        .map(Operator::getId)
                        .anyMatch(objectId -> objectId.equals(resultTvPackage.getOperatorId()))).collect(Collectors.toList());*/
//      Better option
        Set<ObjectId> objectIdSet = getFetchedOperators().stream().map(Operator::getId).collect(Collectors.toSet());
        resultTvPackages = resultTvPackages.stream().filter(resultTvPackage -> objectIdSet.contains(resultTvPackage.getOperatorId())).collect(Collectors.toList());

        return resultTvPackages;
    }
}
