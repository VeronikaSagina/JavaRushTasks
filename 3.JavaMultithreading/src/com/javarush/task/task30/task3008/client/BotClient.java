package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BotClient extends Client {
    @Override
    protected String getUserName() {
        return "date_bot_" + (int) (Math.random() * 100);
    }

    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    public static void main(String[] args) {
        BotClient botClient = new BotClient();
        botClient.run();
    }

    public class BotSocketThread extends SocketThread {
        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. " +
                    "Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
            if (message.contains(": ") && message.split(": ").length >= 2) {
                String[] stringMessage = message.split(": ", 2);
                String name = stringMessage[0];
                String text = stringMessage[1];
                String info = "Информация для " + name;
                SimpleDateFormat format = null;
                switch (text) {
                    case "дата":
                        format = new SimpleDateFormat("d.MM.YYYY");
                        break;
                    case "день":
                        format = new SimpleDateFormat("d");
                        break;
                    case "месяц":
                        format = new SimpleDateFormat("MMMM");
                        break;
                    case "год":
                        format = new SimpleDateFormat("YYYY");
                        break;
                    case "время":
                        format = new SimpleDateFormat("H:mm:ss");
                        break;
                    case "час":
                        format = new SimpleDateFormat("H");
                        break;
                    case "минуты":
                        format = new SimpleDateFormat("m");
                        break;
                    case "секунды":
                        format = new SimpleDateFormat("s");
                        break;
                    default:
                        return;
                }
                sendTextMessage(info + ": " + format.format(Calendar.getInstance().getTime()));
                //super.processIncomingMessage(message);
            }
        }

    }
}