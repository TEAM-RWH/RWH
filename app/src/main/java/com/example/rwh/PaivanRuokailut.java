package com.example.rwh;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import static com.example.rwh.MainActivity.EXTRA;
import static com.example.rwh.OverallPattern.getInstance;
import static com.example.rwh.RuokailuActivity.EXTRA_MESSAGE;

public class PaivanRuokailut extends AppCompatActivity {

    private ListView paivanAteriat;
    private ArrayAdapter paivanRuokailutAdapter;
    private int j,k;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paivan_ruokailut);

        Intent intent = getIntent();
        j = intent.getIntExtra(EXTRA,0);
        //getSupportActionBar().setTitle("Päivän ateriat: " + OverallPattern.getInstance().henkilot.get(j).getNimi());
        Intent intent1 = getIntent();
        String message = intent1.getStringExtra(RuokailuActivity.EXTRA_MESSAGE);

        paivanRuokailutAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                getInstance().getListaPaivanAterioista());
        ;
        paivanAteriat = findViewById(R.id.paivanAteriatListView);
        paivanAteriat.setAdapter(paivanRuokailutAdapter);

    }
}
