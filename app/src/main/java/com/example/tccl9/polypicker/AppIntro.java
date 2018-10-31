package com.example.tccl9.polypicker;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class AppIntro extends AppCompatActivity {
    ImageButton androidImageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appintro);

        androidImageButton = (ImageButton) findViewById(R.id.image_button_android);
        androidImageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openStartPage();
            }
        });
    }
    public void openStartPage(){
        Intent intent = new Intent(this, StartPage.class);
        startActivity(intent);
    }
}
