package com.demo.prices;

import com.demo.prices.domain.Price;
import com.demo.prices.infrastructure.jpa.PriceDbRepository;
import com.demo.prices.infrastructure.jpa.dbo.PriceEntity;
import com.demo.prices.infrastructure.jpa.mapper.PriceEntityMapper;
import com.demo.prices.infrastructure.jpa.repository.PriceDbJpaRepository;
import com.demo.prices.infrastructure.util.UtilTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.text.ParseException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PricesDbRepositoryTest {

    private PriceDbRepository priceDbRepository;

    private PriceDbJpaRepository pricedbJpaRepository;

    private PriceEntityMapper priceEntityMapper;

    private List<PriceEntity> allPrices;

    @BeforeEach
    void setUp() throws ParseException {
        allPrices = UtilTest.loadAllPriceEntity();

        pricedbJpaRepository = mock(PriceDbJpaRepository.class);
        priceEntityMapper = mock(PriceEntityMapper.class);
        priceDbRepository = new PriceDbRepository(pricedbJpaRepository,priceEntityMapper);
    }

    @ParameterizedTest
    @CsvSource({
            "1, 2020-06-14-10.00.00",
            "2, 2020-06-14-16.00.00",
            "3, 2020-06-14-21.00.00",
            "4, 2020-06-15-10.00.00",
            "5, 2020-06-16-21.00.00",
    })
    void whenCallDbRepositoryTest(int test, String fch) throws ParseException {
        List<Price> expected = UtilTest.PriceExpectedPrice(test);

        configMocking(expected, allPrices);

        List<Price> result = priceDbRepository.getPriceByFchAndIdProductAndIdBrand(fch,35455,1);

        assertEquals(result, expected);
    }

    private void configMocking(List<Price> expected, List<PriceEntity> allPrices) {
        when(pricedbJpaRepository.findByProductIdAndBrandID(35455, 1)).thenReturn(allPrices);
        when(priceEntityMapper.priceEntityToPrice((List<PriceEntity>) any())).thenReturn(expected);
    }
}
