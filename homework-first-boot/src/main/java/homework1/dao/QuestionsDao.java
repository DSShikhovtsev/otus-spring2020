package homework1.dao;

import homework1.domain.Question;
import homework1.exception.SimpleException;

import java.util.List;

public interface QuestionsDao {

    List<Question> getQuestions() throws SimpleException;
}
