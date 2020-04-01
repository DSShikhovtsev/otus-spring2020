package homework1.service;

import homework1.domain.Student;
import homework1.exception.SimpleException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CommunicationServiceImpl implements CommunicationService {

    private final ConsoleService consoleService;
    private final StudentService studentService;
    private final QuestionsService questionsService;

    public void communicate() {

        Student student = new Student();

        consoleService.hello();
        try {
            student = studentService.getByName(consoleService.readLine());
            studentService.addPointByName(student.getFio(), consoleService.readQuestions(questionsService.getQuestionsAndAnswers()));
        } catch (SimpleException e) {
            e.printStackTrace();
        }
        consoleService.bye(student.getFio(), studentService.getMarkByName(student.getFio()));
    }
}
