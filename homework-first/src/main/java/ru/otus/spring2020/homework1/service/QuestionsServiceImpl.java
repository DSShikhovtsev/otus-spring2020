package ru.otus.spring2020.homework1.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring2020.homework1.dao.QuestionsDao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Service
public class QuestionsServiceImpl implements QuestionsService {

    private QuestionsDao dao;

    public List<String> getQuestions() {
        return convertQuestions();
    }

    private List<String> convertQuestions() {
        String[] data = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(dao.getQuestions()));
            String row;
            while ((row = reader.readLine()) != null) {
                data = row.split(",");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> list = new ArrayList<>();
        if (data != null) {
            list.addAll(Arrays.asList(data));
        }
        return list;
    }
}
