package com.demo.prices.infrastructure.rest;

import com.demo.prices.application.PriceService;
import com.demo.prices.infrastructure.rest.io.PriceOut;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequiredArgsConstructor
public class ApiRestController {

    private final PriceService priceService;

    @GetMapping("/price/{fch}/{idProduct}/{idString}")
    public ResponseEntity<PriceOut> getPrice(@PathVariable String fch, @PathVariable long idProduct, @PathVariable long idString) throws ParseException {
        return new ResponseEntity<>(priceService.getPriceByFchAndIdProductAndIdBrand(fch, idProduct, idString), HttpStatus.NOT_IMPLEMENTED);
    }
}
