package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;

public class OpenEnded extends Question{

    private Answer answer;
    private static final String INSTRUCTIONS = "Write your answer below.";
    private static final Logger logger = LogManager.getLogger(OpenEnded.class);

    public OpenEnded(String text, Answer answer, String documentation, int difficulty, int pointScale, Section section) {
        super(text, documentation, difficulty, pointScale, section);
        this.answer = answer;
        logger.info("An open-ended Question has been created");
    }

    public ArrayList<Answer> getAnswers() {
        return null;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public String getInstructions() {
        return INSTRUCTIONS;
    }
}
