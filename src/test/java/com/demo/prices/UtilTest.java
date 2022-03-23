package com.demo.prices;

import com.demo.prices.domain.model.Price;
import com.demo.prices.domain.vo.Currency;
import com.demo.prices.domain.vo.DatePrice;
import com.demo.prices.infrastructure.jpa.dbo.PriceEntity;
import com.demo.prices.infrastructure.rest.handler.exceptions.InvalidDateException;
import com.demo.prices.infrastructure.rest.handler.exceptions.NotFoundPriceException;
import com.demo.prices.infrastructure.rest.handler.exceptions.NumberParseException;
import com.demo.prices.infrastructure.rest.io.PriceOut;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Class Util for Test 
 */
public class UtilTest {

    public static final String FCH_START_TEST_1 = "2020-06-14-00.00.00";
    public static final String FCH_END_TEST_1_4 = "2020-12-31-23.59.59";
    public static final String FCH_START_TEST_2 = "2020-06-14-15.00.00";
    public static final String FCH_END_TEST_2 = "2020-06-14-18.30.00";
    public static final String FCH_END_TEST_3 = "2020-06-15-11.00.00";
    public static final String FCH_START_TEST_3 = "2020-06-15-00.00.00";
    public static final String FCH_START_TEST_4 = "2020-06-15-16.00.00";

    public static final String BRAND_ID = "1";
    public static final String PRODUCT_ID = "35455";


    private UtilTest() {
        throw new IllegalStateException("Utility class");
    }

    public static String messageError(int test){
        switch (test){
            case 1 :
                return NotFoundPriceException.MESSAGE;
            case 2 :
                return InvalidDateException.MESSAGE;
            case 3 :
                return NumberParseException.MESSAGE;
            default:
                return "";
        }
    }

    public static PriceOut PriceExpectedOut(int test) throws ParseException {
        PriceOut expected;
        List<PriceOut> allPrices = loadAllPriceOut();
        
        switch (test){
            case 1:
            case 3:
                expected = allPrices.stream().filter(price -> price.getPriceList() == 1).collect(Collectors.toList()).get(0);
                break;
            case 2:
                expected = allPrices.stream().filter(price -> price.getPriceList() == 2)
                        .collect(Collectors.toList()).get(0);
                break;
            case 4:
                expected = allPrices.stream().filter(price ->  price.getPriceList() == 3)
                        .collect(Collectors.toList()).get(0);
                break;
            case 5:
                expected = allPrices.stream().filter(price -> price.getPriceList() == 4)
                        .collect(Collectors.toList()).get(0);
                break;
            default:
                throw new IllegalArgumentException("Not exists test");
        }
        
        return expected;
    }

    public static List<Price> PriceExpectedPrice(int test) throws ParseException {
        List<Price> expected;
        List<Price> allPrices = loadAllPrice();

        switch (test){
            case 1:
            case 3:
                expected = allPrices.stream().filter(price -> price.getPriceList() == 1).collect(Collectors.toList());
                break;
            case 2:
                expected = allPrices.stream().filter(price -> price.getPriceList() == 1 || price.getPriceList() == 2)
                        .collect(Collectors.toList());
                break;
            case 4:
                expected = allPrices.stream().filter(price ->  price.getPriceList() == 1 || price.getPriceList() == 3)
                        .collect(Collectors.toList());
                break;
            case 5:
                expected = allPrices.stream().filter(price -> price.getPriceList() == 1 || price.getPriceList() == 4)
                        .collect(Collectors.toList());
                break;
            default:
                throw new IllegalArgumentException("Not exists test");
        }

        return expected;
    }
    
    private static List<PriceOut> loadAllPriceOut() throws ParseException {
        List<PriceOut> allPrices = new ArrayList<>();
        PriceOut addPrice = new PriceOut();

        addPrice.setBrandID(1);
        addPrice.setStarDate(new DatePrice(FCH_START_TEST_1).getDate());
        addPrice.setEndDate(new DatePrice(FCH_END_TEST_1_4).getDate());
        addPrice.setPrice(35.50);
        addPrice.setPriceList(1);
        addPrice.setProductId(35455);
        allPrices.add(addPrice);

        addPrice = new PriceOut();
        addPrice.setBrandID(1);
        addPrice.setStarDate(new DatePrice(FCH_START_TEST_2).getDate());
        addPrice.setEndDate(new DatePrice(FCH_END_TEST_2).getDate());
        addPrice.setPrice(25.45);
        addPrice.setPriceList(2);
        addPrice.setProductId(35455);
        allPrices.add(addPrice);

        addPrice = new PriceOut();
        addPrice.setBrandID(1);
        addPrice.setStarDate(new DatePrice(FCH_START_TEST_3).getDate());
        addPrice.setEndDate(new DatePrice(FCH_END_TEST_3).getDate());
        addPrice.setPrice(30.50);
        addPrice.setPriceList(3);
        addPrice.setProductId(35455);
        allPrices.add(addPrice);

        addPrice = new PriceOut();
        addPrice.setBrandID(1);
        addPrice.setStarDate(new DatePrice(FCH_START_TEST_4).getDate());
        addPrice.setEndDate(new DatePrice(FCH_END_TEST_1_4).getDate());
        addPrice.setPrice(38.95);
        addPrice.setPriceList(4);
        addPrice.setProductId(35455);
        allPrices.add(addPrice);
        
        return allPrices;
    }

    private static List<Price> loadAllPrice() throws ParseException {
        List<Price> allPrices = new ArrayList<>();
        Price addPrice = new Price();

        addPrice.setBrandID(1);
        addPrice.setStarDate(new DatePrice(FCH_START_TEST_1).getDate());
        addPrice.setEndDate(new DatePrice(FCH_END_TEST_1_4).getDate());
        addPrice.setPrice(35.50);
        addPrice.setPriceList(1);
        addPrice.setProductId(35455);
        addPrice.setPriority(0);
        addPrice.setCurr(String.valueOf(Currency.EUR));
        allPrices.add(addPrice);

        addPrice = new Price();
        addPrice.setBrandID(1);
        addPrice.setStarDate(new DatePrice(FCH_START_TEST_2).getDate());
        addPrice.setEndDate(new DatePrice(FCH_END_TEST_2).getDate());
        addPrice.setPrice(25.45);
        addPrice.setPriceList(2);
        addPrice.setProductId(35455);
        addPrice.setPriority(1);
        addPrice.setCurr(String.valueOf(Currency.EUR));
        allPrices.add(addPrice);

        addPrice = new Price();
        addPrice.setBrandID(1);
        addPrice.setStarDate(new DatePrice(FCH_START_TEST_3).getDate());
        addPrice.setEndDate(new DatePrice(FCH_END_TEST_3).getDate());
        addPrice.setPrice(30.50);
        addPrice.setPriceList(3);
        addPrice.setProductId(35455);
        addPrice.setPriority(1);
        addPrice.setCurr(String.valueOf(Currency.EUR));
        allPrices.add(addPrice);

        addPrice = new Price();
        addPrice.setBrandID(1);
        addPrice.setStarDate(new DatePrice(FCH_START_TEST_4).getDate());
        addPrice.setEndDate(new DatePrice(FCH_END_TEST_1_4).getDate());
        addPrice.setPrice(38.95);
        addPrice.setPriceList(4);
        addPrice.setProductId(35455);
        addPrice.setPriority(1);
        addPrice.setCurr(String.valueOf(Currency.EUR));
        allPrices.add(addPrice);

        return allPrices;
    }

    public static List<PriceEntity> loadAllPriceEntity() throws ParseException {
        List<PriceEntity> allPrices = new ArrayList<>();
        PriceEntity addPrice = new PriceEntity();

        addPrice.setUuid(UUID.randomUUID().toString());
        addPrice.setBrandID(1);
        addPrice.setStarDate(new DatePrice(FCH_START_TEST_1).getDate());
        addPrice.setEndDate(new DatePrice(FCH_END_TEST_1_4).getDate());
        addPrice.setPrice(35.50);
        addPrice.setPriceList(1);
        addPrice.setProductId(35455);
        addPrice.setPriority(0);
        addPrice.setCurr(String.valueOf(Currency.EUR));
        allPrices.add(addPrice);

        addPrice = new PriceEntity();
        addPrice.setUuid(UUID.randomUUID().toString());
        addPrice.setBrandID(1);
        addPrice.setStarDate(new DatePrice(FCH_START_TEST_2).getDate());
        addPrice.setEndDate(new DatePrice(FCH_END_TEST_2).getDate());
        addPrice.setPrice(25.45);
        addPrice.setPriceList(2);
        addPrice.setProductId(35455);
        addPrice.setPriority(1);
        addPrice.setCurr(String.valueOf(Currency.EUR));
        allPrices.add(addPrice);

        addPrice = new PriceEntity();
        addPrice.setUuid(UUID.randomUUID().toString());
        addPrice.setBrandID(1);
        addPrice.setStarDate(new DatePrice(FCH_START_TEST_3).getDate());
        addPrice.setEndDate(new DatePrice(FCH_END_TEST_3).getDate());
        addPrice.setPrice(30.50);
        addPrice.setPriceList(3);
        addPrice.setProductId(35455);
        addPrice.setPriority(1);
        addPrice.setCurr(String.valueOf(Currency.EUR));
        allPrices.add(addPrice);

        addPrice = new PriceEntity();
        addPrice.setUuid(UUID.randomUUID().toString());
        addPrice.setBrandID(1);
        addPrice.setStarDate(new DatePrice(FCH_START_TEST_4).getDate());
        addPrice.setEndDate(new DatePrice(FCH_END_TEST_1_4).getDate());
        addPrice.setPrice(38.95);
        addPrice.setPriceList(4);
        addPrice.setProductId(35455);
        addPrice.setPriority(1);
        addPrice.setCurr(String.valueOf(Currency.EUR));
        allPrices.add(addPrice);

        return allPrices;
    }
}
