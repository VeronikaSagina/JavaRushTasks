package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.QLQuery;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class LogParser2 implements QLQuery {

    private List<UserLog> userLogs;

    public Stream<UserLog> getLogs() {
        return userLogs.stream();
    }

    public LogParser2(Path logDir) {
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
    public Set<Object> execute(String query) {
        int firstSpaceIndex = query.indexOf(' ');
        int nextSpaceIndex = query.indexOf(' ', firstSpaceIndex + 1);
        int endOfReturning = nextSpaceIndex > 0 ? nextSpaceIndex : query.length();
        String returning = query.substring(firstSpaceIndex + 1, endOfReturning);
        Specification specification = new Specification();
        specification.setReturning(returning);
        query = query.substring(endOfReturning);
        if (query.startsWith(" for ")) {
            query = query.substring(" for ".length());
        }
        while (query.length() != 0) {
            int eqIndex = query.indexOf(" = ");
            int dateBetweenIndex = query.indexOf("date between");
            if (eqIndex > 0 && (dateBetweenIndex < 0 || eqIndex < dateBetweenIndex)) {
                String param = query.substring(0, eqIndex);
                String value = query.substring(eqIndex + 4, query.indexOf('"', eqIndex + 4));
                switch (param) {
                    case "user":
                    case "ip":
                        specification.add(param, value);
                        break;
                    case "event":
                        specification.add(param, Event.valueOf(value));
                        break;
                    case "task":
                        specification.add(param, Integer.valueOf(value));
                        break;
                    case "status":
                        specification.add(param, Status.valueOf(value));
                        break;
                }
            } else {
                int afterIndex = query.indexOf('"') + 1;
                int afterEndIndex = query.indexOf('"', afterIndex);
                Date after = UserLog.parseDate(query.substring(afterIndex, afterEndIndex));
                int beforeIndex = query.indexOf('"', afterEndIndex + 1) + 1;
                int beforeEndIndex = query.indexOf('"', beforeIndex);
                Date before = UserLog.parseDate(query.substring(beforeIndex, beforeEndIndex));
                specification.add("after", after);
                specification.add("before", before);
                query = query.substring(beforeEndIndex + 1);
            }
            int andIndex = query.indexOf(" and ");
            if (andIndex < 0) {
                break;
            }
            query = query.substring(andIndex + " and ".length());
        }
        return specification.apply(getLogs());
    }
}
