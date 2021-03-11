package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView questionText, resultTextView, scoreTextView, timerTextView;

    Button goButton;
    Button option0 , option1, option2, option3;
    Button playAgainButton;

    int locationOfCorrectAnswer;
    int score , totalQuestions;

    ConstraintLayout gameLayout;

    ArrayList<Integer> answers = new ArrayList<>();

    /*  This method runs the timer for the app.
    *   It also prevents to answer after timer is over.
    */

    public void timer() {

        CountDownTimer timer = new CountDownTimer(30100 , 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                int time = (int)millisUntilFinished/1000;

                if(time <= 9){
                    timerTextView.setText("0:0" + Integer.toString(time));
                }else{
                    timerTextView.setText("0:" + Integer.toString(time));
                }
            }

            @Override
            public void onFinish() {
                resultTextView.setText("We're Done !!");
                playAgainButton.setVisibility(View.VISIBLE);
                option0.setEnabled(false);
                option1.setEnabled(false);
                option2.setEnabled(false);
                option3.setEnabled(false);
            }
        }.start();
    }

    @SuppressLint("SetTextI18n")
    public void checkAnswer(View view) {

        if(Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())) {
            resultTextView.setText("Correct");
            score++;

        }else {
            resultTextView.setText("Wrong :(");
        }
        totalQuestions++;

        scoreTextView.setText(Integer.toString(score) +"/"+totalQuestions);

        questionGenerator();
    }

    /* This method kick-starts the application.
    */
    public void start(View view) {
        gameLayout.setVisibility(View.VISIBLE);
        goButton.setVisibility(View.INVISIBLE);

        questionGenerator();
        timer();
    }

    /*  This method will generate the random questions for the game.
    *   It also generates the options for the questions.
    */

    @SuppressLint("SetTextI18n")
    public void questionGenerator() {

        answers.clear();

        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        locationOfCorrectAnswer = rand.nextInt(4);

        questionText.setText(Integer.toString(a) + " + " + Integer.toString(b));

        for (int i = 0 ; i < 4 ; i++) {
            if (i == locationOfCorrectAnswer) {
                answers.add(a+b);
            }else {
                int wrongAnswer = rand.nextInt(41);

                while(wrongAnswer == a+b) {
                    wrongAnswer = rand.nextInt(41);
                }
                answers.add(wrongAnswer);
            }
        }

        option0.setText(Integer.toString(answers.get(0)));
        option1.setText(Integer.toString(answers.get(1)));
        option2.setText(Integer.toString(answers.get(2)));
        option3.setText(Integer.toString(answers.get(3)));
    }

    /*  This method is used to reset the game and play again.
    *   It resets all the variables and values assigned.
    */
    public void playAgain(View view) {

        timerTextView.setText("0:05");
        score = 0;
        totalQuestions = 0;

        resultTextView.setText("");
        scoreTextView.setText(Integer.toString(score) +"/"+totalQuestions);

        option0.setEnabled(true);
        option1.setEnabled(true);
        option2.setEnabled(true);
        option3.setEnabled(true);

        playAgainButton.setVisibility(View.INVISIBLE);

        questionGenerator();
        timer();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionText = findViewById(R.id.questionTextView);
        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        timerTextView = findViewById(R.id.timerTextView);

        goButton = findViewById(R.id.goButton);

        option0 = findViewById(R.id.option0);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);

        playAgainButton = findViewById(R.id.playAgainButton);

        gameLayout = findViewById(R.id.gameLayout);

        goButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);
    }
}