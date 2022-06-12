package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Array;
import java.sql.Timestamp;
import java.util.*;

public class Main
{
    private static final Logger logger = LogManager.getLogger(Main.class);
    private static Date date;
    private static Result result;
    private static Section section;
    private static Test firstTest;

    public static void main( String[] args )
    {

        //Create a section
        section = new Section("Security");

        //Create questions
        List<Question> questions = createQuestions();

        //Create a test
        createTest(questions);

        //Begin test
        takeTest(firstTest);
    }

    private static void createTest(List<Question> questions) {
        //Create valued questions by choosing each question's answer point scale and difficulty
        //first question
        ValuedQuestion firstValuedQuestion = questions.get(0).createValuedQuestion(3,new ArrayList<Float>(Arrays.asList(10f, 10f, 10f, -5f)));
        //second question
        ValuedQuestion secondValuedQuestion = questions.get(1).createValuedQuestion(5,new ArrayList<Float>(Arrays.asList(25f)));
        //third question
        ValuedQuestion thirdValuedQuestion = questions.get(2).createValuedQuestion(5,new ArrayList<Float>(Arrays.asList(25f)));
        //fourth question
        ValuedQuestion fourthValuedQuestion = questions.get(3).createValuedQuestion(7,new ArrayList<Float>(Arrays.asList(20f, -5f, -5f, -5f)));

        //add them to the test
        firstTest = new Test(
                "My First Test",
                new ArrayList<>(Arrays.asList(firstValuedQuestion, secondValuedQuestion, thirdValuedQuestion, fourthValuedQuestion)),
                60
        );
    }

    private static List<Question> createQuestions() {
        MultipleChoice firstQuestion = new MultipleChoice(
                "Which of the following are goals desired to be achieved by Information/Data security methods and technics?",
                new ArrayList<>(Arrays.asList(
                        new Answer("Confidentiality",true),
                        new Answer("Integrity",true),
                        new Answer("Availability",true),
                        new Answer("Corruption",false)
                )),
                "The desired goals are Confidentiality, Integrity and Availability.",
                section
        );
        OpenEnded secondQuestion = new OpenEnded(
                "Describe the Information/Data security term 'Confidentiality'.",
                new Answer("Information must not be accessed by unauthorized personnel.",true),
                "...",
                section
        );
        OpenEnded thirdQuestion = new OpenEnded(
                "Describe the Information/Data security term 'Integrity'.",
                new Answer("Information must be accurate, intact, honest and not faulty, corrupted or uninformed",true),
                "...",
                section
        );
        MultipleChoice fourthQuestion = new MultipleChoice(
                "Which of the following is the correct meaning of the term 'Availability'?",
                new ArrayList<>(Arrays.asList(
                        new Answer("Information must always be at user disposal.",true),
                        new Answer("Information can be accessed only by the service provider.",false),
                        new Answer("Information can be accessed by users only when the service provider allows it.",false),
                        new Answer("Information can be accessed by users at certain time periods of the day.",false)
                )),
                "Information must always be available by users.",
                section
        );

        return new ArrayList<>(Arrays.asList(firstQuestion, secondQuestion, thirdQuestion, fourthQuestion));
    }

    //Function that begins and submits test
    private static void takeTest(Test test) {
        logger.info("Beginning of test : " + test.getName());

        //Start Timestamp created to save time the test begins
        date = new Date();
        //Create this test's result instance
        result = new Result(new Timestamp(date.getTime()),test);
        showTest(test);
        chooseAnswers(test);
        overviewTest();
        submit();
        viewTestResult();
    }

    //Function that previews all questions
    private static void showTest(Test test) {
        List<ValuedQuestion> valuedQuestions = test.getValuedQuestions();

        for (ValuedQuestion valuedQuestion : valuedQuestions) {
            showQuestion(valuedQuestion);
        }
    }

    //Function that shows a question and all its possible answers
    private static void showQuestion(ValuedQuestion valuedQuestion) {
        Question question = valuedQuestion.getQuestion();
        List<Answer> answers = question.getPossibleAnswers();

        System.out.println("\n(" + valuedQuestion.getPointScale() + ") QUESTION: " + question.getText());
        System.out.println(question.getInstructions());
        int count = 1;
        for (Answer answer : answers) {
                System.out.println("\t" + count++ + ": " + answer.getText());
        }
    }

    //Function that shows answers chosen by the user in each question
    private static void showChosenAnswers(ValuedQuestion valuedQuestion, List<ValuedAnswer> valuedChosenAnswers) {
        //Index contains the place of the chosen answer in possible answer's list starting at 0
        //Count equals index + 1 or equals 1 when possible answer's list does not contain the user's answer
        int index;
        int count;
        Question question = valuedQuestion.getQuestion();
        if (valuedChosenAnswers != null) {
            System.out.println("YOUR ANSWER(S): ");
            for (ValuedAnswer valuedAnswer : valuedChosenAnswers) {
                Answer answer = valuedAnswer.getAnswer();
                index = question.getPossibleAnswers().indexOf(answer);
                count = index==-1?1:index+1;
                System.out.println("\t" + count + ": " + answer.getText());
            }
        }
    }

    //Function that shows all the correct answers and documentation of the question only when
    //a question is incorrectly answered.
    private static void showCorrectAnswers(AnsweredQuestion answeredQuestion) {
        int index;
        int count;
        ValuedQuestion valuedQuestion = answeredQuestion.getValuedQuestion();
        Question question = valuedQuestion.getQuestion();
        if (!answeredQuestion.getCorrectness()) {
            System.out.println("CORRECT ANSWER(S): ");
            for (Answer correctAnswer : question.getCorrectAnswers()) {
                index = question.getPossibleAnswers().indexOf(correctAnswer);
                count = index==-1?1:index+1;
                System.out.println("\t" + count + ": " + correctAnswer.getText());
            }
            System.out.println("DOCUMENTATION: " + question.getDocumentation());
        }
    }

    //Function that automatically chooses answers for a user
    private static void chooseAnswers(Test test) {
        logger.info("Creating user answers");
        List<ValuedQuestion> valuedQuestions = test.getValuedQuestions();

        //For each question, create an answered question instance with its answers
        //Add each instance at this test's result instance
        //first question
        ValuedQuestion firstValuedQuestion = valuedQuestions.get(0);
        result.getAnsweredQuestionList().add(firstValuedQuestion.createAnsweredQuestion(
                new ArrayList<>(
                        Arrays.asList(
                                firstValuedQuestion.getPossibleValuedAnswers().get(0),
                                firstValuedQuestion.getPossibleValuedAnswers().get(1),
                                firstValuedQuestion.getPossibleValuedAnswers().get(3)))));

        //second question
        ValuedQuestion secondValuedQuestion = valuedQuestions.get(1);
        result.getAnsweredQuestionList().add(secondValuedQuestion.createAnsweredQuestion(
                new ArrayList<>(
                        Arrays.asList(
                                new ValuedAnswer(new Answer("Information must not be accessed by unauthorized personnel.",false),0)))));

        //third question
        ValuedQuestion thirdValueQuestion = valuedQuestions.get(2);
        result.getAnsweredQuestionList().add(thirdValueQuestion.createAnsweredQuestion(
                new ArrayList<>(
                        Arrays.asList(
                                new ValuedAnswer(new Answer("Information must be accurate, intact, honest and not faulty, corrupted or uninformed",false),0)))));

        //fourth question
        ValuedQuestion fourthValuedQuestion = valuedQuestions.get(3);
        result.getAnsweredQuestionList().add(fourthValuedQuestion.createAnsweredQuestion(
                new ArrayList<>(
                        Arrays.asList(
                                fourthValuedQuestion.getPossibleValuedAnswers().get(2)))));
    }

    //Function that overviews the answer's a user has chosen before submitting the test.
    private static void overviewTest() {
        logger.info("Previewing answers of questions");
        List<AnsweredQuestion> answeredQuestionList = result.getAnsweredQuestionList();

        for(AnsweredQuestion answeredQuestion : answeredQuestionList) {
            List<ValuedAnswer> chosenAnswers = answeredQuestion.getValuedChosenAnswers();
            ValuedQuestion question = answeredQuestion.getValuedQuestion();

            showQuestion(question);
            showChosenAnswers(question, chosenAnswers);
        }
    }

    //Function that submits the list of answers to the test taken in the function's parameters.
    private static void submit() {
        logger.info("Submitting result");
        List<AnsweredQuestion> answeredQuestionList = result.getAnsweredQuestionList();

        //End Timestamp created to save the time the test was submitted.
        date = new Date();
        result.setEndTimestamp(new Timestamp(date.getTime()));
        //Calculate each answered question instance's points
        for(AnsweredQuestion answeredQuestion : answeredQuestionList) {
            answeredQuestion.calculatePoints();
        }
        //Calculate rest result's points
        result.calculateGrade();
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
    private static void viewTestResult() {
        logger.info("Viewing Result of test");
        Test test = result.getTest();
        List<AnsweredQuestion> answeredQuestionList = result.getAnsweredQuestionList();

        System.out.println("\nTest name: " + test.getName());

        for (AnsweredQuestion answeredQuestion : answeredQuestionList) {
            List<ValuedAnswer> chosenValuedAnswers = answeredQuestion.getValuedChosenAnswers();
            ValuedQuestion valuedQuestion = answeredQuestion.getValuedQuestion();

            showQuestion(valuedQuestion);
            showChosenAnswers(valuedQuestion, chosenValuedAnswers);
            showCorrectAnswers(answeredQuestion);
            System.out.println("QUESTION SCORE: " + answeredQuestion.getPoints() + "/" + valuedQuestion.getPointScale());
        }

        System.out.println("\nTIME TAKEN: " + calculateTime(result.getStartTimestamp(),result.getEndTimestamp()));

        if (result.getGrade() >= test.getMinScore()) {
            System.out.println("SCORE: " + result.getGrade() + "/" + result.getTest().getTotalScore() + " -- PASSED ");
        } else {
            System.out.println("SCORE: " + result.getGrade() + "/" + result.getTest().getTotalScore() + " -- FAILED ");
        }
    }
}
