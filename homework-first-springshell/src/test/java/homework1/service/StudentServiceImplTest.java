package homework1.service;

import homework1.dao.StudentDao;
import homework1.dao.StudentDaoSimple;
import homework1.domain.Student;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Класс StudentServiceImpl")
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class StudentServiceImplTest {

    @Configuration
    static class TestConfiguration {
        @Bean
        public StudentDao studentDao() {
            return new StudentDaoSimple();
        }
    }

    @Autowired
    private StudentDao studentDao;

    @DisplayName("должен корректно возвращать студента по имени")
    @Test
    void shouldReturnCorrectStudentByName() {
        Student student = new Student("fio");

        assertThat(student).isEqualToComparingFieldByField(studentDao.findByName("fio"));
    }

    @DisplayName("должен корректно возвращать оценку студента по имени")
    @Test
    void shouldReturnCorrectMarkByStudentName() {
        studentDao.findByName("fio");
        assertEquals(0, studentDao.getMarkByName("fio"));
    }

    @DisplayName("должен корректно добавлять баллы к оценке")
    @Test
    void shouldAddPointsCorrectly() {
        studentDao.findByName("fio");
        studentDao.addPointByName("fio", 3);
        assertEquals(3, studentDao.getMarkByName("fio"));
    }
}
