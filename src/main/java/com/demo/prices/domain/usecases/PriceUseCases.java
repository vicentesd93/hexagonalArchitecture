package com.demo.prices.domain.usecases;

import com.demo.prices.infrastructure.rest.handler.exceptions.InvalidDateException;
import com.demo.prices.infrastructure.rest.handler.exceptions.NotFoundPriceException;
import com.demo.prices.infrastructure.rest.handler.exceptions.NumberParseException;
import com.demo.prices.infrastructure.rest.io.PriceOut;

/**
 * Price Use Cases
 */
public interface PriceUseCases{

    /**
     * Returns the price by date, productID and brandID indicated.
     *
     * @param fch
     * @param idProduct
     * @param idBrand
     * @return PriceOut
     * @throws NotFoundPriceException
     * @throws InvalidDateException
     */
    PriceOut getPriceByFchAndIdProductAndIdBrand(String fch, String idProduct, String idBrand)
            throws NotFoundPriceException, InvalidDateException, NumberParseException;
}
