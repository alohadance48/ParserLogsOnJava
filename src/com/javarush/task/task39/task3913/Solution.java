package com.javarush.task.task39.task3913;

import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        try {
            LogParser logParser = new LogParser(Paths.get("/home/vladosl/javarush/3562975/javarush-project/src/com/javarush/task/task39/task3913/logs"));
            SimpleDateFormat sdf = new SimpleDateFormat("d.M.yyyy H:m:s");
            int uniqueIPsCount = logParser.getNumberOfUniqueIPs(null, new Date());
            System.out.println("Количество уникальных IP: " + uniqueIPsCount);
            String query1 = "get ip for user = \"Eduard Petrovich Morozko\" and date between \"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\"";
            Set<Object> ipsForUser = logParser.execute(query1);
            System.out.println("IP для пользователя Eduard Petrovich Morozko в заданном диапазоне:");
            for (Object ip : ipsForUser) {
                System.out.println(ip);
            }
            String query2 = "get user for event = \"WRITE_MESSAGE\"";
            Set<Object> usersWhoWroteMessages = logParser.execute(query2);
            System.out.println("Пользователи, писавшие сообщения:");
            for (Object user : usersWhoWroteMessages) {
                System.out.println(user);
            }
            String query3 = "get event for ip = \"127.0.0.1\"";
            Set<Object> eventsForIP = logParser.execute(query3);
            System.out.println("События для IP 127.0.0.1:");
            for (Object event : eventsForIP) {
                System.out.println(event);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
