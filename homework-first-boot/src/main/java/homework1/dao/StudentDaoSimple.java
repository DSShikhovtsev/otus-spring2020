package homework1.dao;

import homework1.domain.Student;
import lombok.Getter;
import org.springframework.stereotype.Repository;

@Getter
@Repository
public class StudentDaoSimple implements StudentDao {

    private final int mark;

    public StudentDaoSimple() {
        this.mark = (int) (Math.random() * 5);
    }

    public Student findByName(String name) {
        return new Student(name);
    }

    public int getMarkByName(String name) {
        return mark;
    }
}
