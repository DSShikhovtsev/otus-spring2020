package homework1.service;

import homework1.domain.Question;
import homework1.properties.LocaleProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Service
public class ConsoleOutputService implements CommunicationOutputService {

    private final MessageSource messageSource;
    private final LocaleProperties localeProperties;

    @Autowired
    public ConsoleOutputService(MessageSource messageSource, LocaleProperties localeProperties) {
        this.messageSource = messageSource;
        this.localeProperties = localeProperties;
    }

    public int askQuestionsAndGetRightAnswersCount(List<Question> list) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int point = 0;

        for (Question question : list) {
            System.out.println(question.getQuestion());
            try {
                if (reader.readLine().equals(question.getAnswer())) {
                    point++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return point;
    }

    public void hello() {
        System.out.println(messageSource.getMessage("hello", null, localeProperties.getLocale()));
    }

    public void bye(String fio, int mark) {
        System.out.println(messageSource.getMessage("score", new String[] {fio}, localeProperties.getLocale())
                + mark);
        System.out.println(messageSource.getMessage("goodJob", null, localeProperties.getLocale()));
    }
}
