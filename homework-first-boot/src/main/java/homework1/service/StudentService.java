package homework1.service;

import homework1.domain.Student;

public interface StudentService {

    Student getByName(String name);
    int getMarkByName(String name);
}
