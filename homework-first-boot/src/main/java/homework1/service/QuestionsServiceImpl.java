package homework1.service;

import homework1.dao.QuestionsDao;
import homework1.domain.Question;
import homework1.exception.SimpleException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@AllArgsConstructor
@Service
public class QuestionsServiceImpl implements QuestionsService {

    private QuestionsDao dao;

    public List<Question> getQuestionsAndAnswers() throws SimpleException {
        return dao.getQuestions();
    }
}
