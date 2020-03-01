package ru.otus.spring2020.homework1.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileworkService {

    public String[] readData(File file) {
        String[] data = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String row;
            while ((row = reader.readLine()) != null) {
                data = row.split(",");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
