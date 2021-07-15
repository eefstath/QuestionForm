package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Answer {
    private String text;
    private boolean bool;
    private int points;

    private static final Logger logger = LogManager.getLogger(Answer.class);

    public Answer(String text, boolean bool, int points){
        this.text = text;
        this.bool = bool;
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
        return bool;
    }

    public void setBool(boolean bool) {
        this.bool = bool;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
