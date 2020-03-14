package com.example.masterdegree;

import com.example.masterdegree.services.operator.OperatorsService;
import com.example.masterdegree.services.result.ResultServiceImpl;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class TestResultService {

    @Mock
    private OperatorsService operatorsService;

    @InjectMocks
    private ResultServiceImpl resultService;

    private TestDataGenerator testDataGenerator;

    @Before
    public void setup() {
        testDataGenerator = new TestDataGenerator();
    }

//    @Test
//    public void testOneOperatorGetResultByOperators() {
//        testDataGenerator.generateOperatorWithOneTvPackage();
//
//        List<Operator> operators = new ArrayList<>();
//        operators.add(testDataGenerator.getOperatorCanalPlus());
//        when(this.operatorsService.getTvPackagesByOperator(testDataGenerator.getOperatorCanalPlus())).thenReturn(testDataGenerator.getOperatorCanalPlus().getTvPackages());
//        when(this.operatorsService.getFetchedOperators()).thenReturn(operators);
//        ResultTvPackage resultTvPackageComfort = new ResultTvPackage(testDataGenerator.getOperatorCanalPlus().getName(), testDataGenerator.getOperatorCanalPlus().getImgSrc(), testDataGenerator.getTvPackageComfort());
//        List<ResultTvPackage> resultTvPackages = new ArrayList<>();
//        resultTvPackages.add(resultTvPackageComfort);
//        assertThat(this.resultService.getResultByOperators(operatorsService.getFetchedOperators())).isEqualTo(resultTvPackages);
//    }
//
//    @Test
//    public void testManyOperatorGetResultByOperators() {
//        testDataGenerator.generateOperatorWithManyTvPackage();
//
//        List<Operator> operators = new ArrayList<>();
//        operators.add(testDataGenerator.getOperatorCanalPlus());
//        operators.add(testDataGenerator.getOperatorCyfrowyPolsat());
//
//        when(this.operatorsService.getTvPackagesByOperator(testDataGenerator.getOperatorCanalPlus())).thenReturn(testDataGenerator.getOperatorCanalPlus().getTvPackages());
//        when(this.operatorsService.getTvPackagesByOperator(testDataGenerator.getOperatorCyfrowyPolsat())).thenReturn(testDataGenerator.getOperatorCyfrowyPolsat().getTvPackages());
//
//        when(this.operatorsService.getFetchedOperators()).thenReturn(operators);
//
//        ResultTvPackage resultTvPackageComfortCanalPlus = new ResultTvPackage(testDataGenerator.getOperatorCanalPlus().getName(), testDataGenerator.getOperatorCanalPlus().getImgSrc(), testDataGenerator.getTvPackageComfort());
//        ResultTvPackage resultTvPackageSuperpremiumCanalPlus = new ResultTvPackage(testDataGenerator.getOperatorCanalPlus().getName(), testDataGenerator.getOperatorCanalPlus().getImgSrc(), testDataGenerator.getTvPackageSuperpremium());
//        ResultTvPackage resultTvPackageComfortCyfrowyPolsat = new ResultTvPackage(testDataGenerator.getOperatorCyfrowyPolsat().getName(), testDataGenerator.getOperatorCyfrowyPolsat().getImgSrc(), testDataGenerator.getTvPackageComfort());
//        ResultTvPackage resultTvPackageSuperpremiumCyfrowyPolsat = new ResultTvPackage(testDataGenerator.getOperatorCyfrowyPolsat().getName(), testDataGenerator.getOperatorCyfrowyPolsat().getImgSrc(), testDataGenerator.getTvPackageSuperpremium());
//
//        List<ResultTvPackage> resultTvPackages = new ArrayList<>();
//        resultTvPackages.add(resultTvPackageComfortCanalPlus);
//        resultTvPackages.add(resultTvPackageSuperpremiumCanalPlus);
//        resultTvPackages.add(resultTvPackageComfortCyfrowyPolsat);
//        resultTvPackages.add(resultTvPackageSuperpremiumCyfrowyPolsat);
//        assertThat(this.resultService.getResultByOperators(operatorsService.getFetchedOperators())).isEqualTo(resultTvPackages);
//    }


}
