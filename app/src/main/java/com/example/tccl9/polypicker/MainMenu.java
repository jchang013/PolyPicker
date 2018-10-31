package com.example.tccl9.polypicker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {
    Button takeQuiz, browseCatalogue, polytechnics, viewBookmarks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenu);

        takeQuiz = (Button) findViewById(R.id.takequiz);
        takeQuiz.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openStartQuiz();
            }
        });

        browseCatalogue = (Button) findViewById(R.id.browsecatalogue);
        browseCatalogue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBrowseCatalogue();
            }
        });

        polytechnics = (Button) findViewById(R.id.polytechnics);
        polytechnics.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openPolytechnics();
            }
        });

        viewBookmarks = (Button) findViewById(R.id.bookmarks);
        viewBookmarks.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openBookmarks();
            }
        });


    }
    public void openStartQuiz(){
        Intent intent = new Intent(this, QuizStartPage.class);
        startActivity(intent);
    }

    public void openBrowseCatalogue(){
        Intent intent = new Intent(this, BrowseCatalogue.class);
        startActivity(intent);
    }

    public void openPolytechnics(){
        Intent intent = new Intent(this, Polytechnics.class);
        startActivity(intent);
    }

    public void openBookmarks(){
        Intent intent = new Intent(this, Bookmarks.class);
        startActivity(intent);
    }
}

