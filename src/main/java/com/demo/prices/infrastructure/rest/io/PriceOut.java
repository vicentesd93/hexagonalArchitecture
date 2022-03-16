package com.demo.prices.infrastructure.rest.io;

import lombok.Data;

import java.util.Date;

@Data
public class PriceOut {

    private long productId;

    private long brandID;

    private long priceList;

    private Date starDate;

    private Date endDate;

    private double price;
}