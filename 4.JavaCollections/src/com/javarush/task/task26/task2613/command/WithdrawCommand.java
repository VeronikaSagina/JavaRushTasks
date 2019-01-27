package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.Map;
import java.util.ResourceBundle;

class WithdrawCommand implements Command {//вывод
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "withdraw_en");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String code = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code);
        while (true) {
            int sum = askSum();
            try {
                if (manipulator.isAmountAvailable(sum)) {
                    Map<Integer, Integer> amount = manipulator.withdrawAmount(sum);
                    amount.entrySet()
                            .stream()// .sorted((a, b) -> b.getKey().compareTo(a.getKey()))
                            .sorted(Map.Entry.<Integer, Integer>comparingByKey().reversed())
                            .forEach(entry -> ConsoleHelper.writeMessage("\t" + entry.getKey() + " - " + entry.getValue()));
                    ConsoleHelper.writeMessage(String.format(res.getString("success.format"), sum, code));
                    break;
                }else {
                    ConsoleHelper.writeMessage(res.getString("not.enough.money"));
                }
            } catch (NotEnoughMoneyException e) {
                ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
            }
        }
    }

    private int askSum() throws InterruptOperationException {
        while (true) {
            ConsoleHelper.writeMessage(res.getString("specify.amount"));
            String data = ConsoleHelper.readString();
            if (data != null) {
                int sum;
                try {
                    sum = Integer.parseInt(data);
                    if (sum > 0) {
                        return sum;
                    }
                } catch (NumberFormatException e) {
                    //
                }
            }
            ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
        }
    }
}
