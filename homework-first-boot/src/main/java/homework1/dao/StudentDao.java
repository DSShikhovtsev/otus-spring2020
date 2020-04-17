package homework1.dao;

import homework1.domain.Student;

public interface StudentDao {

    Student findByName(String name);
    int getMarkByName(String name);
    void addPointByName(String name, int point);
}
