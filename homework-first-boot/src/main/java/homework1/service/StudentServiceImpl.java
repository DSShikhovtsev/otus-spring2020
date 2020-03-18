package homework1.service;

import homework1.dao.StudentDao;
import homework1.domain.Student;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
