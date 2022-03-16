package com.demo.prices;

import com.demo.prices.application.PriceService;
import com.demo.prices.infrastructure.rest.ApiRestController;
import com.demo.prices.infrastructure.rest.io.PriceOut;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class PricesControllerTest {

	private PriceService priceService;

	private ApiRestController sut;

	@BeforeEach
	void setUp() {
		priceService = mock(PriceService.class);
		sut = new ApiRestController(priceService);
	}

	@Test
	void whenCallApiRestControllerThenCallService() throws ParseException {
		when(priceService.getPriceByFchAndIdProductAndIdBrand(anyString(), anyLong(), anyLong())).thenReturn(new PriceOut());

		sut.getPrice(anyString(),anyLong(),anyLong());

		verify(priceService).getPriceByFchAndIdProductAndIdBrand(anyString(),anyLong(),anyLong());
	}
}
