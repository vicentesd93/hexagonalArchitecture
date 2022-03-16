package com.demo.prices.infrastructure.jpa.repository;

import com.demo.prices.infrastructure.jpa.dbo.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceDbJpaRepository extends JpaRepository<PriceEntity, Long> {
    List<PriceEntity> findByProductIdAndBrandID(long idProduct, long idString);
}
