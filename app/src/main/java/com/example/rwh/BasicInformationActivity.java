package com.example.rwh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Luo BasicInformationActivityn EnergyAgentille.
 * @version 1.0
 * @author Lauri Riikonen
 * @since 21.10.2019
 */
public class BasicInformationActivity extends AppCompatActivity {

    public static final String EXTRA = "123";
    public static final String TAG = "BasicInformation";

    private ArrayAdapter Adapter1;
    private ListView ListView1;
    //private TextView textView2;
    private EditText editName;
    private EditText editPituus;
    private EditText editPaino;
    private EditText editIka;
    private RadioGroup radioGroup;
    private int radioId;
    private RadioButton radioButton;
    //private DecimalFormat laskut = new DecimalFormat("###.##");

    /**
     * Asettaa perusnäkymän BasicInformationActivitylle.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate being called");
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_basic_information);

        //Asetetaan buttonit niin, että ne pysyvät paikallaan
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        //Asetetaan otsikko kyseiselle aktiviteetille
        getSupportActionBar().setTitle("Luo / valitse käyttäjä");

        editName = (EditText) findViewById(R.id.editName);
        editPituus = (EditText) findViewById(R.id.editPituus);
        editPaino = (EditText) findViewById(R.id.editPaino);
        editIka = (EditText) findViewById(R.id.editIka);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);

        //Tarkistetaan mikä radio button on valittuna
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                updateRadioButton();
                if (checkedId == R.id.muuButton) {
                    Toast.makeText(getApplicationContext(), "Valitse oikea sukupuoli", Toast.LENGTH_SHORT).show();
                }

            }
        });


        loadData();
        setData();

    }

    /**
     * Asetetaan info -nappi yläpalkkiin
     * @param menu
     * @return true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.info_menu, menu);
        return true;
    } // Asetetaan info menu action bariin

    /**
     * Luo AlertDialogin, kun painetaan info -nappia yläpalkissa, jossa kerrotaan kyseisen
     * aktiviteetin toiminnasta.
     * @param item
     * @return super.onOptionsItemSelected(item)
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                //Toast.makeText(this, "Item 1 selected", Toast.LENGTH_SHORT).show();

                new AlertDialog.Builder(BasicInformationActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setTitle("Info")
                        .setMessage("Täytä tekstikentät, valitse sukupuoli ja paina 'Lisää käyttäjä' luodaksesi uuden käyttäjän." +
                                "\n\nPitkä painallus käyttäjän poistamiseksi.")
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

    } //Asetetaan, jotta voidaan valita jokin toiminto action barista

    /**
     * Tallentaa luodun käyttäjän SharedPreferenceseihin.
     */
    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("Shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(OverallPattern.getInstance().henkilot);
        editor.putString("henkilo lista", json);
        editor.apply();
    } //Tallennetaan luotu käyttäjä sharedpreferensseihin, josta tiedot voi aina noutaa

    /**
     * Lataa käyttäjän SharedPreferenceseista.
     */
    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("Shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("henkilo lista", null);
        Type type = new TypeToken<ArrayList<Henkilo>>() {
        }.getType();
        OverallPattern.getInstance().henkilot = gson.fromJson(json, type);

        if (OverallPattern.getInstance().henkilot == null) {
            OverallPattern.getInstance().henkilot = new ArrayList<Henkilo>();
        }
    } //Tallennettun henkilön tiedot voidaan noutaa

    /**
     * Tallentaa luodun käyttäjän SharedPreferenceseihin onResume -tilassa.
     */
    @Override
    protected void onPause() {
        super.onPause();
        saveData();
    }

    /**
     * Lisää käyttäjän.
     * @param v
     */
    public void lisaaKayttaja(View v) {

        Log.d(TAG, "Käyttäjä yritetty lisätä");

        if (OverallPattern.getInstance().henkilot.size() < 1) {

            if (!editName.getText().toString().trim().isEmpty() && !editPituus.getText().toString().trim().isEmpty() &&
                    !editPaino.getText().toString().trim().isEmpty() && !editIka.getText().toString().trim().isEmpty() &&
                    !radioButton.getText().equals("Muu")) {

                OverallPattern.getInstance().henkilot.add(new Henkilo(editName.getText().toString().trim().substring(0, 1).toUpperCase() +
                        editName.getText().toString().substring(1).toLowerCase(), Integer.valueOf(editPituus.getText().toString()),
                        Integer.valueOf(editPaino.getText().toString()), Integer.valueOf(editIka.getText().toString()),
                        String.valueOf(radioButton.getText())));

                clearEditTexts();
                saveData();

            } else {
                if (radioButton.getText().equals("Muu")) {
                    Toast.makeText(getApplicationContext(), "Muu ei ole sukupuoli", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Täytä kaikki tekstikentät!", Toast.LENGTH_SHORT).show();
                }
            }
            ListView1.setAdapter(Adapter1);

        } else {
            Toast.makeText(getApplicationContext(), "Vain yksi käyttäjä sallittu", Toast.LENGTH_SHORT).show();
        }
    } //Metodi käyttäjän lisäämistä varten

    /**
     * Päivittää RadioButtonin tilan, jolla valitaan sukupuoli.
     */
    private void updateRadioButton() {
        radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);

    }

    /**
     * Tyhjentää EditText -kentät käyttäjän tietojen syöttämisen jälkeen.
     */
    private void clearEditTexts() {
        editName.getText().clear();
        editPituus.getText().clear();
        editPaino.getText().clear();
        editIka.getText().clear();
    } //Tyhjentää Edit Text kentät käyttäjän tietojen syöttämisen jälkeen

    /**
     * Asettaa ListViewiin adapterin, onItemClickListenerin ja onItemLongClickListenerin.
     */
    private void setData() {


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
                        Toast.makeText(getApplicationContext(), "Valitsit käyttäjän: " + Adapter1.getItem(i), Toast.LENGTH_SHORT).show();
                    }
                });

        ListView1.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long id) {
                        Log.d(TAG, "Long Click :D(" + i + ")");

                        final int which_item = i;

                        new AlertDialog.Builder(BasicInformationActivity.this)
                                .setIcon(android.R.drawable.ic_menu_delete)
                                .setTitle("Poista käyttäjä")
                                .setMessage("Poista " + Adapter1.getItem(i) + "?\n\n" +
                                        "Tämä poistaa käyttäjän ja sen tallennetut tiedot pysyvästi.")
                                .setPositiveButton("Kyllä", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(getApplicationContext(), "Poistettu käyttäjä: " + Adapter1.getItem(which_item),
                                                Toast.LENGTH_SHORT).show();
                                        OverallPattern.getInstance().henkilot.remove(which_item);
                                        Adapter1.notifyDataSetChanged();

                                    }
                                })
                                .setNegativeButton("Ei", null)
                                .show();

                        return true;
                    }

                }
        );

    } //Asetetaan ListViewiin adapteri, sekä onItemClickListener ja onItemLongClickListener

    /**
     * Asettaa ListViewiin adapterin, onItemClickListenerin ja onItemLongClickListenerin onResume -tilassa.
     */
    public void onResume() {
        Log.d(TAG, "onResume being Called");
        super.onResume();
        setData();
    }
}

