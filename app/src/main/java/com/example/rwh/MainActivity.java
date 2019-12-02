package com.example.rwh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ArrayAdapter adapter;
    private String[] activities;
    public static final String EXTRA = "123";
    public static final String TAG = "Projekti";
    private ListAdapter latenAdapter;
    private ListView latenListView;
    private Intent ruokailuActivity;
    private Intent urheiluActivity;
    private TextView nimiView;
    private TextView pituusView;
    private TextView painoView;
    private TextView ikaView;

    private String nimiToSave;
    private String pituusToSave;
    private String painoToSave;
    private String ikaToSave;
    private int i;

    private String hoho;
    public static final String SHARED_PREFS = "sharedPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate being called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Intent intent = getIntent();
        //String checkFlag = intent.getStringExtra("flag");

        nimiView = (TextView) findViewById(R.id.nimiView);
        pituusView = (TextView) findViewById(R.id.pituusView);
        painoView = (TextView) findViewById(R.id.painoView);
        ikaView = (TextView) findViewById(R.id.ikaView);

        ruokailuActivity = new Intent(MainActivity.this, RuokailuActivity.class);
        urheiluActivity = new Intent(MainActivity.this, UrheiluActivity.class);

        String[] activities = {"Ruokailu suoritukset", "Urheilu suoritukset"};

        latenAdapter = new ArrayAdapter<String>(this,    /*CONVERTER*/
                android.R.layout.simple_list_item_1,
                activities)
        ;

        latenListView = findViewById(R.id.lista);
        latenListView.setAdapter(latenAdapter);

        Bundle bundle = getIntent().getExtras();
        i = bundle.getInt(BasicInformationActivity.EXTRA, 0);


        getSupportActionBar().setTitle(OverallPattern.getInstance().henkilot.get(i).getNimi());

        nimiView.setText("Käyttäjän nimi: " + OverallPattern.getInstance().henkilot.get(i).getNimi());
        pituusView.setText("Käyttäjän pituus: " + String.valueOf(OverallPattern.getInstance().henkilot.get(i).getPituus()) + " cm");
        painoView.setText("Käyttäjän paino: " + String.valueOf(OverallPattern.getInstance().henkilot.get(i).getPaino()) + " kg");
        ikaView.setText("Käyttäjän ikä: " + String.valueOf(OverallPattern.getInstance().henkilot.get(i).getIka()) + " vuotta");


        latenListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int indeksi, long Id) {
                        Log.d(TAG, "onItemClick(" + i + ")");
                        /*String activities = */
                        String.valueOf(parent.getItemAtPosition(indeksi));

                        //
                        //saveData();
                        if (indeksi == 0) {
                            ruokailuActivity.putExtra(EXTRA, i);
                            startActivity(ruokailuActivity);
                            Toast.makeText(getApplicationContext(), "Ruokailu activity for user: " + OverallPattern.getInstance().henkilot.get(i).getNimi(), Toast.LENGTH_LONG).show();
                        } else {
                            startActivity(urheiluActivity);
                            Toast.makeText(getApplicationContext(), "Urheilu activity for user: " + OverallPattern.getInstance().henkilot.get(i).getNimi(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

    /*private void saveData() {

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("TEXT1", String.valueOf(nimiView.getText()));
        editor.putString("TEXT2", String.valueOf(pituusView.getText()));
        editor.putString("TEXT3", String.valueOf(painoView.getText()));
        editor.putString("TEXT4", String.valueOf(ikaView.getText()));
        editor.apply();

    }

    private void loadData() {

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        nimiToSave = sharedPreferences.getString("TEXT1", "");
        pituusToSave = sharedPreferences.getString("TEXT2", "");
        painoToSave = sharedPreferences.getString("TEXT3", "");
        ikaToSave = sharedPreferences.getString("TEXT4", "");

    }

    private void updateUI() {

        nimiView.setText(String.valueOf(nimiToSave));
        pituusView.setText(String.valueOf(pituusToSave));
        painoView.setText(String.valueOf(painoToSave));
        ikaView.setText(String.valueOf(ikaToSave));

    }*/
}