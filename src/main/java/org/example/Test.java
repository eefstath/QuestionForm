package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class Test {
    private String name;
    private ArrayList<Question> questions;
    private float minPercentage;
    private float totalScore;
    private static final Logger logger = LogManager.getLogger(Test.class);

    public Test(String name, ArrayList<Question> questions, float minPercentage) {
        this.name = name;
        this.questions = questions;
        this.minPercentage = minPercentage;
        calculateTotalScore();
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

    public float getMinPercentage() {
        return minPercentage;
    }

    public void setMinPercentage(float minPercentage) {
        this.minPercentage = minPercentage;
    }

    public void setTotalScore(float totalScore) {
        this.totalScore = totalScore;
    }

    public float getTotalScore() {
        return totalScore;
    }

    private void calculateTotalScore() {
        for (Question question : questions) {
            this.totalScore += question.getPointScale();
        }
    }
}
