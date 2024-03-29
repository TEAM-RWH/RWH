package com.example.rwh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Luo MainActiviteetin Energy Agent sovellukselle
 *
 * @author Lauri Riikonen
 * @version 1.0
 * @since 21.10.2019
 */

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA = "123";
    public static final String EXTRA2 = "PaivamaaraActivity";
    public static final String TAG = "MainActivity";
    private ArrayAdapter latenAdapter;
    private ListView latenListView;
    private Intent paivamaaraActivity;
    private TextView nimiView;
    private TextView pituusView;
    private TextView painoView;
    private TextView ikaView;
    private TextView sukupuoliView;
    private TextView paivamaaraView;
    private DatePickerDialog.OnDateSetListener setListener;
    private String nimi;
    private int pituus;
    private int paino;
    private int ika;
    private String sukupuoli;
    private int i;
    private DecimalFormat laskut = new DecimalFormat("###.##");

    /**
     * Luo perusnäkymän MainActivitylle.
     *
     * @param savedInstanceState
     */

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
        paivamaaraView = (TextView) findViewById(R.id.paivamaaraView);

        paivamaaraActivity = new Intent(MainActivity.this, PaivamaaraActivity.class);


        lataaPaivamaaraData();

        latenAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                OverallPattern.getInstance().getPaivamaarat())
        ;

        latenListView = findViewById(R.id.lista);
        latenListView.setAdapter(latenAdapter);

        Bundle bundle = getIntent().getExtras();
        i = bundle.getInt(BasicInformationActivity.EXTRA, 0);

        lataaHenkiloData();

        setValues();

        setInformation();

        setMuutokset();

        asetaPaivamaaraValitsin();


    }

    /**
     * Asetetaan info -nappi yläpalkkiin
     *
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
     *
     * @param item
     * @return
     */

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:

                new AlertDialog.Builder(MainActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setTitle("Info")
                        .setMessage("Nimi, paino ja ikä ovat muutettavissa pitkällä painalluksella. Näytössä näkyvät arvot tallennetaan " +
                                "luotavan päivän arvoiksi.")
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

    }//Asetetaan, jotta voidaan valita jokin toiminto action barista

    /**
     * Lisää päivämäärän Singleton -luokan listalle.
     *
     * @param v
     */

    public void lisaaPaivamaara(View v) {

        if (!paivamaaraView.getText().toString().trim().isEmpty()) {
            OverallPattern.getInstance().paivamaarat.add(new Pvm(String.valueOf(paivamaaraView.getText()), pituus, paino, ika, sukupuoli));
        } else {
            Toast.makeText(getApplicationContext(), "Valitse lisättävä päivämäärä", Toast.LENGTH_SHORT).show();
        }
        latenListView.setAdapter(latenAdapter);
        savePaivamaaraData();
    }

    /**
     * Asettaa käyttäjäntiedot TextVieweihin.
     */

    private void setInformation() {
        getSupportActionBar().setTitle("Käyttäjä: " + nimi);

        nimiView.setText("Käyttäjän nimi: " + nimi);
        pituusView.setText("Käyttäjän pituus: " + pituus + " cm");
        painoView.setText("Käyttäjän paino: " + paino + " kg");
        ikaView.setText("Käyttäjän ikä: " + ika + " vuotta");
        sukupuoliView.setText("Sukupuoli: " + sukupuoli);

    } //Asetetaan käyttäjän tiedot TextVieweihin

    /**
     * Asettaa arvot Henkilo -oliolle.
     */

    private void setValues() {
        nimi = OverallPattern.getInstance().henkilot.get(i).getNimi();
        pituus = OverallPattern.getInstance().henkilot.get(i).getPituus();
        paino = OverallPattern.getInstance().henkilot.get(i).getPaino();
        ika = OverallPattern.getInstance().henkilot.get(i).getIka();
        sukupuoli = OverallPattern.getInstance().henkilot.get(i).getSukupuoli();
    }

    /**
     * Tallentaa käyttäjän ominaisuuksien muutetut arvot onPause -tilassa.
     */

    public void onPause() {
        Log.d(TAG, "onPause being called");
        super.onPause();
        saveData();
    }

    /**
     * Asettaa OnItemClicklistenerit sekä OnItemLongClickListenerit
     */

    private void setMuutokset() {


        latenListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int i, long Id) {
                        Log.d(TAG, "Klikattu indeksi(" + i + ")");
                        paivamaaraActivity.putExtra(EXTRA2, i);
                        startActivity(paivamaaraActivity);
                        Toast.makeText(getApplicationContext(), "Valittu päivämäärä: " + latenAdapter.getItem(i), Toast.LENGTH_SHORT).show();
                    }
                });

        latenListView.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long id) {
                        Log.d(TAG, "Long Click :D(" + i + ")");

                        final int which_item = i;

                        new AlertDialog.Builder(MainActivity.this)
                                .setIcon(android.R.drawable.ic_menu_delete)
                                .setTitle("Poista päivämäärä")
                                .setMessage("Poista " + latenAdapter.getItem(i) + "?")
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(getApplicationContext(), "Poistettu päivämäärä: " + latenAdapter.getItem(which_item),
                                                Toast.LENGTH_SHORT).show();
                                        OverallPattern.getInstance().paivamaarat.remove(which_item);
                                        latenAdapter.notifyDataSetChanged();
                                        savePaivamaaraData();
                                    }
                                })
                                .setNegativeButton("No", null)
                                .show();

                        return true;
                    }

                }
        );

        painoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "Clicked " + painoView.getText(), Toast.LENGTH_LONG).show();

            }
        });

        painoView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Muokkaa painoa");
                alert.setMessage("Syötä uusi paino:");


                final EditText input = new EditText(MainActivity.this);
                alert.setView(input);
                input.setInputType(InputType.TYPE_CLASS_NUMBER);

                alert.setPositiveButton("Muuta", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        String onkoNull = (input.getText().toString());
                        if (onkoNull.length() < 1) {
                            Toast.makeText(getApplicationContext(), "Syötä paino", Toast.LENGTH_LONG).show();

                        } else {
                            int saatuArvo = Integer.parseInt(input.getText().toString());

                            OverallPattern.getInstance().henkilot.get(i).setPaino(saatuArvo);
                            setValues();
                            setInformation();
                            saveData();
                        }
                    }
                });

                alert.setNegativeButton("Peruuta", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                });

                alert.show();

                return true;
            }
        });

        nimiView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Muokkaa nimeä");
                alert.setMessage("Syötä uusi nimi:");


                final EditText input = new EditText(MainActivity.this);
                alert.setView(input);
                input.setInputType(InputType.TYPE_CLASS_TEXT);

                alert.setPositiveButton("Muuta", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        String saatuArvo = (input.getText().toString());
                        if(saatuArvo.length() > 0) {

                            saatuArvo = (input.getText().toString().trim().substring(0, 1).toUpperCase() +
                                    input.getText().toString().substring(1));
                            OverallPattern.getInstance().henkilot.get(i).setNimi(saatuArvo);

                            setValues();
                            setInformation();
                            saveData();
                        } else {
                            Toast.makeText(getApplicationContext(),"Syötä uusi nimi", Toast.LENGTH_LONG).show();
                        }
                    }
                });

                alert.setNegativeButton("Peruuta", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                });

                alert.show();

                return true;
            }
        });


        ikaView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Muokkaa ikää");
                alert.setMessage("Syötä uusi ikä:");


                final EditText input = new EditText(MainActivity.this);
                alert.setView(input);
                input.setInputType(InputType.TYPE_CLASS_NUMBER);

                alert.setPositiveButton("Muuta", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {


                        String onkoNull = (input.getText().toString());
                        if (onkoNull.length() < 1) {
                            Toast.makeText(getApplicationContext(), "Syötä ikä", Toast.LENGTH_LONG).show();

                        } else {
                            int saatuArvo = Integer.parseInt(input.getText().toString());

                            OverallPattern.getInstance().henkilot.get(i).setIka(saatuArvo);
                            setValues();
                            setInformation();
                            saveData();
                        }
                    }
                });

                alert.setNegativeButton("Peruuta", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                });

                alert.show();

                return true;
            }
        });


    }//Mahdollistaa käyttäjän tietojen muuttamisen

    /**
     * Tallentaa käyttäjän ominaisuuksien muutokset SharedPreferenceseihin.
     */

    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("Shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(OverallPattern.getInstance().henkilot);
        editor.putString("henkilo lista", json);
        editor.apply();
    }//Käytetään jos käyttäjän ominaisuuksiin(Nimi, paino... tehdään muutoksia

    /**
     * Tallentaa päivämäärälistan SharedPreferenceseihin.
     */

    public void savePaivamaaraData() {
        SharedPreferences sharedPreferences = getSharedPreferences("Shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson2 = new Gson();
        String json2 = gson2.toJson(OverallPattern.getInstance().paivamaarat);
        editor.putString("paivamaara lista", json2);
        editor.apply();

    } //Käyetetään jos käyttäjä lisää päivämääriä

    /**
     * Lataa henkilölistan SharedPreferenceseista.
     */

    public void lataaHenkiloData() {
        SharedPreferences sharedPreferences = getSharedPreferences("Shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();

        String json = sharedPreferences.getString("henkilo lista", null);

        Type type = new TypeToken<ArrayList<Henkilo>>() {
        }.getType();
        OverallPattern.getInstance().henkilot = gson.fromJson(json, type);
    } //Henkilötietojen lataamista varten

    /**
     * Lataa päivämäärälistan SharedPreferenceseista.
     */

    public void lataaPaivamaaraData() {
        SharedPreferences sharedPreferences = getSharedPreferences("Shared preferences", MODE_PRIVATE);
        Gson gson2 = new Gson();
        String json2 = sharedPreferences.getString("paivamaara lista", null);
        Type type2 = new TypeToken<ArrayList<Pvm>>() {
        }.getType();
        OverallPattern.getInstance().paivamaarat = gson2.fromJson(json2, type2);

        if (OverallPattern.getInstance().paivamaarat == null) {
            OverallPattern.getInstance().paivamaarat = new ArrayList<Pvm>();
        }

    } //Päivämäärä listan lataus

    /**
     * Asettaa päivämäärävalitsimen.
     */

    public void asetaPaivamaaraValitsin() {
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        paivamaaraView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MainActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        setListener, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = day + "." + month + "." + year;
                paivamaaraView.setText(date);

            }
        };

    } //Asetetaan päivämäärä valitsin
}