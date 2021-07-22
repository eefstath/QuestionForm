package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Timestamp;
import java.util.*;

public class Main
{
    private static final Logger logger = LogManager.getLogger(Main.class);
    private static Timestamp startTimestamp;
    private static Timestamp endTimestamp;
    private static Date date;

    public static void main( String[] args )
    {
        //Create a section
        Section section = new Section("Security");

        //Create questions
        MultipleChoice firstQuestion = new MultipleChoice(
                "Which of the following are goals desired to be achieved by Information/Data security methods and technics?",
                new ArrayList<>(Arrays.asList(
                        new Answer("Confidentiality",true,10),
                        new Answer("Integrity",true,10),
                        new Answer("Availability",true,10),
                        new Answer("Corruption",false,-5)
                )),
                "The desired goals are Confidentiality, Integrity and Availability.",
                3,
                section
        );
        OpenEnded secondQuestion = new OpenEnded(
                "Describe the Information/Data security term 'Confidentiality'.",
                new Answer("Information must not be accessed by unauthorized personnel.",true,25),
                "...",
                5,
                section
        );
        OpenEnded thirdQuestion = new OpenEnded(
                "Describe the Information/Data security term 'Integrity'.",
                new Answer("Information must be accurate, intact, honest and not faulty, corrupted or uninformed",true,30),
                "...",
                5,
                section
        );
        MultipleChoice fourthQuestion = new MultipleChoice(
                "Which of the following is the correct meaning of the term 'Availability'?",
                new ArrayList<>(Arrays.asList(
                        new Answer("Information must always be at user disposal.",true,15),
                        new Answer("Information can be accessed only by the service provider.",false, -5),
                        new Answer("Information can be accessed by users only when the service provider allows it.",false,-5),
                        new Answer("Information can be accessed by users at certain time periods of the day.",false, 10)
                )),
                "Information must always be available by users.",
                3,
                section
        );

        //Create a test
        Test firstTest = new Test(
                "My First Test",
                new ArrayList<>(Arrays.asList(firstQuestion, secondQuestion, thirdQuestion, fourthQuestion)),
                60
        );

        //Begin test
        takeTest(firstTest);
    }

    private static void takeTest(Test test) {
        logger.info("Beginning of test : " + test.getName());

        date = new Date();
        startTimestamp = new Timestamp(date.getTime());
        ArrayList<ArrayList<Answer>> chosenAnswersList = new ArrayList<>();

//        showTest(test);
        chosenAnswersList = chooseAnswers(test);
//        overviewTest(test, chosenAnswersList);
        Result result = submit(test, chosenAnswersList);
        viewTestResult(result);
    }

    private static void showTest(Test test) {
        ArrayList<Question> questions = test.getQuestions();
        for (Question question : questions) {
            showQuestion(question);
        }
    }

    private static void showQuestion(Question question) {
        ArrayList<Answer> answers = question.getPossibleAnswers();

        System.out.println("\n(" + question.getPointScale() + ") QUESTION: " +question.getText());
        System.out.println(question.getInstructions());
        if (answers != null) {
            int count = 1;
            for (Answer answer : answers) {
                    System.out.println("\t" + count++ + ": " + answer.getText());
            }
        } else {
            System.out.println("----------------------------");
        }
    }

    private static void showChosenAnswers(Question question, ArrayList<Answer> chosenAnswers) {
        int count;
        if (chosenAnswers != null) {
            System.out.println("YOUR ANSWER(S): ");
            for (Answer answer : chosenAnswers) {
                count = question.getPossibleAnswers()==null?1:question.getPossibleAnswers().indexOf(answer)+1;
                System.out.println("\t" + count + ": " + answer.getText());
            }
        }
    }

    private static void showCorrectAnswers(Question question) {
        int count;
        if (!question.getCorrectness()) {
            System.out.println("CORRECT ANSWER(S): ");
            for (Answer correctAnswer : question.getCorrectAnswers()) {
                count = question.getPossibleAnswers()==null?1:question.getPossibleAnswers().indexOf(correctAnswer)+1;
                System.out.println("\t" + count + ": " + correctAnswer.getText());
            }
            System.out.println("DOCUMENTATION: " + question.getDocumentation());
        }
    }

    private static ArrayList<ArrayList<Answer>> chooseAnswers(Test test) {
        logger.info("Creating user answers");
        ArrayList<ArrayList<Answer>> chosenAnswersList = new ArrayList<>();

        //first question
        chosenAnswersList.add(new ArrayList<Answer>(Arrays.asList(
                                test.getQuestions().get(0).getPossibleAnswers().get(0),
                                test.getQuestions().get(0).getPossibleAnswers().get(1),
                                test.getQuestions().get(0).getPossibleAnswers().get(3))));

        //second question
        chosenAnswersList.add(new ArrayList<Answer>(
                Arrays.asList(new Answer("Information must not be accessed by unauthorized personnel.",true,0))
        ));

        //third question
        chosenAnswersList.add(new ArrayList<Answer>(
                Arrays.asList(new Answer("Information must be accurate, intact, honest and not faulty, corrupted or uninformed",true,0))
        ));

        //fourth question
        chosenAnswersList.add(new ArrayList<Answer>(
                Arrays.asList(test.getQuestions().get(3).getPossibleAnswers().get(2))
        ));

        return chosenAnswersList;
    }

    private static void overviewTest(Test test, ArrayList<ArrayList<Answer>> chosenAnswersList) {
        logger.info("Previewing answers of questions");

        ArrayList<Question> questions = test.getQuestions();

        for(int x=0; x<questions.size(); x++) {
            ArrayList<Answer> chosenAnswers = chosenAnswersList.get(x);
            Question question = questions.get(x);

            showQuestion(test.getQuestions().get(x));
            showChosenAnswers(question, chosenAnswers);
        }
    }

    private static Result submit(Test test, ArrayList<ArrayList<Answer>> chosenAnswers) {
        logger.info("Submitting result");

        float score = 0;
        date = new Date();
        endTimestamp = new Timestamp(date.getTime());
        ArrayList<Question> questions = test.getQuestions();

        for(int x=0; x<questions.size(); x++) {
            int points = 0;
            ArrayList<Answer> answers = chosenAnswers.get(x);

            if (answers != null) {
                points += questions.get(x).calculatePoints(answers);
            }
            score += points;
        }
        Result result = new Result(startTimestamp, endTimestamp, test, chosenAnswers, score);
        return result;
    }

    private static String calculateTime(Timestamp startTimestamp, Timestamp endTimestamp) {
        long milliseconds = endTimestamp.getTime() - startTimestamp.getTime();
        int seconds = (int) milliseconds / 1000;
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        seconds = (seconds % 3600) % 60;
        String timeTaken = hours + " hours, " + minutes + " minutes and " + seconds + " seconds";
        return timeTaken;
    }

    private static void viewTestResult(Result result) {
        logger.info("Viewing Result of test");

        int count;
        Test test = result.getTest();
        ArrayList<Question> questions = test.getQuestions();
        ArrayList<ArrayList<Answer>> chosenAnswersList = result.getAnswers();

        System.out.println("\nTest name: " + test.getName());
        for (int x=0; x<questions.size(); x++) {
            ArrayList<Answer> chosenAnswers = chosenAnswersList.get(x);
            Question question = questions.get(x);

            showQuestion(question);
            showChosenAnswers(question, chosenAnswers);
            showCorrectAnswers(question);
            System.out.println("QUESTION SCORE: " + question.getPoints() + "/" + question.getPointScale());
        }

        System.out.println("\nTIME TAKEN: " + calculateTime(result.getStartTimestamp(),result.getEndTimestamp()));

        if (result.getGrade() >= ((test.getMinPercentage() * test.getTotalScore())/100)) {
            System.out.println("SCORE: " + result.getGrade() + "/" + result.getTest().getTotalScore() + " -- PASSED ");
        } else {
            System.out.println("SCORE: " + result.getGrade() + "/" + result.getTest().getTotalScore() + " -- FAILED ");
        }
    }
}
