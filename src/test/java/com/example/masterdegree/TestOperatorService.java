//package com.example.masterdegree;
//
//import com.example.masterdegree.models.dto.FilteredTvPackageResponseDto;
//import com.example.masterdegree.models.model.Operator;
//import com.example.masterdegree.models.dto.ResultTvPackageResponseDto;
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
//        List<ResultTvPackageResponseDto> resultTvPackageResponseDtos = testDataGenerator.generateResultTvPackage();
//
//        Operator operatorCanalPlus = testDataGenerator.getOperatorCanalPlus();
//        operatorsService.addFetchedOperatorResourceToFetchedList((testDataGenerator.getOperatorCanalPlus()));
//        FilteredTvPackageResponseDto filteredTvPackageResponseDto = new FilteredTvPackageResponseDto(testDataGenerator.getTvPackageComfort(), null, null);
//        ResultTvPackageResponseDto resultTvPackageComfort = new ResultTvPackageResponseDto(operatorCanalPlus.get_id(), operatorCanalPlus.getName(), operatorCanalPlus.getImgSrc(), filteredTvPackageResponseDto);
//        List<ResultTvPackageResponseDto> expectedResultTvPackages = new ArrayList<>();
//        expectedResultTvPackages.add(resultTvPackageComfort);
//        assertThat(this.operatorsService.getResultBySelectedOperators(resultTvPackageResponseDtos)).isEqualTo(expectedResultTvPackages);
//    }
//
//    @Test
//    public void testManyOperatorGetResultByOperators() {
//        testDataGenerator.generateOperatorWithManyTvPackage();
//        List<ResultTvPackageResponseDto> resultTvPackageResponseDtos = testDataGenerator.generateResultTvPackage();
//
//        operatorsService.addFetchedOperatorResourceToFetchedList(testDataGenerator.getOperatorCanalPlus());
//        operatorsService.addFetchedOperatorResourceToFetchedList(testDataGenerator.getOperatorCyfrowyPolsat());
//
//        Operator operatorCanalPlus = testDataGenerator.getOperatorCanalPlus();
//        Operator operatorCyfrowyPolsat = testDataGenerator.getOperatorCyfrowyPolsat();
//        FilteredTvPackageResponseDto filteredTvPackageComfortCanalPlus = new FilteredTvPackageResponseDto(testDataGenerator.getTvPackageComfort(), null, null);
//        FilteredTvPackageResponseDto filteredTvPackageSuperPremiumCanalPlus = new FilteredTvPackageResponseDto(testDataGenerator.getTvPackageSuperpremium(), null, null);
//        FilteredTvPackageResponseDto filteredTvPackageComfortCyfrowyPolsat = new FilteredTvPackageResponseDto(testDataGenerator.getTvPackageComfort(), null, null);
//        FilteredTvPackageResponseDto filteredTvPackageSuperPremiumCyfrowyPolsat = new FilteredTvPackageResponseDto(testDataGenerator.getTvPackageSuperpremium(), null, null);
//        ResultTvPackageResponseDto resultTvPackageComfortCanalPlus = new ResultTvPackageResponseDto(operatorCanalPlus.get_id(), operatorCanalPlus.getName(), operatorCanalPlus.getImgSrc(), filteredTvPackageComfortCanalPlus);
//        ResultTvPackageResponseDto resultTvPackageSuperpremiumCanalPlus = new ResultTvPackageResponseDto(operatorCanalPlus.get_id(), operatorCanalPlus.getName(), operatorCanalPlus.getImgSrc(), filteredTvPackageSuperPremiumCanalPlus);
//        ResultTvPackageResponseDto resultTvPackageComfortCyfrowyPolsat = new ResultTvPackageResponseDto(operatorCyfrowyPolsat.get_id(), operatorCyfrowyPolsat.getName(), operatorCyfrowyPolsat.getImgSrc(), filteredTvPackageComfortCyfrowyPolsat);
//        ResultTvPackageResponseDto resultTvPackageSuperpremiumCyfrowyPolsat = new ResultTvPackageResponseDto(operatorCyfrowyPolsat.get_id(), operatorCyfrowyPolsat.getName(), operatorCyfrowyPolsat.getImgSrc(), filteredTvPackageSuperPremiumCyfrowyPolsat);
//
//        List<ResultTvPackageResponseDto> expectedResultTvPackages = new ArrayList<>();
//        expectedResultTvPackages.add(resultTvPackageComfortCanalPlus);
//        expectedResultTvPackages.add(resultTvPackageSuperpremiumCanalPlus);
//        expectedResultTvPackages.add(resultTvPackageComfortCyfrowyPolsat);
//        expectedResultTvPackages.add(resultTvPackageSuperpremiumCyfrowyPolsat);
//        assertThat(this.operatorsService.getResultBySelectedOperators(resultTvPackageResponseDtos)).isEqualTo(expectedResultTvPackages);
//    }
//}
