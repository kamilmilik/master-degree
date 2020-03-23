package com.example.masterdegree.services.operator;

import com.example.masterdegree.models.entity.Operator;
import com.example.masterdegree.models.dto.ResultTvPackage;
import com.example.masterdegree.repositories.OperatorsRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OperatorsServiceImpl implements OperatorsService {

    private Set<Operator> fetchedSelectedOperators;

    private OperatorsRepository operatorsRepository;


    @Autowired
    public OperatorsServiceImpl(OperatorsRepository operatorsRepository) {
        this.operatorsRepository = operatorsRepository;
        this.fetchedSelectedOperators = new HashSet<>();
    }

    @Override
    public void addFetchedOperatorResourceToFetchedList(Operator operator) {
        fetchedSelectedOperators.add(operator);
        System.out.println("Dodaje operatora");
    }

    @Override
    public void removeFetchedOperatorResourceFromList(Operator operator) {
        fetchedSelectedOperators.remove(operator);
    }

    @Override
    public Set<Operator> getFetchedSelectedOperators() {
        return fetchedSelectedOperators;
    }

    @Override
    public boolean isAnyOperatorSelected() {
        return getFetchedSelectedOperators().size() != 0;
    }

    @Override
    public List<Operator> getAllOperatorsFromDb() {
        return operatorsRepository.findAll();
    }

    @Override
    public List<ResultTvPackage> getResultBySelectedOperators(List<ResultTvPackage> resultTvPackages) {
//         Now this is very expensive because you create a stream for each instance in the data ResultTvPackage stream pipeline.
/*        resultTvPackages = resultTvPackages.stream().
                filter(resultTvPackage -> getFetchedOperators().stream()
                        .map(Operator::getId)
                        .anyMatch(objectId -> objectId.equals(resultTvPackage.getOperatorId()))).collect(Collectors.toList());*/
//      Better option
        Set<ObjectId> objectIdSet = getFetchedSelectedOperators().stream().map(Operator::get_id).collect(Collectors.toSet());
        resultTvPackages = resultTvPackages.stream().filter(resultTvPackage -> objectIdSet.contains(resultTvPackage.getOperatorId())).collect(Collectors.toList());

        return resultTvPackages;
    }
}
