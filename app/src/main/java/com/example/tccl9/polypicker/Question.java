package com.example.tccl9.polypicker;

public class Question {
    public String mQuestion[]={
            "Question 1",
            "Question 2",
            "Question 3",
            "Question 4"
    };

    public String mChoices[][]={
            {"q1o1", "q1o2", "q1o3", "q1o4"},
            {"q2o1", "q2o2", "q2o3", "q2o4"},
            {"q3o1", "q3o2", "q3o3", "q3o4"},
            {"q4o1", "q4o2", "q4o3", "q4o4"}
    };

    public String getQuestions(int a){
        return mQuestion[a];
    }
    public String getChoices1(int a){
        return mChoices[a][0];
    }
    public String getChoices2(int a){
        return mChoices[a][1];
    }
    public String getChoices3(int a){
        return mChoices[a][2];
    }
    public String getChoices4(int a){
        return mChoices[a][3];
    }
}
