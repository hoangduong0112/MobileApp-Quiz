package com.hd.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hd.quiz.R;

public class CheatActivity extends AppCompatActivity {
    private Button showAnswer;
    private static final String EXTRA_ANSWER = "AnswerExtra";
    private static final String EXTRA_ANSWER_RESULT = "AnswerExtraResult";

    private boolean realAnswer;
    private TextView viewTextAnswer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        realAnswer = this.getIntent().getBooleanExtra(EXTRA_ANSWER, false);

        viewTextAnswer = findViewById(R.id.CheatAnswerText);

        showAnswer = findViewById(R.id.CheatShowButton);

        showAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewTextAnswer.setText(String.valueOf(realAnswer));
                setAnswerResult(true);
            }
        });


    }

    public static Intent newIntent(Context packageContext, boolean answer){
        Intent i = new Intent(packageContext, CheatActivity.class);
        i.putExtra(EXTRA_ANSWER, answer);
        return i;
    }

    private void setAnswerResult(boolean isAnswerShowed){
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_RESULT, isAnswerShowed);

        setResult(RESULT_OK, data);

    }

    public static boolean getAnswerShowed(Intent intent){
        return intent.getBooleanExtra(EXTRA_ANSWER_RESULT, false);
    }
}