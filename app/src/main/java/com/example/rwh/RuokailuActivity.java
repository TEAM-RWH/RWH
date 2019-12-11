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
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static com.example.rwh.OverallPattern.getInstance;

public class RuokailuActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner ateriaSpinner;
    public static final String TAG = "Ruokailulista";
    public static final String EXTRA = "123";
    private EditText aterianKalorit;
    private AutoCompleteTextView tarkistaKalorit;
    private int j;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ruokailu);

        Intent intent = getIntent();
        j = intent.getIntExtra(EXTRA, 0);

        getSupportActionBar().setTitle("Ruokailut: " + getInstance().henkilot.get(j).getNimi());

        aterianKalorit = findViewById(R.id.aterian_kalorit);
        tarkistaKalorit = findViewById(R.id.tarkistaKalorit);
        asetaTiedot();
        //lataaRuokalista();

    }

    public void lisaaAteriaPaiva(View v) {
        String valinta = "Valitse ateria:";

        if (aterianKalorit.getText().equals(toString())) {
            Toast.makeText(getApplicationContext(), "Syöttämäsi arvo ei ole numero!", Toast.LENGTH_SHORT).show();
        } else if (ateriaSpinner.getSelectedItem().toString().equals(valinta) || aterianKalorit.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Tee kaikki valinnat!", Toast.LENGTH_SHORT).show();
        } else {
            if(ateriaSpinner.getSelectedItem().toString().equals("aamupala")){
                getInstance().paivamaarat.get(j).setAamupala(Integer.valueOf(aterianKalorit.getText().toString()));


                tallennaTiedot();
            } else if (ateriaSpinner.getSelectedItem().toString().equals("Lounas")){
                getInstance().paivamaarat.get(j).setLounas(Integer.valueOf(aterianKalorit.getText().toString()));
                tallennaTiedot();

            } else if (ateriaSpinner.getSelectedItem().toString().equals("Välipala")){
                getInstance().paivamaarat.get(j).setValipala(Integer.valueOf(aterianKalorit.getText().toString()));
                tallennaTiedot();

            } else if (ateriaSpinner.getSelectedItem().toString().equals("Päivällinen")){
                getInstance().paivamaarat.get(j).setPaivallinen(Integer.valueOf(aterianKalorit.getText().toString()));
                tallennaTiedot();

            } else if (ateriaSpinner.getSelectedItem().toString().equals("Iltapala")){
                getInstance().paivamaarat.get(j).setIllallinen(Integer.valueOf(aterianKalorit.getText().toString()));
                tallennaTiedot();
            }
            tyhjennaValinnat();
        }
    }

    public void tyhjennaValinnat() {
        aterianKalorit.getText().clear();
        ateriaSpinner.setSelection(0);
        tarkistaKalorit.getText().clear();
    }

    public void asetaTiedot() {

        //Ruoan tarkistaminen

        AutoCompleteTextView editText = findViewById(R.id.tarkistaKalorit);
        ArrayAdapter<String> ruokaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, OverallPattern.getInstance().getRuokalista());
        editText.setAdapter((ruokaAdapter));

        //Spinnervalikon luominen

        ateriaSpinner = findViewById(R.id.spinner_ateria);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Ateriat, R.layout.spinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ateriaSpinner.setAdapter(adapter);
        ateriaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (parent.getItemAtPosition(position).equals("Valitse ateria:")) {

                } else {
                    String valittuAteria = parent.getItemAtPosition(position).toString();
                    Toast.makeText(parent.getContext(), "Valitsit: " + valittuAteria, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String valittu_ateria = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(), valittu_ateria, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.info_menu, menu);
        return true;
    }

    // Asetetaan info menu action bariin

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Log.d(TAG, "Return to PaivamaaraActivity from RuokailuActivity");
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
            return true;
        }

        switch (item.getItemId()) {
            case R.id.item1:
                Toast.makeText(this, "Item 1 selected", Toast.LENGTH_SHORT).show();

                new AlertDialog.Builder(RuokailuActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setTitle("Info")
                        .setMessage("Valitse mikä ateria lisätään, ja syötä aterian sisältämä kalori määrä.\n\n" +
                                "Voit myös tarkistella yleisten tuotteiden kalorimääriä painamalla 'Tarkista ruoan kalorit' kenttää ja" +
                                " kirjoittamalla hakukenttään tuotteen.")
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
    }

    public void tallennaTiedot(){
        SharedPreferences sharedPreferences = getSharedPreferences("Shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(getInstance().paivamaarat);
        editor.putString("paivamaara lista", json);
        editor.apply();
    }

    public void tallennaRuokalista(){
        SharedPreferences sharedPreferences2 = getSharedPreferences("Shared preferences2", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences2.edit();
        Gson gson = new Gson();
        String json3 = gson.toJson(getInstance().getRuokalista());
        editor.putString("ruokalista", json3);
        editor.apply();
    }

    public void lataaRuokalista(){
        SharedPreferences sharedPreferences = getSharedPreferences("Shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json4 = sharedPreferences.getString("ruokalista", null);
        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();
        getInstance().ruokalista = gson.fromJson(json4, type);
    }

    public void lisaaRuokaListalle(View v){

        String uusiRuoka = tarkistaKalorit.getText().toString();
        if(tarkistaKalorit.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(), "Kirjoita ruoan nimi Tarkista ruoan kalorit -kenttään ja paina lisää ruoka!", Toast.LENGTH_SHORT).show();
        } else if(getInstance().ruokalista.contains(uusiRuoka)){
            tarkistaKalorit.getText().clear();
            Toast.makeText(getApplicationContext(), "Ruoka on jo listalla!", Toast.LENGTH_SHORT).show();
        }else{
            getInstance().ruokalista.add(uusiRuoka);
            tarkistaKalorit.getText().clear();
            Toast.makeText(getApplicationContext(), "Uusi ruoka lisätty", Toast.LENGTH_SHORT).show();
            tallennaRuokalista();
        }
    }

    public void poistaRuokaListalta(View v){

        int tuoteIndex = Integer.valueOf(getInstance().ruokalista.indexOf(tarkistaKalorit.getText().toString()));
        if(tarkistaKalorit.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Kirjoita ruoan nimi Tarkista ruoan kalorit -kenttään ja paina poista ruoka!", Toast.LENGTH_SHORT).show();
        }else if( tuoteIndex < 213){
            Toast.makeText(getApplicationContext(), "Et voi poistaa valitsemaasi ruokaa!", Toast.LENGTH_SHORT).show();
        }else{
            int poistettava = getInstance().ruokalista.indexOf(tarkistaKalorit.getText().toString());
            getInstance().ruokalista.remove(poistettava);
            Toast.makeText(getApplicationContext(), "Valitsemasi tuote poistettu!", Toast.LENGTH_SHORT).show();
        }
    }
}