package com.example.tccl9.polypicker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

public class Bookmarks extends AppCompatActivity {
    ArrayList<Course> courseList = new ArrayList<>();
    DatabaseControl courseDatabase;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookmarks);

        recyclerView = (RecyclerView)findViewById(R.id.bookmarkedCourse);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        courseDatabase = new DatabaseControl(this);
        courseList = courseDatabase.getBookmarkedCourses();

        CourseAdapter adapter = new CourseAdapter(this, courseList);
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
                    Toast.makeText(Bookmarks.this, "Course bookmarked", Toast.LENGTH_SHORT).show();
                }
                else {
                    course.setBookmark(0);
                    courseDatabase.updateBookmark(course);
                    Toast.makeText(Bookmarks.this, "Course remove from bookmark", Toast.LENGTH_SHORT).show();
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
