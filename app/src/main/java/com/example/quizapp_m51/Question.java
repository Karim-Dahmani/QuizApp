package com.example.quizapp_m51;

public class Question {
    private String question;
    private String op1;
    private String op2;
    private int answerN;

    public Question(){}

    public Question(String question, String op1, String op2, int answerN) {
        this.question = question;
        this.op1 = op1;
        this.op2 = op2;
        this.answerN = answerN;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOp1() {
        return op1;
    }

    public void setOp1(String op1) {
        this.op1 = op1;
    }

    public String getOp2() {
        return op2;
    }

    public void setOp2(String op2) {
        this.op2 = op2;
    }

    public int getAnswerN() {
        return answerN;
    }

    public void setAnswerN(int answerN) {
        this.answerN = answerN;
    }
}
