package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OpenEnded extends Question{

    private Answer answer;
    private static final String INSTRUCTIONS = "Write your answer below.";
    private static final Logger logger = LogManager.getLogger(OpenEnded.class);

    public OpenEnded(String text, Answer answer, String documentation, int difficulty, Section section) {
        super(text, documentation, difficulty, section);
        this.answer = answer;
        calculatePointScale();
        logger.info("An open-ended Question has been created");
    }

    public List<Answer> getPossibleAnswers() {
        return new ArrayList<>();
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public Answer getAnswer() {
        return answer;
    }

    public String getInstructions() {
        return INSTRUCTIONS;
    }

    public void calculatePointScale() {
        super.pointScale = this.answer.getPoints();
    }

    public List<Answer> getCorrectAnswers() {
        return new ArrayList<>(Arrays.asList(this.answer));
    }

    public float calculatePoints(List<Answer> chosenAnswers) {
        float points = 0;

        if (chosenAnswers.size() > 0) {
            if(chosenAnswers.get(0).getText().equals(answer.getText())) {
                points = answer.getPoints();
            }
        }
        return points;
    }

}
