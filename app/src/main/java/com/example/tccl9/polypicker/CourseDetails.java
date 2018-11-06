package com.example.tccl9.polypicker;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CourseDetails extends AppCompatActivity {
    Button moreDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);

        Intent intent = getIntent();
        final Course course = (Course)intent.getSerializableExtra("course");
        String cutoff = new Integer(course.getCutoff()).toString();

        moreDetails = (Button) findViewById(R.id.btnMoreDet);
        moreDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openURL(course);
            }
        });

        TextView textViewCName, textViewSName, textViewPName, textViewAggCOPoint, textViewCourseID, textViewCourseDesc;
        textViewCName = (TextView) findViewById(R.id.lbCourseName);
        textViewSName = (TextView) findViewById(R.id.lbSchoolName);
        textViewPName = (TextView) findViewById(R.id.lbPoly);
        textViewCourseID = (TextView) findViewById(R.id.lbCourseCode);
        textViewAggCOPoint = (TextView) findViewById(R.id.lbL1R4);
        textViewCourseDesc = (TextView) findViewById(R.id.lbCourseDesc);

        textViewCName.setText(course.getName());
        textViewSName.setText("Faculty: " + course.getSchool());
        textViewPName.setText(course.getPolytechnic());
        textViewCourseID.setText("Course ID: " + course.getCode());
        textViewAggCOPoint.setText("L1R4: " + cutoff);
        textViewCourseDesc.setText(course.getDescription());

    }
    private void openURL(final Course course){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(course.getLink()));
        startActivity(browserIntent);
    }
}
