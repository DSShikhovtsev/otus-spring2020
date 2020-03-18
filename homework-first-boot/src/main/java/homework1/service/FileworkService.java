package homework1.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Locale;

@Service
public class FileworkService {

    private MessageSource messageSource;

    public FileworkService(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public void consoleWorking(List<String> list) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        list.forEach(t -> {
            System.out.println(t);
            try {
                reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public String readLine() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return reader.readLine();
    }

    public void hello() {
        System.out.println(messageSource.getMessage("hello", null, new Locale("RU")));
    }

    public void bye(String fio, int mark) {
        System.out.println(messageSource.getMessage("score", new String[] {fio}, new Locale("RU"))
                + mark);
        System.out.println(messageSource.getMessage("goodJob", null, new Locale("RU")));
    }
}
