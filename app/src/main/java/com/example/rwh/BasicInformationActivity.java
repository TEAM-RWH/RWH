package com.example.rwh;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class BasicInformationActivity extends AppCompatActivity {

    public static final String EXTRA = "123";
    public static final String TAG = "Henkilölista";

    private ArrayAdapter Adapter1;
    private ListView ListView1;

    private EditText editName;
    private EditText editPituus;
    private EditText editPaino;
    private EditText editIka;

    //private DecimalFormat laskut = new DecimalFormat("###.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_information);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        getSupportActionBar().setTitle("Valitse tai luo käyttäjä");

        editName = (EditText) findViewById(R.id.editName);
        editPituus = (EditText) findViewById(R.id.editPituus);
        editPaino = (EditText) findViewById(R.id.editPaino);
        editIka = (EditText) findViewById(R.id.editIka);

        loadData();


        Adapter1 = new ArrayAdapter<>(this,    /*CONVERTER*/
                android.R.layout.simple_list_item_1,
                OverallPattern.getInstance().getHenkilot())
        ;


        ListView1 = findViewById(R.id.kayttajaValikko);
        ListView1.setAdapter(Adapter1);


        ListView1.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int i, long Id) {
                        Log.d(TAG, "onItemClick(" + i + ")");
                        Intent nextActivity = new Intent(BasicInformationActivity.this, MainActivity.class);
                        nextActivity.putExtra(EXTRA, i);
                        nextActivity.putExtra("flag", "A");
                        startActivity(nextActivity);
                        Toast.makeText(getApplicationContext(), "You clicked user: " + Adapter1.getItem(i), Toast.LENGTH_SHORT).show();
                    }
                });

        ListView1.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long id) {
                        Log.d(TAG, "Long Click :D(" + i + ")");
                        /*muutetaan.setText("Long Click...");
                        Toast.makeText(getApplicationContext(), "Removed car: " + Adapter1.getItem(i), Toast.LENGTH_SHORT).show();
                        GlobalModel.getInstance().autot.remove(i);
                        Adapter1.notifyDataSetChanged();
                        ListView1.requestLayout();*/

                        final int which_item = i;

                        new AlertDialog.Builder(BasicInformationActivity.this)
                                .setIcon(android.R.drawable.ic_menu_delete)
                                .setTitle("Delete user")
                                .setMessage("Delete " + Adapter1.getItem(i) + "?")
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(getApplicationContext(), "Removed user: " + Adapter1.getItem(which_item),
                                                Toast.LENGTH_SHORT).show();
                                        OverallPattern.getInstance().henkilot.remove(which_item);
                                        Adapter1.notifyDataSetChanged();

                                    }
                                })
                                .setNegativeButton("No", null)
                                .show();

                        return true;
                    }

                }
        );

    }

    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("Shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(OverallPattern.getInstance().henkilot);
        editor.putString("henkilo lista", json);
        editor.apply();
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("Shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("henkilo lista", null);
        Type type = new TypeToken<ArrayList<Henkilo>>() {
        }.getType();
        OverallPattern.getInstance().henkilot = gson.fromJson(json, type);
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveData();
    }

    public void taytaLista(View v) {
        Log.d(TAG, "Lisätty listaan");

        OverallPattern.getInstance().henkilot.add(new Henkilo("Kristian", 175, 75, 35));
        OverallPattern.getInstance().henkilot.add(new Henkilo("Hanne", 165, 60, 30));
        OverallPattern.getInstance().henkilot.add(new Henkilo("Dmitri", 180, 70, 23));

        ListView1.setAdapter(Adapter1);
        Toast.makeText(getApplicationContext(), "Lista täytetty", Toast.LENGTH_SHORT).show();
    }

    public void lisaaKayttaja(View v) {

        Log.d(TAG, "Käyttäjä lisätty");

        if (!editName.getText().toString().trim().isEmpty() && !editPituus.getText().toString().trim().isEmpty() &&
                !editPaino.getText().toString().trim().isEmpty() && !editIka.getText().toString().trim().isEmpty()) {

            OverallPattern.getInstance().henkilot.add(new Henkilo(editName.getText().toString().trim(), Integer.valueOf(editPituus.getText().toString()),
                    Integer.valueOf(editPaino.getText().toString()), Integer.valueOf(editIka.getText().toString())));

            editName.getText().clear();
            editPituus.getText().clear();
            editPaino.getText().clear();
            editIka.getText().clear();

        } else {
            Toast.makeText(getApplicationContext(), "Fill all textfields!", Toast.LENGTH_SHORT).show();

        }
        ListView1.setAdapter(Adapter1);
    }
}
