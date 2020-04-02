package com.example.masterdegree.controllers;

import com.example.masterdegree.models.dto.OperatorRequestDto;
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

    @GetMapping("/operators")
    public ResponseEntity<List<OperatorRequestDto>> getOperators() {
        return new ResponseEntity<>(operatorsService.getOperatorsFromDbDto(), HttpStatus.OK);
    }

}
