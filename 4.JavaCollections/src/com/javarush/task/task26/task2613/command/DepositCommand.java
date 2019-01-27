package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;


class DepositCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "deposit_en");
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String code = ConsoleHelper.askCurrencyCode();
        ConsoleHelper.writeMessage(res.getString("invalid.data"));
        String[] validTwoDigits = ConsoleHelper.getValidTwoDigits(code);
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code);
        int denomination = Integer.parseInt(validTwoDigits[0]);
        int count = Integer.parseInt(validTwoDigits[1]);
        manipulator.addAmount(denomination, count);
        ConsoleHelper.writeMessage(String.format(res.getString("success.format"), denomination * count, code));
    }
}
