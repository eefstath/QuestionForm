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

    //Function that begins and submits test
    private static void takeTest(Test test) {
        logger.info("Beginning of test : " + test.getName());

        //Start Timestamp created to save time the test begins
        date = new Date();
        startTimestamp = new Timestamp(date.getTime());

        showTest(test);
        List<AnsweredQuestion> answeredQuestionList = chooseAnswers(test);
        overviewTest(answeredQuestionList);
        Result result = submit(test, answeredQuestionList);
        viewTestResult(result);
    }

    //Function that previews all questions
    private static void showTest(Test test) {
        List<Question> questions = test.getQuestions();
        for (Question question : questions) {
            showQuestion(question);
        }
    }

    //Function that shows a question and all its possible answers
    private static void showQuestion(Question question) {
        List<Answer> answers = question.getPossibleAnswers();

        System.out.println("\n(" + question.getPointScale() + ") QUESTION: " +question.getText());
        System.out.println(question.getInstructions());
        int count = 1;
        for (Answer answer : answers) {
                System.out.println("\t" + count++ + ": " + answer.getText());
        }
    }

    //Function that shows answers chosen by the user in each question
    private static void showChosenAnswers(Question question, List<Answer> chosenAnswers) {
        //Index contains the place of the chosen answer in possible answer's list starting at 0
        //Count equals index + 1 or equals 1 when possible answer's list does not contain the user's answer
        int index;
        int count;
        if (chosenAnswers != null) {
            System.out.println("YOUR ANSWER(S): ");
            for (Answer answer : chosenAnswers) {
                index = question.getPossibleAnswers().indexOf(answer);
                count = index==-1?1:index+1;
                System.out.println("\t" + count + ": " + answer.getText());
            }
        }
    }

    //Function that shows all the correct answers and documentation of the question only when
    //a question is incorrectly answered.
    private static void showCorrectAnswers(AnsweredQuestion answeredQuestion) {
        int count;
        Question question = answeredQuestion.getQuestion();

        if (!answeredQuestion.getCorrectness()) {
            System.out.println("CORRECT ANSWER(S): ");
            for (Answer correctAnswer : question.getCorrectAnswers()) {
                count = question.getPossibleAnswers()==null?1:question.getPossibleAnswers().indexOf(correctAnswer)+1;
                System.out.println("\t" + count + ": " + correctAnswer.getText());
            }
            System.out.println("DOCUMENTATION: " + question.getDocumentation());
        }
    }

    //Function that automatically chooses answers for a user
    private static List<AnsweredQuestion> chooseAnswers(Test test) {
        logger.info("Creating user answers");
        List<AnsweredQuestion> answeredQuestionList = new ArrayList<>();

        //first question
        answeredQuestionList.add(new AnsweredQuestion(test.getQuestions().get(0),
                new ArrayList<>(
                        Arrays.asList(
                                test.getQuestions().get(0).getPossibleAnswers().get(0),
                                test.getQuestions().get(0).getPossibleAnswers().get(1),
                                test.getQuestions().get(0).getPossibleAnswers().get(3)))));

        //second question
        answeredQuestionList.add(new AnsweredQuestion(test.getQuestions().get(1),
                new ArrayList<>(
                        Arrays.asList(
                                new Answer("Information must not be accessed by unauthorized personnel.",true,0)))));

        //third question
        answeredQuestionList.add(new AnsweredQuestion(test.getQuestions().get(2),
                new ArrayList<>(
                        Arrays.asList(
                                new Answer("Information must be accurate, intact, honest and not faulty, corrupted or uninformed",true,0)))));

        //fourth question
        answeredQuestionList.add(new AnsweredQuestion(test.getQuestions().get(3),
                new ArrayList<>(
                        Arrays.asList(
                                test.getQuestions().get(3).getPossibleAnswers().get(2)))));

        return answeredQuestionList;
    }

    //Function that overviews the answer's a user has chosen before submitting the test.
    private static void overviewTest(List<AnsweredQuestion> answeredQuestionList) {
        logger.info("Previewing answers of questions");

        for(AnsweredQuestion answeredQuestion : answeredQuestionList) {
            List<Answer> chosenAnswers = answeredQuestion.getChosenAnswers();
            Question question = answeredQuestion.getQuestion();

            showQuestion(question);
            showChosenAnswers(question, chosenAnswers);
        }
    }

    //Function that submits the list of answers to the test taken in the function's parameters.
    private static Result submit(Test test, List<AnsweredQuestion> answeredQuestionList) {
        logger.info("Submitting result");

        //Test total score
        float score = 0;
        //End Timestamp created to save the time the test was submitted.
        date = new Date();
        endTimestamp = new Timestamp(date.getTime());

        for(AnsweredQuestion answeredQuestion : answeredQuestionList) {
            //Each question's points gathered
            int points = 0;
            List<Answer> answers = answeredQuestion.getChosenAnswers();
            Question question = answeredQuestion.getQuestion();

            if (answers != null) {
                points += question.calculatePoints(answers);

                //Set points and correctness of answered question
                answeredQuestion.setPoints(points);
                if(points == question.getPointScale() ) {
                    answeredQuestion.setCorrectness(true);
                }
            }
            score += points;
        }
        return new Result(startTimestamp, endTimestamp, test, answeredQuestionList, score);
    }

    //Function that calculates time taken to complete and submit a test
    private static String calculateTime(Timestamp startTimestamp, Timestamp endTimestamp) {
        long milliseconds = endTimestamp.getTime() - startTimestamp.getTime();
        int seconds = (int) milliseconds / 1000;
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        seconds = (seconds % 3600) % 60;
        return  hours + " hours, " + minutes + " minutes and " + seconds + " seconds";
    }

    //Function that shows a completed test with all chosen answers, each question points,
    //the test's score and the time taken to submit it.
    private static void viewTestResult(Result result) {
        logger.info("Viewing Result of test");

        Test test = result.getTest();
        List<AnsweredQuestion> answeredQuestionList = result.getAnsweredQuestionList();

        System.out.println("\nTest name: " + test.getName());

        for (AnsweredQuestion answeredQuestion : answeredQuestionList) {
            List<Answer> chosenAnswers = answeredQuestion.getChosenAnswers();
            Question question = answeredQuestion.getQuestion();

            showQuestion(question);
            showChosenAnswers(question, chosenAnswers);
            showCorrectAnswers(answeredQuestion);
            System.out.println("QUESTION SCORE: " + answeredQuestion.getPoints() + "/" + question.getPointScale());
        }

        System.out.println("\nTIME TAKEN: " + calculateTime(result.getStartTimestamp(),result.getEndTimestamp()));

        if (result.getGrade() >= test.getMinScore()) {
            System.out.println("SCORE: " + result.getGrade() + "/" + result.getTest().getTotalScore() + " -- PASSED ");
        } else {
            System.out.println("SCORE: " + result.getGrade() + "/" + result.getTest().getTotalScore() + " -- FAILED ");
        }
    }
}
