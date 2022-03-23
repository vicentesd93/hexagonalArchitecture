package com.demo.prices;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class PricesControllerTestIntegration {

	private static final String BASE_PRODUCTS_URL = "http://localhost:8080/price";

	@Autowired
	private MockMvc mockMvc;

	private static Stream<Arguments> usesCasesServiceOk() {
		return Stream.of(
				arguments(1, "2020-06-14-10.00.00"),
				arguments(2, "2020-06-14-16.00.00"),
				arguments(3, "2020-06-14-21.00.00"),
				arguments(4, "2020-06-15-10.00.00"),
				arguments(5, "2020-06-16-21.00.00")
		);
	}
	private static Stream<Arguments> usesCasesServiceKo() {
		return Stream.of(
				arguments(1, "2020-06-14-10.00.00", "35455", "2"),
				arguments(2, "errorDate", "35455", "2"),
				arguments(3, "2020-06-14-10.00.00", "35455", "error"),
				arguments(3, "2020-06-14-10.00.00", "error", "2")
		);
	}


	@ParameterizedTest
	@MethodSource("usesCasesServiceOk")
	void whenCallServiceUsesCaseOkTest(int test, String fch) throws Exception {
		double expectedPrice = UtilTest.PriceExpectedOut(test).getPrice();

		this.mockMvc.perform(
				get(BASE_PRODUCTS_URL + "/" + fch + "/" + UtilTest.PRODUCT_ID + "/" + UtilTest.BRAND_ID))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.price").value(expectedPrice));
	}

	@ParameterizedTest
	@MethodSource("usesCasesServiceKo")
	void whenCallServiceUsesCaseKo(int test, String fch, String productId, String brandId) throws Exception {
		String expectedMessageError = UtilTest.messageError(test);

		this.mockMvc.perform(
				get(BASE_PRODUCTS_URL + "/" + fch + "/" + productId + "/" + brandId))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.message").value(expectedMessageError));
	}
}
