package com.example.tccl9.polypicker;

public class Course {
    private String courseID;
    private String sName;
    private String cName;
    private String pName;
    private int aggCOPoint;

    public Course(String courseID, int aggCOPoint, String sName, String cName, String pName){
        this.courseID = courseID;
        this.aggCOPoint=aggCOPoint;
        this.sName=sName;
        this.cName=cName;
        this.pName=pName;
    }

    public String getCourseID(){
        return courseID;
    }

    public int getaggCOPoint(){
        return aggCOPoint;
    }

    public String getsName(){
        return sName;
    }

    public String getcName(){
        return cName;
    }

    public String getpName(){
        return pName;
    }
}
