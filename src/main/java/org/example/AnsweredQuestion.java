package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

public class AnsweredQuestion{
    private Question question;
    private List<Answer> chosenAnswers;
    private float points;
    private boolean correctness;

    private static final Logger logger = LogManager.getLogger(AnsweredQuestion.class);


    public AnsweredQuestion(Question question, List<Answer> chosenAnswers) {
        this(question, chosenAnswers,0,false);
    }

    public AnsweredQuestion(Question question, List<Answer> chosenAnswers, float points, boolean correctness) {
        this.question = question;
        this.chosenAnswers = chosenAnswers;
        this.points = points;
        this.correctness = correctness;
        logger.info("An answered question instance has been created");
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<Answer> getChosenAnswers() {
        return chosenAnswers;
    }

    public void setChosenAnswers(List<Answer> chosenAnswers) {
        this.chosenAnswers = chosenAnswers;
    }

    public float getPoints() {
        return points;
    }

    public void setPoints(float points) {
        this.points = points;
    }

    public boolean getCorrectness() {
        return correctness;
    }
    public void setCorrectness(boolean correctness) {
        this.correctness = correctness;
    }
}
