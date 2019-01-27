package com.javarush.task.task39.task3913;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Specification {
    private Map<String, Object> params = new HashMap<>();
    private String returning;

    public void add(String paramName, Object value) {
        params.put(paramName, value);
    }

    public void setReturning(String returning) {
        this.returning = returning;
    }

    public Set<Object> apply(Stream<UserLog> logs) {
        if (params.containsKey("ip")) {
            logs = logs.filter(log -> log.getIp().equals(params.get("ip")));
        }
        if (params.containsKey("user")) {
            logs = logs.filter(log -> log.getName().equals(params.get("user")));
        }
        if (params.containsKey("after")) {
            logs = logs.filter(log -> log.getDate().after((Date) params.get("after")));
        }
        if (params.containsKey("before")) {
            logs = logs.filter(log -> log.getDate().before((Date) params.get("before")));
        }
        if (params.containsKey("event")) {
            logs = logs.filter(log -> log.getEvent() == params.get("event"));
        }
        if (params.containsKey("task")) {
            logs = logs.filter(log -> log.getTask().equals(params.get("task")));
        }
        if (params.containsKey("status")) {
            logs = logs.filter(log -> log.getStatus() == params.get("status"));
        }
//        return mapper(logs).collect(Collectors.toSet());
        Function<UserLog, Object> mapper = mapper();
        return logs.map(mapper)
                .collect(Collectors.toSet());
    }

    private Stream<Object> mapper(Stream<UserLog> logs) {
        switch (returning) {
            case "ip":
                return logs.map(UserLog::getIp);
            case "user":
                return logs.map(UserLog::getName);
            case "date":
                return logs.map(UserLog::getDate);
            case "event":
                return logs.map(UserLog::getEvent);
            case "task":
                return logs.map(UserLog::getTask);
            case "status":
                return logs.map(UserLog::getStatus);
            default:
                throw new UnsupportedOperationException();
        }
    }
    private Function<UserLog, Object> mapper() {
        switch (returning) {
            case "ip":
                return UserLog::getIp;
            case "user":
                return UserLog::getName;
            case "date":
                return UserLog::getDate;
            case "event":
                return UserLog::getEvent;
            case "task":
                return UserLog::getTask;
            case "status":
                return UserLog::getStatus;
            default:
                throw new UnsupportedOperationException();
        }
    }
}
