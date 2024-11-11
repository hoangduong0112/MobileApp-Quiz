package com.hd.quiz;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultCaller;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private TextView questionText;
    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private Button preButton;
    private Button cheatButton;
    private int index = 0;
    private boolean isCheater;

    private static final String KEY_INDEX = "index";
    ArrayList<Question> questions = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        questions.add(new Question(getString(R.string.Question1), true));

        questions.add(new Question(getString(R.string.Question2), false));

        questions.add(new Question(getString(R.string.Question3), false));

        questionText = findViewById(R.id.QuestionText);
        trueButton = findViewById(R.id.TrueButton);
        falseButton = findViewById(R.id.FalseButton);
        nextButton = findViewById(R.id.NextButton);
        preButton = findViewById(R.id.PreviousButton);
        cheatButton = findViewById(R.id.CheatButton);
        questionText.setText(questions.get(index).getQuestionName());
        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAnswer(true);
            }
        });
        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAnswer(false);
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index++;
                questionText.setText(questions.get(index).getQuestionName());
                isCheater = false;
            }
        });
        preButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index--;
                questionText.setText(questions.get(index).getQuestionName());
                isCheater = false;
            }
        });

        if (savedInstanceState !=null){
            index = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        cheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent i = new Intent(MainActivity.this, CheatActivity.class);
                boolean answer = questions.get(index).getAnswer();
                Intent i = CheatActivity.newIntent(MainActivity.this, answer);
//                startActivity(i);
                startActivity4Result.launch(i);

            }
        });

    }

    public void showAnswer(Boolean answer){
        if(questions.get(index).getAnswer() == answer)
            if(isCheater == true)
                Toast.makeText(this, "Cheaterrrr", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(this, "Chinh xac", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Dap an sai", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_INDEX, index);
    }

    ActivityResultLauncher<Intent> startActivity4Result = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result != null && result.getResultCode()== RESULT_OK)
                        if (result.getData() != null)
                            isCheater = CheatActivity.getAnswerShowed(result.getData());
                }
            }
    );



}