package com.example.tccl9.polypicker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class DatabaseControl {
    myDbHelper myhelper;
    public DatabaseControl(Context context) {
        myhelper = new myDbHelper(context);
    }

    public void insertData(String code, String name, String school, String polytechnic, String description,
                           int cutoff, String link) {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.COURSE_CODE, code);
        contentValues.put(myDbHelper.COURSE_NAME, name);
        contentValues.put(myDbHelper.SCHOOL, school);
        contentValues.put(myDbHelper.POLYTECHNIC, polytechnic);
        contentValues.put(myDbHelper.COURSE_DESC, description);
        contentValues.put(myDbHelper.CUTOFF, cutoff);
        contentValues.put(myDbHelper.COURSE_LINK, link);
        contentValues.put(myDbHelper.BOOKMARKED, 0);
        dbb.insert(myDbHelper.TABLE_NAME, null, contentValues);
    }

    public ArrayList<Course> getAllCourses() {
        ArrayList<Course> courseList = new ArrayList<>();
        Course course;
        SQLiteDatabase db = myhelper.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + myDbHelper.TABLE_NAME, null);
        res.moveToFirst();

        while(res.isAfterLast() == false) {
            course = new Course(res.getString(res.getColumnIndex(myDbHelper.COURSE_CODE)),
                    res.getString(res.getColumnIndex(myDbHelper.COURSE_NAME)),
                    res.getString(res.getColumnIndex(myDbHelper.SCHOOL)),
                    res.getString(res.getColumnIndex(myDbHelper.POLYTECHNIC)),
                    res.getString(res.getColumnIndex(myDbHelper.COURSE_DESC)),
                    res.getInt(res.getColumnIndex(myDbHelper.CUTOFF)),
                    res.getString(res.getColumnIndex(myDbHelper.COURSE_LINK)),
                    res.getInt(res.getColumnIndex(myDbHelper.BOOKMARKED)));
            courseList.add(course);
            res.moveToNext();
        }
        return courseList;
    }

    public void updateBookmark(String code) {

    }

    static class myDbHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "PolyPickerDB";    // Database Name
        private static final String TABLE_NAME = "course";   // Table Name
        private static final int DATABASE_Version = 1;    // Database Version
        private static final String COURSE_CODE = "code";   // Column I (Primary Key)
        private static final String COURSE_NAME = "name";   // Column II
        private static final String SCHOOL = "school";      // Column III
        private static final String POLYTECHNIC = "polytechnic"; //Column IV
        private static final String COURSE_DESC = "description"; // Column V
        private static final String CUTOFF = "cutoff";       // Column VI
        private static final String COURSE_LINK = "link";   // Column VII
        private static final String BOOKMARKED = "bookmark";    // Column VIII

        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                COURSE_CODE + " VARCHAR(5) PRIMARY KEY, " +
                COURSE_NAME + " VARCHAR(255), " +
                SCHOOL + " VARCHAR(255), " +
                POLYTECHNIC + " VARCHAR(255), " +
                COURSE_DESC + " VARCHAR(255), " +
                CUTOFF + " INTEGER, " +
                COURSE_LINK + " VARCHAR(255), " +
                BOOKMARKED + " INTEGER);";
        private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;

        private Context context;
        public myDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context=context;
        }

        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_TABLE);

            } catch (Exception e) {
                Message.message(context,""+e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                Message.message(context,"OnUpgrade");
                db.execSQL(DROP_TABLE);
                onCreate(db);
            }catch (Exception e) {
                Message.message(context,""+e);
            }
        }
    }

}
