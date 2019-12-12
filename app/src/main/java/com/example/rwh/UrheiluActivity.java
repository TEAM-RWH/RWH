package com.example.rwh;

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
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import com.google.gson.Gson;
import java.text.DecimalFormat;
import static com.example.rwh.OverallPattern.getInstance;

public class UrheiluActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TextView urheilulaji;
    private TextView urheilucaloriesView;
    private TextView kesto;
    private TextView kaloreitapoltettu;
    int met;
    private int j;
    double kalorit;
    //private int i;
    public static final String TAG = "Urheilulista";
    public static final String EXTRA = "123";
    private DecimalFormat laskut = new DecimalFormat("###.##");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urheilu);
        getSupportActionBar().setTitle("Urheilut: " + getInstance().paivamaarat.get(j).getPaivamaara());

        urheilucaloriesView = findViewById(R.id.urheilucaloriesView);
        urheilucaloriesView.setText("Urheilu Suoritus");

        urheilulaji = findViewById(R.id.urheilulaji);

        kesto = findViewById(R.id.kesto);

        Intent intent = getIntent();
        j = intent.getIntExtra(EXTRA, 0);

        //Information about calorie burning calculations
        //This calculation relies on a key value known as a MET, which stands for metabolic equivalent.
        //One "MET" is "roughly equivalent to the energy cost of sitting quietly,"
        //according to the Compendium, and can be considered 1 kcal/kg/hour.
        //Since sitting quietly is one MET, a 70 kg person would burn 70 calories (kcal) if they sat quietly for an hour.
        //MET value multiplied by weight in kilograms tells you calories burned per hour (MET*weight in kg=calories/hour).
        //If you only want to know how many calories you burned in a half hour,
        //divide that number by two. If you want to know about 15 minutes, divide that number by four.

        kaloreitapoltettu = findViewById(R.id.kaloreitapoltettu);


        met = (OverallPattern.getInstance().paivamaarat.get(j).getPaivanPaino()); //laskee paljonko yksi MET on kyseiselle henkilölle.



        Spinner spinner = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.urheilu, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        Spinner spinner2 = findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.kesto, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
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
                kaloreitapoltettu.setText("Poltettu: " + laskut.format(kalorit) + " kcal");
                break;
                // code for first spinner. Depending on spinner.getselecteditem assign adapter to second spinner
            }
            case R.id.spinner2: { // code for second spinner
                kaloreitapoltettu.setText(Double.toString(0));
                time = parent.getItemAtPosition(position).toString();
                kesto.setText(time);
                kalorit = Calculations.sport(sport, time, met);
                kaloreitapoltettu.setText("Poltettu: " + laskut.format(kalorit) + " kcal");
                break;

                //Use get item selected and get selected item position
            }

        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.info_menu, menu);
        return true;
    }// Asetetaan info menu action bariin

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "Return to main from UrheiluActivity");
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
            return true;
        }

        switch (item.getItemId()) {
            case R.id.item1:
                Toast.makeText(this, "Item 1 selected", Toast.LENGTH_SHORT).show();

                new AlertDialog.Builder(UrheiluActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setTitle("Info")
                        .setMessage("Valitse aktiviteetti sekä aktiviteetin kesto ja lisää suoritus painamalla 'Lisää urheilusuoritus' nappia\n\n" +
                                "Information about calorie burning calculations\n\n" +
                                "This calculation relies on a key value known as a MET, which stands for metabolic equivalent.\n\n" +
                                "One \"MET\" is \"roughly equivalent to the energy cost of sitting quietly, " +
                                "according to the Compendium, and can be considered 1 kcal/kg/hour.\n\n" +
                                "Since sitting quietly is one MET, a 70 kg person would burn 70 calories (kcal) if they sat quietly for an hour.\n\n" +
                                "MET value multiplied by weight in kilograms tells you calories burned per hour (MET*weight in kg=calories/hour).\n\n" +
                                "If you only want to know how many calories you burned in a half hour, " +
                                "divide that number by two. If you want to know about 15 minutes, divide that number by four.")
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

    }

    public void lisaaUrheiuSuoritus(View v){
        if(!urheilulaji.getText().equals("Valitse aktiviteetti")) {
            Toast.makeText(getApplicationContext(), "Urheilusuoritus lisätty", Toast.LENGTH_SHORT).show();
            OverallPattern.getInstance().paivamaarat.get(j).setPoltetutKalorit(kalorit);
            tallennaTiedot();
        } else {
            Toast.makeText(getApplicationContext(), "Valitse aktiviteetti!", Toast.LENGTH_SHORT).show();
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
