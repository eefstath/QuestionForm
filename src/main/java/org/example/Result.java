package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

//Class Result contains the results of a test taken by a user.
public class Result {
//    private int id;
    //Start and End timestamp are saved to calculate the exact time needed for the test to be submitted.
    private Timestamp startTimestamp;
    private Timestamp endTimestamp;
    private Test test;
    //List of the test's answered questions
    private List<AnsweredQuestion> answeredQuestionList;
    private float grade;

    private static final Logger logger = LogManager.getLogger(Result.class);

    public Result(Timestamp startTimestamp, Test test) {
        this(startTimestamp, startTimestamp, test, new ArrayList<AnsweredQuestion>(), 0);
    }
    public Result(Timestamp startTimestamp, Timestamp endTimestamp, Test test, List<AnsweredQuestion> answeredQuestionList, float grade) {
        this.startTimestamp = startTimestamp;
        this.endTimestamp = endTimestamp;
        this.test = test;
        this.answeredQuestionList = answeredQuestionList;
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

    public List<AnsweredQuestion> getAnsweredQuestionList() {
        return answeredQuestionList;
    }

    public void setAnsweredQuestionList(List<AnsweredQuestion> answeredQuestionList) {
        this.answeredQuestionList = answeredQuestionList;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public void calculateGrade() {
        for(AnsweredQuestion answeredQuestion : answeredQuestionList) {
            grade += answeredQuestion.points;
        }
    }
}
