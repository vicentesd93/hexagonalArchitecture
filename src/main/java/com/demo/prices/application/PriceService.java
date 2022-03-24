package com.demo.prices.application;

import com.demo.prices.domain.model.Price;
import com.demo.prices.domain.repository.PriceRepository;
import com.demo.prices.domain.usecases.PriceUseCases;
import com.demo.prices.domain.vo.DatePrice;
import com.demo.prices.infrastructure.rest.handler.exceptions.InvalidDateException;
import com.demo.prices.infrastructure.rest.handler.exceptions.NotFoundPriceException;
import com.demo.prices.infrastructure.rest.handler.exceptions.NumberParseException;
import com.demo.prices.infrastructure.rest.io.PriceOut;
import com.demo.prices.infrastructure.rest.mapper.PriceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PriceService implements PriceUseCases {

    private final PriceMapper priceMapper;

    private final PriceRepository priceRepository;

    public PriceOut getPriceByFchAndIdProductAndIdBrand(String fch, String idProduct, String idBrand)
            throws NotFoundPriceException, InvalidDateException, NumberParseException {
        List<Price> pricesByParameters;

        pricesByParameters = getPrices(fch, idProduct, idBrand);

       if(pricesByParameters.size() > 1){
            pricesByParameters = pricesByParameters.stream().filter(price -> price.getPriority() == 1)
                    .collect(Collectors.toList());
        }

        return priceMapper.priceToPriceOut(pricesByParameters.get(0));
    }

    private List<Price> getPrices(String fch, String idProduct, String idBrand) throws NumberParseException,
            InvalidDateException, NotFoundPriceException {
        long idBrandLong;
        long idProductLong;
        List<Price> pricesByParameters;
        Date fchDate;

        try{
            fchDate = new DatePrice(fch).getDate();
            idBrandLong = Long.valueOf(idBrand);
            idProductLong = Long.valueOf(idProduct);
            pricesByParameters = priceRepository.getPriceByFchAndIdProductAndIdBrand(fchDate, idProductLong ,idBrandLong);
        }catch (NumberFormatException  e){
            throw new NumberParseException();
        }catch (ParseException e) {
            throw new InvalidDateException();
        }
        if(pricesByParameters.isEmpty()){
            throw new NotFoundPriceException();
        }

        return pricesByParameters;
    }
}
