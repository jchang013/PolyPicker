package com.example.tccl9.polypicker;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Polytechnic_Maps extends AppCompatActivity {

    ImageButton locNP, locSP, locNYP, locTP, locRP;
    Button navigate;
    TextView viewLoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_polytechnic__maps);

        locNP = (ImageButton) findViewById(R.id.iBtnNP);
        locNP.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                TextView viewLoc = (TextView) findViewById(R.id.lbPolyLocation);
                viewLoc.setText("535 Clementi Rd, Singapore 599489");
            }
        });
        locSP = (ImageButton) findViewById(R.id.iBtnSP);
        locSP.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                TextView viewLoc = (TextView) findViewById(R.id.lbPolyLocation);
                viewLoc.setText("500 Dover Rd, Singapore 139651");
            }
        });
        locNYP = (ImageButton) findViewById(R.id.iBtnNYP);
        locNYP.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                TextView viewLoc = (TextView) findViewById(R.id.lbPolyLocation);
                viewLoc.setText("180 Ang Mo Kio Ave 8, Singapore 569830");
            }
        });
        locTP = (ImageButton) findViewById(R.id.iBtnTP);
        locTP.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                TextView viewLoc = (TextView) findViewById(R.id.lbPolyLocation);
                viewLoc.setText("21 Tampines Ave 1, Singapore 529757");
            }
        });
        locRP = (ImageButton) findViewById(R.id.iBtnRP);
        locRP.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                TextView viewLoc = (TextView) findViewById(R.id.lbPolyLocation);
                viewLoc.setText("9 Woodlands Ave 9, Singapore 738964");
            }
        });

        navigate = (Button) findViewById(R.id.btnNavigate);
        navigate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                TextView viewLoc = (TextView) findViewById(R.id.lbPolyLocation);
                String location = (String) viewLoc.getText();

                if(location.contains("535 Clementi Rd, Singapore 599489")) {
                    Uri.Builder directionsBuilder = new Uri.Builder()
                            .scheme("https")
                            .authority("www.google.com")
                            .appendPath("maps")
                            .appendPath("dir")
                            .appendPath("")
                            .appendQueryParameter("api", "1")
                            .appendQueryParameter("origin", "1.3483" + "," + "103.6831")
                            .appendQueryParameter("destination", "1.3321" + "," + "103.77447");

                    String url = directionsBuilder.build().toString();
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    intent.setPackage("com.google.android.apps.maps");
                    startActivity(intent);
                    //startActivity(new Intent(Intent.ACTION_VIEW, directionsBuilder.build()));
                }
                else if(location.equals("500 Dover Rd, Singapore 139651")) {
                    Uri.Builder directionsBuilder = new Uri.Builder()
                            .scheme("https")
                            .authority("www.google.com")
                            .appendPath("maps")
                            .appendPath("dir")
                            .appendPath("")
                            .appendQueryParameter("api", "1")
                            .appendQueryParameter("destination", "1.3099" + "," + "103.7775");

                    String url = directionsBuilder.build().toString();
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    intent.setPackage("com.google.android.apps.maps");
                    startActivity(intent);
                    //startActivity(new Intent(Intent.ACTION_VIEW, directionsBuilder.build()));
                }
                else if(location.equals("180 Ang Mo Kio Ave 8, Singapore 569830")) {
                    Uri.Builder directionsBuilder = new Uri.Builder()
                            .scheme("https")
                            .authority("www.google.com")
                            .appendPath("maps")
                            .appendPath("dir")
                            .appendPath("")
                            .appendQueryParameter("api", "1")
                            .appendQueryParameter("destination", "1.3801" + "," + "103.8490");

                    String url = directionsBuilder.build().toString();
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    intent.setPackage("com.google.android.apps.maps");
                    startActivity(intent);
                    //startActivity(new Intent(Intent.ACTION_VIEW, directionsBuilder.build()));
                }
                else if(location.equals("21 Tampines Ave 1, Singapore 529757")) {
                    Uri.Builder directionsBuilder = new Uri.Builder()
                            .scheme("https")
                            .authority("www.google.com")
                            .appendPath("maps")
                            .appendPath("dir")
                            .appendPath("")
                            .appendQueryParameter("api", "1")
                            .appendQueryParameter("destination", "1.3468" + "," + "103.9326");

                    String url = directionsBuilder.build().toString();
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    intent.setPackage("com.google.android.apps.maps");
                    startActivity(intent);
                    //startActivity(new Intent(Intent.ACTION_VIEW, directionsBuilder.build()));
                }
                else if(location.equals("9 Woodlands Ave 9, Singapore 738964")) {
                    Uri.Builder directionsBuilder = new Uri.Builder()
                            .scheme("https")
                            .authority("www.google.com")
                            .appendPath("maps")
                            .appendPath("dir")
                            .appendPath("")
                            .appendQueryParameter("api", "1")
                            .appendQueryParameter("destination", "1.4422" + "," + "103.7859");

                    String url = directionsBuilder.build().toString();
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    intent.setPackage("com.google.android.apps.maps");
                    startActivity(intent);
                    //startActivity(new Intent(Intent.ACTION_VIEW, directionsBuilder.build()));
                }
            }
        });

        Button call = (Button) findViewById(R.id.btnCall);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView viewLoc = (TextView) findViewById(R.id.lbPolyLocation);
                String location = (String) viewLoc.getText();
                String number="";


                    if(location.contains("535 Clementi Rd, Singapore 599489")) {
                        number="tel: 6463 1233";
                    }
                    else if(location.equals("500 Dover Rd, Singapore 139651")) {
                        number="tel: 6775 1133";
                    }
                    else if(location.equals("180 Ang Mo Kio Ave 8, Singapore 569830")) {
                        number="tel: 6451 5115";
                    }
                    else if(location.equals("21 Tampines Ave 1, Singapore 529757")) {
                        number="tel: 6780 4201";
                    }
                    else if(location.equals("9 Woodlands Ave 9, Singapore 738964")) {
                        number="tel: 6510 3000";
                    }
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse(number));
                startActivity(callIntent);

                if (ActivityCompat.checkSelfPermission(Polytechnic_Maps.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
            }
        });

        Button email = (Button) findViewById(R.id.btnMail);
        email.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                TextView viewLoc = (TextView) findViewById(R.id.lbPolyLocation);
                String location = (String) viewLoc.getText();
                String emailAddr="";

                if(location.contains("535 Clementi Rd, Singapore 599489")) {
                    emailAddr="admissions@np.edu.sg";
                }
                else if(location.equals("500 Dover Rd, Singapore 139651")) {
                    emailAddr="contactus@sp.edu.sg";
                }
                else if(location.equals("180 Ang Mo Kio Ave 8, Singapore 569830")) {
                    emailAddr="askNYP@nyp.edu.sg";
                }
                else if(location.equals("21 Tampines Ave 1, Singapore 529757")) {
                    emailAddr="admissions@tp.edu.sg";
                }
                else if(location.equals("9 Woodlands Ave 9, Singapore 738964")) {
                    emailAddr="RP-Admissions-Office@rp.edu.sg";
                }
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse(emailAddr));

                final String subject = "";
                final String body = "";

                final String uriText = "mailto:" + Uri.encode(emailAddr) +
                        "?subject=" + Uri.encode(subject) +
                        "&body=" + Uri.encode(body);

                emailIntent.setData(Uri.parse(uriText));
                startActivity(emailIntent);

            }
        });

    }
}
