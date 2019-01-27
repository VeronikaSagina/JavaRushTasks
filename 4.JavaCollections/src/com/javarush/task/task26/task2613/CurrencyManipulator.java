package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class CurrencyManipulator {
    private String currencyCode;//код валюты USD
    private Map<Integer, Integer> denominations = new HashMap<>();//номинал количество

    public String getCurrencyCode() {
        return currencyCode;
    }

    public int getTotalAmount() {
        int count = 0;
        for (Map.Entry<Integer, Integer> next : denominations.entrySet()) {
            count += next.getKey() * next.getValue();
        }
        return count;
    }

    public  boolean hasMoney() {
        return denominations.size() > 0;
    }

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public void addAmount(int denomination, int count) {//номинал и количество
        if (!denominations.containsKey(denomination)) {
            denominations.put(denomination, count);
        } else {
            denominations.put(denomination, denominations.get(denomination) + count);
        }
    }
    public boolean isAmountAvailable(int expectedAmount){
        return getTotalAmount() >= expectedAmount;
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException{
        TreeMap<Integer, Integer> treeMapAll = new TreeMap<>(Comparator.reverseOrder());
        treeMapAll.putAll(denominations);
        int sum = treeMapAll
                .entrySet()
                .stream()
                .mapToInt(e -> e.getKey() * e.getValue()).sum();
        if (sum < expectedAmount){
           throw new NotEnoughMoneyException();
        }
        if (sum == expectedAmount){
            denominations.clear();
            return treeMapAll;
        }
        Map<Integer, Integer> resultMap = new TreeMap<>();
        int currentAmount = 0;
        int tempAmount = expectedAmount;
        for (Map.Entry<Integer, Integer>  entry : treeMapAll.entrySet()){
            Integer currency = entry.getKey();//валюта
            Integer amount = entry.getValue();//количество
            if (tempAmount >= currency){
               int total = tempAmount / currency;//нужное количество
               if (total <= amount){
                   resultMap.put(currency, total);
                   entry.setValue(amount - total);
                   currentAmount += total * currency;
                   tempAmount = expectedAmount - currentAmount;
               }else {
                   resultMap.put(currency, amount);
                   entry.setValue(0);
                   currentAmount += currency * amount;
                   tempAmount = expectedAmount - currentAmount;
               }
                if (currentAmount == expectedAmount){
                    break;
                }
                if (currentAmount > expectedAmount){
                   throw new NotEnoughMoneyException();
                }
            }
        }
        if (currentAmount != expectedAmount){
            throw new NotEnoughMoneyException();
        }
        denominations = treeMapAll.entrySet().stream()
                .filter( e -> e.getValue() != 0)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return resultMap;
    }
}
