package homework1.dao;

import homework1.domain.Question;
import homework1.exception.QuestionsAndAnswerLoadingException;
import homework1.properties.QuestionProperties;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Repository
public class QuestionsDaoCsv implements QuestionsDao {

    private QuestionProperties questionProperties;

    @Autowired
    public QuestionsDaoCsv(QuestionProperties questionProperties) {
        this.questionProperties = questionProperties;
    }

    public List<Question> getQuestions() throws QuestionsAndAnswerLoadingException {
        return getQuestionsFromFile();
    }

    private List<Question> getQuestionsFromFile() throws QuestionsAndAnswerLoadingException {
        return convertQuestions();
    }

    private List<Question> convertQuestions() throws QuestionsAndAnswerLoadingException {
        List<Question> queAns = new ArrayList<>();
        String[] data;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(getFile()));
            String row;
            while ((row = reader.readLine()) != null) {
                data = row.split(",");
                queAns.add(new Question(data[0], data[1]));
            }
        } catch (IOException e) {
            throw new QuestionsAndAnswerLoadingException(e);
        }
        return queAns;
    }

    private File getFile() {
        ClassLoader loader = QuestionsDaoCsv.class.getClassLoader();
        return new File(loader.getResource(questionProperties.getFile()).getFile());
    }
}
