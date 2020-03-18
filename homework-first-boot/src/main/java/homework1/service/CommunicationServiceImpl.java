package homework1.service;

import homework1.domain.Student;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@AllArgsConstructor
@Service
public class CommunicationServiceImpl implements CommunicationService {

    private final FileworkService fileworkService;
    private final StudentService studentService;
    private final QuestionsService questionsService;

    public void communicate() {

        Student student = new Student();

        fileworkService.hello();
        try {
            student = studentService.getByName(fileworkService.readLine());
            fileworkService.consoleWorking(questionsService.getQuestions());
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileworkService.bye(student.getFio(), studentService.getMarkByName(student.getFio()));
    }
}
