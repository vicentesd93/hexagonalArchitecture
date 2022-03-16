package com.demo.prices.infrastructure.jpa.dbo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "PRICES")
public class PriceEntity {

    @Column
    @Id
    private String uuid;

    @Column
    private long brandID;

    @Column
    private Date starDate;

    @Column
    private Date endDate;

    @Column
    private long priceList;

    @Column
    private long productId;

    @Column
    private long priority;

    @Column
    private double price;

    @Column
    private String curr;
}
