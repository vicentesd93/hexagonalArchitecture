package com.demo.prices.infrastructure.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

    public static final String EUR = "EUR";

    private Util() {
        throw new IllegalStateException("Utility class");
    }

    public static Date stringFchToDateFch(String fchString) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss").parse(fchString);
    }
}
