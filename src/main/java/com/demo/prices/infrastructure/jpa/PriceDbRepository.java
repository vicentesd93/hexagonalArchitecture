package com.demo.prices.infrastructure.jpa;

import com.demo.prices.domain.repository.PriceRepository;
import com.demo.prices.domain.model.Price;
import com.demo.prices.infrastructure.jpa.dbo.PriceEntity;
import com.demo.prices.infrastructure.jpa.mapper.PriceEntityMapper;
import com.demo.prices.infrastructure.jpa.repository.PriceDbJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class PriceDbRepository implements PriceRepository {

    private final PriceDbJpaRepository pricedbJpaRepository;

    private final PriceEntityMapper priceEntityMapper;

    @Override
    public List<Price> getPriceByFchAndIdProductAndIdBrand(Date fch, long idProduct, long idString){
        List<Price> result;

        List<PriceEntity> resultPriceEntity = pricedbJpaRepository.findByProductIdAndBrandID(idProduct, idString);
        List<PriceEntity> filterFch = resultPriceEntity.stream().filter(
                        price -> price.getStarDate().before(fch) && price.getEndDate().after(fch)).
                collect(Collectors.toList());

        result = priceEntityMapper.priceEntityToPrice(filterFch);

        return result;
    }
}
