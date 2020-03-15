package ru.otus.spring2020.homework1.dao;

import org.springframework.stereotype.Repository;

import java.io.File;

@Repository
public interface QuestionsDao {

    File getQuestions();
}
