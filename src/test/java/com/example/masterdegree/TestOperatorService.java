package com.example.masterdegree;

import com.example.masterdegree.models.Operator;
import com.example.masterdegree.models.ResultTvPackage;
import com.example.masterdegree.services.OperatorsServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class TestOperatorService {

    @InjectMocks
    private OperatorsServiceImpl operatorsService;

    private TestDataGenerator testDataGenerator;

    @Before
    public void setup() {
        testDataGenerator = new TestDataGenerator();
    }

    @Test
    public void testOneOperatorGetResultByOperators() {
        testDataGenerator.generateOperatorWithOneTvPackage();
        List<ResultTvPackage> resultTvPackages = testDataGenerator.generateResultTvPackage();

        Operator operatorCanalPlus = testDataGenerator.getOperatorCanalPlus();
        operatorsService.addFetchedOperatorResourceToFetchedList((testDataGenerator.getOperatorCanalPlus()));
        ResultTvPackage resultTvPackageComfort = new ResultTvPackage(operatorCanalPlus.getId(), operatorCanalPlus.getName(), operatorCanalPlus.getImgSrc(), testDataGenerator.getTvPackageComfort(), null);
        List<ResultTvPackage> expectedResultTvPackages = new ArrayList<>();
        expectedResultTvPackages.add(resultTvPackageComfort);
        assertThat(this.operatorsService.getResultByFetchedOperators(resultTvPackages)).isEqualTo(expectedResultTvPackages);
    }

    @Test
    public void testManyOperatorGetResultByOperators() {
        testDataGenerator.generateOperatorWithManyTvPackage();
        List<ResultTvPackage> resultTvPackages = testDataGenerator.generateResultTvPackage();

        operatorsService.addFetchedOperatorResourceToFetchedList(testDataGenerator.getOperatorCanalPlus());
        operatorsService.addFetchedOperatorResourceToFetchedList(testDataGenerator.getOperatorCyfrowyPolsat());

        Operator operatorCanalPlus = testDataGenerator.getOperatorCanalPlus();
        Operator operatorCyfrowyPolsat = testDataGenerator.getOperatorCyfrowyPolsat();
        ResultTvPackage resultTvPackageComfortCanalPlus = new ResultTvPackage(operatorCanalPlus.getId(), operatorCanalPlus.getName(), operatorCanalPlus.getImgSrc(), testDataGenerator.getTvPackageComfort(), null);
        ResultTvPackage resultTvPackageSuperpremiumCanalPlus = new ResultTvPackage(operatorCanalPlus.getId(), operatorCanalPlus.getName(), operatorCanalPlus.getImgSrc(), testDataGenerator.getTvPackageSuperpremium(), null);
        ResultTvPackage resultTvPackageComfortCyfrowyPolsat = new ResultTvPackage(operatorCyfrowyPolsat.getId(), operatorCyfrowyPolsat.getName(), operatorCyfrowyPolsat.getImgSrc(), testDataGenerator.getTvPackageComfort(), null);
        ResultTvPackage resultTvPackageSuperpremiumCyfrowyPolsat = new ResultTvPackage(operatorCyfrowyPolsat.getId(), operatorCyfrowyPolsat.getName(), operatorCyfrowyPolsat.getImgSrc(), testDataGenerator.getTvPackageSuperpremium(), null);

        List<ResultTvPackage> expectedResultTvPackages = new ArrayList<>();
        expectedResultTvPackages.add(resultTvPackageComfortCanalPlus);
        expectedResultTvPackages.add(resultTvPackageSuperpremiumCanalPlus);
        expectedResultTvPackages.add(resultTvPackageComfortCyfrowyPolsat);
        expectedResultTvPackages.add(resultTvPackageSuperpremiumCyfrowyPolsat);
        assertThat(this.operatorsService.getResultByFetchedOperators(resultTvPackages)).isEqualTo(expectedResultTvPackages);
    }
}
