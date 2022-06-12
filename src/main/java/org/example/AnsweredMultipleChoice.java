package org.example;

import java.util.List;

public class AnsweredMultipleChoice extends AnsweredQuestion{

    public AnsweredMultipleChoice(ValuedQuestion valuedQuestion, List<ValuedAnswer> valuedChosenAnswers) {
        super(valuedQuestion, valuedChosenAnswers);
    }

    //Function calculates Multiple choice question's points by adding each valued answer's points
    public void calculatePoints() {
        List<ValuedAnswer> valuedChosenAnswers = super.valuedChosenAnswers;
        ValuedQuestion valuedQuestion = super.valuedQuestion;

        if (valuedChosenAnswers.size() > 0) {
            for (ValuedAnswer valuedAnswer : valuedChosenAnswers) {
                points += valuedAnswer.getPoints();
            }
        }
        if(points == valuedQuestion.pointScale) {
            correctness = true;
        }
    }
}
