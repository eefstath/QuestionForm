package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Answer {
    private String text;
    private boolean correctness;
    private float points;

    private static final Logger logger = LogManager.getLogger(Answer.class);

    public Answer(String text, boolean bool, float points){
        this.text = text;
        this.correctness = bool;
        this.points = points;
        logger.info("An answer has been created");
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean getBool() {
        return correctness;
    }

    public void setBool(boolean correctness) {
        this.correctness = correctness;
    }

    public float getPoints() {
        return points;
    }

    public void setPoints(float points) {
        this.points = points;
    }
}
