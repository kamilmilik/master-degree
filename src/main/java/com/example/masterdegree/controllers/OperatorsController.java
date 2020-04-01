package com.example.masterdegree.controllers;

import com.example.masterdegree.models.dto.OperatorRequestDto;
import com.example.masterdegree.models.dto.ResultTvPackagesResponseDto;
import com.example.masterdegree.models.entity.Operator;
import com.example.masterdegree.core.operator.OperatorsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//@RepositoryRestController
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OperatorsController {

    private final OperatorsService operatorsService;

//    private final FilteredResultService filteredResultService;

    @GetMapping("/operators")
    public ResponseEntity<List<OperatorRequestDto>> getOperators() {
//        List<Operator> operators = operatorsService.getAllOperatorsFromDb();
//        operators.stream().map(Operator::getId).collect(Collectors.toList()).forEach(System.out::println);
        return new ResponseEntity<>(operatorsService.getOperators(), HttpStatus.OK);
    }

    @GetMapping("/operator") // http://localhost:8095/api/operator
    public ResponseEntity<ResultTvPackagesResponseDto> getFilteredTvPackagesByIdOperator(@RequestParam String id) {
        return new ResponseEntity<>(operatorsService.getFilteredTvPackagesByOperatorId(id),HttpStatus.ACCEPTED);
    }

    @PostMapping("/operators/not-selected")
    public ResponseEntity<Void> fetchNotSelectedOperator(@RequestBody OperatorRequestDto operatorRequestDto) {
        System.out.println("Jestem tutaj not selected");
        Operator operator = operatorsService.getOperatorById(operatorRequestDto);
        operatorsService.removeFetchedOperatorFromFetchedList(operator);
        System.out.println("Operatory: ");
        operatorsService.getFetchedSelectedOperators().forEach(System.out::println);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
