package com.demo.prices.infrastructure.rest.mapper;

import com.demo.prices.domain.Price;
import com.demo.prices.infrastructure.rest.io.PriceOut;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Mapper Price-PriceOut
 */
@Mapper(componentModel = "spring")
public interface PriceMapper {

    PriceOut priceToPriceOut(Price price);

    List<PriceOut> priceToPriceOut(List<Price> price);

    Price priceOutToPrice(PriceOut priceOut);

    List<Price> priceOutToPrice(List<PriceOut> priceOut);
}
