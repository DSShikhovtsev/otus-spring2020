package homework1.dao;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

import java.io.File;

@ConfigurationProperties(prefix = "question")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Repository
public class QuestionsDaoSimple implements QuestionsDao {

    private String file;

    public File getQuestions() {
        return convertToFile();
    }

    private File convertToFile() {
        ClassLoader loader = QuestionsDaoSimple.class.getClassLoader();
        return new File(loader.getResource(file).getFile());
    }
}
