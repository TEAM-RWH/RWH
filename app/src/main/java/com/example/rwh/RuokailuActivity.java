package com.example.rwh;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class RuokailuActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Button valitseRuoat;
    private TextView aterian_paiva, ruoka1;
    private Spinner ateriaSpinner;
    private DatePickerDialog.OnDateSetListener setListener;
    public static final String TAG = "Ruokailulista";
    private String valittuAteriaLuokalle, aterianPvmluokalle, aterian_pvm;
    private ArrayList<String> valittujenRuokienLista;
    private ArrayAdapter pvmAdapter;
    private ListView listView;
    public static final String EXTRA = "123";
    private int j;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ruokailu);

       Intent intent = getIntent();
       j = intent.getIntExtra(EXTRA,0);


        getSupportActionBar().setTitle("Ruokailut: " + OverallPattern.getInstance().henkilot.get(j).getNimi());

        valittujenRuokienLista = new ArrayList<>();
        //ateriat = new ArrayList<Ateria>();

        pvmAdapter = new ArrayAdapter<>(this,    /*CONVERTER*/
                android.R.layout.simple_list_item_1,
                OverallPattern.getInstance().getPaivamaarat());
        ;

        //ListView luominen päiville

        listView = findViewById(R.id.ateria_paivat);
        listView.setAdapter(pvmAdapter);

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int i, long Id) {
                        Log.d(TAG, "RuokailuActivityOnItemClick(" + i + ")");
                        Intent nextActivity = new Intent(RuokailuActivity.this, PaivanRuokailut.class);
                        nextActivity.putExtra(EXTRA, i);
                        //nextActivity.putExtra("flag", "A");
                        startActivity(nextActivity);
                        Toast.makeText(getApplicationContext(), "Valitsit: " + pvmAdapter.getItem(i), Toast.LENGTH_SHORT).show();
                    }
                });

        listView.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long id) {
                        Log.d(TAG, "Long Click :D(" + i + ")");


                        final int which_item = i;

                        new androidx.appcompat.app.AlertDialog.Builder(RuokailuActivity.this)
                                .setIcon(android.R.drawable.ic_menu_delete)
                                .setTitle("Poista päivä")
                                .setMessage("Poista " + pvmAdapter.getItem(i) + "?")
                                .setPositiveButton("Kyllä", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(getApplicationContext(), "Poistettu päivä: " + pvmAdapter.getItem(which_item),
                                                Toast.LENGTH_SHORT).show();
                                        OverallPattern.getInstance().paivamaarat.remove(which_item);
                                        pvmAdapter.notifyDataSetChanged();

                                    }
                                })
                                .setNegativeButton("Ei", null)
                                .show();

                        return true;
                    }

                }
        );

        //Päivämäärävalitsimen luominen

        aterian_paiva = findViewById(R.id.aterian_pvm);
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        aterian_paiva.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        RuokailuActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
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
                aterian_paiva.setText(date);
                aterianPvmluokalle = date;

            }
        };

        //Spinnervalikon luominen

        ateriaSpinner = findViewById(R.id.spinner_ateria);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Ateriat, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ateriaSpinner.setAdapter(adapter);
        ateriaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 if(parent.getItemAtPosition(position).equals("Valitse ateria:")){

                  }
                  else{
                  String valittuAteria = parent.getItemAtPosition(position).toString();
                  valittuAteriaLuokalle = parent.getItemAtPosition(position).toString();
                  Toast.makeText(parent.getContext(),"Valitsit: " + valittuAteria, Toast.LENGTH_SHORT).show();
                      }
                  }

             @Override
             public void onNothingSelected(AdapterView<?> parent) {

             }
             });

        //Ruokavalikon luominen

        ruoka1 = findViewById(R.id.ruoat);
        valitseRuoat = findViewById(R.id.ruoat_button);
        valitseRuoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(RuokailuActivity.this);
                //String array alert dialogin monivalinta vaihtoehdoille
                final String[] ruoat = new String[]{"omena", "banaani", "leipä", "Lasi maitoa", "Juustoa"};
                //Boolean array alustetuille valituille vaihtoehdoille
                final boolean[] valitutRuoat = new boolean[]{
                        false,
                        false,
                        false,
                        false,
                        false
                };
                //ruoat arrayn muunto listaksi
                final List<String> ruokienLista = Arrays.asList(ruoat);
                //AlertDialogin otsikon asettaminen
                builder.setTitle("Valitse ruoat:");
                //Iconin asettaminen
                builder.setIcon(R.drawable.ico);
                builder.setMultiChoiceItems(ruoat, valitutRuoat, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        valitutRuoat[which] = isChecked;
                        //
                        String valitut = ruokienLista.get(which);
                        //
                        Toast.makeText(RuokailuActivity.this, valitut+" " + isChecked, Toast.LENGTH_SHORT);
                    }
                });
                //valittujen ruokien hyväksyminen
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int i = 0; i < ruoat.length; i++) {
                            boolean valittu = valitutRuoat[i];

                            if (valittu) {

                                valittujenRuokienLista.add(ruokienLista.get(i));
                                ruoka1.setText(ruoka1.getText() + ruokienLista.get(i) + "\n");

                            }
                        }
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

        if ( id == android.R.id.home ) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void lisaaAteriaPaiva(View v){
        OverallPattern.getInstance().paivamaarat.add(new Pvm(aterianPvmluokalle));
        OverallPattern.getInstance().ateriat.add(new Ateria(valittuAteriaLuokalle,valittujenRuokienLista));
        tyhjennaValinnat();
        listView.setAdapter(pvmAdapter);

    }

    public void tyhjennaValinnat(){
        aterian_paiva.setText(R.string.aterian_pvm);
        ateriaSpinner.setSelection(0);
        ruoka1.setText(R.string.valitutRuoat_textview);
    }
}