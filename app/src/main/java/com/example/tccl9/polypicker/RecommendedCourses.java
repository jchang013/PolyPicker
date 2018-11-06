package com.example.tccl9.polypicker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RecommendedCourses extends AppCompatActivity {
    DatabaseControl courseDatabase;
    ArrayList<Course> courseList;
    RecyclerView recyclerView;
    CourseAdapter adapter;
    TextView category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommended_courses);

        courseDatabase = new DatabaseControl(this);

        Intent intent = getIntent();
        String orientation = (String)intent.getSerializableExtra("orientation");
        category = (TextView) findViewById(R.id.lbRecommendedCourses);
        category.setText("Category: " + orientation);

        recyclerView = (RecyclerView)findViewById(R.id.lbRecCourseCat);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        courseList = courseDatabase.searchCourse(orientation);
        adapter = new CourseAdapter(this, courseList);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new CourseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Course course = courseList.get(position);
                openCourseDetails(course);
            }

            @Override
            public void onBookmarkClick(int position) {
                Course course = courseList.get(position);
                if (course.getBookmark() == 0) {
                    course.setBookmark(1);
                    courseDatabase.updateBookmark(course);
                    Toast.makeText(RecommendedCourses.this, "Course bookmarked", Toast.LENGTH_SHORT).show();
                }
                else {
                    course.setBookmark(0);
                    courseDatabase.updateBookmark(course);
                    Toast.makeText(RecommendedCourses.this, "Course remove from bookmark", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void openCourseDetails(Course course) {
        Intent intent = new Intent(this, CourseDetails.class);
        intent.putExtra("course", course);
        startActivity(intent);
    }
}
