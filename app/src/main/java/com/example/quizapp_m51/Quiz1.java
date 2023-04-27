package com.example.quizapp_m51;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class Quiz1 extends AppCompatActivity {


    RadioGroup rg;
    TextView qst;
    RadioButton rb1;
    RadioButton rb2;
    Button bNext;
    int score=0;
    String RepCorrect="Non";

    private ArrayList<Question> questionArrayList;
    private int questionCounter;
    private int questionTotalCount;
    private Question currentQuestions;
    private boolean answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz1);
        rg=(RadioGroup) findViewById(R.id.rg);
        rb1=(RadioButton) findViewById(R.id.rb1);
        rb2=(RadioButton) findViewById(R.id.rb2);
        qst=(TextView) findViewById(R.id.question);
        bNext=(Button) findViewById(R.id.bNext);

        fetchDB();

    }
    private void fetchDB(){
        QuizDbHelper quizDbHelper = new QuizDbHelper(this);
        questionArrayList = quizDbHelper.getAllQuestions();

        startQuiz();
    }

    private void startQuiz() {
        questionTotalCount = questionArrayList.size();
        Collections.shuffle(questionArrayList);

        showQuestions();

        bNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!answer){
                    if(rb1.isChecked() || rb2.isChecked()){
                        quizOperations();
                    }else {
                        Toast.makeText(Quiz1.this,"Please select Option",Toast.LENGTH_SHORT);
                    }
                }
            }
        });



    }

    private void quizOperations() {
        answer = true;
        RadioButton rbselected =findViewById(rg.getCheckedRadioButtonId());
        int answerNr = rg.indexOfChild(rbselected)+1;
        checkSolution(answerNr ,rbselected);
    }

    private void checkSolution(int answerNr, RadioButton rbselected) {
        switch (currentQuestions.getAnswerN()){
            case 1: if(currentQuestions.getAnswerN()==answerNr){
                    rb1.setBackground(ContextCompat.getDrawable(this,R.drawable.when_answer_correct));
                    showQuestions();

            }else {
                changetoIncorrectColor(rbselected);
                showQuestions();
            }
            break;
            case 2 :
                if(currentQuestions.getAnswerN()==answerNr){
                    rb2.setBackground(ContextCompat.getDrawable(this,R.drawable.when_answer_correct));
                    showQuestions();

                }else {
                    changetoIncorrectColor(rbselected);
                    showQuestions();
                }
                break;
        }
        if(questionCounter < questionTotalCount){
            bNext.setText("Confirm and Finish");
        }
    }

    private void changetoIncorrectColor(RadioButton rbselected) {
        rb1.setBackground(ContextCompat.getDrawable(this,R.drawable.when_answer_wrong));

    }

    private void showQuestions() {
        rg.clearCheck();

        if(questionCounter<questionTotalCount){

            currentQuestions=questionArrayList.get(questionCounter);
            qst.setText(currentQuestions.getQuestion());
            rb1.setText(currentQuestions.getOp1());
            rb2.setText(currentQuestions.getOp2());

            questionCounter++;
            answer = false;

        }
    }

}