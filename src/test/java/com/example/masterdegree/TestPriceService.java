//package com.example.masterdegree;
//
//import com.example.masterdegree.models.dto.FilteredTvPackageResponseDto;
//import com.example.masterdegree.models.model.Operator;
//import com.example.masterdegree.models.dto.ResultTvPackageResponseDto;
//import com.example.masterdegree.services.operator.OperatorsServiceImpl;
//import com.example.masterdegree.services.price.PriceServiceImpl;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//
//@RunWith(MockitoJUnitRunner.class)
//public class TestPriceService {
//    @Mock
//    private OperatorsServiceImpl operatorsService;
//
//    @InjectMocks
//    private PriceServiceImpl priceService;
//
//    private TestDataGenerator testDataGenerator;
//
//    @Before
//    public void setup() {
//        this.testDataGenerator = new TestDataGenerator();
//
//    }
//
//    @Test
//    public void testGetMinimumSelectedPrice() {
//        double minPrice = 20.00;
//        double maxPrice = 40.00;
//        priceService.setSelectedRangePrice(new double[]{minPrice, maxPrice});
//        assertThat(priceService.getMinSelectedPrice()).isEqualTo(minPrice);
//
//        priceService.setSelectedRangePrice(new double[]{maxPrice, minPrice});
//        assertThat(priceService.getMinSelectedPrice()).isEqualTo(minPrice);
//    }
//
//    @Test
//    public void testGetMaximumSelectedPrice() {
//        double minPrice = 20.00;
//        double maxPrice = 40.00;
//        priceService.setSelectedRangePrice(new double[]{minPrice, maxPrice});
//        assertThat(priceService.getMaxSelectedPrice()).isEqualTo(maxPrice);
//
//        priceService.setSelectedRangePrice(new double[]{maxPrice, minPrice});
//        assertThat(priceService.getMaxSelectedPrice()).isEqualTo(maxPrice);
//    }
//
//    @Test
//    public void testSelectTvPackagesByRangePrice() {
//        testDataGenerator.generateOperatorWithManyTvPackage();
//        List<ResultTvPackageResponseDto> resultTvPackageResponseDtos = testDataGenerator.generateResultTvPackage();
//
//        priceService.setSelectedRangePrice(new double[]{20.00, 60.00});
//
//        Operator operatorCanalPlus = testDataGenerator.getOperatorCanalPlus();
//        Operator operatorCyfrowyPolsat = testDataGenerator.getOperatorCyfrowyPolsat();
//
//        FilteredTvPackageResponseDto filteredTvPackageComfortCanalPlus = new FilteredTvPackageResponseDto(testDataGenerator.getTvPackageComfort(), null, null);
//        FilteredTvPackageResponseDto filteredTvPackageComfortCyfrowyPolsat = new FilteredTvPackageResponseDto(testDataGenerator.getTvPackageComfort(), null, null);
//        ResultTvPackageResponseDto resultTvPackageCanalPlusComfort = new ResultTvPackageResponseDto(operatorCanalPlus.get_id(), operatorCanalPlus.getName(), operatorCanalPlus.getImgSrc(), filteredTvPackageComfortCanalPlus);
//        ResultTvPackageResponseDto resultTvPackageCyfrowyPolsatComfort = new ResultTvPackageResponseDto(operatorCyfrowyPolsat.get_id(), operatorCyfrowyPolsat.getName(), operatorCyfrowyPolsat.getImgSrc(), filteredTvPackageComfortCyfrowyPolsat);
//        List<ResultTvPackageResponseDto> expectedResultTvPackages = new ArrayList<>();
//        expectedResultTvPackages.add(resultTvPackageCanalPlusComfort);
//        expectedResultTvPackages.add(resultTvPackageCyfrowyPolsatComfort);
//        assertThat(this.priceService.getResultFilteredByRangePriceInMainTvPackages(resultTvPackageResponseDtos)).isEqualTo(expectedResultTvPackages);
//
//        expectedResultTvPackages.clear();
//        priceService.setSelectedRangePrice(new double[]{80.00, 20.00});
//        FilteredTvPackageResponseDto filteredTvPackageSuperPremiumCanalPlus = new FilteredTvPackageResponseDto(testDataGenerator.getTvPackageSuperpremium(), null, null);
//        FilteredTvPackageResponseDto filteredTvPackageSuperPremiumCyfrowyPolsat = new FilteredTvPackageResponseDto(testDataGenerator.getTvPackageSuperpremium(), null, null);
//
//        ResultTvPackageResponseDto resultTvPackageCanalPlusSuperPremium = new ResultTvPackageResponseDto(operatorCanalPlus.get_id(), operatorCanalPlus.getName(), operatorCanalPlus.getImgSrc(),filteredTvPackageSuperPremiumCanalPlus);
//        ResultTvPackageResponseDto resultTvPackageCyfrowyPolsatSuperPremium = new ResultTvPackageResponseDto(operatorCyfrowyPolsat.get_id(), operatorCyfrowyPolsat.getName(), operatorCyfrowyPolsat.getImgSrc(), filteredTvPackageSuperPremiumCyfrowyPolsat);
//        expectedResultTvPackages.add(resultTvPackageCanalPlusComfort);
//        expectedResultTvPackages.add(resultTvPackageCanalPlusSuperPremium);
//        expectedResultTvPackages.add(resultTvPackageCyfrowyPolsatComfort);
//        expectedResultTvPackages.add(resultTvPackageCyfrowyPolsatSuperPremium);
//        assertThat(this.priceService.getResultFilteredByRangePriceInMainTvPackages(resultTvPackageResponseDtos)).isEqualTo(expectedResultTvPackages);
//
//        expectedResultTvPackages.clear();
//        priceService.setSelectedRangePrice(new double[]{20.00, 30.00});
//        assertThat(this.priceService.getResultFilteredByRangePriceInMainTvPackages(resultTvPackageResponseDtos)).isEqualTo(expectedResultTvPackages);
//
//        expectedResultTvPackages.clear();
//        priceService.setSelectedRangePrice(new double[]{210.00, 230.00});
//        assertThat(this.priceService.getResultFilteredByRangePriceInMainTvPackages(resultTvPackageResponseDtos)).isEqualTo(expectedResultTvPackages);
//    }
//}
