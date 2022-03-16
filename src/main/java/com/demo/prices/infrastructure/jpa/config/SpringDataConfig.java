package com.demo.prices.infrastructure.jpa.config;

import com.demo.prices.infrastructure.jpa.dbo.PriceEntity;
import com.demo.prices.infrastructure.jpa.repository.PriceDbJpaRepository;
import com.demo.prices.infrastructure.util.Util;
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
        priceDbJpaRepository.save(new PriceEntity(UUID.randomUUID().toString(),1, Util.stringFchToDateFch
                ("2020-06-14-00.00.00"), Util.stringFchToDateFch("2020-12-31-23.59.59"), 1,
                35455, 0, 35.50, Util.EUR));
        priceDbJpaRepository.save(new PriceEntity(UUID.randomUUID().toString(),1, Util.stringFchToDateFch
                ("2020-06-14-15.00.00"), Util.stringFchToDateFch("2020-06-14-18.30.00"), 2,
                35455, 1, 25.45, Util.EUR));
        priceDbJpaRepository.save(new PriceEntity(UUID.randomUUID().toString(),1, Util.stringFchToDateFch
                ("2020-06-15-00.00.00"), Util.stringFchToDateFch("2020-06-15-11.00.00"), 3,
                35455, 1, 30.50, Util.EUR));
        priceDbJpaRepository.save(new PriceEntity(UUID.randomUUID().toString(),1, Util.stringFchToDateFch
                ("2020-06-15-16.00.00"), Util.stringFchToDateFch("2020-12-31-23.59.59"), 4,
                35455, 1, 38.95, Util.EUR));
    }
}
