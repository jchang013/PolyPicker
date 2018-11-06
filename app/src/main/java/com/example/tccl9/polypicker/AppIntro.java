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
import java.io.InputStream;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class AppIntro extends AppCompatActivity {
    private static final String spDataAPI = "https://data.gov.sg/api/action/datastore_search?resource_id=57b4ca93-3a50-4623-8aba-59c050ca8db9&limit=99";
    private static final String npDataAPI = "https://data.gov.sg/api/action/datastore_search?resource_id=12650e74-73d0-4797-874a-14f42899376f&limit=99";
    private static final String rpDataAPI = "https://data.gov.sg/api/action/datastore_search?resource_id=1efcbf25-2f5d-4328-8e2b-fe604a6bacd9&limit=99";
    private static final String nypDataAPI = "https://data.gov.sg/api/action/datastore_search?resource_id=df488f8c-c9bf-4ee9-a724-6ff1df6b85df&limit=99";
    private static final String tpDataAPI = "https://data.gov.sg/api/action/datastore_search?resource_id=57a85ec7-1a80-4907-9b4e-5b8b9d047184&limit=99";
    boolean spInDatabase = false;
    boolean npInDatabase = false;
    boolean rpInDatabase = false;
    boolean nypInDatabase = false;
    boolean tpInDatabase = false;
    ImageButton androidImageButton;
    ArrayList<Course> CourseList;
    DatabaseControl courseDatabase;
    List<String[]> polyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appintro);

        InputStream inputStream = getResources().openRawResource(R.raw.poly);
        CSVFile csvFile = new CSVFile(inputStream);
        polyList = csvFile.read();
        String[] test = polyList.get(1);

        courseDatabase = new DatabaseControl(this);
        if (courseDatabase.getAllCourses().isEmpty()) {
            new getCourseData().execute();  //execute for all if table is empty
        } else {
            if (courseDatabase.polytechnicsInDatabase().contains("Singapore Polytechnic")) {
                spInDatabase = true;
            }
            if (courseDatabase.polytechnicsInDatabase().contains("Ngee Ann Polytechnic")) {
                npInDatabase = true;
            }
            if (courseDatabase.polytechnicsInDatabase().contains("Republic Polytechnic")) {
                rpInDatabase = true;
            }
            if (courseDatabase.polytechnicsInDatabase().contains("Nanyang Polytechnic")) {
                nypInDatabase = true;
            }
            if (courseDatabase.polytechnicsInDatabase().contains("Temasek Polytechnic")) {
                tpInDatabase = true;
            }
            new getCourseData().execute();
        }

        androidImageButton = (ImageButton) findViewById(R.id.image_button_android);
        androidImageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openStartPage();
            }
        });
        Toast.makeText(AppIntro.this, "Testing Course code: "+test[2], Toast.LENGTH_LONG).show();
    }
    public void openStartPage(){
        Intent intent = new Intent(this, StartPage.class);
        startActivity(intent);
    }


    private class getCourseData extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Toast.makeText(AppIntro.this, "Course data is downloading", Toast.LENGTH_LONG).show();

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
            if (rpInDatabase == false) {
                getDataAPIRequestRP(rpDataAPI, "Republic Polytechnic");
            }
            if (nypInDatabase == false) {
                getDataAPIRequestNYP(nypDataAPI, "Nanyang Polytechnic");
            }
            if (tpInDatabase == false) {
                getDataAPIRequestTP(tpDataAPI, "Temasek Polytechnic");
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
                        String category = null; //add in method to return category
                        int cutoff = 0;         //add in method to return cutoff point with course code later
                        for (int j = 1; j < polyList.size(); j++) {
                            String[] poly = polyList.get(j);
                            if (poly[2].equals(code)) {
                                category = poly[4].toLowerCase();
                                if (!poly[5].equals("-")) {
                                    cutoff = Integer.parseInt(poly[5]);
                                }
                                break;
                            }
                        }
                        String category_capitalized = capitalizeWord(category);
                        String school = course.getString("school");
                        String description = course.getString("course_description");

                        String link = course.getString("reference");
                        courseDatabase.insertData(code, name, category_capitalized, school, polytechnic, description, cutoff, link);
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
                } catch (final NumberFormatException e) {

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
        protected void getDataAPIRequestRP(String url, String polytechnic) {
            //For RP, NYP and TP data API, the columns are different and some columns not available
            //This is for RP
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
                        String category = null; //add in method to return category
                        int cutoff = 0;         //add in method to return cutoff point with course code later
                        for (String[] poly : polyList) {
                            if (poly[2].equals(code)) {
                                category = poly[4].toLowerCase();
                                if (!poly[5].equals("-")) {
                                    cutoff = Integer.parseInt(poly[5]);
                                }
                                break;
                            }
                        }
                        String category_capitalized = capitalizeWord(category);
                        String school = course.getString("school");
                        //String description = course.getString("course_description");
                        //String link = course.getString("reference");
                        courseDatabase.insertData(code, name, category_capitalized, school, polytechnic, null, cutoff, null);
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
                } catch (final NumberFormatException e) {

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
        protected void getDataAPIRequestNYP(String url, String polytechnic) {
            //For RP, NYP and TP data API, the columns are different and some columns not available
            //This is for NYP
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
                        String code = course.getString("jae_course_code");
                        String name = course.getString("course_title");
                        String category = null; //add in method to return category
                        int cutoff = 0;         //add in method to return cutoff point with course code later
                        for (String[] poly : polyList) {
                            if (poly[2].equals(code)) {
                                category = poly[4].toLowerCase();
                                if (!poly[5].equals("-")) {
                                    cutoff = Integer.parseInt(poly[5]);
                                }
                                break;
                            }
                        }
                        String category_capitalized = capitalizeWord(category);
                        //String school = course.getString("school");
                        //String description = course.getString("course_description");
                        String link = course.getString("url");
                        courseDatabase.insertData(code, name, category_capitalized,null, polytechnic, null, cutoff, link);
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
                } catch (final NumberFormatException e) {

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
        protected void getDataAPIRequestTP(String url, String polytechnic) {
            //For RP, NYP and TP data API, the columns are different and some columns not available
            //This is for TP
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
                        String code = course.getString("moe_course_code");
                        String name = course.getString("course_name");
                        String category = null; //add in method to return category
                        int cutoff = 0;         //add in method to return cutoff point with course code later
                        for (String[] poly : polyList) {
                            if (poly[2].equals(code)) {
                                category = poly[4].toLowerCase();
                                if (!poly[5].equals("-")) {
                                    cutoff = Integer.parseInt(poly[5]);
                                }
                                break;
                            }
                        }
                        String category_capitalized = capitalizeWord(category);
                        String school = course.getString("school");
                        String description = course.getString("course_description");
                        String link = course.getString("reference");
                        courseDatabase.insertData(code, name, category_capitalized, school, polytechnic, description, cutoff, link);
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
                } catch (final NumberFormatException e) {

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
    public static String capitalizeWord(String str){
        if (str == null)
            return null;
        String words[]=str.split("\\s");
        String capitalizeWord="";
        for(String w:words){
            String first=w.substring(0,1);
            String afterfirst=w.substring(1);
            capitalizeWord+=first.toUpperCase()+afterfirst+" ";
        }
        return capitalizeWord.trim();
    }
}
