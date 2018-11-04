package com.example.tccl9.polypicker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;

public class CourseDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);
        Intent intent = getIntent();
        Course course = (Course)intent.getSerializableExtra("course");
        String cutoff = new Integer(course.getCutoff()).toString();

        TextView textViewCName, textViewSName, textViewPName, textViewAggCOPoint, textViewCourseID, textViewCourseDesc;
        textViewCName = (TextView) findViewById(R.id.lbCourseName);
        //textViewSName = (TextView) findViewById(R.id.lbSchoolName);
        textViewPName = (TextView) findViewById(R.id.lbPoly);
        textViewCourseID = (TextView) findViewById(R.id.lbCourseCode);
        textViewAggCOPoint = (TextView) findViewById(R.id.lbL1R4);
        textViewCourseDesc = (TextView) findViewById(R.id.lbCourseDesc);

        textViewCName.setText(course.getName());
        //textViewSName.setText(course.getSchool());
        textViewPName.setText(course.getPolytechnic());
        textViewCourseID.setText(course.getCode());
        textViewAggCOPoint.setText(cutoff);
        textViewCourseDesc.setText(course.getDescription());

    }
}
