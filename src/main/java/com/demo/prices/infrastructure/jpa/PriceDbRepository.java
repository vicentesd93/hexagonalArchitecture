package com.demo.prices.infrastructure.jpa;

import com.demo.prices.application.PriceRepository;
import com.demo.prices.domain.Price;
import com.demo.prices.infrastructure.jpa.dbo.PriceEntity;
import com.demo.prices.infrastructure.jpa.mapper.PriceEntityMapper;
import com.demo.prices.infrastructure.jpa.repository.PriceDbJpaRepository;
import com.demo.prices.infrastructure.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class PriceDbRepository implements PriceRepository {

    private final PriceDbJpaRepository pricedbJpaRepository;

    private final PriceEntityMapper priceEntityMapper;

    @Override
    public List<Price> getPriceByFchAndIdProductAndIdBrand(String fch, long idProduct, long idString) throws ParseException {
        List<Price> result;
        Date fchDate = Util.stringFchToDateFch(fch);

        List<PriceEntity> resultPriceEntity = pricedbJpaRepository.findByProductIdAndBrandID(idProduct, idString);
        List<PriceEntity> filterFch = resultPriceEntity.stream().filter(
                        price -> price.getStarDate().before(fchDate) && price.getEndDate().after(fchDate)).
                collect(Collectors.toList());

        result = priceEntityMapper.priceEntityToPrice(filterFch);

        return result;
    }
}
