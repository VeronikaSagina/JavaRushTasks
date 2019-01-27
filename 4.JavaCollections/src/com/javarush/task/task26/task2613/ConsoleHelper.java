package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "common_en");
    public static void writeMessage(String message) {
        System.out.println(message);
    }
    public static void printExitMessage() {
        writeMessage(res.getString("the.end"));
    }
    public static String readString() throws InterruptOperationException {
        String read = null;
        try {
           read = bis.readLine();
        } catch (IOException e) {
            //e.printStackTrace();
        }  if (read != null && read.equalsIgnoreCase("exit")){
            printExitMessage();
            throw new InterruptOperationException();
        }
        return read;
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        while (true) {
            writeMessage(String.format(res.getString("choose.denomination.and.count.format"), currencyCode));
            String data = readString();
            if (data != null && data.split(" ").length == 2) {
                String[] arr = data.split(" ");
                if (isValidDigit(arr[0]) && isValidDigit(arr[1])) {
                    return arr;
                }
            }
        }
    }

    private static boolean isValidDigit(String s) {
        int i;
        try {
            i = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return i > 0;
    }

    public static Operation askOperation() throws InterruptOperationException {
        while (true){
            writeMessage(res.getString("choose.operation"));
            String number = readString();
            if (number != null){
                try {
                    return Operation.getAllowableOperationByOrdinal(Integer.parseInt(number));
                } catch (IllegalArgumentException e) {
                    //System.out.println();
                }
            }
        }
    }

    public static String askCurrencyCode() throws InterruptOperationException {
        while (true) {
            writeMessage(res.getString("choose.currency.code"));
            String code = readString();
            if (code != null && code.length() == 3) {
                return code.toUpperCase();
            }
            writeMessage(res.getString("invalid.data"));
        }
    }
}
