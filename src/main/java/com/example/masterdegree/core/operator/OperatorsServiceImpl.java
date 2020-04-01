package com.example.masterdegree.core.operator;

import com.example.masterdegree.models.dto.OperatorRequestDto;
import com.example.masterdegree.models.dto.ResultTvPackagesResponseDto;
import com.example.masterdegree.models.entity.MainTvPackage;
import com.example.masterdegree.models.entity.Operator;
import com.example.masterdegree.models.dto.ResultTvPackageResponseDto;
import com.example.masterdegree.models.mappers.OperatorMapper;
import com.example.masterdegree.repositories.OperatorsRepository;
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
//    private final FilteredResultService filteredResultService;

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
    public Operator getOperatorById(OperatorRequestDto operatorRequestDto) {
        return operatorsRepository.findById(operatorRequestDto.getId())
                .orElseThrow(() -> new RuntimeException("Operator with id: " + operatorRequestDto.getId() + " doesn't exist"));
    }

    @Override
    public List<MainTvPackage> getMainTvPackagesByIdOperator(String id) {
        return operatorsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Operator with id: " + id + " doesn't exist"))
                .getTvPackages();
    }

    @Override
    public ResultTvPackagesResponseDto getFilteredTvPackagesByOperatorId(String id) {
        Operator operator = operatorsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Operator with id: " + id + " doesn't exist"));
//        return new ResultTvPackagesResponseDto(filteredResultService.createFilteredTvPackagesByOperator(operator));
        return null;
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
    public List<OperatorRequestDto> getOperators() {
        return operatorsRepository.findAll().stream().map(operatorMapper::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<Operator> getAllOperatorsFromDb() {
        return operatorsRepository.findAll();
    }


    @Override
    public List<ResultTvPackageResponseDto> getResultBySelectedOperators(List<ResultTvPackageResponseDto> resultTvPackageResponseDtos) {
//         Now this is very expensive because you create a stream for each instance in the data ResultTvPackageResponseDto stream pipeline.
/*        resultTvPackageResponseDtos = resultTvPackageResponseDtos.stream().
                filter(resultTvPackage -> getFetchedOperators().stream()
                        .map(Operator::getId)
                        .anyMatch(objectId -> objectId.equals(resultTvPackage.getOperatorId()))).collect(Collectors.toList());*/
//      Better option
        Set<String> objectIdSet = getFetchedSelectedOperators().stream().map(Operator::getId).collect(Collectors.toSet());
        resultTvPackageResponseDtos = resultTvPackageResponseDtos.stream().filter(resultTvPackage -> objectIdSet.contains(resultTvPackage.getOperatorId())).collect(Collectors.toList());

        return resultTvPackageResponseDtos;
    }
}
