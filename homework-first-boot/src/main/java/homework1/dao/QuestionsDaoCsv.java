package homework1.dao;

import homework1.domain.Question;
import homework1.exception.SimpleException;
import homework1.properties.QuestionsProperties;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@NoArgsConstructor
@Repository
public class QuestionsDaoCsv implements QuestionsDao {

    private QuestionsProperties properties;

    @Autowired
    public QuestionsDaoCsv(QuestionsProperties properties) {
        this.properties = properties;
    }

    public List<Question> getQuestions() throws SimpleException {
        return getQuestionsFromFile();
    }

    private List<Question> getQuestionsFromFile() throws SimpleException {
        return convertQuestions();
    }

    private List<Question> convertQuestions() throws SimpleException {
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
            throw new SimpleException(e);
        }
        return queAns;
    }

    private File getFile() {
        ClassLoader loader = QuestionsDaoCsv.class.getClassLoader();
        return new File(loader.getResource(properties.getFile()).getFile());
    }
}
