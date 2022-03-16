package com.demo.prices.application;

import com.demo.prices.domain.Price;

import java.text.ParseException;
import java.util.List;

/**
 * Price Repository (Application Layer)
 */
public interface PriceRepository {
    List<Price> getPriceByFchAndIdProductAndIdBrand(String fch, long idProduct, long idString) throws ParseException;
}
