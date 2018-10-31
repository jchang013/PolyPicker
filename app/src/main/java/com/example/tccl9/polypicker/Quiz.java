package com.example.tccl9.polypicker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;
import java.util.Arrays;

import javax.xml.transform.Result;

public class Quiz extends AppCompatActivity{
    RadioGroup op;
    RadioButton op1, op2, op3, op4;
    Button nextButton, exitButton;
    TextView question;
    Question mQ = new Question();
    private static int Qnum = 0;
    private static int countAnswered=1;
    public String quizResult;
    public final int mQLength = 4;
    public int[] ResultArr = new int[mQLength];

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz);
        op = (RadioGroup) findViewById(R.id.options);
        op1 = (RadioButton) findViewById(R.id.option1);
        op2 = (RadioButton) findViewById(R.id.option2);
        op3 = (RadioButton) findViewById(R.id.option3);
        op4 = (RadioButton) findViewById(R.id.option4);
        nextButton = (Button) findViewById(R.id.next);
        exitButton = (Button) findViewById(R.id.exit);
        question = (TextView) findViewById(R.id.question);

        updateQuestion(Qnum);

        op.setOnCheckedChangeListener(new OnCheckedChangeListener(){
                @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){
                int choice=0;
                switch(checkedId)
                {
                    case R.id.option1:
                        choice=1;
                        break;
                    case R.id.option2:
                        choice=2;
                        break;
                    case R.id.option3:
                        choice=3;
                        break;
                    case R.id.option4:
                        choice=4;
                        break;
                }
                updateResult(choice, Qnum);
            }

        });

        nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!(countAnswered<mQLength)) {
                        openResultsPage();
                    }
                    else{
                        Qnum++;
                        op.clearCheck();
                        updateQuestion(Qnum);
                        countAnswered++;
                    }
                }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainMenu();
            }
        });
    }
    /*******************************************************************/
    /************************** M E T H O D S **************************/
    /*******************************************************************/
    public void updateQuestion(int num){
        if(num==mQLength) return;

        question.setText(mQ.getQuestions(num));
        op1.setText(mQ.getChoices1(num));
        op2.setText(mQ.getChoices2(num));
        op3.setText(mQ.getChoices3(num));
        op4.setText(mQ.getChoices4(num));
    }

    public void updateResult(int num, int Qnum){
        int Qnumber=Qnum;
        int choice=num;

        ResultArr[Qnumber]=choice;

        if(Qnum==mQLength) {
            quizResult = Arrays.toString(ResultArr);
        }

        Toast.makeText(this, Arrays.toString(ResultArr), Toast.LENGTH_SHORT).show();
    }

    public int[] getResult(){
        return ResultArr;
    }

    public void openMainMenu(){
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }

    private void openResultsPage() {
        Bundle quizResult = new Bundle();
        quizResult.putIntArray("PTArray", ResultArr);
        Intent intent = new Intent(this, ResultPage.class);
        intent.putExtras(quizResult);
        startActivity(intent);
    }

}


