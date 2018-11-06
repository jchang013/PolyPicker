package com.example.tccl9.polypicker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class ResultPage extends AppCompatActivity {

    private int cAS=0, cE=0, cBMGT=0, cENG=0, cHS=0, cHUMANS=0, cIT=0, cMARITIME=0, cADM=0;
    private String descAS = "Cool, you like sciences and like to get to the core of how the world of science can be used to explain real world phenomenom and scenarios. Your quest for knowledge expands into how science can be used to make the world a better place; pusing research boundaries and make ground-breaking discoveries!";
    private String descE ="The world needs more people like you! The world we live in, the concrete jungle that we\'re surrounded, beautified by wonderful architectures and energy efficient technologies and the creation of the environment and cities that we call home. You like to spend time thinking on how you can make a difference in the land that we step on, the buildings that we enter, down to the house that we find comfort in. ";
    private String descBMGT ="There\'s a saying that goes: \"Money makes the world go round\" and like everyone else, wealth and riches is your creed and honestly nothing beats the charisma of a businessman/woman with their buesiness driven mind. Be it maths, communication, creativity, entrepreneur mind, you possess what it need to fight the battle in the business driven world; Go get it! ";
    private String descENG = "How often do you get lost in your train of thoughts asking the 5W1Hs for everything around you? Especially the How(s) - you\'re often intrigued by how things works and how it can be further improved. This though process of yours is truly a gem to the world. You\'re made for championing current inventions and not only look to refine, but to reinvent -  changing how the world works, and of course, with your very own creation! ";
    private String descHS ="Someone please bless this precious soul of yours; you are one beautiful human being always thinking of the people around you. You are constantly putting others before yourself and making this place a better place. Thank you. You’re constantly thinking of ways the world can be alleviated of sufferings of health concerns and how you can contribute in this process. ";
    private String descHUMANS ="Humans are complex. The world is complex – and it is through powerful translators and mind of yours that decipher how the world communicates and thinks; dissecting the various cultures, and people – reading into the philosophy and linguistic to understand us human being on a deeper level. ";
    private String descIT ="Humans are complex. The world is complex – and it is through powerful translators and mind of yours that decipher how the world communicates and thinks; dissecting the various cultures, and people – reading into the philosophy and linguistic to understand us human being on a deeper level. ";
    private String descMARITIME ="In case you still didn\'t know, the Earth consists of 71% water. The world\'s transportation network would be so crippled without Maritime technologies like the large cargo ships and boats that ferry goods and humans. In short, we need you! ";
    private String descADM ="No we won\'t judge your art. You are precious. So is your mind that constantly lives amongst the extremes: the fantasy dystopian/utopian and the deepest realm of darkness. Your creative spurts and slumps are not unheard of; You cherish time alone but god knows how much you\'ve created both physically and conceptually. In a world dominated by logical people, the creative mind is a precious gem and we need talents like you to translate ideas into visual realities. ";

    /*
		1.	Applied Sciences (AS)
		2.	Built environment (E)
		3.	Business and management (BMGT)
		4.	Engineering (ENG)
		5.	Health Science (HS)
		6.	Humanities (HUMANS)
		7.	Information & Digital Technologies (IT)
		8.	Maritime Studies (MARITIME)
		9.	Media & Design (ADM)
    */

    TextView resultView;
    CheckBox bookmark;
    List<Course> courseList;
    RecyclerView recyclerView;
    Button seeRecommended;
    String orientation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultpage_oriented);

        Bundle myBundle = getIntent().getExtras();
        int[] quizResult = myBundle.getIntArray("PTArray");

        for(int i=0; i<quizResult.length; i++) {
            switch(i) {
                case 0:
                    if (quizResult[i] == 1) {
                        cADM++;
                        cHUMANS++;
                        cE++;
                        break;
                    } else if (quizResult[i] == 2) {
                        cBMGT++;
                        cIT++;
                        break;
                    } else if (quizResult[i] == 3) {
                        cAS++;
                        cE++;
                        cMARITIME++;
                        cIT++;
                        break;
                    } else if (quizResult[i] == 4) {
                        cADM++;
                        break;
                    }

                case 1:
                    if (quizResult[i] == 1) {
                        cAS++;
                        cIT++;
                        cENG++;
                        break;
                    } else if (quizResult[i] == 2) {
                        cBMGT++;
                        cIT++;
                        break;
                    } else if (quizResult[i] == 3) {
                        cAS++;
                        cHS++;
                        break;
                    } else if (quizResult[i] == 4) {
                        cIT++;
                        cADM++;
                        break;
                    }
                case 2:
                    if (quizResult[i] == 1) {
                        cE++;
                        cIT++;
                        cHUMANS++;
                        break;
                    } else if (quizResult[i] == 2) {
                        cBMGT++;
                        break;
                    } else if (quizResult[i] == 3) {
                        cE++;
                        cENG++;
                        cMARITIME++;
                        break;
                    } else if (quizResult[i] == 4) {
                        cADM++;
                        break;
                    }
                case 3:
                    if (quizResult[i] == 1) {
                        cHUMANS++;
                        cHS++;
                        break;
                    } else if (quizResult[i] == 2) {
                        cBMGT++;
                        cIT++;
                        break;
                    } else if (quizResult[i] == 3) {
                        cENG++;
                        cMARITIME++;
                        cE++;
                        cAS++;
                        break;
                    } else if (quizResult[i] == 4) {
                        cADM++;
                        break;
                    }
                case 4:
                    if (quizResult[i] == 1) {
                        cIT++;
                        break;
                    } else if (quizResult[i] == 2) {
                        cHUMANS++;
                        cAS++;
                        cHS++;
                        break;
                    } else if (quizResult[i] == 3) {
                        cADM++;
                        cBMGT++;
                        cIT++;
                        break;
                    } else if (quizResult[i] == 4) {
                        cE++;
                        cENG++;
                        cMARITIME++;
                        cADM++;
                        break;
                    }//
                case 5:
                    if (quizResult[i] == 1) {
                        cHUMANS++; cHS++; cBMGT++;
                        break;
                    } else if (quizResult[i] == 2) {
                        cBMGT++; cENG++; cMARITIME++; cIT++;
                        break;
                    } else if (quizResult[i] == 3) {
                        cENG++; cIT++; cE++; cAS++;
                        break;
                    } else if (quizResult[i] == 4) {
                        cADM++; cE++;
                        break;
                    }
                case 6:
                    if (quizResult[i] == 1) {
                        cHUMANS++; cHS++;
                        break;
                    } else if (quizResult[i] == 2) {
                        cBMGT++;
                        break;
                    } else if (quizResult[i] == 3) {
                        cIT++; cENG++; cMARITIME++; cAS++; cHS++;
                        break;
                    } else if (quizResult[i] == 4) {
                        cHUMANS++; cADM++;
                        break;
                    }
                case 7:
                    if (quizResult[i] == 1) {
                        cHS++; cHUMANS++;
                        break;
                    } else if (quizResult[i] == 2) {
                        cBMGT++;
                        break;
                    } else if (quizResult[i] == 3) {
                        cAS++; cENG++; cMARITIME++; cIT++; cE++;
                        break;
                    } else if (quizResult[i] == 4) {
                        cADM++;
                        break;
                    }
                case 8:
                    if (quizResult[i] == 1) {
                        cHS++; cIT++;
                        break;
                    } else if (quizResult[i] == 2) {
                        cBMGT++;
                        break;
                    } else if (quizResult[i] == 3) {
                        cENG++; cMARITIME++; cE++; cIT++; cAS++;
                        break;
                    } else if (quizResult[i] == 4) {
                        cADM++; cE++;
                        break;
                    }
                case 9:
                    if (quizResult[i] == 1) {
                        cADM++; cE++; cHUMANS++; cAS++;
                        break;
                    } else if (quizResult[i] == 2) {
                        cBMGT++;
                        break;
                    } else if (quizResult[i] == 3) {
                        cHS++; cENG++; cMARITIME++; cIT++;
                        break;
                    } else if (quizResult[i] == 4) {
                        cIT++; cADM++; cAS++;
                        break;
                    }
                case 10:
                     if (quizResult[i] == 1) {
                         cIT++; cADM++;
                         break;
                     } else if (quizResult[i] == 2) {
                         cBMGT++; cIT++;
                         break;
                     } else if (quizResult[i] == 3) {
                        cENG++; cMARITIME++; cADM++; cIT++; cE++;
                         break;
                     } else if (quizResult[i] == 4) {
                         cHUMANS++; cAS++; cENG++; cMARITIME++; cE++; cIT++;
                         break;
                     }
                case 11:
                    if (quizResult[i] == 1) {
                        cBMGT++; cIT++;
                        break;
                    } else if (quizResult[i] == 2) {
                        cAS++; cIT++; cADM++; cENG++; cE++;
                        break;
                    } else if (quizResult[i] == 3) {
                        cADM++; cIT++; cAS++;
                        break;
                    } else if (quizResult[i] == 4) {
                        cHUMANS++; cHS++; cAS++; cIT++; cENG++; cMARITIME++;
                        break;
                    }
                case 12:
                    if (quizResult[i] == 1) {
                        cIT++; cENG++; cMARITIME++;
                        break;
                    } else if (quizResult[i] == 2) {
                        cIT++; cE++; cENG++; cHS++; cAS++;
                        break;
                    } else if (quizResult[i] == 3) {
                        cENG++; cHS++; cAS++; cBMGT++;
                        break;
                    } else if (quizResult[i] == 4) {
                        cIT++; cMARITIME++; cENG++; cBMGT++;
                        break;
                    }
                case 13:
                    if (quizResult[i] == 1) {
                        cADM++; cE++;
                        break;
                    } else if (quizResult[i] == 2) {
                        cBMGT++; cENG++; cMARITIME++;
                        break;
                    } else if (quizResult[i] == 3) {
                        cIT++; cE++; cENG++; cMARITIME++; cHUMANS++; cAS++; cHS++;
                        break;
                    } else if (quizResult[i] == 4) {
                        cAS++; cHS++; cENG++; cMARITIME++; cIT++; cBMGT++;
                        break;
                    }
            }//end switch
        }//end for loop

//        StringBuilder sb = new StringBuilder("AS"+Integer.toString(cAS)+"\n"+"E"+Integer.toString(cE)+"\n"+"BMGT"+Integer.toString(cBMGT)+"\n"+"ENG"+Integer.toString(cENG)+"\n"+"HS"+Integer.toString(cHS)+"\n"+"HUMANS"+Integer.toString(cHUMANS)+"\n"+"IT"+Integer.toString(cIT)+"\n"+"MARITIME"+Integer.toString(cMARITIME)+"\n"+"ADM"+Integer.toString(cADM)+"\n");
        TextView resultView = (TextView) findViewById(R.id.orientated);
        TextView orientedDesc = (TextView) findViewById(R.id.whatoriented);
//        resultView.setText(sb.toString());

        int highestC;
        highestC = Math.max(cAS, Math.max(cE, Math.max(cBMGT, Math.max(cENG, Math.max(cHS, Math.max(cHUMANS, Math.max(cIT, Math.max(cMARITIME, cADM))))))));
        if(cAS==highestC) {
            StringBuilder resultOriented = new StringBuilder("Applied Science");
            resultView.setText("you are\n"+resultOriented+"\noriented!");
            orientedDesc.setText(descAS);
            orientation = "Applied Science";
        }
        else if(cE==highestC) {
            StringBuilder resultOriented = new StringBuilder("Environmental");
            resultView.setText("you are\n"+resultOriented+"\noriented!");
            orientedDesc.setText(descE);
            orientation = "Built Environment";
        }
        else if(cBMGT==highestC) {
            StringBuilder resultOriented = new StringBuilder("Business");
            resultView.setText("you are\n"+resultOriented+"\noriented!");
            orientedDesc.setText(descBMGT);
            orientation = "Business & Management";
        }
        else if(cENG==highestC) {
            StringBuilder resultOriented = new StringBuilder("Engineering");
            resultView.setText("you are\n"+resultOriented+"\noriented!");
            orientedDesc.setText(descENG);
            orientation = "Engineering";
        }
        else if(cHS==highestC) {
            StringBuilder resultOriented = new StringBuilder("Health Science");
            resultView.setText("you are\n"+resultOriented+"\noriented!");
            orientedDesc.setText(descHS);
            orientation = "Health Science";
        }
        else if(cHUMANS==highestC) {
            StringBuilder resultOriented = new StringBuilder("Humanities");
            resultView.setText("you are\n"+resultOriented+"\noriented!");
            orientedDesc.setText(descHUMANS);
            orientation = "Humanities";
        }
        else if(cIT==highestC) {
            StringBuilder resultOriented = new StringBuilder("IT");
            resultView.setText("you are\n"+resultOriented+"\noriented!");
            orientedDesc.setText(descIT);
            orientation = "Information & Digital Technologies";
        }
        else if(cMARITIME==highestC) {
            StringBuilder resultOriented = new StringBuilder("Maritime");
            resultView.setText("you are\n"+resultOriented+"\noriented!");
            orientedDesc.setText(descMARITIME);
            orientation = "Maritime Studies";
        }
        else if(cADM==highestC) {
            StringBuilder resultOriented = new StringBuilder("Arts and Design");
            resultView.setText("you are\n"+resultOriented+"\noriented!");
            orientedDesc.setText(descADM);
            orientation = "Media & Design";
        }

        /*
        recyclerView =(RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        courseList = new ArrayList<>();

        courseList.add(new Course("N01", "Computer Engineering", null, "SCSE", "NTU", null, 12, "www.test.com", 0));
        courseList.add(new Course("N02", "Computer Science", null, "SCSE", "NTU", null, 13, "www.test.com", 0));
        courseList.add(new Course("N03", "Computer Whatever", null, "SCSE", "NTU", null, 14, "www.test.com", 0));

        CourseAdapter adapter = new CourseAdapter(this, courseList);

        recyclerView.setAdapter(adapter);
        */
        seeRecommended = (Button) findViewById(R.id.seeCourses);
        seeRecommended.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRecommendedCourses(orientation);
            }
        });

    }
    public void openRecommendedCourses(String orientation){
        Intent intent = new Intent(this, RecommendedCourses.class);
        intent.putExtra("orientation", orientation);
        startActivity(intent);
    }
}
