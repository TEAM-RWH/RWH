package com.example.rwh;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.ArrayList;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private ArrayAdapter adapter;
    private String[] activities;
    public static final String EXTRA = "123";
    public static final String TAG = "MainActivity";
    private ListAdapter latenAdapter;
    private ListView latenListView;
    private Intent ruokailuActivity;
    private Intent urheiluActivity;
    private TextView nimiView;
    private TextView pituusView;
    private TextView painoView;
    private TextView ikaView;
    private TextView sukupuoliView;
    private TextView arvioView;
    private TextView testView;

    private String nimi;
    private int pituus;
    private int paino;
    private int ika;
    private String sukupuoli;
    private double arvoSukupuolelle;

    private String nimiToSave;
    private String pituusToSave;
    private String painoToSave;
    private String ikaToSave;
    private int i;

    private String hoho;
    public static final String SHARED_PREFS = "sharedPrefs";
    private DecimalFormat laskut = new DecimalFormat("###.##");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate being called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nimiView = (TextView) findViewById(R.id.nimiView);
        pituusView = (TextView) findViewById(R.id.pituusView);
        painoView = (TextView) findViewById(R.id.painoView);
        ikaView = (TextView) findViewById(R.id.ikaView);
        sukupuoliView = (TextView) findViewById(R.id.sukupuoliView);
        arvioView = (TextView) findViewById(R.id.arvioView);
        testView = (TextView) findViewById(R.id.testView);

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


        setValues();

        setInformation();

        setMuutokset();

        latenListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int indeksi, long Id) {
                        Log.d(TAG, "onItemClick(" + i + ")");
                        /*String activities = */
                        String.valueOf(parent.getItemAtPosition(indeksi));

                        //saveData();
                        if (indeksi == 0) {
                            Toast.makeText(getApplicationContext(), "Ruokailu activity for user: " + nimi, Toast.LENGTH_LONG).show();
                            ruokailuActivity.putExtra(EXTRA, i);
                            startActivity(ruokailuActivity);

                        } else {
                            Toast.makeText(getApplicationContext(), "Urheilu activity for user: " + nimi, Toast.LENGTH_LONG).show();
                            urheiluActivity.putExtra(EXTRA, i);
                            startActivity(urheiluActivity);
                        }
                    }
                });

    }

    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("Shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(OverallPattern.getInstance().henkilot);
        editor.putString("henkilo lista", json);
        editor.apply();
    }

    /*private void updateUI() {

        nimiView.setText(String.valueOf(nimiToSave));
        pituusView.setText(String.valueOf(pituusToSave));
        painoView.setText(String.valueOf(painoToSave));
        ikaView.setText(String.valueOf(ikaToSave));

    }*/

    private void setInformation() {
        getSupportActionBar().setTitle("Käyttäjätiedot: " + nimi);

        nimiView.setText("Käyttäjän nimi: " + nimi);
        pituusView.setText("Käyttäjän pituus: " + pituus + " cm");
        painoView.setText("Käyttäjän paino: " + paino + " kg");
        ikaView.setText("Käyttäjän ikä: " + ika + " vuotta");
        sukupuoliView.setText("Sukupuoli: " + sukupuoli);

        if (sukupuoli.equals("Nainen")) {
            arvioView.setText("Arvioitu energiantarpeesi on " + laskut.format(447.593 + ((9.247 * paino) + (3.098 * pituus) - (4.330 * ika)) * 1.5)
                    + " kcal/pv.");
        } else {
            arvioView.setText("Arvioitu energiantarpeesi on " + laskut.format(88.362 + ((13.397 * paino) + (4.799 * pituus) - (5.677 * ika)) * 1.5)
                    + " kcal/pv.");
        }

    }

    private void setValues() {
        nimi = OverallPattern.getInstance().henkilot.get(i).getNimi();
        pituus = OverallPattern.getInstance().henkilot.get(i).getPituus();
        paino = OverallPattern.getInstance().henkilot.get(i).getPaino();
        ika = OverallPattern.getInstance().henkilot.get(i).getIka();
        sukupuoli = OverallPattern.getInstance().henkilot.get(i).getSukupuoli();
    }

    public void onPause(){
        Log.d(TAG, "onPause being called");
        super.onPause();
        saveData();
    }

    private void setMuutokset(){
        painoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Clicked " + painoView.getText(), Toast.LENGTH_LONG).show();

            }
        });

        painoView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(getApplicationContext(), "Long Click", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Muokkaa painoa");
                alert.setMessage("Syötä uusi paino:");

                // Set an EditText view to get user input
                final EditText input = new EditText(MainActivity.this);
                alert.setView(input);
                input.setInputType(InputType.TYPE_CLASS_NUMBER);

                alert.setPositiveButton("Muuta", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        // Do something with value!
                        int saatuArvo = Integer.parseInt(input.getText().toString());
                        OverallPattern.getInstance().henkilot.get(i).setPaino(saatuArvo);
                        //Toast.makeText(getApplicationContext(), String.valueOf(OverallPattern.getInstance().henkilot.get(i).getPaino()), Toast.LENGTH_LONG).show();
                        setValues();
                        setInformation();
                        saveData();
                        testView.setText(String.valueOf(OverallPattern.getInstance().henkilot.get(i).getPaino()));
                    }
                });

                alert.setNegativeButton("Peruuta", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Canceled.
                    }
                });

                alert.show();

                return true;
            }
        });

        nimiView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(getApplicationContext(), "Long Click", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Muokkaa nimeä");
                alert.setMessage("Syötä uusi nimi:");

                // Set an EditText view to get user input
                final EditText input = new EditText(MainActivity.this);
                alert.setView(input);
                input.setInputType(InputType.TYPE_CLASS_TEXT);

                alert.setPositiveButton("Muuta", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        // Do something with value!
                        String saatuArvo = (input.getText().toString());
                        OverallPattern.getInstance().henkilot.get(i).setNimi(saatuArvo);
                        //Toast.makeText(getApplicationContext(), String.valueOf(OverallPattern.getInstance().henkilot.get(i).getPaino()), Toast.LENGTH_LONG).show();
                        setValues();
                        setInformation();
                        saveData();
                        testView.setText(String.valueOf(OverallPattern.getInstance().henkilot.get(i).getNimi()));
                    }
                });

                alert.setNegativeButton("Peruuta", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Canceled.
                    }
                });

                alert.show();

                return true;
            }
        });


    }
}