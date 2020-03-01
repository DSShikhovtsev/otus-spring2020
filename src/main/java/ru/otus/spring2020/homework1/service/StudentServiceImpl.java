package ru.otus.spring2020.homework1.service;

import lombok.AllArgsConstructor;
import ru.otus.spring2020.homework1.dao.StudentDao;
import ru.otus.spring2020.homework1.domain.Student;

@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private StudentDao dao;

    public Student getByName(String name) {
        return dao.findByName(name);
    }
}
