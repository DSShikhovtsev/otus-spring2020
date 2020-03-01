package ru.otus.spring2020.homework1;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring2020.homework1.service.CommunicationService;

public class Application {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        CommunicationService communicationService = context.getBean(CommunicationService.class);
        communicationService.communicate();
    }
}
