package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.Arrays;

public class Main
{
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main( String[] args )
    {
        //Create a section
        Section section = new Section("Security");

        //Create questions
        OpenEnded firstQuestion = new OpenEnded(
                "Which three (3) goals are desired to be achieved by Information/Data security methods and technics?",
                new Answer("Confidentiality, Integrity, Availability",true, 25),
                "",
                3,
                25,
                section
        );
        OpenEnded secondQuestion = new OpenEnded(
                "Describe the Information/Data security term 'Confidentiality'.",
                new Answer("Information must not be accessed by unauthorized personnel.",true,25),
                "",
                3,
                25,
                section
        );
        OpenEnded thirdQuestion = new OpenEnded(
                "Describe the Information/Data security term 'Integrity'.",
                new Answer("Information must be accurate, intact honest and not faulty, corrupted or uninformed",true,25),
                "",
                3,
                25,
                section
        );
        MultipleChoice fourthQuestion = new MultipleChoice(
                "Which of the following is the correct meaning of the term 'Availability'?",
                new ArrayList<>(Arrays.asList(
                        new Answer("Information must always be at user disposal.",true,25),
                        new Answer("Information can be accessed only by the service provider.",false, -5),
                        new Answer("Information can be accessed by users only when the service provider allows it.",false,-5),
                        new Answer("Information can be accessed by users at certain time periods of the day.",false, 10)
                )),
                "",
                6,
                25,
                section
        );

        //Create a test
        Test firstTest = new Test(
                "My First Test",
                new ArrayList<>(Arrays.asList(firstQuestion, secondQuestion, thirdQuestion, fourthQuestion)),
                65
        );
        takeTest(firstTest);
    }

    private static void takeTest(Test test) {
        logger.info("Beginning of test : " + test.getName());
        showTest(test);
    }

    private static void showTest(Test test) {
        ArrayList<Question> questions = test.getQuestions();
        for (Question question : questions) {
            showQuestion(question);
            showAnswers(question);
        }
    }

    private static void showQuestion(Question question) {
        System.out.println("\n(" + question.getPointScale() + "/100) QUESTION: " +question.getText());
        System.out.println(question.getInstructions());

    }

    private static void showAnswers(Question question) {
        ArrayList<Answer> answers = question.getAnswers();
        if(answers != null){
            int count = 1;
            for (Answer answer : answers) {
                System.out.println(count++ + ": " + answer.getText());
            }
        }else{
            System.out.println("----------------------------");
        }
    }
}
