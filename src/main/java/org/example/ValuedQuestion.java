package org.example;

import java.util.List;

public abstract class ValuedQuestion {
    protected Question question;
    protected float pointScale;
    protected float difficulty;

    public ValuedQuestion(Question question, float difficulty) {
        this.question = question;
        this.difficulty = difficulty;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Question getQuestion() {
        return question;
    }

    public void setPointScale(float pointScale) {
        this.pointScale = pointScale;
    }

    public float getPointScale() {
        return pointScale;
    }

    public void setDifficulty(float difficulty) {
        this.difficulty = difficulty;
    }

    public float getDifficulty() {
        return difficulty;
    }

    public abstract List<ValuedAnswer> getPossibleValuedAnswers();

    public abstract void calculatePointScale();

    public abstract AnsweredQuestion createAnsweredQuestion(List<ValuedAnswer> valuedChosenAnswers);
}
