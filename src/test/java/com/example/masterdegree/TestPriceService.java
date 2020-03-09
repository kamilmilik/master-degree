package com.example.masterdegree;

import com.example.masterdegree.models.Operator;
import com.example.masterdegree.models.ResultTvPackage;
import com.example.masterdegree.services.OperatorsServiceImpl;
import com.example.masterdegree.services.PriceServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(MockitoJUnitRunner.class)
public class TestPriceService {
    @Mock
    private OperatorsServiceImpl operatorsService;

    @InjectMocks
    private PriceServiceImpl priceService;

    private TestDataGenerator testDataGenerator;

    @Before
    public void setup() {
        this.testDataGenerator = new TestDataGenerator();

    }

    @Test
    public void testGetMinimumSelectedPrice() {
        double minPrice = 20.00;
        double maxPrice = 40.00;
        priceService.setSelectedRangePrice(new double[]{minPrice, maxPrice});
        assertThat(priceService.getMinSelectedPrice()).isEqualTo(minPrice);

        priceService.setSelectedRangePrice(new double[]{maxPrice, minPrice});
        assertThat(priceService.getMinSelectedPrice()).isEqualTo(minPrice);
    }

    @Test
    public void testGetMaximumSelectedPrice() {
        double minPrice = 20.00;
        double maxPrice = 40.00;
        priceService.setSelectedRangePrice(new double[]{minPrice, maxPrice});
        assertThat(priceService.getMaxSelectedPrice()).isEqualTo(maxPrice);

        priceService.setSelectedRangePrice(new double[]{maxPrice, minPrice});
        assertThat(priceService.getMaxSelectedPrice()).isEqualTo(maxPrice);
    }

    @Test
    public void testSelectTvPackagesByRangePrice() {
        testDataGenerator.generateOperatorWithManyTvPackage();
        List<ResultTvPackage> resultTvPackages = testDataGenerator.generateResultTvPackage();

        priceService.setSelectedRangePrice(new double[]{20.00, 60.00});

        Operator operatorCanalPlus = testDataGenerator.getOperatorCanalPlus();
        Operator operatorCyfrowyPolsat = testDataGenerator.getOperatorCyfrowyPolsat();

        ResultTvPackage resultTvPackageCanalPlusComfort = new ResultTvPackage(operatorCanalPlus.getId(), operatorCanalPlus.getName(), operatorCanalPlus.getImgSrc(), testDataGenerator.getTvPackageComfort(), null);
        ResultTvPackage resultTvPackageCyfrowyPolsatComfort = new ResultTvPackage(operatorCyfrowyPolsat.getId(), operatorCyfrowyPolsat.getName(), operatorCyfrowyPolsat.getImgSrc(), testDataGenerator.getTvPackageComfort(), null);
        List<ResultTvPackage> expectedResultTvPackages = new ArrayList<>();
        expectedResultTvPackages.add(resultTvPackageCanalPlusComfort);
        expectedResultTvPackages.add(resultTvPackageCyfrowyPolsatComfort);
        assertThat(this.priceService.getResultByRangePrice(resultTvPackages)).isEqualTo(expectedResultTvPackages);

        expectedResultTvPackages.clear();
        priceService.setSelectedRangePrice(new double[]{80.00, 20.00});
        ResultTvPackage resultTvPackageCanalPlusSuperPremium = new ResultTvPackage(operatorCanalPlus.getId(), operatorCanalPlus.getName(), operatorCanalPlus.getImgSrc(), testDataGenerator.getTvPackageSuperpremium(), null);
        ResultTvPackage resultTvPackageCyfrowyPolsatSuperPremium = new ResultTvPackage(operatorCyfrowyPolsat.getId(), operatorCyfrowyPolsat.getName(), operatorCyfrowyPolsat.getImgSrc(), testDataGenerator.getTvPackageSuperpremium(), null);
        expectedResultTvPackages.add(resultTvPackageCanalPlusComfort);
        expectedResultTvPackages.add(resultTvPackageCanalPlusSuperPremium);
        expectedResultTvPackages.add(resultTvPackageCyfrowyPolsatComfort);
        expectedResultTvPackages.add(resultTvPackageCyfrowyPolsatSuperPremium);
        assertThat(this.priceService.getResultByRangePrice(resultTvPackages)).isEqualTo(expectedResultTvPackages);

        expectedResultTvPackages.clear();
        priceService.setSelectedRangePrice(new double[]{20.00, 30.00});
        assertThat(this.priceService.getResultByRangePrice(resultTvPackages)).isEqualTo(expectedResultTvPackages);

        expectedResultTvPackages.clear();
        priceService.setSelectedRangePrice(new double[]{210.00, 230.00});
        assertThat(this.priceService.getResultByRangePrice(resultTvPackages)).isEqualTo(expectedResultTvPackages);
    }
}
