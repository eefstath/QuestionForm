package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class MultipleChoice extends Question{
    private ArrayList<Answer> answers;
    private static final String INSTRUCTIONS = "Chose the correct answer(s).";
    private static final Logger logger = LogManager.getLogger(MultipleChoice.class);

    public MultipleChoice(String text, ArrayList<Answer> answers,String documentation, int difficulty, Section section) {
        super(text, documentation, difficulty, section);
        this.answers = answers;
        calculatePointScale();
        logger.info("A multiple-choice question has been created");
    }

    public ArrayList<Answer> getPossibleAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }

    public String getInstructions() {
        return INSTRUCTIONS;
    }

    public void calculatePointScale() {
        for (Answer answer : this.answers) {
            super.pointScale += answer.getBool()?answer.getPoints():0;
        }
    }

    public ArrayList<Answer> getCorrectAnswers() {
        ArrayList<Answer> correctAnswers = new ArrayList<>();
        for (Answer answer : this.getPossibleAnswers()) {
            if (answer.getBool()) {
                correctAnswers.add(answer);
            }
        }
        return correctAnswers;
    }

    public float calculatePoints(ArrayList<Answer> chosenAnswers) {
        int points = 0;

        if (chosenAnswers.size() > 0) {
            for (Answer answer : chosenAnswers) {
                points += answer.getPoints();
            }
        }
        if (points == 0) {
            this.correctness = true;

        } else {
            this.correctness = false;
        }
        this.points = points;
        return points;
    }
}
