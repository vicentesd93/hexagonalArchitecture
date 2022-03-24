package com.demo.prices.domain.repository;

import com.demo.prices.domain.model.Price;

import java.util.Date;
import java.util.List;

/**
 * Price Repository (Application Layer)
 */
public interface PriceRepository {
    List<Price> getPriceByFchAndIdProductAndIdBrand(Date fch, long idProduct, long idString);
}
