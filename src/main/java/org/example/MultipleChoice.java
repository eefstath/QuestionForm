package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class MultipleChoice extends Question{
    private ArrayList<Answer> answers;

    private static final Logger logger = LogManager.getLogger(MultipleChoice.class);

    public MultipleChoice(String text, String documentation, int difficulty, int pointScale, ArrayList<Answer> answers, Section section) {
        super(text, documentation, difficulty, pointScale, section);
        this.answers = answers;
        logger.info("A multiple-choice question has been created");
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }
}
