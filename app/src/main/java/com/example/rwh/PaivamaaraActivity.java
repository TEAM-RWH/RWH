package com.example.rwh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import static com.example.rwh.OverallPattern.getInstance;

public class PaivamaaraActivity extends AppCompatActivity {

    private int j;
    public static final String EXTRA = "123";
    public static final String EXTRA2 = "PaivamaaraActivity";
    public static final String TAG = "PaivamaaraActivity";

    private String[] activities;

    private Intent ruokailuActivity;
    private Intent urheiluActivity;

    private ListView list;
    private ArrayAdapter adapter;

    private TextView paivamaaraView;
    private TextView aamupalaView;
    private TextView lounasView;
    private TextView valipalaView;
    private TextView paivallinenView;
    private TextView illallinenView;
    private TextView testView;
    private Spinner spinnerAktiivisuus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"onCreate being called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paivamaara);



        Intent intent = getIntent();
        j = intent.getIntExtra(EXTRA2, 0);

        getSupportActionBar().setTitle("" + getInstance().paivamaarat.get(j).getPaivamaara());

        paivamaaraView = (TextView) findViewById(R.id.paivamaaraView);
        aamupalaView = (TextView) findViewById(R.id.aamupalaView);
        lounasView = (TextView) findViewById(R.id.lounasView);
        valipalaView = (TextView) findViewById(R.id.valipalaView);
        paivallinenView = (TextView) findViewById(R.id.paivallinenView);
        illallinenView = (TextView) findViewById(R.id.illallinenView);
        testView = (TextView) findViewById(R.id.testView);

        spinnerAktiivisuus = findViewById(R.id.spinnerAktiivisuus);


        ruokailuActivity = new Intent(PaivamaaraActivity.this, RuokailuActivity.class);
        urheiluActivity = new Intent(PaivamaaraActivity.this, UrheiluActivity.class);

        String[] activities = {"Ruokailu suoritukset", "Urheilu suoritukset"};

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                activities)
        ;

        list = findViewById(R.id.lista);
        list.setAdapter(adapter);

        asetaTiedot();

        /*list.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int indeksi, long Id) {
                        Log.d(TAG, "onItemClick(" + indeksi + ")");
                        String.valueOf(parent.getItemAtPosition(indeksi));

                        //saveData();
                        if (indeksi == 0) {
                            //Toast.makeText(getApplicationContext(), "Ruokailu activity for user: " + nimi, Toast.LENGTH_LONG).show();
                            ruokailuActivity.putExtra(EXTRA, j);
                            startActivity(ruokailuActivity);

                        } else {
                            //Toast.makeText(getApplicationContext(), "Urheilu activity for user: " + nimi, Toast.LENGTH_LONG).show();
                            urheiluActivity.putExtra(EXTRA, j);
                            startActivity(urheiluActivity);
                        }
                    }
                });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Aktiivisuus, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAktiivisuus.setAdapter(adapter);
        spinnerAktiivisuus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),"Painoit " + position, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        testView.setText("" + getInstance().paivamaarat.get(j).getSukupuoli());
        paivamaaraView.setText("Saadut kalorit " + OverallPattern.getInstance().paivamaarat.get(j).getPaivamaara());
        if(OverallPattern.getInstance().paivamaarat.get(j).getAamupala() != 0) {
        aamupalaView.setText("Aamupalasta saadut kalorit: " + OverallPattern.getInstance().paivamaarat.get(j).getAamupala() + " kcal");
        }
        if(OverallPattern.getInstance().paivamaarat.get(j).getLounas() != 0) {
        lounasView.setText("Lounaasta saadaut kalorit: " + OverallPattern.getInstance().paivamaarat.get(j).getLounas() + " kcal");
        }
        if(OverallPattern.getInstance().paivamaarat.get(j).getValipala() != 0) {
        valipalaView.setText("Välipalasta saadut kalorit: " + OverallPattern.getInstance().paivamaarat.get(j).getValipala() + " kcal");
        }
        if(OverallPattern.getInstance().paivamaarat.get(j).getPaivallinen() != 0) {
        paivallinenView.setText("Päivällisestä saadut kalorit: " + OverallPattern.getInstance().paivamaarat.get(j).getPaivallinen() + " kcal");
        }
        if(OverallPattern.getInstance().paivamaarat.get(j).getIllallinen() != 0) {
        illallinenView.setText("Iltapalasta saadut kalorit: " + OverallPattern.getInstance().paivamaarat.get(j).getIllallinen() + " kcal");
        }*/

        if(OverallPattern.getInstance().paivamaarat.get(j).getAamupala() == 0){

        }
        else{
            aamupalaView.setText(OverallPattern.getInstance().paivamaarat.get(j).getAamupala());
        }

    }

    public void onResume(){
        Log.d(TAG, "onResume being called");
        super.onResume();
        paivamaaraView.setText("Saadut kalorit " + OverallPattern.getInstance().paivamaarat.get(j).getPaivamaara());
        if(OverallPattern.getInstance().paivamaarat.get(j).getAamupala() != 0) {
            aamupalaView.setText("Aamupalasta saadut kalorit: " + OverallPattern.getInstance().paivamaarat.get(j).getAamupala() + " kcal");
        }
        if(OverallPattern.getInstance().paivamaarat.get(j).getLounas() != 0) {
            lounasView.setText("Lounaasta saadaut kalorit: " + OverallPattern.getInstance().paivamaarat.get(j).getLounas() + " kcal");
        }
        if(OverallPattern.getInstance().paivamaarat.get(j).getValipala() != 0) {
            valipalaView.setText("Välipalasta saadut kalorit: " + OverallPattern.getInstance().paivamaarat.get(j).getValipala() + " kcal");
        }
        if(OverallPattern.getInstance().paivamaarat.get(j).getPaivallinen() != 0) {
            paivallinenView.setText("Päivällisestä saadut kalorit: " + OverallPattern.getInstance().paivamaarat.get(j).getPaivallinen() + " kcal");
        }
        if(OverallPattern.getInstance().paivamaarat.get(j).getIllallinen() != 0) {
            illallinenView.setText("Iltapalasta saadut kalorit: " + OverallPattern.getInstance().paivamaarat.get(j).getIllallinen() + " kcal");
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.info_menu, menu);
        return true;
    }// Asetetaan info menu action bariin

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Log.d(TAG, "Return to main from PaivamaaraActivity");
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
            return true;
        }

        switch (item.getItemId()) {
            case R.id.item1:
                Toast.makeText(this, "Item 1 selected", Toast.LENGTH_SHORT).show();

                new AlertDialog.Builder(PaivamaaraActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setTitle("Info")
                        .setMessage("Valitse päivän aktiivisuustaso ja lisää ruokailu sekä urheilu suoritukset.\n\n" +
                                "1. Aktiivisuustaso 2. Kerroin 3. Kuvaus\n\n" +
                                "Täysi lepo | 1.0 | Sängyssä makaaminen, nukkuminen\n\n" +
                                "Kevyt | 1.3 | Kevyt työ, ei lainkaan vapaa-ajan aktiivisuutta\n\n" +
                                "Tavallinen | 1.5 | Kevyt työ, jonkin verran vapaa-ajan aktiivisuutta\n\n" +
                                "Kohtalainen | 1.9 | Kevyt työ ja kuntoliikuntaa (esim. tunnin juoksulenkki päivässä) tai keskiraskas työ\n\n" +
                                "Kova | 2.2 | Päivittäinen kova liikunta (esim. 2 tuntia uimista päivässä) tai fyysisesti raskas työ\n\n" +
                                "Erittäin kova | 2.5 | Ammattiurheilija, esim. kilpapyöräilijä\n\n")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), "Poistuttu info ruudusta",
                                        Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
                return true;
            case R.id.item2:
                Toast.makeText(this, "Item 2 selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item3:
                Toast.makeText(this, "Item 3 selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.subitem1:
                Toast.makeText(this, "Subitem 1 selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.subitem2:
                Toast.makeText(this, "Subitem 2 selected", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

        //return super.onOptionsItemSelected(item);
    } //Crash protection

    public void asetaTiedot(){
        list.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int indeksi, long Id) {
                        Log.d(TAG, "onItemClick(" + indeksi + ")");
                        String.valueOf(parent.getItemAtPosition(indeksi));

                        //saveData();
                        if (indeksi == 0) {
                            //Toast.makeText(getApplicationContext(), "Ruokailu activity for user: " + nimi, Toast.LENGTH_LONG).show();
                            ruokailuActivity.putExtra(EXTRA, j);
                            startActivity(ruokailuActivity);

                        } else {
                            //Toast.makeText(getApplicationContext(), "Urheilu activity for user: " + nimi, Toast.LENGTH_LONG).show();
                            urheiluActivity.putExtra(EXTRA, j);
                            startActivity(urheiluActivity);
                        }
                    }
                });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Aktiivisuus, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAktiivisuus.setAdapter(adapter);
        spinnerAktiivisuus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),"Painoit " + position, Toast.LENGTH_LONG).show();
                if(position == 0){
                    getInstance().paivamaarat.get(j).setAktiivisuustaso(1.0);
                    testView.setText("" + getInstance().paivamaarat.get(j).getAktiivisuustaso());
                    tallennaTiedot();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        testView.setText("" + getInstance().paivamaarat.get(j).getAktiivisuustaso());
        paivamaaraView.setText("Saadut kalorit " + OverallPattern.getInstance().paivamaarat.get(j).getPaivamaara());
        if(OverallPattern.getInstance().paivamaarat.get(j).getAamupala() != 0) {
            aamupalaView.setText("Aamupalasta saadut kalorit: " + OverallPattern.getInstance().paivamaarat.get(j).getAamupala() + " kcal");
        }
        if(OverallPattern.getInstance().paivamaarat.get(j).getLounas() != 0) {
            lounasView.setText("Lounaasta saadaut kalorit: " + OverallPattern.getInstance().paivamaarat.get(j).getLounas() + " kcal");
        }
        if(OverallPattern.getInstance().paivamaarat.get(j).getValipala() != 0) {
            valipalaView.setText("Välipalasta saadut kalorit: " + OverallPattern.getInstance().paivamaarat.get(j).getValipala() + " kcal");
        }
        if(OverallPattern.getInstance().paivamaarat.get(j).getPaivallinen() != 0) {
            paivallinenView.setText("Päivällisestä saadut kalorit: " + OverallPattern.getInstance().paivamaarat.get(j).getPaivallinen() + " kcal");
        }
        if(OverallPattern.getInstance().paivamaarat.get(j).getIllallinen() != 0) {
            illallinenView.setText("Iltapalasta saadut kalorit: " + OverallPattern.getInstance().paivamaarat.get(j).getIllallinen() + " kcal");
        }


    }

    public void asetaAktiivisuusKerroin(){
        if (spinnerAktiivisuus.getSelectedItem().toString().equals("1.0 Täysi lepo")){
            getInstance().paivamaarat.get(j).setAktiivisuustaso(1.0);
            //tallennaTiedot();
        } else if (spinnerAktiivisuus.getSelectedItem().toString().equals("1.3 Kevyt")){
            getInstance().paivamaarat.get(j).setAktiivisuustaso(1.3);
            //tallennaTiedot();
        } else if (spinnerAktiivisuus.getSelectedItem().toString().equals("1.5 Tavallinen")){
            getInstance().paivamaarat.get(j).setAktiivisuustaso(1.5);
            //tallennaTiedot();
        } else if (spinnerAktiivisuus.getSelectedItem().toString().equals("1.9 Kohtalainen")){
            getInstance().paivamaarat.get(j).setAktiivisuustaso(1.9);
            //tallennaTiedot();
        } else if (spinnerAktiivisuus.getSelectedItem().toString().equals("2.2 Kova")){
            getInstance().paivamaarat.get(j).setAktiivisuustaso(2.2);
            //tallennaTiedot();
        }else if (spinnerAktiivisuus.getSelectedItem().toString().equals("2.5 Erittäin kova")){
            getInstance().paivamaarat.get(j).setAktiivisuustaso(2.5);
            //tallennaTiedot();
        }
    }

    public void tallennaTiedot(){
        SharedPreferences sharedPreferences = getSharedPreferences("Shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(getInstance().paivamaarat);
        editor.putString("paivamaara lista", json);
        editor.apply();
    }

}
