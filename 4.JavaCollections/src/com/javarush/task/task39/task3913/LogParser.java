package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogParser implements IPQuery, UserQuery, DateQuery, EventQuery, QLQuery {
    private List<UserLog> userLogs;

    public Stream<UserLog> getLogs() {
        return userLogs.stream();
    }

    public LogParser(Path logDir) {
        userLogs = new ArrayList<>();
        try (Stream<Path> pathStream = Files.walk(logDir)) {
            pathStream.filter(Files::isRegularFile)
                    .filter(path -> path.getFileName().toString().endsWith(".log"))
                    .flatMap(path -> {
                        try {
                            return Files.readAllLines(path, StandardCharsets.UTF_8).stream();
                        } catch (IOException e) {
                            throw new UnsupportedOperationException(e);
                        }
                    })
                    .map(UserLog::new)
                    .forEach(userLogs::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {// после ____ до
        return getUniqueIPs(after, before).size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        return getIpsByDatePeriod(userLogs.stream(), after, before);
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        return getIpsByDatePeriod(userLogs.stream()
                .filter(userLog -> userLog.getName().equals(user)), after, before);
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        return getIpsByDatePeriod(userLogs.stream()
                .filter(userLog -> userLog.getEvent() == event), after, before);
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        return getIpsByDatePeriod(userLogs.stream()
                .filter(userLog -> userLog.getStatus() == status), after, before);
    }

    public Set<String> getIpsByDatePeriod(Stream<UserLog> userLogStream, Date after, Date before) {
        return userLogStream.filter(userLog -> after == null || userLog.getDate().after(after))
                .filter(userLog -> before == null || userLog.getDate().before(before))
                .map(UserLog::getIp)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getAllUsers() {
        return userLogs.stream()
                .map(UserLog::getName)
                .collect(Collectors.toSet());
    }

    @Override
    public int getNumberOfUsers(Date after, Date before) {
        return (int) userLogs.stream()
                .filter(userLog -> after == null || userLog.getDate().after(after))
                .filter(userLog -> before == null || userLog.getDate().before(before))
                .map(UserLog::getName)
                .distinct()
                .count();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        return (int) userLogs.stream()
                .filter(userLog -> userLog.getName().equals(user))
                .filter(userLog -> after == null || userLog.getDate().after(after))
                .filter(userLog -> before == null || userLog.getDate().before(before))
                .map(userLog -> userLog.getEvent())
                .distinct()
                .count();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        return userLogs.stream()
                .filter(userLog -> userLog.getIp().equals(ip))
                .filter(userLog -> after == null || userLog.getDate().after(after))
                .filter(userLog -> before == null || userLog.getDate().before(before))
                .map(UserLog::getName)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {
        return getUsersByEvent(Event.LOGIN, after, before);
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        return getUsersByEvent(Event.DOWNLOAD_PLUGIN, after, before);
    }

    private Set<String> getUsersByEvent(Event event, Date after, Date before) {
        return userLogs.stream()
                .filter(userLog -> userLog.getEvent() == event)
                .filter(userLog -> after == null || userLog.getDate().after(after))
                .filter(userLog -> before == null || userLog.getDate().before(before))
                .map(UserLog::getName)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        return getUsersByEvent(Event.WRITE_MESSAGE, after, before);
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        return getUsersByEvent(Event.SOLVE_TASK, after, before);
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        return userLogs.stream()
                .filter(userLog -> after == null || userLog.getDate().after(after))
                .filter(userLog -> before == null || userLog.getDate().before(before))
                .filter(u -> u.getEvent() == Event.SOLVE_TASK)
                .filter(user -> user.getTask() != null && user.getTask() == task)
                .map(UserLog::getName)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        return getUsersByEvent(Event.DONE_TASK, after, before);
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        return userLogs.stream()
                .filter(userLog -> after == null || userLog.getDate().after(after))
                .filter(userLog -> before == null || userLog.getDate().before(before))
                .filter(u -> u.getEvent() == Event.DONE_TASK)
                .filter(user -> user.getTask() != null && user.getTask() == task)
                .map(UserLog::getName)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) {
        return userLogs.stream()
                .filter(userLog -> userLog.getName().equals(user))
                .filter(userLog -> after == null || userLog.getDate().after(after))
                .filter(userLog -> before == null || userLog.getDate().before(before))
                .filter(userLog -> userLog.getEvent() == event)
                .map(UserLog::getDate)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {
        return userLogs.stream()
                .filter(userLog -> after == null || userLog.getDate().after(after))
                .filter(userLog -> before == null || userLog.getDate().before(before))
                .filter(userLog -> userLog.getStatus() == Status.FAILED)
                .map(UserLog::getDate)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before) {
        return userLogs.stream()
                .filter(userLog -> after == null || userLog.getDate().after(after))
                .filter(userLog -> before == null || userLog.getDate().before(before))
                .filter(userLog -> userLog.getStatus() == Status.ERROR)
                .map(UserLog::getDate)
                .collect(Collectors.toSet());
    }

    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) {
        return getDatesForUserAndEvent(user, Event.LOGIN, after, before).stream()
                .sorted()
                .findFirst()
                .orElse(null);
    }

    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) {
        return getDate(Event.SOLVE_TASK, user, after, before, task);
    }

    private Date getDate(Event event, String user, Date after, Date before, int task) {
        return userLogs.stream()
                .filter(userLog -> after == null || userLog.getDate().after(after))
                .filter(userLog -> before == null || userLog.getDate().before(before))
                .filter(userLog -> userLog.getEvent() == event)
                .filter(userLog -> userLog.getName().equals(user))
                .filter(userLog -> userLog.getTask() == task)
                .map(UserLog::getDate)
                .sorted()
                .findFirst()
                .orElse(null);
    }

    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) {
        return getDate(Event.DONE_TASK, user, after, before, task);
    }

    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) {
        return getDatesForUserAndEvent(user, Event.WRITE_MESSAGE, after, before);
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {
        return getDatesForUserAndEvent(user, Event.DOWNLOAD_PLUGIN, after, before);
    }

    @Override
    public int getNumberOfAllEvents(Date after, Date before) {
        return (int) userLogs.stream()
                .filter(userLog -> after == null || userLog.getDate().after(after))
                .filter(userLog -> before == null || userLog.getDate().before(before))
                .map(UserLog::getEvent)
                .distinct()
                .count();
    }

    @Override
    public Set<Event> getAllEvents(Date after, Date before) {
        return userLogs.stream()
                .filter(userLog -> after == null || userLog.getDate().after(after))
                .filter(userLog -> before == null || userLog.getDate().before(before))
                .map(UserLog::getEvent)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getEventsForIP(String ip, Date after, Date before) {
        return userLogs.stream()
                .filter(userLog -> after == null || userLog.getDate().after(after))
                .filter(userLog -> before == null || userLog.getDate().before(before))
                .filter(userLog -> userLog.getIp().equals(ip))
                .map(UserLog::getEvent)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getEventsForUser(String user, Date after, Date before) {
        return userLogs.stream()
                .filter(userLog -> after == null || userLog.getDate().after(after))
                .filter(userLog -> before == null || userLog.getDate().before(before))
                .filter(userLog -> userLog.getName().equals(user))
                .map(UserLog::getEvent)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getFailedEvents(Date after, Date before) {
        return userLogs.stream()
                .filter(userLog -> after == null || userLog.getDate().after(after))
                .filter(userLog -> before == null || userLog.getDate().before(before))
                .filter(userLog -> userLog.getStatus() == Status.FAILED)
                .map(UserLog::getEvent)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getErrorEvents(Date after, Date before) {
        return userLogs.stream()
                .filter(userLog -> after == null || userLog.getDate().after(after))
                .filter(userLog -> before == null || userLog.getDate().before(before))
                .filter(userLog -> userLog.getStatus() == Status.ERROR)
                .map(UserLog::getEvent)
                .collect(Collectors.toSet());
    }

    @Override
    public int getNumberOfAttemptToSolveTask(int task, Date after, Date before) {
        return (int) userLogs.stream()
                .filter(userLog -> after == null || userLog.getDate().after(after))
                .filter(userLog -> before == null || userLog.getDate().before(before))
                .filter(userLog -> userLog.getEvent() == Event.SOLVE_TASK)
                .filter(userLog -> userLog.getTask() == task)
                .count();
    }

    @Override
    public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before) {
        return (int) userLogs.stream()
                .filter(userLog -> after == null || userLog.getDate().after(after))
                .filter(userLog -> before == null || userLog.getDate().before(before))
                .filter(userLog -> userLog.getEvent() == Event.DONE_TASK)
                .filter(userLog -> userLog.getTask() == task)
                //  .filter(userLog -> userLog.getStatus() == Status.OK)
                .count();
    }

    @Override
    public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before) {
        return userLogs.stream()
                .filter(userLog -> after == null || userLog.getDate().after(after))
                .filter(userLog -> before == null || userLog.getDate().before(before))
                .filter(userLog -> userLog.getEvent() == Event.SOLVE_TASK)
                .collect(Collectors.groupingBy(UserLog::getTask, Collectors.summingInt(value -> 1)));

    }

    @Override
    public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before) {
        return userLogs.stream()
                .filter(userLog -> after == null || userLog.getDate().after(after))
                .filter(userLog -> before == null || userLog.getDate().before(before))
                .filter(userLog -> userLog.getEvent() == Event.DONE_TASK)
                .collect(Collectors.groupingBy(UserLog::getTask, Collectors.summingInt(value -> 1)));

    }

    private List<String> parse(String sValue) {
        if (!sValue.contains("\"")) {
            return Stream.of(sValue.split(" "))
                    .collect(Collectors.toList());
        }
        String line1 = sValue.substring(0, sValue.indexOf('"')).replace("=", "");
        List<String> result = Stream.of(line1.split(" ")).collect(Collectors.toList());
        Queue<Integer> fifo = new LinkedList<>();
        for (int i = 0; i < sValue.length(); i++) {
            if (sValue.charAt(i) == '\"') {
                if (fifo.isEmpty()) {
                    fifo.add(i);
                } else {
                    int start = fifo.remove();
                    result.add(sValue.substring(start + 1, i));
                }
            }
        }
        return result;
    }

    //[get, ip, for, user, Eduard Petrovich Morozko, 11.12.2013 0:00:00, 03.01.2014 23:59:59]
    @Override
    public Set<Object> execute(String query) {
        List<String> request = parse(query);
        if (request.size() == 2) {
            return getRespondForOneParam(query);
        }
        String field1 = request.get(1);
        String field2 = request.get(3);
        String value1 = request.get(4);
        String after = null;
        String before = null;
        if (request.size() > 5) {
            after = request.get(5);
            before = request.get(6);
        }
        String finalAfter = after;
        String finalBefore = before;
        switch (field2) {
            case "ip": {
                return userLogs.stream()
                        .filter(userLog -> finalAfter == null || userLog.getDate().after(UserLog.parseDate(finalAfter)))
                        .filter(userLog -> finalBefore == null || userLog.getDate().before(UserLog.parseDate(finalBefore)))
                        .filter(userLog -> userLog.getIp().equals(value1))
                        .map(userLog -> userLog.getAny(field1))
                        .collect(Collectors.toSet());
            }
            case "user": {
                return userLogs.stream()
                        .filter(userLog -> finalAfter == null || userLog.getDate().after(UserLog.parseDate(finalAfter)))
                        .filter(userLog -> finalBefore == null || userLog.getDate().before(UserLog.parseDate(finalBefore)))
                        .filter(userLog -> userLog.getName().equals(value1))
                        .map(userLog -> userLog.getAny(field1))
                        .collect(Collectors.toSet());
            }
            case "date": {
                return userLogs.stream()
                        .filter(userLog -> userLog.getDate().equals(UserLog.parseDate(value1)))
                        .filter(userLog -> finalAfter == null || userLog.getDate().after(UserLog.parseDate(finalAfter)))
                        .filter(userLog -> finalBefore == null || userLog.getDate().before(UserLog.parseDate(finalBefore)))
                        .map(userLog -> userLog.getAny(field1))
                        .collect(Collectors.toSet());
            }
            case "event": {
                return userLogs.stream()
                        .filter(userLog -> finalAfter == null || userLog.getDate().after(UserLog.parseDate(finalAfter)))
                        .filter(userLog -> finalBefore == null || userLog.getDate().before(UserLog.parseDate(finalBefore)))
                        .filter(userLog -> userLog.getEvent()== Event.valueOf(value1))
                        .map(userLog -> userLog.getAny(field1))
                        .collect(Collectors.toSet());
            }
            case "status": {
                return userLogs.stream()
                        .filter(userLog -> finalAfter == null || userLog.getDate().after(UserLog.parseDate(finalAfter)))
                        .filter(userLog -> finalBefore == null || userLog.getDate().before(UserLog.parseDate(finalBefore)))
                        .filter(userLog -> userLog.getStatus()== Status.valueOf(value1))
                        .map(userLog -> userLog.getAny(field1))
                        .collect(Collectors.toSet());
            }
        }
        return null;
    }

    private Set<Object> getRespondForOneParam(String query) {
        switch (query) {
            case "get ip": {
                // return new HashSet<>(getIPsForUser(userStrA[userStrA.length - 1], null, null));
                return new HashSet<>(getUniqueIPs(null, null));
            }
            case "get user": {
                return new HashSet<>(getAllUsers());
            }
            case "get date": {
                return userLogs.stream().map(UserLog::getDate).distinct().collect(Collectors.toSet());
            }
            case "get event": {
                return new HashSet<>(getAllEvents(null, null));
            }
            case "get status": {
                return userLogs.stream().map(UserLog::getStatus).distinct().collect(Collectors.toSet());
            }
        }
        return null;
    }

}