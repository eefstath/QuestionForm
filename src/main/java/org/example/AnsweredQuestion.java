package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

public abstract class AnsweredQuestion{
    protected ValuedQuestion valuedQuestion;
    protected List<ValuedAnswer> valuedChosenAnswers;
    protected float points;
    protected boolean correctness;

    protected static final Logger logger = LogManager.getLogger(AnsweredQuestion.class);


    public AnsweredQuestion(ValuedQuestion valuedQuestion, List<ValuedAnswer> valuedChosenAnswers) {
        this(valuedQuestion, valuedChosenAnswers,0,false);
    }

    public AnsweredQuestion(ValuedQuestion valuedQuestion, List<ValuedAnswer> valuedChosenAnswers, float points, boolean correctness) {
        this.valuedQuestion = valuedQuestion;
        this.valuedChosenAnswers = valuedChosenAnswers;
        this.points = points;
        this.correctness = correctness;
        logger.info("An answered question instance has been created");
    }

    public ValuedQuestion getValuedQuestion() {
        return valuedQuestion;
    }

    public void setValuedQuestion(ValuedQuestion valuedQuestion) {
        this.valuedQuestion = valuedQuestion;
    }

    public List<ValuedAnswer> getValuedChosenAnswers() {
        return valuedChosenAnswers;
    }

    public void setValuedChosenAnswers(List<ValuedAnswer> valuedChosenAnswers) {
        this.valuedChosenAnswers = valuedChosenAnswers;
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

    public abstract void calculatePoints();
}
