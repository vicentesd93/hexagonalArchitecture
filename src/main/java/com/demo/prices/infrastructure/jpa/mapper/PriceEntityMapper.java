package com.demo.prices.infrastructure.jpa.mapper;

import com.demo.prices.domain.model.Price;
import com.demo.prices.infrastructure.jpa.dbo.PriceEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PriceEntityMapper {

    PriceEntity priceToPriceEntity(Price price);

    List<PriceEntity> priceToPriceEntity(List<Price> price);

    Price priceEntityToPrice(PriceEntity priceOut);

    List<Price> priceEntityToPrice(List<PriceEntity> priceOut);
}
