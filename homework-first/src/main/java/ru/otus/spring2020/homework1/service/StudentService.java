package ru.otus.spring2020.homework1.service;

import ru.otus.spring2020.homework1.domain.Student;

public interface StudentService {

    Student getByName(String name);
}
