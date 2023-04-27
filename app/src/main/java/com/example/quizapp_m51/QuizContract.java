package com.example.quizapp_m51;

import android.provider.BaseColumns;

public class QuizContract {
    public QuizContract() {
    }

    public static class QuestionTable implements BaseColumns{

        public static final String TABLE_NAME ="QuizQuestion";
        public static final String COLUMN_QUESTION ="question";
        public static final String COLUMN_OPTION1 ="option1";
        public static final String COLUMN_OPTION2 ="option2";
        public static final String COLUMN_ANSWER_NR ="answer";
    }
}
