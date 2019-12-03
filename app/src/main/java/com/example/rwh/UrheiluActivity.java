package com.example.rwh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.text.DecimalFormat;

public class UrheiluActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TextView urheilulaji;
    private TextView urheilucaloriesView;
    private TextView kesto;
    private TextView kaloreitapoltettu;
    private TextView cheerup;
    int met;
    double kalorit;
    //private int i;
    public static final String TAG = "Urheilulista";
    public static final String EXTRA = "123";
    private DecimalFormat laskut = new DecimalFormat("###.##");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urheilu);
        getSupportActionBar().setTitle("Urheilu page");

        //Bundle bundle = getIntent().getExtras();
        //i = bundle.getInt(BasicInformationActivity.EXTRA, 0);

        urheilucaloriesView = findViewById(R.id.urheilucaloriesView);
        urheilucaloriesView.setText("Urheilu Suoritus");

        urheilulaji = findViewById(R.id.urheilulaji);
        //urheilulaji.setText("blabla");

        kesto = findViewById(R.id.kesto);
        //kesto.setText("blabla");


        Intent intent = getIntent();
        int i = intent.getIntExtra(EXTRA, 0);

        //Information about calorie burning calculations
        //This calculation relies on a key value known as a MET, which stands for metabolic equivalent.
        //One "MET" is "roughly equivalent to the energy cost of sitting quietly,"
        //according to the Compendium, and can be considered 1 kcal/kg/hour.
        //Since sitting quietly is one MET, a 70 kg person would burn 70 calories (kcal) if they sat quietly for an hour.
        //MET value multiplied by weight in kilograms tells you calories burned per hour (MET*weight in kg=calories/hour).
        //If you only want to know how many calories you burned in a half hour,
        //divide that number by two. If you want to know about 15 minutes, divide that number by four.

        kaloreitapoltettu = findViewById(R.id.kaloreitapoltettu);


        met = (OverallPattern.getInstance().henkilot.get(i).getPaino()); //laskee paljonko yksi MET on kyseiselle henkilölle.


        cheerup = findViewById(R.id.cheerup);
        cheerup.setText("Hyvin Menee!");


        Spinner spinner = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.urheilu, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        Spinner spinner2 = findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.kesto, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(this);
    }

    String sport;
    String time;

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spinner1: { // code for first spinner
                kaloreitapoltettu.setText(Double.toString(0));
                sport = parent.getItemAtPosition(position).toString();
                urheilulaji.setText(sport);
                kalorit = Calculations.sport(sport, time, met);
                kaloreitapoltettu.setText(laskut.format(kalorit) + " kcal");
                break;
                // code for first spinner. Depending on spinner.getselecteditem assign adapter to second spinner
            }
            case R.id.spinner2: { // code for second spinner
                kaloreitapoltettu.setText(Double.toString(0));
                time = parent.getItemAtPosition(position).toString();
                kesto.setText(time);
                kalorit = Calculations.sport(sport, time, met);
                kaloreitapoltettu.setText(laskut.format(kalorit) + " kcal");
                break;

                //Use get item selected and get selected item position
            }

        }
    }

    //String text = parent.getItemAtPosition(position).toString();
//        Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();
//        urheilulaji.setText(text);
//        kesto.setText(text);
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "Return to main from UrheiluActivity");
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*private double laskut(String sport, String time, double met) {
        int aika = 0;
        if (sport.equals("Kävely Birdwatching")) {
            if (time.equals("10 min")) {
                aika = 10;
            } else if (time.equals("20 min")) {
                aika = 20;
            } else if (time.equals("30 min")) {
                aika = 30;
            } else if (time.equals("40 min")) {
                aika = 40;
            } else if (time.equals("50 min")) {
                aika = 50;
            } else if (time.equals("60 min")) {
                aika = 60;
            }
            //2.5 met	bird watching, slow walk
            return (((met * 2.5) / 6) * aika);

        } else if (sport.equals("Hölkkä")) {
            if (time.equals("10 min")) {
                aika = 10;
            } else if (time.equals("20 min")) {
                aika = 20;
            } else if (time.equals("30 min")) {
                aika = 30;
            } else if (time.equals("40 min")) {
                aika = 40;
            } else if (time.equals("50 min")) {
                aika = 50;
            } else if (time.equals("60 min")) {
                aika = 60;
            }
            //7.0 met	jogging, general
            return (((met * 7.0) / 6) * aika);
        } else if (sport.equals("Juoksu")) {
            if (time.equals("10 min")) {
                aika = 10;
            } else if (time.equals("20 min")) {
                aika = 20;
            } else if (time.equals("30 min")) {
                aika = 30;
            } else if (time.equals("40 min")) {
                aika = 40;
            } else if (time.equals("50 min")) {
                aika = 50;
            } else if (time.equals("60 min")) {
                aika = 60;
            }
            //9.8	running, 6 mph (10 min/mile)
            return (((met * 9.8) / 6) * aika);
        } else if (sport.equals("PikaJuoksu")) {
            if (time.equals("10 min")) {
                aika = 10;
            } else if (time.equals("20 min")) {
                aika = 20;
            } else if (time.equals("30 min")) {
                aika = 30;
            } else if (time.equals("40 min")) {
                aika = 40;
            } else if (time.equals("50 min")) {
                aika = 50;
            } else if (time.equals("60 min")) {
                aika = 60;
            }
            // 19.0
            //running, 12 mph (5 min/mile)
            return (((met * 19.0) / 6) * aika);
        } else {
            return 0;
        }


    }*/
}


