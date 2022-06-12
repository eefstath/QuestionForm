package org.example;

import java.util.List;

public class ValuedAnswer {
    private Answer answer;
    private float points;

    public ValuedAnswer(Answer answer, float points) {
        this.answer = answer;
        this.points = points;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public void setPoints(float points) {
        this.points = points;
    }

    public float getPoints() {
        return points;
    }
}
