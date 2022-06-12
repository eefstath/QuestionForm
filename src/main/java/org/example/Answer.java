package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Answer {
    private String text;
    private boolean correctness;

    private static final Logger logger = LogManager.getLogger(Answer.class);

    public Answer(String text, boolean correctness){
        this.text = text;
        this.correctness = correctness;
        logger.info("An answer has been created");
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean getCorrectness() {
        return correctness;
    }

    public void setCorrectness(boolean correctness) {
        this.correctness = correctness;
    }

    //Function that creates a valued answer using the points parameter
    public ValuedAnswer createValuedAnswer(float points){
        return new ValuedAnswer(this,points);
    }
}
