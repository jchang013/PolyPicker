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
    private static final String spDataAPI = "https://data.gov.sg/api/action/datastore_search?resource_id=57b4ca93-3a50-4623-8aba-59c050ca8db9&limit=99";
    private static final String npDataAPI = "https://data.gov.sg/api/action/datastore_search?resource_id=12650e74-73d0-4797-874a-14f42899376f&limit=99";
    private static final String rpDataAPI = "";
    private static final String nypDataAPI = "";
    private static final String tpDataAPI = "";
    boolean spInDatabase = false;
    boolean npInDatabase = false;
    ImageButton androidImageButton;
    ArrayList<Course> CourseList;
    DatabaseControl courseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appintro);

        courseDatabase = new DatabaseControl(this);
        if (courseDatabase.getAllCourses().isEmpty()) {
            new getCourseData().execute();  //execute for all if table is empty
        } else {
            /*if (courseDatabase.polytechnicsInDatabase().contains("Singapore Polytechnic")) {
                spInDatabase = true;
            }
            if (courseDatabase.polytechnicsInDatabase().contains("Ngee Ann Polytechnic")) {
                npInDatabase = true;
            }*/
            new getCourseData().execute();
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
            //add getDataAPIRequest method, initially all false, will check on top if in database first before calling
            if (spInDatabase == false) {
                getDataAPIRequest(spDataAPI, "Singapore Polytechnic");
            }
            if (npInDatabase == false) {
                getDataAPIRequest(npDataAPI, "Ngee Ann Polytechnic");
            }
            return null;
        }
        protected void getDataAPIRequest(String url, String polytechnic) {
            HttpHandler hh = new HttpHandler();
            String jsonStr = hh.makeServiceCall(url);

            //Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    JSONObject jsonResult = jsonObj.getJSONObject("result");
                    JSONArray courses = jsonResult.getJSONArray("records");

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
        }
    }
}
