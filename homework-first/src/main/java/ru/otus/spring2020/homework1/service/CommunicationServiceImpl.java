package ru.otus.spring2020.homework1.service;

import lombok.AllArgsConstructor;
import ru.otus.spring2020.homework1.Application;
import ru.otus.spring2020.homework1.domain.Student;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

@AllArgsConstructor
public class CommunicationServiceImpl implements CommunicationService {

    private FileworkService fileworkService;
    private StudentService studentService;

    public void communicate() {
        ClassLoader classLoader = Application.class.getClassLoader();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        File file = new File(classLoader.getResource("questions.csv").getFile());
        String[] data = fileworkService.readData(file);
        Student student = new Student();

        System.out.println("Введите свои Фамилию Имя отчество: ");
        try {
            student = studentService.getByName(reader.readLine());

            for (int i = 0; i < data.length; i++) {
                System.out.println(data[i]);
                reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Пользователь " + student.getFio() + ", количество вопросов, на которые вы верно ответили, равно " + student.getMark());
        System.out.println("Поздравляем! Вы особенный!");
    }
}
