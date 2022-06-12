package org.example;

import java.util.List;

public class ValuedMultipleChoice extends ValuedQuestion{

    private List<ValuedAnswer> valuedAnswers;

    public ValuedMultipleChoice(Question question, List<ValuedAnswer> valuedAnswers, float difficulty) {
        super(question, difficulty);
        this.valuedAnswers = valuedAnswers;
        calculatePointScale();
    }

    //Function calculates the point scale of a Multiple Choice question by
    //adding the points of each correct answer.
    //Correctness of each answer is stored in Answer Class and Points in ValuedAnswer class
    public void calculatePointScale() {
        for (ValuedAnswer valuedAnswer : valuedAnswers) {
            Answer answer = valuedAnswer.getAnswer();
            super.pointScale += answer.getCorrectness()?valuedAnswer.getPoints():0;
        }
    }

    public List<ValuedAnswer> getValuedAnswers() {
        return valuedAnswers;
    }

    public void setValuedAnswers(List<ValuedAnswer> valuedAnswers) {
        this.valuedAnswers = valuedAnswers;
    }

    public List<ValuedAnswer> getPossibleValuedAnswers() {
        return valuedAnswers;
    }


    public AnsweredQuestion createAnsweredQuestion(List<ValuedAnswer> valuedChosenAnswers){
        return new AnsweredMultipleChoice(this,valuedChosenAnswers);
    }
}
