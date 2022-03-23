package com.demo.prices;

import com.demo.prices.domain.model.Price;
import com.demo.prices.domain.vo.DatePrice;
import com.demo.prices.infrastructure.jpa.PriceDbRepository;
import com.demo.prices.infrastructure.jpa.dbo.PriceEntity;
import com.demo.prices.infrastructure.jpa.mapper.PriceEntityMapper;
import com.demo.prices.infrastructure.jpa.repository.PriceDbJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PricesDbRepositoryTestUnit {

    private PriceDbRepository priceDbRepository;

    private PriceDbJpaRepository pricedbJpaRepository;

    private PriceEntityMapper priceEntityMapper;

    private List<PriceEntity> allPrices;

    private static Stream<Arguments> usesCasesRepository() {
        return Stream.of(
                arguments(1, "2020-06-14-10.00.00"),
                arguments(2, "2020-06-14-16.00.00"),
                arguments(3, "2020-06-14-21.00.00"),
                arguments(4, "2020-06-15-10.00.00"),
                arguments(5, "2020-06-16-21.00.00")
        );
    }

    @BeforeEach
    void setUp() throws ParseException {
        allPrices = UtilTest.loadAllPriceEntity();

        pricedbJpaRepository = mock(PriceDbJpaRepository.class);
        priceEntityMapper = mock(PriceEntityMapper.class);
        priceDbRepository = new PriceDbRepository(pricedbJpaRepository,priceEntityMapper);
    }

    @ParameterizedTest
    @MethodSource("usesCasesRepository")
    void whenCallDbRepositoryTest(int test, String fch) throws ParseException {
        List<Price> expected = UtilTest.PriceExpectedPrice(test);
        Date fchDate = new DatePrice(fch).getDate();

        configMocking(expected, allPrices);

        List<Price> result = priceDbRepository.getPriceByFchAndIdProductAndIdBrand(fchDate,35455,1);

        assertEquals(result, expected);
    }

    private void configMocking(List<Price> expected, List<PriceEntity> allPrices) {
        when(pricedbJpaRepository.findByProductIdAndBrandID(anyLong(), anyLong())).thenReturn(allPrices);
        when(priceEntityMapper.priceEntityToPrice((List<PriceEntity>) any())).thenReturn(expected);
    }
}
