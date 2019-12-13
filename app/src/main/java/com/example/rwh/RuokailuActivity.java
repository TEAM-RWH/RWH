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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;


import static com.example.rwh.OverallPattern.getInstance;

/**
 * Luo Ruokailu Aktiviteetin Energy Agent sovellukselle
 * @version 1.0
 * @author Kristian Wink
 * @21.10.2019
 */

public class RuokailuActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner ateriaSpinner;
    public static final String TAG = "Ruokailulista";
    public static final String EXTRA = "123";
    private EditText aterianKalorit;
    private AutoCompleteTextView tarkistaKalorit;
    private int j;
    private ArrayAdapter<String> ruokaAdapter;
    private AutoCompleteTextView editText;
    /**
     * Noutaa käyttäjän nimen Overallpatterinista yläpalkkiin, käyttää metodia
     * lataaRuokalista() noutaakseen ruokalistan ja käyttää asetaTiedot metodia asettaakseen
     * arvot omille kohdilleen
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ruokailu);

        Intent intent = getIntent();
        j = intent.getIntExtra(EXTRA, 0);

        getSupportActionBar().setTitle("Ruokailut: " + getInstance().paivamaarat.get(j).getPaivamaara());

        aterianKalorit = findViewById(R.id.aterian_kalorit);
        tarkistaKalorit = findViewById(R.id.tarkistaKalorit);

        lataaRuokalista();
        asetaTiedot();
    }

    /**
     * Luo aterian määrätylle päivälle
     * @param v
     */

    public void lisaaAteriaPaiva(View v) {
        String valinta = "Valitse ateria:";

        if (aterianKalorit.getText().equals(toString())) {
            Toast.makeText(getApplicationContext(), "Syöttämäsi arvo ei ole numero!", Toast.LENGTH_SHORT).show();
        } else if (ateriaSpinner.getSelectedItem().toString().equals(valinta) || aterianKalorit.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Valitse ateria ja syötä kalorimäärä!", Toast.LENGTH_SHORT).show();
        } else {
            if (ateriaSpinner.getSelectedItem().toString().equals("Aamupala")){
                getInstance().paivamaarat.get(j).setAamupala(Double.valueOf(aterianKalorit.getText().toString()));
                Toast.makeText(getApplicationContext(), "Aamupala lisätty", Toast.LENGTH_SHORT).show();
                tallennaTiedot();
            } else if (ateriaSpinner.getSelectedItem().toString().equals("Lounas")){
                getInstance().paivamaarat.get(j).setLounas(Double.valueOf(aterianKalorit.getText().toString()));
                Toast.makeText(getApplicationContext(), "Lounas lisätty", Toast.LENGTH_SHORT).show();
                tallennaTiedot();
            } else if (ateriaSpinner.getSelectedItem().toString().equals("Välipala")){
                getInstance().paivamaarat.get(j).setValipala(Double.valueOf(aterianKalorit.getText().toString()));
                Toast.makeText(getApplicationContext(), "Välipala lisätty", Toast.LENGTH_SHORT).show();
                tallennaTiedot();
            } else if (ateriaSpinner.getSelectedItem().toString().equals("Päivällinen")){
                getInstance().paivamaarat.get(j).setPaivallinen(Double.valueOf(aterianKalorit.getText().toString()));
                Toast.makeText(getApplicationContext(), "Päivällinen lisätty", Toast.LENGTH_SHORT).show();
                tallennaTiedot();
            } else if (ateriaSpinner.getSelectedItem().toString().equals("Iltapala")){
                getInstance().paivamaarat.get(j).setIllallinen(Double.valueOf(aterianKalorit.getText().toString()));
                Toast.makeText(getApplicationContext(), "Iltapala lisätty", Toast.LENGTH_SHORT).show();
                tallennaTiedot();
            }

            tyhjennaValinnat();
        }
    }

    /**
     * Luo perusnäkymän aktiviteetille.
     */

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

    /**
     * Määrittää, mitä tapahtuu, kun listan indeksi on valittuna adapterissa.
     * @param adapterView
     * @param view
     * @param i
     * @param l
     */

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String valittu_ateria = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(), valittu_ateria, Toast.LENGTH_SHORT).show();
    }

    /**
     * Määrittää, mitä tapahtuu, kun mikään listan indekseistä ei ole valittuna adapterin listalla.
     * @param adapterView
     */

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    /**
     * Asetetaan info -nappi yläpalkkiin
     * @param menu
     * @return
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.info_menu, menu);
        return true;
    }// Asetetaan info menu action bariin

    /**
     * Luo AlertDialogin, kun painetaan info -nappia yläpalkissa, jossa kerrotaan kyseisen
     * aktiviteetin toiminnasta.
     * @param item
     * @return
     */

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


                new AlertDialog.Builder(RuokailuActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setTitle("Info")
                        .setMessage("Valitse mikä ateria lisätään, ja syötä aterian sisältämä kalori määrä ja paina Lisää ateria nappia\n\n" +
                                "Voit myös tarkistella yleisten tuotteiden kalorimääriä painamalla 'Etsi tai lisää tuote' kenttään ja" +
                                " kirjoittamalla hakukenttään tuotteen nimen.\n\n"+
                                "Omia tuotteita voi lisätä  ja poistaa kirjoittamalla tuotteen nimen ja kalorimäärän 'Etsi tai lisää tuote' kenttään" +
                                " ja painamalla 'Lisää oma tuote' tai 'Poista tuote' nappeja.")
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

    /**
     * Tallentaa muokatun päivämäärälistan SharedPreferenceseihin.
     */

    public void tallennaTiedot(){
        SharedPreferences sharedPreferences = getSharedPreferences("Shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(getInstance().paivamaarat);
        editor.putString("paivamaara lista", json);
        editor.apply();
    }

    /**
     * Tyhjentää täytetyt kentät RuokailuActivityssa.
     */

    public void tyhjennaValinnat() {
        aterianKalorit.getText().clear();
        ateriaSpinner.setSelection(0);
        tarkistaKalorit.getText().clear();
    }

    /**
     * Lisää käyttäjän nimeämän ruokatuotteen ruokalistalle.
     * @param v
     */

    public void lisaaRuokaListalle(View v){

        String uusiRuoka = tarkistaKalorit.getText().toString();
        if(tarkistaKalorit.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(), "Kirjoita ruoan nimi Etsi tai lisää tuote -kenttään ja paina Lisää ateria!", Toast.LENGTH_SHORT).show();
        } else if(getInstance().ruokalista.contains(uusiRuoka)){
            tarkistaKalorit.getText().clear();
            Toast.makeText(getApplicationContext(), "Ruoka on jo listalla!", Toast.LENGTH_SHORT).show();
        }else{
            getInstance().ruokalista.add(uusiRuoka);
            tarkistaKalorit.getText().clear();
            Toast.makeText(getApplicationContext(), "Uusi ruoka lisätty", Toast.LENGTH_SHORT).show();
        }
        tallennaRuokalista();
        paivitaTuoteLista();
    }

    /**
     * Poistaa käyttäjän nimeämän ruokatuotteen ruokalistalta.
     * @param v
     */

    public void poistaRuokaListalta(View v){

        int tuoteIndex = Integer.valueOf(getInstance().ruokalista.indexOf(tarkistaKalorit.getText().toString()));
        if(tarkistaKalorit.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Kirjoita ruoan nimi Etsi tai lisää tuote -kenttään ja paina Poista tuote!", Toast.LENGTH_SHORT).show();
        }else if( tuoteIndex < 213){
            Toast.makeText(getApplicationContext(), "Et voi poistaa valitsemaasi ruokaa!", Toast.LENGTH_SHORT).show();
        }else{
            int poistettava = getInstance().ruokalista.indexOf(tarkistaKalorit.getText().toString());
            getInstance().ruokalista.remove(poistettava);
            Toast.makeText(getApplicationContext(), "Valitsemasi tuote poistettu!", Toast.LENGTH_SHORT).show();
        }
        tallennaRuokalista();
        paivitaTuoteLista();
    }

    /**
     * Tallentaa muokatun ruokalistan, kun EnergyAgent menee onPause -tilaan.
     */

    @Override
    protected void onPause(){
        super.onPause();
        tallennaRuokalista();
    }

    /**
     * Tallentaa muokatun ruokalistan SharedPreferenceseihin.
     */

    public void tallennaRuokalista() {
        SharedPreferences sharedPreferences = getSharedPreferences("Shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson2 = new Gson();
        String json2 = gson2.toJson(OverallPattern.getInstance().ruokalista);
        editor.putString("ruokalista", json2);
        editor.apply();
    }

    /**
     * Lataa muokatun ruokalistan SharedPreferenceseista.
     */

    public void lataaRuokalista() {
        SharedPreferences sharedPreferences = getSharedPreferences("Shared preferences", MODE_PRIVATE);
        Gson gson2 = new Gson();
        String json2 = sharedPreferences.getString("ruokalista", null);
        Type type2 = new TypeToken<ArrayList<String>>() {
        }.getType();
        getInstance().ruokalista = gson2.fromJson(json2, type2);

        if(getInstance().ruokalista == null) {
            getInstance().ruokalista = new ArrayList<>();
            getInstance().ruokalista.add("Amerikansalaatti/jäävuorisalaatti 10 kcal/100g");
            getInstance().ruokalista.add("Herne pakaste 123 kcal/100g");
            getInstance().ruokalista.add("Herne-maissi-paprikasekoitus 69 kcal/100g");
            getInstance().ruokalista.add("Kaali (valkokaali, keräkaali) 28 kcal/100g");
            getInstance().ruokalista.add("Kesäkurpitsa, Zucchini 18 kcal/100g");
            getInstance().ruokalista.add("Kiinankaali 20 kcal/100g");
            getInstance().ruokalista.add("Kurkku 11 kcal/100g");
            getInstance().ruokalista.add("Kurpitsa 17 kcal/100g");
            getInstance().ruokalista.add("Lanttu 29 kcal/100g");
            getInstance().ruokalista.add("Lehtisalaatti 10 kcal/100g");
            getInstance().ruokalista.add("Lehtiselleri 10 kcal/100g");
            getInstance().ruokalista.add("Linssit 320 kcal/100g");
            getInstance().ruokalista.add("Maissi pakaste 123 kcal/100g");
            getInstance().ruokalista.add("Mukulaselleri 30 kcal/100g");
            getInstance().ruokalista.add("Nauris 25 kcal/100g");
            getInstance().ruokalista.add("Palsternakka 77 kcal/100g");
            getInstance().ruokalista.add("Paprika punainen ja keltainen 30 kcal/100g");
            getInstance().ruokalista.add("Paprika 25 kcal/100g");
            getInstance().ruokalista.add("Parsakaali (broccoli) 30 kcal/100g");
            getInstance().ruokalista.add("Pavut, vihreät 35 kcal/100g");
            getInstance().ruokalista.add("Persilja, tuore 27 kcal/100g");
            getInstance().ruokalista.add("Porkkana 30 kcal/100g");
            getInstance().ruokalista.add("Punajuuri 36 kcal/100g");
            getInstance().ruokalista.add("Ruusukaali 20 kcal/100g");
            getInstance().ruokalista.add("Sipuli 30 kcal/100g");
            getInstance().ruokalista.add("Tilli, tuore 30 kcal/100g");
            getInstance().ruokalista.add("Tomaatti 23 kcal/100g");
            getInstance().ruokalista.add("Peruna 75 kcal/100g");
            getInstance().ruokalista.add("Ananas tuore, kuorittu 56 kcal/100g");
            getInstance().ruokalista.add("Appelsiini, kuorittu 47 kcal/100g");
            getInstance().ruokalista.add("Avokado kuoreton, kivetön 198 kcal/100g");
            getInstance().ruokalista.add("Banaani kuorittu 87 kcal/100g");
            getInstance().ruokalista.add("Greippi, kuorittu 35 kcal/100g");
            getInstance().ruokalista.add("Karpalo 33 kcal/100g");
            getInstance().ruokalista.add("Karviainen 29 kcal/100g");
            getInstance().ruokalista.add("Kiivi 47 kcal/100g");
            getInstance().ruokalista.add("Luumu kuivattu 165 kcal/100g");
            getInstance().ruokalista.add("Luumu kivetön, tuore 47 kcal/100g");
            getInstance().ruokalista.add("Mandariini 36 kcal/100g");
            getInstance().ruokalista.add("Mango kuutio 67 kcal/100g");
            getInstance().ruokalista.add("Mansikka 46 kcal/100g");
            getInstance().ruokalista.add("Mustaherukka 41 kcal/100g");
            getInstance().ruokalista.add("Mustikka 37 kcal/100g");
            getInstance().ruokalista.add("Omena 35 kcal/100g");
            getInstance().ruokalista.add("Persikka 39 kcal/100g");
            getInstance().ruokalista.add("Punaherukka 37 kcal/100g");
            getInstance().ruokalista.add("Puolukka 38 kcal/100g");
            getInstance().ruokalista.add("Päärynä 42 kcal/100g");
            getInstance().ruokalista.add("Sitruuna 40 kcal/100g");
            getInstance().ruokalista.add("Suomuurain 54 kcal/100g");
            getInstance().ruokalista.add("Lakka 54 kcal/100g");
            getInstance().ruokalista.add("Vadelma 40 kcal/100g");
            getInstance().ruokalista.add("Vesimelonin liha 37 kcal/100g");
            getInstance().ruokalista.add("Viinirypäle 69 kcal/100g");
            getInstance().ruokalista.add("Balkanmakkara 242 kcal/100g");
            getInstance().ruokalista.add("Broilerin fileesuikale marinoitu 180 kcal/100g");
            getInstance().ruokalista.add("Broilerin fileesuikale maustamaton 110 kcal/100g");
            getInstance().ruokalista.add("Broilerin paistijauheliha 128 kcal/100g");
            getInstance().ruokalista.add("Broilerin rintaleike nahkoineen 149 kcal/100g");
            getInstance().ruokalista.add("Broilerinfileeleike 100 kcal/100g");
            getInstance().ruokalista.add("Grillimakkara, tavallinen 220 kcal/100g");
            getInstance().ruokalista.add("Jauheliha nauta, 17% rasvaa 228 kcal/100g");
            getInstance().ruokalista.add("Jauheliha poron 145 kcal/100g");
            getInstance().ruokalista.add("Jauheliha sika-nauta 22% rasv. 262 kcal/100g");
            getInstance().ruokalista.add("Jauheliha, Pirkka kevyt 7% rasv. 140 kcal/100g");
            getInstance().ruokalista.add("Kalkkunafilee	105 kcal/100g");
            getInstance().ruokalista.add("Kalkkunanfileeleike 100 kcal/100g");
            getInstance().ruokalista.add("Karjalanpaistiliha 156 kcal/100g");
            getInstance().ruokalista.add("Keittokinkku 130 kcal/100g");
            getInstance().ruokalista.add("Kinkkumakkara 150 kcal/100g");
            getInstance().ruokalista.add("Lauantaimakkara 225 kcal/100g");
            getInstance().ruokalista.add("Lenkkimakkara 220 kcal/100g");
            getInstance().ruokalista.add("Maksamakkara 243 kcal/100g");
            getInstance().ruokalista.add("Maksapasteija, kevyt 132 kcal/100g");
            getInstance().ruokalista.add("Meetvursti, kotimainen 400 kcal/100g");
            getInstance().ruokalista.add("Metvursti, ulkolainen 468 kcal/100g");
            getInstance().ruokalista.add("Mustamakkara 218 kcal/100g");
            getInstance().ruokalista.add("Nakki, a-luokka herkku 226 kcal/100g");
            getInstance().ruokalista.add("Nakki, kevytnakki 180 kcal/100g");
            getInstance().ruokalista.add("Nakki, tavallinen keskiarvo 233 kcal/100g");
            getInstance().ruokalista.add("Naudan lihasuikale 130 kcal/100g");
            getInstance().ruokalista.add("Naudanpaisti 127 kcal/100g");
            getInstance().ruokalista.add("Pekoni 280 kcal/100g");
            getInstance().ruokalista.add("Pitsakinkku suikale 125 kcal/100g");
            getInstance().ruokalista.add("Poron paisti 180 kcal/100g");
            getInstance().ruokalista.add("Porsaan ulkofile 110 kcal/100g");
            getInstance().ruokalista.add("Saunapalvikinkku 90 kcal/100g");
            getInstance().ruokalista.add("Sianliha filee	144 kcal/100g");
            getInstance().ruokalista.add("Sianliha, raaka kinkkusuikale 130 kcal/100g");
            getInstance().ruokalista.add("Ahvenfile 86 kcal/100g");
            getInstance().ruokalista.add("Haukifile 84 kcal/100g");
            getInstance().ruokalista.add("Kampelafile 94 kcal/100g");
            getInstance().ruokalista.add("Kirjolohifilee 166 kcal/100g");
            getInstance().ruokalista.add("Kuha 75 kcal/100g");
            getInstance().ruokalista.add("Lohifilee 195 kcal/100g");
            getInstance().ruokalista.add("Lohi graavilohi 180 kcal/100g");
            getInstance().ruokalista.add("Made 70 kcal/100g");
            getInstance().ruokalista.add("Muikku 108 kcal/100g");
            getInstance().ruokalista.add("Seiti pakaste 80 kcal/100g");
            getInstance().ruokalista.add("Siika 100 kcal/100g");
            getInstance().ruokalista.add("Silakkafileet 144 kcal/100g");
            getInstance().ruokalista.add("Silli, kevytsuolattu 250 kcal/100g");
            getInstance().ruokalista.add("Silli maustesilli 229 kcal/100g");
            getInstance().ruokalista.add("Makrillifilee savustettu 290 kcal/100g");
            getInstance().ruokalista.add("Katkaravut, pakaste 67 kcal/100g");
            getInstance().ruokalista.add("Mäti, keskiarvo 155 kcal/100g");
            getInstance().ruokalista.add("Rapu 66 kcal/100g");
            getInstance().ruokalista.add("Sardiinit tomaattikastikkeessa 165 kcal/100g");
            getInstance().ruokalista.add("Simpukat suolaliemessä 102 kcal/100g");
            getInstance().ruokalista.add("Tonnikalapalat vedessä 100 kcal/100g");
            getInstance().ruokalista.add("Tonnikalapalat öljyssä 180 kcal/100g");
            getInstance().ruokalista.add("CornFlakes maissihiutale 380 kcal/100g");
            getInstance().ruokalista.add("Donitsit 325 kcal/100g");
            getInstance().ruokalista.add("Kaurahiutale 350 kcal/100g");
            getInstance().ruokalista.add("Kääretorttu, kuningatar 330 kcal/100g");
            getInstance().ruokalista.add("Makaroni, tavallinen 350 kcal/100g");
            getInstance().ruokalista.add("Moniviljaleipä 275 kcal/100g");
            getInstance().ruokalista.add("Mysli 340 kcal/100g");
            getInstance().ruokalista.add("Mysli, hedelmä 330 kcal/100g");
            getInstance().ruokalista.add("Näkkileipä, ruis 339 kcal/100g");
            getInstance().ruokalista.add("Paahtoleipä 262 kcal/100g");
            getInstance().ruokalista.add("Patonki 250 kcal/100g");
            getInstance().ruokalista.add("Perunarieska 218 kcal/100g");
            getInstance().ruokalista.add("Pullapitko 325 kcal/100g");
            getInstance().ruokalista.add("Ranskanleipä 230 kcal/100g");
            getInstance().ruokalista.add("Riisi, pitkä irtonainen 370 kcal/100g");
            getInstance().ruokalista.add("Riisi, puuro 340 kcal/100g");
            getInstance().ruokalista.add("Riisi, tumma irtonainen 370 kcal/100g");
            getInstance().ruokalista.add("Riisimurot 380 kcal/100g");
            getInstance().ruokalista.add("Rouhe sekaleipä 270 kcal/100g");
            getInstance().ruokalista.add("Ruisjauho 305 kcal/100g");
            getInstance().ruokalista.add("Ruisleipä, jälkiuuni 280 kcal/100g");
            getInstance().ruokalista.add("Ruisleipä, ruispalat 250 kcal/100g");
            getInstance().ruokalista.add("Ruisvuokaleipä 205 kcal/100g");
            getInstance().ruokalista.add("Setsuuri 300 kcal/100g");
            getInstance().ruokalista.add("Spagetti 350 kcal/100g");
            getInstance().ruokalista.add("Suklaa muffinit 390 kcal/100g");
            getInstance().ruokalista.add("Suklaamurot	398 kcal/100g");
            getInstance().ruokalista.add("Sämpylä veteen leivottu 250 kcal/100g");
            getInstance().ruokalista.add("Sämpylä, hampurilais 295 kcal/100g");
            getInstance().ruokalista.add("Sämpyläjauho	345 kcal/100g");
            getInstance().ruokalista.add("Vehnäjauho 350 kcal/100g");
            getInstance().ruokalista.add("Jogurtti, asidofilus 2% rasv 60 kcal/100g");
            getInstance().ruokalista.add("Jogurtti, Turkkilainen 10% rasv. 119 kcal/100g");
            getInstance().ruokalista.add("Juusto Edam 24% rasvaa 330 kcal/100g");
            getInstance().ruokalista.add("Juusto Emmental 29% rasv. 370 kcal/100g");
            getInstance().ruokalista.add("Juusto kermainen 31% rasv. 379 kcal/100g");
            getInstance().ruokalista.add("Juusto Kevyt 17% rasvaa 298 kcal/100g");
            getInstance().ruokalista.add("Juusto Tuorejuusto kevyt 180 kcal/100g");
            getInstance().ruokalista.add("Juusto, sinihome 335 kcal/100g");
            getInstance().ruokalista.add("Juusto, Sulate kevyt 195 kcal/100g");
            getInstance().ruokalista.add("Kasvisrasvasekoite 4% rasv. 60 kcal/100g");
            getInstance().ruokalista.add("Kerma, kevyt 10% rasvaa 120 kcal/100g");
            getInstance().ruokalista.add("Kerma, kuohu 35% rasvaa 340 kcal/100g");
            getInstance().ruokalista.add("Kermaviili, 6% rasvaa 80 kcal/100g");
            getInstance().ruokalista.add("Leivontamargariini, 80% rasv. 720 kcal/100g");
            getInstance().ruokalista.add("Maito rasvaton 33 kcal/100g");
            getInstance().ruokalista.add("Maito, kevyt 46 kcal/100g");
            getInstance().ruokalista.add("Maitorahka 62 kcal/100g");
            getInstance().ruokalista.add("Margariini 40% 350 kcal/100g");
            getInstance().ruokalista.add("Margariini 60%, keskiarvo 533 kcal/100g");
            getInstance().ruokalista.add("Margariini juokseva 80% rasv. 720 kcal/100g");
            getInstance().ruokalista.add("Salaattijuusto öljyssä 270 kcal/100g");
            getInstance().ruokalista.add("Salaattijuusto, kevyt 185 kcal/100g");
            getInstance().ruokalista.add("Voi, meijerivoi 720 kcal/100g");
            getInstance().ruokalista.add("3-Olut 34 kcal/100g");
            getInstance().ruokalista.add("4-Olut 38 kcal/100g");
            getInstance().ruokalista.add("Ananasmehu, täys 48 kcal/100g");
            getInstance().ruokalista.add("Appelsiinitäysmehu 40 kcal/100g");
            getInstance().ruokalista.add("I-Olut 29 kcal/100g");
            getInstance().ruokalista.add("Kahvi, juoma 3 kcal/100g");
            getInstance().ruokalista.add("Konjakki 229 kcal/100g");
            getInstance().ruokalista.add("Kotikalja 27 kcal/100g");
            getInstance().ruokalista.add("Liköörit 300 kcal/100g");
            getInstance().ruokalista.add("Virvoitusjuoma 38 kcal/100g");
            getInstance().ruokalista.add("Omenamehu, täys 46 kcal/100g");
            getInstance().ruokalista.add("Kevyt juomatiiviste 6 kcal/100g");
            getInstance().ruokalista.add("Sekamehutiiviste 260 kcal/100g");
            getInstance().ruokalista.add("Punaviini 70 kcal/100g");
            getInstance().ruokalista.add("Siideri 48 kcal/100g");
            getInstance().ruokalista.add("Tee, juoma 0,2 kcal/100g");
            getInstance().ruokalista.add("Valkoviini, kuiva 68 kcal/100g");
            getInstance().ruokalista.add("Valkoviini, makea 98 kcal/100g");
            getInstance().ruokalista.add("Viina, kirkas 230 kcal/100g");
            getInstance().ruokalista.add("Tomaattimurska 30 kcal/100g");
            getInstance().ruokalista.add("Etikka 31 kcal/100g");
            getInstance().ruokalista.add("Kananmunan valkuainen 42 kcal/100g");
            getInstance().ruokalista.add("Viipalekurkkusäilyke 44 kcal/100g");
            getInstance().ruokalista.add("Tomaattipyree 85 kcal/100g");
            getInstance().ruokalista.add("Salaattikastike, kevyt 90 kcal/100g");
            getInstance().ruokalista.add("Tomaattiketsuppi 110 kcal/100g");
            getInstance().ruokalista.add("Hernekeittopurkki 115 kcal/100g");
            getInstance().ruokalista.add("Oliivit, vihreä paprikatäyte 119 kcal/100g");
            getInstance().ruokalista.add("Rankanperunat, pakaste 127 kcal/100g");
            getInstance().ruokalista.add("Kananmuna, keitetty 143 kcal/100g");
            getInstance().ruokalista.add("Kalapuikot, pakaste 190 kcal/100g");
            getInstance().ruokalista.add("Mansikkahillo 200 kcal/100g");
            getInstance().ruokalista.add("Sinappi, tavallinen 250 kcal/100g");
            getInstance().ruokalista.add("Kananmunan keltuainen 285 kcal/100g");
            getInstance().ruokalista.add("Hunaja 335 kcal/100g");
            getInstance().ruokalista.add("Hampulilaiskastike 340 kcal/100g");
            getInstance().ruokalista.add("Majoneesi, kevyt 390 kcal/100g");
            getInstance().ruokalista.add("Sokeri 406 kcal/100g");
            getInstance().ruokalista.add("Jogurtti rusinat 430 kcal/100g");
            getInstance().ruokalista.add("Popcornit 440 kcal/100g");
            getInstance().ruokalista.add("Perunalastu, tavallinen 540 kcal/100g");
            getInstance().ruokalista.add("Juustosnacks 550 kcal/100g");
            getInstance().ruokalista.add("Cashew-pähkinät 560 kcal/100g");
            getInstance().ruokalista.add("Suolapähkinät	600 kcal/100g");
            getInstance().ruokalista.add("Majoneesi, rasvainen 720 kcal/100g");
            getInstance().ruokalista.add("Oliiviöljy, extra virgin 880 kcal/100g");
            getInstance().ruokalista.add("Ruokaöljy, rypsi 900 kcal/100g");
            getInstance().ruokalista.add("Salaattikastike 300-500 kcal/100g");
        }

    }

    /**
     * Päivittää ruokalistan muutoksen jälkeen.
     */
    public void paivitaTuoteLista(){
        Intent intent = new Intent(this, RuokailuActivity.class);
        startActivity(intent);
    }

}