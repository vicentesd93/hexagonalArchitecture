package com.demo.prices.infrastructure.rest;

import com.demo.prices.domain.usecases.PriceUseCases;
import com.demo.prices.infrastructure.rest.doc.ApiRestControllerSwagger;
import com.demo.prices.infrastructure.rest.handler.exceptions.InvalidDateException;
import com.demo.prices.infrastructure.rest.handler.exceptions.NotFoundPriceException;
import com.demo.prices.infrastructure.rest.handler.exceptions.NumberParseException;
import com.demo.prices.infrastructure.rest.io.PriceOut;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApiRestController implements ApiRestControllerSwagger {

    private final PriceUseCases priceUseCases;

    @GetMapping("/price/{fch}/{idProduct}/{idBrand}")
    public ResponseEntity<PriceOut> getPrice(@PathVariable String fch, @PathVariable String idProduct, @PathVariable String idBrand)
            throws InvalidDateException, NotFoundPriceException, NumberParseException {
        return new ResponseEntity<>(priceUseCases.getPriceByFchAndIdProductAndIdBrand(fch, idProduct, idBrand),
                HttpStatus.OK);
    }
}
