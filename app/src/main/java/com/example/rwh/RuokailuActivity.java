package com.example.rwh;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.rwh.OverallPattern.getInstance;

public class RuokailuActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner ateriaSpinner;
    public static final String TAG = "Ruokailulista";
    public static final String EXTRA = "123";
    private EditText aterianKalorit;
    private TextView testView;
    private int j;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ruokailu);

        Intent intent = getIntent();
        j = intent.getIntExtra(EXTRA, 0);

        getSupportActionBar().setTitle("Ruokailut: " + getInstance().paivamaarat.get(j).getPaivamaara());

        aterianKalorit = findViewById(R.id.aterian_kalorit);
        testView = findViewById(R.id.testView);

        //lataaVanhatValinnat();
        asetaTiedot();
    }

    public void lisaaAteriaPaiva(View v) {
        String valinta = "Valitse ateria:";

        if (aterianKalorit.getText().equals(toString())) {
            Toast.makeText(getApplicationContext(), "Syöttämäsi arvo ei ole numero!", Toast.LENGTH_SHORT).show();
        } else if (ateriaSpinner.getSelectedItem().toString().equals(valinta) || aterianKalorit.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Tee kaikki valinnat!", Toast.LENGTH_SHORT).show();
        } else {
            if (ateriaSpinner.getSelectedItem().toString().equals("Aamupala")){
                getInstance().paivamaarat.get(j).setAamupala(Integer.valueOf(aterianKalorit.getText().toString()));
                testView.setText(String.valueOf(getInstance().paivamaarat.get(j).getAamupala()));
                tallennaTiedot();
            } else if (ateriaSpinner.getSelectedItem().toString().equals("Lounas")){
                getInstance().paivamaarat.get(j).setLounas(Integer.valueOf(aterianKalorit.getText().toString()));
                testView.setText(String.valueOf(getInstance().paivamaarat.get(j).getLounas()));
                tallennaTiedot();
            } else if (ateriaSpinner.getSelectedItem().toString().equals("Välipala")){
                getInstance().paivamaarat.get(j).setValipala(Integer.valueOf(aterianKalorit.getText().toString()));
                testView.setText(String.valueOf(getInstance().paivamaarat.get(j).getValipala()));
                tallennaTiedot();
            } else if (ateriaSpinner.getSelectedItem().toString().equals("Päivällinen")){
                getInstance().paivamaarat.get(j).setPaivallinen(Integer.valueOf(aterianKalorit.getText().toString()));
                testView.setText(String.valueOf(getInstance().paivamaarat.get(j).getPaivallinen()));
                tallennaTiedot();
            } else if (ateriaSpinner.getSelectedItem().toString().equals("Iltapala")){
                getInstance().paivamaarat.get(j).setIllallinen(Integer.valueOf(aterianKalorit.getText().toString()));
                testView.setText(String.valueOf(getInstance().paivamaarat.get(j).getIllallinen()));
                tallennaTiedot();
            }

            tyhjennaValinnat();
        }
    }

    public void tyhjennaValinnat() {
        aterianKalorit.getText().clear();
        ateriaSpinner.setSelection(0);

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
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "Return to main from RuokailuActivity");
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
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