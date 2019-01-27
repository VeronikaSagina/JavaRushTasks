package com.javarush.task.task39.task3913;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserLog {
    private String ip;
    private String name;
    private Date date;
    private Event event;
    private Status status;
    private Integer task;

    public UserLog(String log) {
        String[] line = log.split("\t");
        ip = line[0];
        name = line[1];
        date = parseDate(line[2]);
        String[] arrEvent = line[3].split("\\s");
        event = Event.valueOf(arrEvent[0]);
        if (arrEvent.length > 1) {
            task = Integer.valueOf(arrEvent[1]);
        }
        status = Status.valueOf(line[line.length - 1]);
    }

    public Object getAny(String value) {
        switch (value) {
            case "ip":
                return getIp();
            case "user":
                return getName();
            case "date":
                return getDate();
            case "status":
                return getStatus();
            case "event":
                return getEvent();
            case "task":
                return getTask();
        }
        return null;
    }

    public String getIp() {
        return ip;
    }

    public Integer getTask() {
        return task;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public Event getEvent() {
        return event;
    }

    public Status getStatus() {
        return status;
    }

    public static Date parseDate(String sDate) {
        SimpleDateFormat format = new SimpleDateFormat("d.M.yyyy H:m:s");
        Date date = null;
        try {
            date = format.parse(sDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
