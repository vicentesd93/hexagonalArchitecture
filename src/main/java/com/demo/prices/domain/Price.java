package com.demo.prices.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Price {

    private long brandID;

    private Date starDate;

    private Date endDate;

    private long priceList;

    private long productId;

    private long priority;

    private double price;

    private String curr;
}
