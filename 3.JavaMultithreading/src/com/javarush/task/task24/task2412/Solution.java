package com.javarush.task.task24.task2412;

import java.text.ChoiceFormat;
import java.text.Format;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/* 
Знания - сила!
*/
public class Solution {
    public static void main(String[] args) {
        List<Stock> stocks = getStocks();
        sort(stocks);
        Date actualDate = new Date();
        printStocks(stocks, actualDate);
    }

    public static void printStocks(List<Stock> stocks, Date actualDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        double[] filelimits = {0d, actualDate.getTime()};
        String[] filepart = {"closed {4}", "open {2} and last {3}"};

        ChoiceFormat fileform = new ChoiceFormat(filelimits, filepart);
        Format[] testFormats = {null, null, dateFormat, fileform};
        MessageFormat pattform = new MessageFormat("{0}   {1} | {5} {6}");
        pattform.setFormats(testFormats);

        for (Stock stock : stocks) {
            String name = ((String) stock.get("name"));
            String symbol = (String) stock.get("symbol");
            double open = !stock.containsKey("open") ? 0 : ((double) stock.get("open"));
            double last = !stock.containsKey("last") ? 0 : ((double) stock.get("last"));
            double change = !stock.containsKey("change") ? 0 : ((double) stock.get("change"));
            Date date = (Date) stock.get("date");
            Object[] testArgs = {name, symbol, open, last, change, date, date.getTime()};
            System.out.println(pattform.format(testArgs));
        }
    }

    public static void sort(List<Stock> list) {
        list.sort(Comparator.comparing(stock -> (String) (((HashMap<String, Object>)stock).get("name")))
                .thenComparing(stock -> (Date) (((HashMap<String, Object>)stock).get("date")), Comparator.reverseOrder())
                .thenComparingDouble(stock -> {
                    Map<String, Object> map = (HashMap<String, Object>) stock;
                    return -(map.containsKey("open") ?
                            (double) map.get("last") - (double) map.get("open") :
                            (double) map.get("change"));
                })
        );
/*
        list.sort((stock1, stock2) -> {
            String[] names = new String[]{, (String) stock2.get("name")};
            int flag = names[0].compareTo(names[1]);
            if (flag != 0) {
                return flag;
            }
            Date date1 = (Date) stock1.get("date");
            Date date2 = (Date) stock2.get("date");
            flag = -date1.compareTo(date2);
            if (flag != 0) {
                return flag;
            }
            double profit1 = stock1.containsKey("open") ?
                    (double) stock1.get("last") - (double) stock1.get("open") :
                    (double) stock1.get("change");

            double profit2 = stock2.containsKey("open") ?
                    (double) stock2.get("last") - (double) stock2.get("open") :
                    (double) stock2.get("change");

            return Double.compare(profit2, profit1);
        });
*/

    }

    public static class Stock extends HashMap<String, Object> {
        public Stock(String name, String symbol, double open, double last) {
            put("name", name);
            put("symbol", symbol);
            put("open", open);
            put("last", last);
            put("date", getRandomDate(2020));
        }

        public Stock(String name, String symbol, double change, Date date) {
            put("name", name);
            put("symbol", symbol);
            put("date", date);
            put("change", change);
        }

    }

    public static List<Stock> getStocks() {
        List<Stock> stocks = new ArrayList();

        stocks.add(new Stock("Fake Apple Inc.", "AAPL", 125.64, 123.43));
        stocks.add(new Stock("Fake Apple Inc.", "AAPL", 125.64, 125.64));
        stocks.add(new Stock("Fake Apple Inc.", "AAPL", 125.64, 123.75));
        stocks.add(new Stock("Fake Cisco Systems, Inc.", "CSCO", 25.84, 26.3));
        stocks.add(new Stock("Fake Google Inc.", "GOOG", 516.2, 512.6));
        stocks.add(new Stock("Fake Intel Corporation", "INTC", 21.36, 21.53));
        stocks.add(new Stock("Fake Level 3 Communications, Inc.", "LVLT", 5.55, 5.54));
        stocks.add(new Stock("Fake Microsoft Corporation", "MSFT", 29.56, 29.72));

        stocks.add(new Stock("Fake Nokia Corporation (ADR)", "NOK", .1, getRandomDate()));
        stocks.add(new Stock("Fake Oracle Corporation", "ORCL", .15, getRandomDate()));
        stocks.add(new Stock("Fake Starbucks Corporation", "SBUX", .03, getRandomDate()));
        stocks.add(new Stock("Fake Yahoo! Inc.", "YHOO", .32, getRandomDate()));
        stocks.add(new Stock("Fake Applied Materials, Inc.", "AMAT", .26, getRandomDate()));
        stocks.add(new Stock("Fake Comcast Corporation", "CMCSA", .5, getRandomDate()));
        stocks.add(new Stock("Fake Sirius Satellite", "SIRI", -.03, getRandomDate()));

        return stocks;
    }

    public static Date getRandomDate() {
        return getRandomDate(1970);
    }

    public static Date getRandomDate(int startYear) {
        int year = startYear + (int) (Math.random() * 30);
        int month = (int) (Math.random() * 12);
        int day = (int) (Math.random() * 28);
        year = 2018;
        month = 3;
        day = 12;
        GregorianCalendar calendar = new GregorianCalendar(year, month, day);
        return calendar.getTime();
    }
}

