package com.example.tccl9.polypicker;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

public class BrowseCatalogue extends AppCompatActivity {
    String[] list_Polytechnics = {"Singapore Polytechnic", "Ngee Ann Polytechnic", "Temasek Polytechnic", "Nanyang Polytechnic", "Republic Polytechnic"};
    String[] list_CourseCat = {"Applied Science", "Built Environment", "Business and Management", "Engineering", "Health Science", "Humanities", "Information & Digital Technologies", "Maritime Studies", "Media & Design"};
    boolean [] p_checked_items = new boolean[list_Polytechnics.length];
    boolean [] c_checked_items = new boolean[list_CourseCat.length];
    Button filterPoly, filterCourseCat;
    ArrayList<Integer> p_items_selected = new ArrayList<>();
    ArrayList<Integer> c_items_selected = new ArrayList<>();
    ArrayList<Course> courseList = new ArrayList<>();
    DatabaseControl courseDatabase;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browse_catalogue);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        courseDatabase = new DatabaseControl(this);
        courseList = courseDatabase.getAllCourses();
        //Toast.makeText(this, courseDatabase.getAllCourses().get(0).getName(), Toast.LENGTH_LONG).show();
        CourseAdapter adapter = new CourseAdapter(this, courseDatabase.getAllCourses());

        filterPoly = (Button) findViewById(R.id.btnPolyFilter);
        filterPoly.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {

                AlertDialog.Builder mybuilder = new AlertDialog.Builder((BrowseCatalogue.this));
                mybuilder.setTitle("Select Polytechnic");
                mybuilder.setMultiChoiceItems(list_Polytechnics, p_checked_items, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int index, boolean isChecked) {
                        if(isChecked) {
                            p_items_selected.add(index);
                        }
                        else{
                            p_items_selected.remove(index);
                        }
                    }});

                mybuilder.setCancelable(false);
                mybuilder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                     @Override
                    public void onClick(DialogInterface dialogInterface, int index){
                         String items = "";
                         for(int i = 0; i< p_items_selected.size(); i++){
                             items=items+list_Polytechnics[p_items_selected.get(i)];
                             if(i!= p_items_selected.size()-1){
                                 items=items+",";
                             }
                         }
                         if(p_items_selected.size()>0) {
                             TextView view_polyFilter = (TextView) findViewById(R.id.selectedPoly);
                             view_polyFilter.setVisibility(View.VISIBLE);
                             view_polyFilter.setText(String.valueOf(p_items_selected.size()) + " selected");
                             filterPoly.setBackgroundResource(R.drawable.buttonshape);
                         }
                         else{
                             TextView view_polyFilter = (TextView) findViewById(R.id.selectedPoly);
                             view_polyFilter.setVisibility(View.INVISIBLE);
                             filterPoly.setBackgroundResource(R.drawable.buttonshape_notselected);
                         }
                     }
                });

                mybuilder.setNegativeButton("Back", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i){
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog dialog = mybuilder.create();
                dialog.show();
            }
        });


        filterCourseCat = (Button) findViewById(R.id.btnCourseCatFilter);
        filterCourseCat.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {

                AlertDialog.Builder mybuilder = new AlertDialog.Builder((BrowseCatalogue.this));
                mybuilder.setTitle("Select Course Category");
                mybuilder.setMultiChoiceItems(list_CourseCat, c_checked_items, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int index, boolean isChecked) {
                        if(isChecked) {
                            c_items_selected.add(index);
                        }
                        else{
                            c_items_selected.remove(index);
                        }
                    }});

                mybuilder.setCancelable(false);
                mybuilder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int index){
                        String items = "";
                        for(int i = 0; i< c_items_selected.size(); i++){
                            items=items+list_CourseCat[c_items_selected.get(i)];
                            if(i!= c_items_selected.size()-1){
                                items=items+",";
                            }
                        }
                        if(c_items_selected.size()>0) {
                            TextView view_polyFilter = (TextView) findViewById(R.id.selectedCat);
                            view_polyFilter.setVisibility(View.VISIBLE);
                            view_polyFilter.setText(String.valueOf(c_items_selected.size()) + " selected");
                            filterCourseCat.setBackgroundResource(R.drawable.buttonshape);
                        }
                        else{
                            TextView view_polyFilter = (TextView) findViewById(R.id.selectedCat);
                            view_polyFilter.setVisibility(View.INVISIBLE);
                            filterPoly.setBackgroundResource(R.drawable.buttonshape_notselected);
                        }
                    }
                });

                mybuilder.setNegativeButton("Back", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i){
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog dialog = mybuilder.create();
                dialog.show();
            }
        });

        SeekBar cop = (SeekBar) findViewById(R.id.seekBar);
        final TextView copText = (TextView) findViewById(R.id.lbCOP);
        cop.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                copText.setText("Cut-Off Point: " + progress);
                copText.setVisibility(View.VISIBLE);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        recyclerView.setAdapter(adapter);
    }
}
