package com.demo.prices.domain.vo;

import lombok.Getter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
public class DatePrice {

    private Date date;

    public static final String DATE_FORMAT = "yyyy-MM-dd-HH.mm.ss";

    public DatePrice(String fchString) throws ParseException {
        date = new SimpleDateFormat(DATE_FORMAT).parse(fchString);
    }
}
