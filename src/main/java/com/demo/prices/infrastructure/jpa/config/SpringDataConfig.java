package com.demo.prices.infrastructure.jpa.config;

import com.demo.prices.domain.vo.Currency;
import com.demo.prices.domain.vo.DatePrice;
import com.demo.prices.infrastructure.jpa.dbo.PriceEntity;
import com.demo.prices.infrastructure.jpa.repository.PriceDbJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.UUID;

@Configuration
@EnableJpaRepositories
        (basePackages = "com.demo.prices.infrastructure.jpa")
@EntityScan(basePackages = "com.demo.prices.infrastructure.jpa.dbo")
@RequiredArgsConstructor
public class SpringDataConfig implements ApplicationRunner {

    private final PriceDbJpaRepository priceDbJpaRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        priceDbJpaRepository.save(new PriceEntity(UUID.randomUUID().toString(),1, new DatePrice
                ("2020-06-14-00.00.00").getDate(), new DatePrice("2020-12-31-23.59.59").getDate(), 1,
                35455, 0, 35.50, String.valueOf(Currency.EUR)));
        priceDbJpaRepository.save(new PriceEntity(UUID.randomUUID().toString(),1, new DatePrice
                ("2020-06-14-15.00.00").getDate(), new DatePrice("2020-06-14-18.30.00").getDate(), 2,
                35455, 1, 25.45, String.valueOf(Currency.EUR)));
        priceDbJpaRepository.save(new PriceEntity(UUID.randomUUID().toString(),1, new DatePrice
                ("2020-06-15-00.00.00").getDate(), new DatePrice("2020-06-15-11.00.00").getDate(), 3,
                35455, 1, 30.50, String.valueOf(Currency.EUR)));
        priceDbJpaRepository.save(new PriceEntity(UUID.randomUUID().toString(),1, new DatePrice
                ("2020-06-15-16.00.00").getDate(), new DatePrice("2020-12-31-23.59.59").getDate(), 4,
                35455, 1, 38.95, String.valueOf(Currency.EUR)));
    }
}
