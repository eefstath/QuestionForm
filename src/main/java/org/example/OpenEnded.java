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

    public OpenEnded(String text, Answer answer, String documentation, Section section) {
        super(text, documentation, section);
        this.answer = answer;
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

    public List<Answer> getCorrectAnswers() {
        return new ArrayList<>(Arrays.asList(this.answer));
    }

    //Function that creates a valued open ended question by firstly creating the valued answer and then setting
    //difficulty and the answer in it.
    public ValuedQuestion createValuedQuestion(float difficulty, List<Float> values) {
        return new ValuedOpenEnded(this, answer.createValuedAnswer(values.get(0)), difficulty);
    }

}
