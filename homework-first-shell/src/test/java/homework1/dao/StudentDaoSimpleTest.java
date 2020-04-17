package homework1.dao;

import homework1.domain.Student;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Класс StudentDaoSimple")
public class StudentDaoSimpleTest {

    @DisplayName("должен корректно возвращать студента по имени")
    @Test
    void findByNameShouldReturnCorrectStudent() {
        StudentDaoSimple dao = new StudentDaoSimple();
        Student student = new Student("fio");

        assertEquals(dao.findByName("fio").getFio(), student.getFio());
    }

    @DisplayName("должен корректно возвращать оценку по имени студента")
    @Test
    void getMarkByNameShouldReturnCorrectMark() {
        StudentDaoSimple dao = new StudentDaoSimple();
        dao.findByName("fio");

        assertEquals(0, dao.getMarkByName("fio"));
    }

    @DisplayName("должен корректно добавлять баллы к оценке")
    @Test
    void addPointByNameShouldAddPointsCorrectly() {
        StudentDaoSimple dao = new StudentDaoSimple();
        dao.addPointByName("fio", 3);

        assertEquals(3, dao.getStudentsMark().get("fio"));
    }
}
