package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Result {
//    private int id;
    private Timestamp startTimestamp;
    private Timestamp endTimestamp;
    private Test test;
    private ArrayList<ArrayList<Answer>> answers;
    private float grade;

    private static final Logger logger = LogManager.getLogger(Result.class);

    public Result(Timestamp startTimestamp, Timestamp endTimestamp, Test test, ArrayList<ArrayList<Answer>> chosenAnswers, float grade) {
        this.startTimestamp = startTimestamp;
        this.endTimestamp = endTimestamp;
        this.test = test;
        this.answers = chosenAnswers;
        this.grade = grade;
        logger.info("A result has been created");
    }

    public Timestamp getStartTimestamp() {
        return startTimestamp;
    }

    public void setStartTimestamp(Timestamp timestamp) {
        this.startTimestamp = timestamp;
    }

    public Timestamp getEndTimestamp() {
        return endTimestamp;
    }

    public void setEndTimestamp(Timestamp endTimestamp) {
        this.endTimestamp = endTimestamp;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public ArrayList<ArrayList<Answer>> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<ArrayList<Answer>> answers) {
        this.answers = answers;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }
}
