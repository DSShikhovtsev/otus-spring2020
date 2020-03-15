package ru.otus.spring2020.homework1.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

import java.io.File;

@PropertySource("classpath:application.properties")
@Repository
public class QuestionsDaoSimple implements QuestionsDao {

    private final String questions;

    public QuestionsDaoSimple(@Value("${question.file}") String questions) {
        this.questions = questions;
    }

    public File getQuestions() {
        return convertToFile();
    }

    private File convertToFile() {
        ClassLoader loader = QuestionsDaoSimple.class.getClassLoader();
        return new File(loader.getResource(questions).getFile());
    }
}
