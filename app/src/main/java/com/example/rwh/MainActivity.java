package com.example.rwh;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ArrayAdapter adapter;
    private String [] activities;
    public static final String EXTRA = "com.example.project.MESSAGE";
    public static final String TAG = "Projekti";
    private ListAdapter latenAdapter;
    private ListView latenListView;
    private Intent ruokailuActivity;
    private Intent urheiluActivity;
    private TextView henkiloView;
    private String hoho;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ruokailuActivity = new Intent(MainActivity.this,RuokailuActivity.class);
        urheiluActivity =  new Intent(MainActivity.this,UrheiluActivity.class);

        String [] activities = {"Ruokailu suoritukset", "Urheilu suoritukset"};

        latenAdapter = new ArrayAdapter<String>(this,    /*CONVERTER*/
                android.R.layout.simple_list_item_1,
                activities)
        ;

        latenListView = findViewById(R.id.lista);
        latenListView.setAdapter(latenAdapter);

        Henkilo tyyppi = new Henkilo(170, 75, 40, 1000, 500);

        henkiloView = findViewById(R.id.henkiloView);

        henkiloView.setText(String.valueOf(tyyppi.getIka()*tyyppi.getRuokaKalorit()-tyyppi.getUrheiluKalorit()));


        latenListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int i, long Id) {
                        Log.d(TAG, "onItemClick(" + i + ")");
                        /*String activities = */String.valueOf(parent.getItemAtPosition(i));

                        //nextActivity.putExtra(EXTRA, i);
                        if(i == 0) {
                            startActivity(ruokailuActivity);
                        } else {
                            startActivity(urheiluActivity);
                        }
                    }
                });

    }
}