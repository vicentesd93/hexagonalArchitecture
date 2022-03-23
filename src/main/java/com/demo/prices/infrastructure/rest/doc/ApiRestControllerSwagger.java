package com.demo.prices.infrastructure.rest.doc;

import com.demo.prices.infrastructure.rest.handler.exceptions.InvalidDateException;
import com.demo.prices.infrastructure.rest.handler.exceptions.NotFoundPriceException;
import com.demo.prices.infrastructure.rest.handler.exceptions.NumberParseException;
import com.demo.prices.infrastructure.rest.io.PriceOut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

@OpenAPIDefinition(info = @Info(title = "API Price - Hexagonal Architecture + TDD",
        description = "API developed in hexagonal architecture applying TDD", version = "v1",
        license = @License(name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0")))
public interface ApiRestControllerSwagger {

    ResponseEntity<PriceOut> getPrice(@PathVariable String fch, @PathVariable String idProduct, @PathVariable String idBrand)
            throws InvalidDateException, NotFoundPriceException, NumberParseException;
}
