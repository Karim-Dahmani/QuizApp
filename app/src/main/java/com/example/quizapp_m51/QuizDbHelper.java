package com.example.quizapp_m51;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import  com.example.quizapp_m51.QuizContract.*;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class QuizDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="emsiquiz";
    private static final int DATABASE_VERSION=1;

    private SQLiteDatabase db;
    public QuizDbHelper( Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
            this.db =sqLiteDatabase;

            final String SQL_CREATE_QUESTIONS_TABLE =" CREATE TABLE " +
                    QuestionTable.TABLE_NAME +"("+
                    QuestionTable._ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    QuestionTable.COLUMN_QUESTION +"TEXT"+
                    QuestionTable.COLUMN_OPTION1 +"TEXT"+
                    QuestionTable.COLUMN_OPTION2 +"TEXT"+
                    QuestionTable.COLUMN_ANSWER_NR +"INTEGER"+")";

               db.execSQL(SQL_CREATE_QUESTIONS_TABLE);

        fillQuestionsTable(); // insert data into table

        }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

            db.execSQL(" DROP TABLE IF EXISTS " + QuestionTable.TABLE_NAME);
            onCreate(db);
    }
    private void  addQuestions(Question question){
        ContentValues cv = new ContentValues();
        cv.put(QuestionTable.COLUMN_QUESTION,question.getQuestion());
        cv.put(QuestionTable.COLUMN_OPTION1,question.getOp1());
        cv.put(QuestionTable.COLUMN_OPTION2,question.getOp2());
        cv.put(QuestionTable.COLUMN_ANSWER_NR,question.getAnswerN());

        db.insert(QuestionTable.TABLE_NAME , null,cv);
    }

    private  void fillQuestionsTable(){
        Question q1 = new Question("Le livre 1984 a été écrit par George Orwell","oui","non",1);
        addQuestions(q1);
        Question q2 = new Question("La statue de la Liberté a été offerte par la France aux États-Unis","oui","non",1);
        addQuestions(q1);
        Question q3 = new Question("L'opéra de Sydney est situé à Melbourne.","oui","non",2);
        addQuestions(q1);
        Question q4 = new Question("La Mona Lisa est une peinture de Vincent van Gogh.","oui","non",2);
        addQuestions(q1);
        Question q5 = new Question("Le Taj Mahal est un monument situé en Inde.","oui","non",1);
        addQuestions(q1);
        Question q6 = new Question("Le Mont Everest est la plus haute montagne du monde. ","oui","non",1);
        addQuestions(q1);
        Question q7 = new Question("Les Beatles étaient un groupe de musique populaire dans les années 1960.","oui","non",1);
        addQuestions(q1);
        Question q8 = new Question("La langue officielle de la Chine est le mandarin. ","oui","non",1);
        addQuestions(q1);
        Question q9 = new Question("Le Titanic a coulé en 1911.","oui","non",2);
        addQuestions(q1);
        Question q10 = new Question("La Tour Eiffel est le monument le plus visité au monde.","oui","non",2);
        addQuestions(q1);
    }
    @SuppressLint("Range")
    public ArrayList<Question> getAllQuestions(){
        ArrayList<Question>questionsList = new ArrayList<>() ;
        db=getReadableDatabase();
        String Projection[]={
                QuestionTable._ID,
                QuestionTable.COLUMN_QUESTION,
                QuestionTable.COLUMN_OPTION1,
                QuestionTable.COLUMN_OPTION2,
                QuestionTable.COLUMN_ANSWER_NR
        };
        Cursor c = db.query(QuestionTable.TABLE_NAME,
                Projection,
                null,
                null,
                null,
                null,
                null);
        if(c.moveToFirst()){
            do{
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_ANSWER_NR)));

                questionsList.add(question);
            }while (c.moveToNext());
        }
        c.close();
        return (ArrayList<Question>) questionsList;
    }
}
