package homework1.service;

import homework1.domain.Question;
import homework1.properties.LocaleProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

@Service
public class ConsoleService {

    private MessageSource messageSource;
    private LocaleProperties localeProperties;

    @Autowired
    public ConsoleService(MessageSource messageSource, LocaleProperties localeProperties) {
        this.messageSource = messageSource;
        this.localeProperties = localeProperties;
    }

    public int readQuestions(List<Question> list) {
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

    public String readLine() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void hello() {
        System.out.println(messageSource.getMessage("hello", null, new Locale(localeProperties.getLocale())));
    }

    public void bye(String fio, int mark) {
        System.out.println(messageSource.getMessage("score", new String[] {fio}, new Locale(localeProperties.getLocale()))
                + mark);
        System.out.println(messageSource.getMessage("goodJob", null, new Locale(localeProperties.getLocale())));
    }
}
