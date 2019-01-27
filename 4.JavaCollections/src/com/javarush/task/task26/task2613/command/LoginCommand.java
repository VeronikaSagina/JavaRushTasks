package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

class LoginCommand implements Command {
    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "verifiedCards");
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "login_en");
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        while (true) {
            ConsoleHelper.writeMessage(res.getString("specify.data"));
            String dataNumber = ConsoleHelper.readString();
            String dataPin = ConsoleHelper.readString();
            if (dataNumber != null && dataPin != null) {
                if (validCreditCards.containsKey(dataNumber) && dataPin.equals(validCreditCards.getString(dataNumber))) {
                    ConsoleHelper.writeMessage(res.getString("success.format"));
                    return;
                }else {
                    ConsoleHelper.writeMessage(res.getString("not.verified.format"));
                }
            }
            ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
            ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
        }
    }
}
