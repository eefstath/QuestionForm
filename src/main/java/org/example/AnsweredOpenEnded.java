package org.example;

import java.util.List;

public class AnsweredOpenEnded extends AnsweredQuestion{

    protected ValuedAnswer valuedAnswer;
    public AnsweredOpenEnded(ValuedQuestion valuedQuestion, List<ValuedAnswer> valuedChosenAnswers) {
        super(valuedQuestion, valuedChosenAnswers);
    }

    //Function calculates Open Ended question's points by adding the correct answer's points
    public void calculatePoints() {
        List<ValuedAnswer> valuedChosenAnswers = super.valuedChosenAnswers;
        ValuedAnswer valuedAnswer;
        Answer correctAnswer;
        ValuedQuestion valuedQuestion = super.valuedQuestion;
        Question question = valuedQuestion.getQuestion();
        if (valuedChosenAnswers.size() > 0) {

            valuedAnswer = valuedChosenAnswers.get(0);
            correctAnswer = question.getCorrectAnswers().get(0);
            if(correctAnswer.getText() == valuedAnswer.getAnswer().getText()){
                points = valuedQuestion.getPointScale();
            }
        }
        if(points == valuedQuestion.pointScale) {
            correctness = true;
        }
    }
}
