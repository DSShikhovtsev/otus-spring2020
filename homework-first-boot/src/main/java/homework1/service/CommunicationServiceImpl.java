package homework1.service;

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

        String fio = "";

        consoleService.hello();
        try {
            fio = consoleService.readLine();
            studentService.addPointByName(fio, consoleService.askQuestionsAndGetRightAnswersCount(questionsService.getQuestionsAndAnswers()));
        } catch (SimpleException e) {
            e.printStackTrace();
        }
        consoleService.bye(fio, studentService.getMarkByName(fio));
    }
}
