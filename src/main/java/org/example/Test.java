package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

//Class Test is the test created
public class Test {
    private String name;
    private List<ValuedQuestion> valuedQuestions;
    //Minimum percentage needed to pass the test successfully
    private float minPercentage;
    //Minimum score needed to pass the test successfully
    private float minScore;
    //Sum of all question's points
    private float totalScore;
    private static final Logger logger = LogManager.getLogger(Test.class);

    public Test(String name, List<ValuedQuestion> valuedQuestions, float minPercentage) {
        this.name = name;
        this.valuedQuestions = valuedQuestions;
        this.minPercentage = minPercentage;
        calculateTotalScore();
        calculateMinScore();
        logger.info("A test has been created");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ValuedQuestion> getValuedQuestions() {
        return valuedQuestions;
    }

    public void setValuedQuestions(List<ValuedQuestion> valuedQuestions) {
        this.valuedQuestions = valuedQuestions;
    }

    public float getMinPercentage() {
        return minPercentage;
    }

    public void setMinPercentage(float minPercentage) {
        this.minPercentage = minPercentage;
    }

    public float getMinScore() {
        return minScore;
    }

    public void setMinScore(float minScore) {
        this.minScore = minScore;
    }

    public void setTotalScore(float totalScore) {
        this.totalScore = totalScore;
    }

    public float getTotalScore() {
        return totalScore;
    }

    //Function adds each question's points to calculate total score of test
    private void calculateTotalScore() {
        for (ValuedQuestion question : valuedQuestions) {
            totalScore += question.getPointScale();
        }
    }

    //Function calculates the minimum score by multiplying the minimum percentage to the total score
    private void calculateMinScore() {
        minScore = minPercentage * totalScore / 100;
    }
}
