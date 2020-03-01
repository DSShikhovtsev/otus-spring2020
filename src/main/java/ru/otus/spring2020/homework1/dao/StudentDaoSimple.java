package ru.otus.spring2020.homework1.dao;

import ru.otus.spring2020.homework1.domain.Student;

public class StudentDaoSimple implements StudentDao {

    private int mark;

    public void setMark(int mark) {
        this.mark = (int) (Math.random() * mark);
    }

    public Student findByName(String name) {
        return new Student(name, mark);
    }
}
