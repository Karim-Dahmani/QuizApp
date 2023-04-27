package com.example.quizapp_m51;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class Quiz1 extends AppCompatActivity {


    RadioGroup rg;
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
        bNext=(Button) findViewById(R.id.bNext);

        fetchDB();

    }
    private void fetchDB(){
        QuizDbHelper quizDbHelper = new QuizDbHelper(this);
        questionArrayList = quizDbHelper.getAllQuestions();
    }

}