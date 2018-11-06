package com.example.tccl9.polypicker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.HashSet;

public class DatabaseControl {
    myDbHelper myhelper;
    public DatabaseControl(Context context) {
        myhelper = new myDbHelper(context);
    }

    public void insertData(String code, String name, String category, String school,
                           String polytechnic, String description,
                           int cutoff, String link) {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.COURSE_CODE, code);
        contentValues.put(myDbHelper.COURSE_NAME, name);
        contentValues.put(myDbHelper.COURSE_CATEGORY, category);
        contentValues.put(myDbHelper.SCHOOL, school);
        contentValues.put(myDbHelper.POLYTECHNIC, polytechnic);
        contentValues.put(myDbHelper.COURSE_DESC, description);
        contentValues.put(myDbHelper.CUTOFF, cutoff);
        contentValues.put(myDbHelper.COURSE_LINK, link);
        contentValues.put(myDbHelper.BOOKMARKED, 0);
        db.insert(myDbHelper.TABLE_NAME, null, contentValues);
        db.close();
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
                    res.getString(res.getColumnIndex(myDbHelper.COURSE_CATEGORY)),
                    res.getString(res.getColumnIndex(myDbHelper.POLYTECHNIC)),
                    res.getString(res.getColumnIndex(myDbHelper.COURSE_DESC)),
                    res.getInt(res.getColumnIndex(myDbHelper.CUTOFF)),
                    res.getString(res.getColumnIndex(myDbHelper.COURSE_LINK)),
                    res.getInt(res.getColumnIndex(myDbHelper.BOOKMARKED)));
            courseList.add(course);
            res.moveToNext();
        }
        db.close();
        return courseList;
    }

    public ArrayList<Course> getBookmarkedCourses() {
        ArrayList<Course> courseList = new ArrayList<>();
        Course course;
        SQLiteDatabase db = myhelper.getReadableDatabase();

        Cursor res = db.rawQuery("select * from " + myDbHelper.TABLE_NAME +
                                    " where " + myDbHelper.BOOKMARKED + " = ?", new String[] {"1"});
        res.moveToFirst();

        while(res.isAfterLast() == false) {
            course = new Course(res.getString(res.getColumnIndex(myDbHelper.COURSE_CODE)),
                    res.getString(res.getColumnIndex(myDbHelper.COURSE_NAME)),
                    res.getString(res.getColumnIndex(myDbHelper.SCHOOL)),
                    res.getString(res.getColumnIndex(myDbHelper.COURSE_CATEGORY)),
                    res.getString(res.getColumnIndex(myDbHelper.POLYTECHNIC)),
                    res.getString(res.getColumnIndex(myDbHelper.COURSE_DESC)),
                    res.getInt(res.getColumnIndex(myDbHelper.CUTOFF)),
                    res.getString(res.getColumnIndex(myDbHelper.COURSE_LINK)),
                    res.getInt(res.getColumnIndex(myDbHelper.BOOKMARKED)));
            courseList.add(course);
            res.moveToNext();
        }
        db.close();
        return courseList;
    }

    public ArrayList<Course> searchCourse(String query) {
        ArrayList<Course> courseList = new ArrayList<>();
        Course course;
        SQLiteDatabase db = myhelper.getReadableDatabase();
        //Select all columns where column code, school, polytechnic, description has query
        Cursor res = db.rawQuery("select * from " + myDbHelper.TABLE_NAME + " where " +
                myDbHelper.COURSE_CODE + " like '%" + query + "%' or " +
                myDbHelper.COURSE_NAME + " like '%" + query + "%' or " +
                myDbHelper.COURSE_CATEGORY + " like '%" + query + "%' or " +
                myDbHelper.SCHOOL + " like '%" + query + "%' or " +
                myDbHelper.COURSE_DESC + " like '%" + query + "%'"
                ,null);
        res.moveToFirst();

        while(res.isAfterLast() == false) {
            course = new Course(res.getString(res.getColumnIndex(myDbHelper.COURSE_CODE)),
                    res.getString(res.getColumnIndex(myDbHelper.COURSE_NAME)),
                    res.getString(res.getColumnIndex(myDbHelper.COURSE_CATEGORY)),
                    res.getString(res.getColumnIndex(myDbHelper.SCHOOL)),
                    res.getString(res.getColumnIndex(myDbHelper.POLYTECHNIC)),
                    res.getString(res.getColumnIndex(myDbHelper.COURSE_DESC)),
                    res.getInt(res.getColumnIndex(myDbHelper.CUTOFF)),
                    res.getString(res.getColumnIndex(myDbHelper.COURSE_LINK)),
                    res.getInt(res.getColumnIndex(myDbHelper.BOOKMARKED)));
            courseList.add(course);
            res.moveToNext();
        }
        db.close();
        return courseList;
    }

    public ArrayList<Course> getCoursesAboveCutoff(int cutoff) {
        ArrayList<Course> courseList = new ArrayList<>();
        Course course;
        SQLiteDatabase db = myhelper.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + myDbHelper.TABLE_NAME +
                                    " where " + myDbHelper.CUTOFF + " >= " + cutoff, null);
        res.moveToFirst();

        while(res.isAfterLast() == false) {
            course = new Course(res.getString(res.getColumnIndex(myDbHelper.COURSE_CODE)),
                    res.getString(res.getColumnIndex(myDbHelper.COURSE_NAME)),
                    res.getString(res.getColumnIndex(myDbHelper.COURSE_CATEGORY)),
                    res.getString(res.getColumnIndex(myDbHelper.SCHOOL)),
                    res.getString(res.getColumnIndex(myDbHelper.POLYTECHNIC)),
                    res.getString(res.getColumnIndex(myDbHelper.COURSE_DESC)),
                    res.getInt(res.getColumnIndex(myDbHelper.CUTOFF)),
                    res.getString(res.getColumnIndex(myDbHelper.COURSE_LINK)),
                    res.getInt(res.getColumnIndex(myDbHelper.BOOKMARKED)));
            courseList.add(course);
            res.moveToNext();
        }
        db.close();
        return courseList;
    }

    public void updateBookmark(Course course) {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        db.execSQL("update " + myDbHelper.TABLE_NAME +
                " set " + myDbHelper.BOOKMARKED + " = '" + course.getBookmark() + "' " +
                " where " + myDbHelper.COURSE_CODE + " = '" + course.getCode() + "'");
        db.close();
    }

    public ArrayList<String> polytechnicsInDatabase() {
        HashSet<String> polytechnicsSet = new HashSet<String>();
        ArrayList<String> polytechnicsList = new ArrayList<String>();
        String polytechnic;
        SQLiteDatabase db = myhelper.getReadableDatabase();
        Cursor res = db.rawQuery("select " + myDbHelper.POLYTECHNIC +
                                        " from " + myDbHelper.TABLE_NAME, null);
        res.moveToFirst();
        while(res.isAfterLast() == false) {
            polytechnic = res.getString(res.getColumnIndex(myDbHelper.POLYTECHNIC));
            polytechnicsSet.add(polytechnic);
            res.moveToNext();
        }
        polytechnicsList.addAll(polytechnicsSet);
        db.close();
        return polytechnicsList;
    }

    static class myDbHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "PolyPickerDB";    // Database Name
        private static final String TABLE_NAME = "course";   // Table Name
        private static final int DATABASE_Version = 5;    // Database Version
        private static final String COURSE_CODE = "code";   // Column I (Primary Key)
        private static final String COURSE_NAME = "name";   // Column II
        private static final String COURSE_CATEGORY = "category";   //Column III
        private static final String SCHOOL = "school";      // Column IV
        private static final String POLYTECHNIC = "polytechnic"; //Column V
        private static final String COURSE_DESC = "description"; // Column VI
        private static final String CUTOFF = "cutoff";       // Column VII
        private static final String COURSE_LINK = "link";   // Column VIII
        private static final String BOOKMARKED = "bookmark";    // Column IX

        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                COURSE_CODE + " VARCHAR(255) PRIMARY KEY, " +
                COURSE_NAME + " VARCHAR(255), " +
                COURSE_CATEGORY + " VARCHAR(255), " +
                SCHOOL + " VARCHAR(255), " +
                POLYTECHNIC + " VARCHAR(255), " +
                COURSE_DESC + " VARCHAR(5000), " +
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
                //Message.message(context,""+e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                //Message.message(context,"OnUpgrade");
                db.execSQL(DROP_TABLE);
                onCreate(db);
            }catch (Exception e) {
                //Message.message(context,""+e);
            }
        }
    }

}
