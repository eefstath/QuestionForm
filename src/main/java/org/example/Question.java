package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Question {
    private String text;
    private String documentation;
    private int difficulty;
    private int pointScale;
    private Section section;
//    private int correctSum;
//    private int falseSum;

    public Question(String text, String documentation, int difficulty, int pointScale, Section section) {
        this.text = text;
        this.documentation = documentation;
        this.difficulty = difficulty;
        this.pointScale = pointScale;
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

    public int getPointScale() {
        return pointScale;
    }

    public void setPointScale(int pointScale) {
        this.pointScale = pointScale;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public Section getSection() {
        return section;
    }
}
