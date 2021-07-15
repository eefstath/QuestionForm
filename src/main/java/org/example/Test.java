package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class Test {
    private String name;
    private ArrayList<Question> questions;
    private int base;

    private static final Logger logger = LogManager.getLogger(Test.class);

    public Test(String name, ArrayList<Question> questions, int base) {
        this.name = name;
        this.questions = questions;
        this.base = base;
        logger.info("A test has been created");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public int getBase() {
        return base;
    }

    public void setBase(int base) {
        this.base = base;
    }
}
