package com.demo.prices.application;

import com.demo.prices.domain.Price;
import com.demo.prices.infrastructure.rest.io.PriceOut;
import com.demo.prices.infrastructure.rest.mapper.PriceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PriceService {

    private final PriceMapper priceMapper;

    private final PriceRepository priceRepository;

    public PriceOut getPriceByFchAndIdProductAndIdBrand(String fch, long idProduct, long idString) throws ParseException {
        List<Price> pricesByParameters = priceRepository.getPriceByFchAndIdProductAndIdBrand(fch,idProduct,idString);

        if(pricesByParameters.size() > 1){
            pricesByParameters = pricesByParameters.stream().filter(price -> price.getPriority() == 1).collect(Collectors.toList());
        }

        return priceMapper.priceToPriceOut(pricesByParameters.get(0));
    }
}
