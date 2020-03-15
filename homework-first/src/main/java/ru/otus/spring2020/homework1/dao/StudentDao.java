package ru.otus.spring2020.homework1.dao;

import ru.otus.spring2020.homework1.domain.Student;

public interface StudentDao {

    Student findByName(String name);
    int getMarkByName(String name);
}
