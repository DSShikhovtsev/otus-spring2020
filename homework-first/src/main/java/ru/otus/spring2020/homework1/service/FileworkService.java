package ru.otus.spring2020.homework1.service;

import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

@Service
public class FileworkService {

    public void consoleWorking(List<String> list) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        list.forEach(t -> {
            System.out.println(t);
            try {
                reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public String readLine() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return reader.readLine();
    }

    public void hello() {
        System.out.println("Введите свои Фамилию Имя отчество: ");
    }

    public void bye(String fio, int mark) {
        System.out.println("Пользователь " + fio
                + ", количество вопросов, на которые вы верно ответили, равно "
                + mark);
        System.out.println("Поздравляем! Вы особенный!");
    }
}
