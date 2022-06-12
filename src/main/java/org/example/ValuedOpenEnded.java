package org.example;

import java.util.ArrayList;
import java.util.List;

public class ValuedOpenEnded extends ValuedQuestion{

    private ValuedAnswer valuedAnswer;

    public ValuedOpenEnded(Question question, ValuedAnswer valuedAnswer, float difficulty) {
        super(question, difficulty);
        this.valuedAnswer = valuedAnswer;
        calculatePointScale();
    }

    //Function calculates the point scale of an Open Ended question by
    //getting the points of its one and only answer.
    public void calculatePointScale() {
        super.pointScale = valuedAnswer.getPoints();
    }

    public ValuedAnswer getValuedAnswer() {
        return valuedAnswer;
    }

    public void setValuedAnswer(ValuedAnswer valuedAnswer) {
        this.valuedAnswer = valuedAnswer;
    }

    public List<ValuedAnswer> getPossibleValuedAnswers() {
        return new ArrayList<>();
    }

    public AnsweredQuestion createAnsweredQuestion(List<ValuedAnswer> valuedChosenAnswers) {
        return new AnsweredOpenEnded(this,valuedChosenAnswers);
    }
}
