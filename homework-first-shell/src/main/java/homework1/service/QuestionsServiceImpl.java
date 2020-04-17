package homework1.service;

import homework1.dao.QuestionsDao;
import homework1.domain.Question;
import homework1.exception.QuestionsAndAnswerLoadingException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class QuestionsServiceImpl implements QuestionsService {

    private final QuestionsDao dao;

    public List<Question> getQuestionsAndAnswers() throws QuestionsAndAnswerLoadingException {
        return dao.getQuestions();
    }
}
