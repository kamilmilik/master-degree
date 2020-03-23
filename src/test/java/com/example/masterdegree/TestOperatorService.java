//package com.example.masterdegree;
//
//import com.example.masterdegree.models.dto.FilteredTvPackage;
//import com.example.masterdegree.models.entity.Operator;
//import com.example.masterdegree.models.dto.ResultTvPackage;
//import com.example.masterdegree.services.operator.OperatorsServiceImpl;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@RunWith(MockitoJUnitRunner.class)
//public class TestOperatorService {
//
//    @InjectMocks
//    private OperatorsServiceImpl operatorsService;
//
//    private TestDataGenerator testDataGenerator;
//
//    @Before
//    public void setup() {
//        testDataGenerator = new TestDataGenerator();
//    }
//
//    @Test
//    public void testOneOperatorGetResultByOperators() {
//        testDataGenerator.generateOperatorWithOneTvPackage();
//        List<ResultTvPackage> resultTvPackages = testDataGenerator.generateResultTvPackage();
//
//        Operator operatorCanalPlus = testDataGenerator.getOperatorCanalPlus();
//        operatorsService.addFetchedOperatorResourceToFetchedList((testDataGenerator.getOperatorCanalPlus()));
//        FilteredTvPackage filteredTvPackage = new FilteredTvPackage(testDataGenerator.getTvPackageComfort(), null, null);
//        ResultTvPackage resultTvPackageComfort = new ResultTvPackage(operatorCanalPlus.get_id(), operatorCanalPlus.getName(), operatorCanalPlus.getImgSrc(), filteredTvPackage);
//        List<ResultTvPackage> expectedResultTvPackages = new ArrayList<>();
//        expectedResultTvPackages.add(resultTvPackageComfort);
//        assertThat(this.operatorsService.getResultBySelectedOperators(resultTvPackages)).isEqualTo(expectedResultTvPackages);
//    }
//
//    @Test
//    public void testManyOperatorGetResultByOperators() {
//        testDataGenerator.generateOperatorWithManyTvPackage();
//        List<ResultTvPackage> resultTvPackages = testDataGenerator.generateResultTvPackage();
//
//        operatorsService.addFetchedOperatorResourceToFetchedList(testDataGenerator.getOperatorCanalPlus());
//        operatorsService.addFetchedOperatorResourceToFetchedList(testDataGenerator.getOperatorCyfrowyPolsat());
//
//        Operator operatorCanalPlus = testDataGenerator.getOperatorCanalPlus();
//        Operator operatorCyfrowyPolsat = testDataGenerator.getOperatorCyfrowyPolsat();
//        FilteredTvPackage filteredTvPackageComfortCanalPlus = new FilteredTvPackage(testDataGenerator.getTvPackageComfort(), null, null);
//        FilteredTvPackage filteredTvPackageSuperPremiumCanalPlus = new FilteredTvPackage(testDataGenerator.getTvPackageSuperpremium(), null, null);
//        FilteredTvPackage filteredTvPackageComfortCyfrowyPolsat = new FilteredTvPackage(testDataGenerator.getTvPackageComfort(), null, null);
//        FilteredTvPackage filteredTvPackageSuperPremiumCyfrowyPolsat = new FilteredTvPackage(testDataGenerator.getTvPackageSuperpremium(), null, null);
//        ResultTvPackage resultTvPackageComfortCanalPlus = new ResultTvPackage(operatorCanalPlus.get_id(), operatorCanalPlus.getName(), operatorCanalPlus.getImgSrc(), filteredTvPackageComfortCanalPlus);
//        ResultTvPackage resultTvPackageSuperpremiumCanalPlus = new ResultTvPackage(operatorCanalPlus.get_id(), operatorCanalPlus.getName(), operatorCanalPlus.getImgSrc(), filteredTvPackageSuperPremiumCanalPlus);
//        ResultTvPackage resultTvPackageComfortCyfrowyPolsat = new ResultTvPackage(operatorCyfrowyPolsat.get_id(), operatorCyfrowyPolsat.getName(), operatorCyfrowyPolsat.getImgSrc(), filteredTvPackageComfortCyfrowyPolsat);
//        ResultTvPackage resultTvPackageSuperpremiumCyfrowyPolsat = new ResultTvPackage(operatorCyfrowyPolsat.get_id(), operatorCyfrowyPolsat.getName(), operatorCyfrowyPolsat.getImgSrc(), filteredTvPackageSuperPremiumCyfrowyPolsat);
//
//        List<ResultTvPackage> expectedResultTvPackages = new ArrayList<>();
//        expectedResultTvPackages.add(resultTvPackageComfortCanalPlus);
//        expectedResultTvPackages.add(resultTvPackageSuperpremiumCanalPlus);
//        expectedResultTvPackages.add(resultTvPackageComfortCyfrowyPolsat);
//        expectedResultTvPackages.add(resultTvPackageSuperpremiumCyfrowyPolsat);
//        assertThat(this.operatorsService.getResultBySelectedOperators(resultTvPackages)).isEqualTo(expectedResultTvPackages);
//    }
//}
