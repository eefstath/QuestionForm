package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class MultipleChoice extends Question{
    private List<Answer> answers;
    private static final String INSTRUCTIONS = "Chose the correct answer(s).";
    private static final Logger logger = LogManager.getLogger(MultipleChoice.class);

    public MultipleChoice(String text, ArrayList<Answer> answers,String documentation, Section section) {
        super(text, documentation, section);
        this.answers = answers;
        logger.info("A multiple-choice question has been created");
    }

    public List<Answer> getPossibleAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public String getInstructions() {
        return INSTRUCTIONS;
    }

    public List<Answer> getCorrectAnswers() {
        List<Answer> correctAnswers = new ArrayList<>();
        for (Answer answer : this.getPossibleAnswers()) {
            if (answer.getCorrectness()) {
                correctAnswers.add(answer);
            }
        }
        return correctAnswers;
    }

    //Function that creates a valued multiple choice question by firstly creating a valued answer for each one of its
    //possible ones and then setting difficulty and the list of valued answers in it.
    public ValuedQuestion createValuedQuestion(float difficulty, List<Float> values) {
        List<ValuedAnswer> valuedAnswers = new ArrayList<>();
        for(int i=0; i<answers.size(); i++) {
            valuedAnswers.add(answers.get(i).createValuedAnswer(values.get(i)));
        }
        return new ValuedMultipleChoice(this, valuedAnswers, difficulty);
    }

}
