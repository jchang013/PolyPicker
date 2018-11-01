package com.example.tccl9.polypicker;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.os.AsyncTask;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class AppIntro extends AppCompatActivity {
    ImageButton androidImageButton;
    ArrayList<Course> CourseList;
    DatabaseControl courseDatabase;
    boolean sp = false, np = false, rp = false, nyp = false, tp = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appintro);

        courseDatabase = new DatabaseControl(this);
        if (courseDatabase.getAllCourses() == null) {
            new getCourseData().execute();
        } else {
            if (courseDatabase.polytechnicsInDatabase().contains("Singapore Polytechnic")) {
                sp = true;
            }
            if (courseDatabase.polytechnicsInDatabase().contains("Ngee Ann Polytechnic")) {
                np = true;
            }
            if (courseDatabase.polytechnicsInDatabase().contains("Republic Polytechnic")) {
                rp = true;
            }
            if (courseDatabase.polytechnicsInDatabase().contains("Nanyang Polytechnic")) {
                nyp = true;
            }
            if (courseDatabase.polytechnicsInDatabase().contains("Temasek Polytechnic")) {
                tp = true;
            }
            if (sp == false || np == false || rp == false || nyp == false || tp == false) {
                new getCourseData().execute();
            }
        }

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


    private class getCourseData extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(AppIntro.this, "Course Data is downloading", Toast.LENGTH_LONG).show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler spHTTP = new HttpHandler();
            String url = "https://data.gov.sg/api/action/datastore_search?resource_id=57b4ca93-3a50-4623-8aba-59c050ca8db9&limit=99";
            String jsonStr = spHTTP.makeServiceCall(url);

            //Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
<<<<<<< HEAD
                    //JSONObject jsonResult = jsonObj.getJSONObject("result");
                    JSONArray courses = jsonObj.getJSONArray("records");

                    for (int i = 0; i < courses.length(); i++) {
                        JSONObject course = courses.getJSONObject(i);
                        String code = course.getString("course_code");
                        String name = course.getString("course_name");
                        String school = course.getString("school");
                        String description = course.getString("course_description");
                        int cutoff = 0;         //add in method to return cutoff point with course code later
                        String link = course.getString("reference");
                        //int bookmark = 0;       //bookmark set as false initially
                        courseDatabase.insertData(code, name, school, polytechnic, description, cutoff, link);
                    }

                    Toast.makeText(getApplicationContext(),
                                "Singapore Polytechnic courses successfully downloaded",
                                Toast.LENGTH_LONG).show();

=======
                    JSONArray spCourses = jsonObj.getJSONArray("records");
                    //add in code to convert jsonarray to arraylist
>>>>>>> parent of 749d2b8... Modified AppIntro class and DatabaseControl class
                } catch (final JSONException e) {
                    //Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
            else {
                //Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from data.gov.sg.",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }
            return null;
        }
        //add method to reuse calling data.gov.sg api
    }
}
