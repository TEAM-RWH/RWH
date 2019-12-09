package com.example.rwh;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.rwh.OverallPattern.getInstance;

public class RuokailuActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner ateriaSpinner;
    private DatePickerDialog.OnDateSetListener setListener;
    public static final String TAG = "Ruokailulista";
    private String valittuAteriaLuokalle, aterianPvmluokalle, aterian_pvm;
    private ArrayList<String> valittujenRuokienLista;
    private ArrayList<String> paivamaarat2;
    private ArrayAdapter pvmAdapter;
    private ListView listView;
    public String valittuAteriaMetodille;
    public static final String EXTRA = "123";
    public static final String EXTRA_MESSAGE = "com.example.rwh.MESSAGE";
    private EditText aterianKalorit;
    private Button ruokaLista;
    private int j;
    //private Multimap<String, ArrayList<Ateria>> paivat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ruokailu);

        Intent intent = getIntent();
        j = intent.getIntExtra(EXTRA, 0);

        getSupportActionBar().setTitle("Ruokailut: " + getInstance().henkilot.get(j).getNimi());

        aterianKalorit = findViewById(R.id.aterian_kalorit);

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
            if(ateriaSpinner.getSelectedItem().toString().equals("aamupala")){
                getInstance().paivamaarat.get(j).setAamupala(Integer.valueOf(aterianKalorit.getText().toString()));
            }

            tyhjennaValinnat();
            //tallennaValinnat();
            //lataaVanhatValinnat();
            //asetaVanhatTiedot();
        }
    }

    public void tyhjennaValinnat() {
        aterianKalorit.getText().clear();
        ateriaSpinner.setSelection(0);

    }

    /*public void tallennaValinnat() {
        SharedPreferences sharedPreferences = getSharedPreferences("Shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
        String json = gson.toJson(getInstance().aterioidenLista);
        String json2 = gson.toJson(getInstance().paivienLista);

        editor.putString("aterioiden lista", json);
        editor.putString("paivien lista", json2);
        editor.apply();
    }

    public void lataaVanhatValinnat() {

        SharedPreferences sharedPreferences = getSharedPreferences("Shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        Gson gson2 = new Gson();

        String json = sharedPreferences.getString("aterioiden lista", null);
        String json2 = sharedPreferences.getString("paivien lista", null);


        Type type = new TypeToken<ArrayList<ArrayList<Ateria>>>(){
        }.getType();
        getInstance().aterioidenLista = gson.fromJson(json, type);

        Type type2 = new TypeToken<ArrayList<ArrayList<Pvm>>>(){
        }.getType();
        getInstance().paivienLista = gson2.fromJson(json2,type2);

    }*/

    public void asetaTiedot() {
        //ruoan tarkistaminen
        final String[] ruoat = new String[]{"omena", "banaani", "leipä", "Lasi maitoa", "Juustoa",
                "asd", "fafa", "fjdalkfn", "fdaa", "fafa", "dasfaf", "fafdaf", "bsfbfdb", "gdshdhg",
                "dhshgs", "gdsgs", "shgsf", "dsgfsh"};

        AutoCompleteTextView editText = findViewById(R.id.tarkistaKalorit);
        ArrayAdapter<String> ruokaAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ruoat);
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

        /*Ruokavalikon luominen;

        ruokaLista = findViewById(R.id.ruokalista);
        ruokaLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(RuokailuActivity.this);
                //String array alert dialogin monivalinta vaihtoehdoille
                final String[] ruoat = new String[]{"omena", "banaani", "leipä", "Lasi maitoa", "Juustoa",
                        "asd", "fafa", "fjdalkfn", "fdaa", "fafa", "dasfaf", "fafdaf", "bsfbfdb", "gdshdhg",
                        "dhshgs", "gdsgs", "shgsf", "dsgfsh"};

                //ruoat arrayn muunto listaksi
                final List<String> ruokienLista = Arrays.asList(ruoat);

                //AlertDialogin otsikon asettaminen
                builder.setTitle("Ruokienlista:");

                builder.setItems(ruoat, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int item) {
                        String selectedText = ruoat[item].toString();  //Selected item in listview
                    }
                });

                //valintojen peruminen
                builder.setNeutralButton("Takaisin", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //koodataan mitä tapahtuu, jos peruuttaa valinnat
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });*/

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

}