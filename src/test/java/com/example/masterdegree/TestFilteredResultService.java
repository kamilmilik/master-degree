//package com.example.masterdegree;
//
//import com.example.masterdegree.services.operator.OperatorsService;
//import com.example.masterdegree.services.filteredresult.FilteredResultServiceImpl;
//import org.junit.Before;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@RunWith(MockitoJUnitRunner.class)
//public class TestFilteredResultService {
//
//    @Mock
//    private OperatorsService operatorsService;
//
//    @InjectMocks
//    private FilteredResultServiceImpl resultService;
//
//    private TestDataGenerator testDataGenerator;
//
//    @Before
//    public void setup() {
//        testDataGenerator = new TestDataGenerator();
//    }
//
////    @Test
////    public void testOneOperatorGetResultByOperators() {
////        testDataGenerator.generateOperatorWithOneTvPackage();
////
////        List<Operator> operators = new ArrayList<>();
////        operators.add(testDataGenerator.getOperatorCanalPlus());
////        when(this.operatorsService.getTvPackagesByOperator(testDataGenerator.getOperatorCanalPlus())).thenReturn(testDataGenerator.getOperatorCanalPlus().getTvPackages());
////        when(this.operatorsService.getFetchedOperators()).thenReturn(operators);
////        ResultTvPackageResponseDto resultTvPackageComfort = new ResultTvPackageResponseDto(testDataGenerator.getOperatorCanalPlus().getName(), testDataGenerator.getOperatorCanalPlus().getImgSrc(), testDataGenerator.getTvPackageComfort());
////        List<ResultTvPackageResponseDto> resultTvPackageResponseDtos = new ArrayList<>();
////        resultTvPackageResponseDtos.add(resultTvPackageComfort);
////        assertThat(this.resultService.getResultByOperators(operatorsService.getFetchedOperators())).isEqualTo(resultTvPackageResponseDtos);
////    }
////
////    @Test
////    public void testManyOperatorGetResultByOperators() {
////        testDataGenerator.generateOperatorWithManyTvPackage();
////
////        List<Operator> operators = new ArrayList<>();
////        operators.add(testDataGenerator.getOperatorCanalPlus());
////        operators.add(testDataGenerator.getOperatorCyfrowyPolsat());
////
////        when(this.operatorsService.getTvPackagesByOperator(testDataGenerator.getOperatorCanalPlus())).thenReturn(testDataGenerator.getOperatorCanalPlus().getTvPackages());
////        when(this.operatorsService.getTvPackagesByOperator(testDataGenerator.getOperatorCyfrowyPolsat())).thenReturn(testDataGenerator.getOperatorCyfrowyPolsat().getTvPackages());
////
////        when(this.operatorsService.getFetchedOperators()).thenReturn(operators);
////
////        ResultTvPackageResponseDto resultTvPackageComfortCanalPlus = new ResultTvPackageResponseDto(testDataGenerator.getOperatorCanalPlus().getName(), testDataGenerator.getOperatorCanalPlus().getImgSrc(), testDataGenerator.getTvPackageComfort());
////        ResultTvPackageResponseDto resultTvPackageSuperpremiumCanalPlus = new ResultTvPackageResponseDto(testDataGenerator.getOperatorCanalPlus().getName(), testDataGenerator.getOperatorCanalPlus().getImgSrc(), testDataGenerator.getTvPackageSuperpremium());
////        ResultTvPackageResponseDto resultTvPackageComfortCyfrowyPolsat = new ResultTvPackageResponseDto(testDataGenerator.getOperatorCyfrowyPolsat().getName(), testDataGenerator.getOperatorCyfrowyPolsat().getImgSrc(), testDataGenerator.getTvPackageComfort());
////        ResultTvPackageResponseDto resultTvPackageSuperpremiumCyfrowyPolsat = new ResultTvPackageResponseDto(testDataGenerator.getOperatorCyfrowyPolsat().getName(), testDataGenerator.getOperatorCyfrowyPolsat().getImgSrc(), testDataGenerator.getTvPackageSuperpremium());
////
////        List<ResultTvPackageResponseDto> resultTvPackageResponseDtos = new ArrayList<>();
////        resultTvPackageResponseDtos.add(resultTvPackageComfortCanalPlus);
////        resultTvPackageResponseDtos.add(resultTvPackageSuperpremiumCanalPlus);
////        resultTvPackageResponseDtos.add(resultTvPackageComfortCyfrowyPolsat);
////        resultTvPackageResponseDtos.add(resultTvPackageSuperpremiumCyfrowyPolsat);
////        assertThat(this.resultService.getResultByOperators(operatorsService.getFetchedOperators())).isEqualTo(resultTvPackageResponseDtos);
////    }
//
//
//}
