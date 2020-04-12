package homework1.dao;

import homework1.domain.Student;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Getter
@Repository
public class StudentDaoSimple implements StudentDao {

    private final Map<String, Integer> studentsMark;

    public StudentDaoSimple() {
        this.studentsMark = new HashMap<>();
    }

    public Student findByName(String name) {
        studentsMark.put(name, 0);
        return new Student(name);
    }

    public int getMarkByName(String name) {
        return studentsMark.get(name);
    }

    public void addPointByName(String name, int point) {
        studentsMark.put(name, point);
    }
}
