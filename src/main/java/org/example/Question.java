package org.example;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

//Class Question is father class to OpenEnded and MultipleChoice classes.
public abstract class Question {
    protected String text;
    protected String documentation;
    protected int difficulty;
    protected float pointScale;
    protected Section section;
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

    public abstract String getInstructions();

    public abstract List<Answer> getPossibleAnswers();

    public abstract void calculatePointScale();

    public abstract List<Answer> getCorrectAnswers();

    public abstract float calculatePoints(List<Answer> chosenAnswers);
}
