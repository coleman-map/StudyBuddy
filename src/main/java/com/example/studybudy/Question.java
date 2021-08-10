package com.example.studybudy;

import java.util.ArrayList;

public class Question {

    private String question;
    private ArrayList<String> answerChoices;
    private String answer;

    public Question() { } //flashcards object call

    public Question(String question, ArrayList<String> answerChoices, String answer) {
        this.question = question;
        this.answerChoices = answerChoices;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public ArrayList<String> getAnswerChoices() {
        return answerChoices;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswerChoices(ArrayList<String> answerChoices) {
        this.answerChoices = answerChoices;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }


}