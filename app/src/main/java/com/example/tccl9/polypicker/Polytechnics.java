package com.example.tccl9.polypicker;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Polytechnics extends AppCompatActivity {

    Button viewLocations;
    ImageButton webNP, webSP, webNYP, webTP, webRP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.polytechnics);

        viewLocations = (Button) findViewById(R.id.btnLocation);
        viewLocations.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openLocations();
            }
        });

        webNP = (ImageButton) findViewById(R.id.btnNP);
        webNP.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                browserNP(v);
            }
        });
        webSP = (ImageButton) findViewById(R.id.btnSP);
        webSP.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                browserSP(v);
            }
        });
        webTP = (ImageButton) findViewById(R.id.btnTP);
        webTP.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                browserTP(v);
            }
        });
        webNYP = (ImageButton) findViewById(R.id.btnNYP);
        webNYP.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                browserNYP(v);
            }
        });
        webRP = (ImageButton) findViewById(R.id.btnRP);
        webRP.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                browserRP(v);
            }
        });

    }

    private void browserSP(View v) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.sp.edu.sg/"));
        startActivity(browserIntent);
    }

    private void browserTP(View v) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.tp.edu.sg/"));
        startActivity(browserIntent);
    }

    private void browserNYP(View v) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.nyp.edu.sg/"));
        startActivity(browserIntent);
    }

    private void browserRP(View v) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.rp.edu.sg/"));
        startActivity(browserIntent);
    }

    private void browserNP(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.np.edu.sg/Pages/default.aspx"));
        startActivity(browserIntent);
    }

    public void openLocations(){
        Intent intent = new Intent(this, Polytechnic_Maps.class);
        startActivity(intent);
    }
}
