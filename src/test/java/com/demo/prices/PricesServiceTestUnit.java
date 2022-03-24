package com.demo.prices;

import com.demo.prices.domain.repository.PriceRepository;
import com.demo.prices.application.PriceService;
import com.demo.prices.domain.model.Price;
import com.demo.prices.infrastructure.rest.handler.exceptions.InvalidDateException;
import com.demo.prices.infrastructure.rest.handler.exceptions.NotFoundPriceException;
import com.demo.prices.infrastructure.rest.handler.exceptions.NumberParseException;
import com.demo.prices.infrastructure.rest.io.PriceOut;
import com.demo.prices.infrastructure.rest.mapper.PriceMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PricesServiceTestUnit {

    private static Stream<Arguments> usesCasesService() {
        return Stream.of(
                arguments(1, "2020-06-14-10.00.00"),
                arguments(2, "2020-06-14-16.00.00"),
                arguments(3, "2020-06-14-21.00.00"),
                arguments(4, "2020-06-15-10.00.00"),
                arguments(5, "2020-06-16-21.00.00")
        );
    }

    private static Stream<Arguments> usesCasesExceptions() {
        return Stream.of(
                arguments(1, "2020-06-14-10.00.00", "35455", "2"),
                arguments(2, "errorDate", "35455", "2"),
                arguments(3, "2020-06-14-10.00.00", "35455", "error"),
                arguments(3, "2020-06-14-10.00.00", "error", "2")
        );
    }

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
    @MethodSource("usesCasesService")
    void whenCallServiceTest(int test, String fch) throws ParseException, NotFoundPriceException, InvalidDateException, NumberParseException {
        PriceOut expected = UtilTest.PriceExpectedOut(test);
        List<Price> expectedPriceRepository = UtilTest.PriceExpectedPrice(test);

        configMockingServiceTest(expectedPriceRepository, expected);

        PriceOut result = priceService.getPriceByFchAndIdProductAndIdBrand(fch, UtilTest.PRODUCT_ID, String.valueOf(UtilTest.BRAND_ID));

        assertEquals(result, expected);
    }

    @ParameterizedTest
    @MethodSource("usesCasesExceptions")
    void whenCallServiceTestExceptions(int test, String fch, String productId, String brandId){
        switch (test) {
            case 1:
                configMockingExceptionsTest();
                assertThrows(NotFoundPriceException.class,
                        callService(fch, productId, brandId));
                break;
            case 2:
                configMockingExceptionsTest();
                assertThrows(InvalidDateException.class,
                        callService(fch, productId, brandId));
                break;
            case 3:
                configMockingExceptionsTest();
                assertThrows(NumberParseException.class,
                        callService(fch, productId, brandId));
                break;
        }
    }

    private Executable callService(String fch, String productId, String brandId) {
        return () -> priceService.getPriceByFchAndIdProductAndIdBrand(fch,
                productId, brandId);
    }

    private void configMockingExceptionsTest() {
        when(priceRepository.getPriceByFchAndIdProductAndIdBrand(any(), anyLong(), anyLong()))
                .thenReturn(new ArrayList<>());
    }

    private void configMockingServiceTest(List<Price> expectedPriceRepository, PriceOut expected) {
        when(priceRepository.getPriceByFchAndIdProductAndIdBrand(any(), anyLong(), anyLong()))
                .thenReturn(expectedPriceRepository);
        when(priceMapper.priceToPriceOut((Price) any())).thenReturn(expected);
    }
}