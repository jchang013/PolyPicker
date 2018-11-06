package com.example.tccl9.polypicker;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.CheckBox;
import android.widget.Toast;

public class CourseDetails extends AppCompatActivity {
    Button moreDetails;
    CheckBox bookmarkView;
    boolean bookmarked = false;
    DatabaseControl courseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);

        courseDatabase = new DatabaseControl(this);
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

        TextView textViewCName, textViewSName, textViewPName,
                textViewAggCOPoint, textViewCourseID, textViewCourseDesc, textViewCourseCluster;
        textViewCName = (TextView) findViewById(R.id.lbCourseName);
        textViewSName = (TextView) findViewById(R.id.lbSchoolName);
        textViewCourseCluster = (TextView) findViewById(R.id.lbCourseCluster);
        textViewPName = (TextView) findViewById(R.id.lbPoly);
        textViewCourseID = (TextView) findViewById(R.id.lbCourseCode);
        textViewAggCOPoint = (TextView) findViewById(R.id.lbL1R4);
        textViewCourseDesc = (TextView) findViewById(R.id.lbCourseDesc);
        bookmarkView = findViewById(R.id.checkBox);
        if (course.getBookmark() == 1) {
            bookmarked = true;
        }
        bookmarkView.setChecked(bookmarked);
        bookmarkView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (course.getBookmark() == 0) {
                    course.setBookmark(1);
                    courseDatabase.updateBookmark(course);
                    Toast.makeText(CourseDetails.this, "Course bookmarked", Toast.LENGTH_SHORT).show();
                }
                else {
                    course.setBookmark(0);
                    courseDatabase.updateBookmark(course);
                    Toast.makeText(CourseDetails.this, "Course remove from bookmark", Toast.LENGTH_SHORT).show();
                }
            }
        });

        if (course.getSchool() == null) {
            course.setSchool("N/A");
        }
        if (course.getCategory() == null) {
            course.setCategory("N/A");
        }
        if (cutoff.equals("0")) {
            cutoff = "N/A";
        }
        if (course.getDescription() == null) {
            course.setDescription("N/A");
        }
        textViewCName.setText(course.getName());
        textViewSName.setText("Faculty: " + course.getSchool());
        textViewPName.setText(course.getPolytechnic());
        textViewCourseCluster.setText("Course Cluster: " + course.getCategory());
        textViewCourseID.setText("Course ID: " + course.getCode());
        textViewAggCOPoint.setText("L1R4: " + cutoff);
        textViewCourseDesc.setText(course.getDescription());

    }
    private void openURL(final Course course){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(course.getLink()));
        startActivity(browserIntent);
    }
}
