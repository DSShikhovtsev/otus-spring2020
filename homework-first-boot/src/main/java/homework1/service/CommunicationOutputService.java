package homework1.service;

import homework1.domain.Question;

import java.util.List;

public interface CommunicationOutputService {

    int askQuestionsAndGetRightAnswersCount(List<Question> list);
    void hello();
    void bye(String fio, int mark);
}
