package homework1.service;

import homework1.domain.Question;
import homework1.exception.SimpleException;

import java.util.List;

public interface QuestionsService {

    List<Question> getQuestionsAndAnswers() throws SimpleException;
}
