package com.example.tccl9.polypicker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewDebug;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResultPage extends AppCompatActivity {

    private int as, be, bm, eng, hs, human, infotech, maritime, media = 0;
    //as = Chemical & Life Sciences
    //be =
    //bm = Business Management
    //eng = Engineering
    //hs = Health & Social Science
    //human =
    //infotech =  Information Technology
    //maritime =
    //media = Interactive & Digital Media
    //design = Design

    TextView resultView;
    CheckBox bookmark;
    List<Course> courseList;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultpage);

        Bundle myBundle = getIntent().getExtras();
        int[] quizResult = myBundle.getIntArray("PTArray");

        //TextView resultView = (TextView) findViewById(R.id.result);
        //resultView.setText(Arrays.toString(quizResult));

            for(int i=0; i<quizResult.length; i++) {
                switch (quizResult[i]) {
                    case 1:
                        if(i==0) {
                            as++;
                            break;
                        }
                        else if(i==1) {
                            be++;
                            break;
                        }
                        else if(i==2) {
                            bm++;
                            break;
                        }
                        else if(i==3) {
                            eng++;
                            break;
                        }


                    case 2:
                        if(i==0) {
                            as++;
                            break;
                        }
                        else if(i==1) {
                            be++;
                            break;
                        }
                        else if(i==2) {
                            bm++;
                            break;
                        }
                        else if(i==3) {
                            eng++;
                            break;
                        }

                    case 3:
                        if(i==0) {
                            as++;
                            break;
                        }
                        else if(i==1) {
                            be++;
                            break;
                        }
                        else if(i==2) {
                            bm++;
                            break;
                        }
                        else if(i==3) {
                            eng++;
                            break;
                        }
                    case 4:
                        if(i==0) {
                            as++;
                            break;
                        }
                        else if(i==1) {
                            be++;
                            break;
                        }
                        else if(i==2) {
                            bm++;
                            break;
                        }
                        else if(i==3) {
                            eng++;
                            break;
                        }
                }
            }

        StringBuilder sb=new StringBuilder("as"+Integer.toString(as)+"\n"+"be"+Integer.toString(be)+"\n"+"bm"+Integer.toString(bm)+"\n"+"eng"+Integer.toString(eng)+"\n");
        TextView resultView = (TextView) findViewById(R.id.result);
        //resultView.setText(sb.toString());

        CheckBox bookmark = (CheckBox) findViewById(R.id.star);
        bookmark.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) Toast.makeText(ResultPage.this, "Favourite", Toast.LENGTH_SHORT).show();
                else Toast.makeText(ResultPage.this, "Unfavourite", Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView =(RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        courseList = new ArrayList<>();

        courseList.add(new Course("N01", "Computer Engineering", null, "SCSE", "NTU", null, 12, "www.test.com", 0));
        courseList.add(new Course("N02", "Computer Science", null, "SCSE", "NTU", null, 13, "www.test.com", 0));
        courseList.add(new Course("N03", "Computer Whatever", null, "SCSE", "NTU", null, 14, "www.test.com", 0));

        CourseAdapter adapter = new CourseAdapter(this, courseList);

        recyclerView.setAdapter(adapter);

    }
}
