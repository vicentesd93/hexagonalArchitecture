package com.demo.prices;

import com.demo.prices.application.PriceRepository;
import com.demo.prices.application.PriceService;
import com.demo.prices.domain.Price;
import com.demo.prices.infrastructure.rest.io.PriceOut;
import com.demo.prices.infrastructure.rest.mapper.PriceMapper;
import com.demo.prices.infrastructure.util.UtilTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.text.ParseException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PricesServiceTest {

    private PriceService priceService;

    private PriceMapper priceMapper;

    private PriceRepository priceRepository;

    @BeforeEach
    void setUp() {
        priceMapper = mock(PriceMapper.class);
        priceRepository = mock(PriceRepository.class);
        priceService = new PriceService(priceMapper, priceRepository);
    }

    @ParameterizedTest
    @CsvSource({
            "1, 2020-06-14-10.00.00",
            "2, 2020-06-14-16.00.00",
            "3, 2020-06-14-21.00.00",
            "4, 2020-06-15-10.00.00",
            "5, 2020-06-16-21.00.00",
    })
    void whenCallServiceTest(int test, String fch) throws ParseException {
        PriceOut expected = UtilTest.PriceExpectedOut(test);
        List<Price> expectedPriceRepository = UtilTest.PriceExpectedPrice(test);

        configMocking(fch, expectedPriceRepository, expected);

        PriceOut result = priceService.getPriceByFchAndIdProductAndIdBrand(fch,35455,1);

        assertEquals(result, expected);
    }

    private void configMocking(String fch, List<Price> expectedPriceRepository, PriceOut expected) throws ParseException {
        when(priceRepository.getPriceByFchAndIdProductAndIdBrand(
                fch, 35455, 1)).thenReturn(expectedPriceRepository);
        when(priceMapper.priceToPriceOut(expectedPriceRepository.get(0))).thenReturn(expected);
    }
}
