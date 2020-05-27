package homework1.service;

import homework1.dao.QuestionsDao;
import homework1.dao.QuestionsDaoCsv;
import homework1.domain.Question;
import homework1.exception.QuestionsAndAnswerLoadingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Класс QuestionServiceImpl")
@ExtendWith(SpringExtension.class)
@TestPropertySource("classpath:test.yml")
@SpringBootTest
public class QuestionServiceImplTest {

    @Configuration
    static class TestConfiguration {
        @Bean
        public QuestionsDao questionsDao() {
            return new QuestionsDaoCsv();
        }
    }

    @Autowired
    private QuestionsDao questionsDao;

    @DisplayName("должен корректно возвращать список вопросов и ответов")
    @Test
    void shouldReturnListOfQuestionsAndAnswers() throws QuestionsAndAnswerLoadingException {
        QuestionsServiceImpl service = new QuestionsServiceImpl(questionsDao);
        List<Question> list = new ArrayList<>();
        list.add(new Question("1. Sharks have 5 rows of teeth? (Y/N)", "N"));
        list.add(new Question("2. Is the earth the center of the universe? (Y/N)", "N"));
        list.add(new Question("3. Is the cucumber red? (Y/N)", "N"));
        list.add(new Question("4. Is the hedgehog a predator? (Y/N)", "Y"));
        list.add(new Question("5. Does an umbrella protect against rain? (Y/N)", "Y"));

        assertThat(list).isEqualTo(service.getQuestionsAndAnswers());
    }
}
