package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class Result {
//    private int id;
    private int timestamp;
    private Test test;
    private ArrayList<Answer> answers;
    private float grade;

    private static final Logger logger = LogManager.getLogger(Result.class);

    public Result(int timestamp, Test test, ArrayList<Answer> chosenAnswers, float grade) {
        this.timestamp = timestamp;
        this.test = test;
        this.answers = chosenAnswers;
        this.grade = grade;
        logger.info("A result has been created");
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }
}
