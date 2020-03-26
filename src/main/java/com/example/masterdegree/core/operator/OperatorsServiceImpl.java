package com.example.masterdegree.core.operator;

import com.example.masterdegree.models.dto.OperatorDto;
import com.example.masterdegree.models.entity.Operator;
import com.example.masterdegree.models.dto.ResultTvPackage;
import com.example.masterdegree.repositories.OperatorsRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OperatorsServiceImpl implements OperatorsService {

    private Set<Operator> fetchedSelectedOperators = new HashSet<>();
    private final OperatorsRepository operatorsRepository;
    private final OperatorMapper operatorMapper;

    @Override
    public void addFetchedOperatorToFetchedList(Operator operator) {
        fetchedSelectedOperators.add(operator);
        System.out.println("Dodaje operatora");
    }

    @Override
    public void removeFetchedOperatorFromFetchedList(Operator operator) {
        fetchedSelectedOperators.remove(operator);
    }

    @Override
    public Operator getOperatorById(OperatorDto operatorDto) {
        return operatorsRepository.findById(operatorDto.getId())
                .orElseThrow(() -> new RuntimeException("Operator with id: " + operatorDto.getId() + " doesn't exist"));
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
    public List<OperatorDto> getOperators() {
        return operatorsRepository.findAll().stream().map(operatorMapper::convertToDto).collect(Collectors.toList());
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
        Set<String> objectIdSet = getFetchedSelectedOperators().stream().map(Operator::getId).collect(Collectors.toSet());
        resultTvPackages = resultTvPackages.stream().filter(resultTvPackage -> objectIdSet.contains(resultTvPackage.getOperatorId())).collect(Collectors.toList());

        return resultTvPackages;
    }
}
