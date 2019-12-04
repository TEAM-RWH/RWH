package com.example.rwh;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class UrheiluActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TextView urheilulaji;
    private TextView urheilucaloriesView;
    private TextView kesto;
    private TextView kaloreitapoltettu;
    private TextView cheerup;
    private TextView urheilu_paiva;
    private String urheilunPvmluokalle;
    private String aterianPvmluokalle;
    private String valittuAteriaLuokalle;
    private ListView listView;
    private ArrayAdapter pvmAdapter;
    private DatePickerDialog.OnDateSetListener setListener;
    private ArrayList<String> valittujenRuokienLista, paivamaarat;
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

        //ListView luominen päiville
        pvmAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, OverallPattern.getInstance().getLiikunnat())
                ;

        listView = findViewById(R.id.urheilu_paivat);
        listView.setAdapter(pvmAdapter);

       /* listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int i, long Id) {
                        Log.d(TAG, "UrheiluActivityOnItemClick(" + i + ")");
                        Intent nextActivity = new Intent(UrheiluActivity.this, PaivanRuokailut.class);
                        nextActivity.putExtra(EXTRA, i);
                        //nextActivity.putExtra("flag", "A");
                        startActivity(nextActivity);
                        Toast.makeText(getApplicationContext(), "You clicked user: " + pvmAdapter.getItem(i), Toast.LENGTH_SHORT).show();
                    }
                });

        listView.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long id) {
                        Log.d(TAG, "Long Click :D(" + i + ")");


                        final int which_item = i;

                        new androidx.appcompat.app.AlertDialog.Builder(UrheiluActivity.this)
                                .setIcon(android.R.drawable.ic_menu_delete)
                                .setTitle("Poista päivä")
                                .setMessage("Poista " + pvmAdapter.getItem(i) + "?")
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(getApplicationContext(), "Removed user: " + pvmAdapter.getItem(which_item),
                                                Toast.LENGTH_SHORT).show();
                                        OverallPattern.getInstance().henkilot.remove(which_item);
                                        pvmAdapter.notifyDataSetChanged();

                                    }
                                })
                                .setNegativeButton("No", null)
                                .show();

                        return true;
                    }

                }
        );*/

        //Päivämäärävalitsimen luominen

        urheilu_paiva = findViewById(R.id.urheilu_pvm);
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        urheilu_paiva.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        UrheiluActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        setListener, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month+1;
                String date = day+"/"+month+"/"+year;
                urheilu_paiva.setText(date);
                urheilunPvmluokalle = date;
                //paivamaarat.add
            }
        };

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
    public void lisaaAteriaPaiva(View v){
        //ateriat.add();

        OverallPattern.getInstance().liikunnat.add(new Liikkuminen("laji", 10));
        //OverallPattern.getInstance().ateriat.add(new Ateria(valittuAteriaLuokalle,valittujenRuokienLista));
        //listView.setAdapter(pvmAdapter);

    }
}


