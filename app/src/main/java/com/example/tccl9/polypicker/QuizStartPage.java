package com.example.tccl9.polypicker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class QuizStartPage extends AppCompatActivity {

    Button startquiz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quizstartpage);

        startquiz = (Button) findViewById(R.id.startquizbutton);
        startquiz.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openQuiz();
            }
        });
    }
    public void openQuiz(){
        Intent intent = new Intent(this, Quiz.class);
        startActivity(intent);
    }
}
