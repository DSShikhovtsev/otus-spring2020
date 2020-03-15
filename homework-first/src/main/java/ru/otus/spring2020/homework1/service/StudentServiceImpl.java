package ru.otus.spring2020.homework1.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring2020.homework1.dao.StudentDao;
import ru.otus.spring2020.homework1.domain.Student;

@AllArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {

    private StudentDao dao;

    public Student getByName(String name) {
        return dao.findByName(name);
    }

    public int getMarkByName(String name) {
        return dao.getMarkByName(name);
    }
}
