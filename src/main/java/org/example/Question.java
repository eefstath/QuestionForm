package org.example;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public abstract class Question {
    protected String text;
    protected String documentation;
    protected int difficulty;
    protected float pointScale;
    protected Section section;
    protected boolean correctness;
    protected float points;
//    private int correctSum;
//    private int falseSum;

    public Question(String text, String documentation, int difficulty, Section section) {
        this.text = text;
        this.documentation = documentation;
        this.difficulty = difficulty;
        this.section = section;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDocumentation() {
        return documentation;
    }

    public void setDocumentation(String documentation) {
        this.documentation = documentation;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public float getPointScale() {
        return pointScale;
    }

    public void setPointScale(float pointScale) {
        this.pointScale = pointScale;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public Section getSection() {
        return section;
    }

    //Sets where the question was answered correctly or not
    public void setCorrectness(boolean correctness) {
        this.correctness = correctness;
    }

    //Gets where the question was answered correctly or not
    public boolean getCorrectness() {
        return correctness;
    }

    public void setPoints(float points) {
        this.points = points;
    }

    public float getPoints() {
        return points;
    }

    public abstract String getInstructions();

    public abstract ArrayList<Answer> getPossibleAnswers();

    public abstract void calculatePointScale();

    public abstract ArrayList<Answer> getCorrectAnswers();

    public abstract float calculatePoints(ArrayList<Answer> chosenAnswers);
}
