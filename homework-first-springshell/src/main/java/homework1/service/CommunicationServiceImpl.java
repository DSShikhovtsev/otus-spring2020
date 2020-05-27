package homework1.service;

import homework1.exception.SimpleException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CommunicationServiceImpl implements CommunicationService {

    private final CommunicationOutputService communicationOutputService;
    private final StudentService studentService;
    private final QuestionsService questionsService;
    private final CommunicationInputService communicationInputService;

    public void communicate() {

        String fio = "";

        communicationOutputService.hello();
        try {
            fio = communicationInputService.readLine();
            studentService.addPointByName(fio, communicationOutputService.askQuestionsAndGetRightAnswersCount(questionsService.getQuestionsAndAnswers()));
        } catch (SimpleException e) {
            e.printStackTrace();
        }
        communicationOutputService.bye(fio, studentService.getMarkByName(fio));
    }
}
