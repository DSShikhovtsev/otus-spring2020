package homework1.service;

import homework1.domain.Question;

import java.util.List;

public interface ConsoleService {

    int askQuestionsAndGetRightAnswersCount(List<Question> list);
    String readLine();
    void hello();
    void bye(String fio, int mark);
}
