package homework1.dao;

import homework1.domain.Student;
import lombok.Getter;
import org.springframework.stereotype.Repository;

@Getter
@Repository
public class StudentDaoSimple implements StudentDao {

    private int mark;

    public StudentDaoSimple() {
        this.mark = 0;
    }

    public Student findByName(String name) {
        return new Student(name);
    }

    public int getMarkByName(String name) {
        return mark;
    }

    public void addPointByName(String name, int point) {
        mark += point;
    }
}
