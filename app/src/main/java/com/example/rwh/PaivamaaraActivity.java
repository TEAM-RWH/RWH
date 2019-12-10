package com.example.rwh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static com.example.rwh.OverallPattern.getInstance;

public class PaivamaaraActivity extends AppCompatActivity {

    private int j;
    public static final String EXTRA = "123";
    public static final String EXTRA2 = "PaivamaaraActivity";
    public static final String TAG = "PaivamaaraActivity";

    private String[] activities;

    private Intent ruokailuActivity;
    private Intent urheiluActivity;

    private ListView list;
    private ArrayAdapter adapter;

    private TextView paivamaaraView;
    private TextView aamupalaView;
    private TextView lounasView;
    private TextView valipalaView;
    private TextView paivallinenView;
    private TextView illallinenView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"onCreate being called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paivamaara);



        Intent intent = getIntent();
        j = intent.getIntExtra(EXTRA2, 0);

        paivamaaraView = (TextView) findViewById(R.id.paivamaaraView);
        aamupalaView = (TextView) findViewById(R.id.aamupalaView);
        lounasView = (TextView) findViewById(R.id.lounasView);
        valipalaView = (TextView) findViewById(R.id.valipalaView);
        paivallinenView = (TextView) findViewById(R.id.paivallinenView);
        illallinenView = (TextView) findViewById(R.id.illallinenView);


        ruokailuActivity = new Intent(PaivamaaraActivity.this, RuokailuActivity.class);
        urheiluActivity = new Intent(PaivamaaraActivity.this, UrheiluActivity.class);

        String[] activities = {"Ruokailu suoritukset", "Urheilu suoritukset"};

        //lataaTiedot();

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                activities)
        ;

        list = findViewById(R.id.lista);
        list.setAdapter(adapter);

        list.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int indeksi, long Id) {
                        Log.d(TAG, "onItemClick(" + indeksi + ")");
                        String.valueOf(parent.getItemAtPosition(indeksi));

                        //saveData();
                        if (indeksi == 0) {
                            //Toast.makeText(getApplicationContext(), "Ruokailu activity for user: " + nimi, Toast.LENGTH_LONG).show();
                            ruokailuActivity.putExtra(EXTRA, j);
                            startActivity(ruokailuActivity);

                        } else {
                            //Toast.makeText(getApplicationContext(), "Urheilu activity for user: " + nimi, Toast.LENGTH_LONG).show();
                            urheiluActivity.putExtra(EXTRA, j);
                            startActivity(urheiluActivity);
                        }
                    }
                });

        paivamaaraView.setText(OverallPattern.getInstance().paivamaarat.get(j).getPaivamaara());
        aamupalaView.setText(String.valueOf(OverallPattern.getInstance().paivamaarat.get(j).getAamupala()));

    }

    public void onResume(){
        Log.d(TAG, "onResume being called");
        super.onResume();
        paivamaaraView.setText(OverallPattern.getInstance().paivamaarat.get(j).getPaivamaara());
        aamupalaView.setText(String.valueOf(OverallPattern.getInstance().paivamaarat.get(j).getAamupala()));
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
    } //Crash protection

    public void lataaTiedot() {
        SharedPreferences sharedPreferences = getSharedPreferences("Shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json2 = sharedPreferences.getString("paivamaara lista", null);
        Type type2 = new TypeToken<ArrayList<Pvm>>() {
        }.getType();
        OverallPattern.getInstance().paivamaarat = gson.fromJson(json2, type2);


    }
}
