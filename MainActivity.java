package com.myfirst.flashcardapp;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemClickListener,
        OnClickListener {

    public SQLiteDatabase db;
    int doubled;
    int rndmaswrs;
    int rtAnswr, rtAnswerS, rtAnswerM, rtAnswerD = 0;
    int a1;
    int a2;
    int a3;
    int totalScore = 0;
    int score = 0;
    Button button;
    Button button2;
    Button button3;
    TextView rN1, rN2, attmp, totattmp, highscore, sign;
    private DatabaseHelper myDb;
    String name;
    private ListView listv;
    private int listposition;
    private SharedPreferences sp;
    private boolean checkedBox, checkedBox1, checkedBox2;
    private DateFormat time;
    private EditText nameBox;
    private Editor editor;
    int dbl = 1;
    boolean isdoubled = false;


    // private ListView obj;
    static final String NAME = "name";
    static final String SCORE = "score";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this, "db", null, 1);
        sp = getSharedPreferences("checked", 0);

        doubled = sp.getInt("double up", 0);
        checkedBox = sp.getBoolean("check", false);

        dbl = getIntent().getIntExtra("obdbl", 0);


        rN1 = (TextView) findViewById(R.id.textView5);
        rN2 = (TextView) findViewById(R.id.textView7);
        totattmp = (TextView) findViewById(R.id.textView8);
        highscore = (TextView) findViewById(R.id.textView3);
        sign = (TextView) findViewById(R.id.textView6);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);

        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(this);

        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(this);

        totattmp.setText(" " + totalScore);
        highscore.setText(" " + score);

        listposition = getIntent().getIntExtra("mathdecision", 0);
        dbl = getIntent().getIntExtra("opdbl",2);



        setValues(listposition);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {

    }

    public void setValues(int operation) {
        button.setBackgroundColor(Color.GRAY);
        button2.setBackgroundColor(Color.GRAY);
        button3.setBackgroundColor(Color.GRAY);
        int operand1 = (int) (Math.random() * 10);
        int operand2 = (int) (Math.random() * 10);

        if (dbl == 2) {

            operand1 *= dbl;
            operand2 *= dbl;
        }


        switch (listposition) {
            case 0:
                rtAnswr = operand1 + operand2;
                sign.setText("+");
                break;

            case 1:
                rtAnswr = operand1 - operand2;
                sign.setText("-");
                break;


            case 2:
                rtAnswr = operand1 * operand2;
                sign.setText("*");
                break;


            case 3:
                rtAnswr = operand1 * 2 / operand2 * 2;
                sign.setText("/");
                break;

            case 4: {
                Intent intent = new Intent(this, HistoryList.class);
                startActivity(intent);
                break;
            }
            case 5: {
                Intent intent = new Intent(this, Preferences.class);
                startActivity(intent);
                if (checkedBox) {
                    operand1 = (int) (Math.random() * 100 * 2);
                    operand2 = (int) (Math.random() * 100 * 2);
                    break;
                }

            }

        }

        rN1.setText(Integer.toString(operand1));
        rN2.setText(Integer.toString(operand2));
        int wrong1 = operand1 + operand2 + 2;
        int wrong2 = operand1 + operand2 + 1;

        button2.setText("" + wrong1);
        button3.setText("" + wrong2);
        button.setText(Integer.toString(rtAnswr));

        rndmaswrs = rndAswrs(3);

        switch (rndmaswrs) {
            case 0:
                button.setText("" + rtAnswr);
                button2.setText("" + wrong2);
                button3.setText("" + wrong1);
                break;
            case 1:
                button.setText("" + wrong2);
                button2.setText("" + rtAnswr);
                button3.setText("" + wrong1);
                break;
            case 2:
                button.setText("" + wrong2);
                button2.setText("" + wrong1);
                button3.setText("" + rtAnswr);
                break;
        }

    }

    public void insert(int score) {

        SQLiteDatabase db = myDb.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COLUMN_SCORE, score);
        db.insert(DatabaseHelper.TABLE, DatabaseHelper.COLUMN_SCORE, cv);

    }

    public static void shuffle(List<?> list, Random rnd) {

    }

    // private static void shuffle(LinkedList list) {
    // // TODO Auto-generated method stub
    // class Collections{
    // public void main(String arg[]){
    // LinkedList list = new LinkedList();
    // list.add(sum);
    // list.add(wrongA1);
    // list.add(wrongA2);
    //
    // Collections.shuffle(list);
    // }
    // }
    // }

    public int rndAswrs(int v) {
        Random random = new Random();
        int b = random.nextInt(v);
        return b;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                if (rndmaswrs == 0) {
                    button.setBackgroundColor(Color.GREEN);
                    score++;
                    totalScore++;
                    Toast.makeText(this, "Good job", Toast.LENGTH_LONG).show();
                    setValues(score);
                } else {
                    button.setBackgroundColor(Color.RED);
                    Toast.makeText(this, "Try Again", Toast.LENGTH_LONG).show();
                    totalScore++;
                }
                totattmp.setText("" + score + "/" + totalScore);
                break;

            case R.id.button2:
                if (rndmaswrs == 1) {
                    button2.setBackgroundColor(Color.GREEN);
                    score++;
                    totalScore++;
                    Toast.makeText(this, "Good job", Toast.LENGTH_LONG).show();
                    setValues(score);
                } else {
                    button2.setBackgroundColor(Color.RED);
                    Toast.makeText(this, "Try Again", Toast.LENGTH_LONG).show();
                    totalScore++;
                }
                totattmp.setText("" + score + "/" + totalScore);

                break;

            case R.id.button3:
                if (rndmaswrs == 2) {
                    button3.setBackgroundColor(Color.GREEN);
                    score++;
                    totalScore++;
                    Toast.makeText(this, "Good job", Toast.LENGTH_LONG).show();
                    setValues(score);
                } else {
                    button3.setBackgroundColor(Color.RED);
                    Toast.makeText(this, "Try Again", Toast.LENGTH_LONG).show();
                }
                totattmp.setText("" + score + "/" + totalScore);

                break;
        }
        if (totalScore == 10) {
//			totalScore = 0;
//			score = 0;

            ContentValues cv = new ContentValues();
            cv.put(DatabaseHelper.COLUMN_SCORE, score + "/" + totalScore);
            SQLiteDatabase db = myDb.getWritableDatabase();
            db.insert(DatabaseHelper.TABLE, DatabaseHelper.COLUMN_SCORE, cv);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.option, menu);// TODO Auto-generated method stub
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        return super.onOptionsItemSelected(item);
    }

    {

    }

    // @Override
    // protected void onPause() {
    //
    //
    // // TODO Auto-generated method stub
    // super.onPause();
    // db.close();
    // }

}
