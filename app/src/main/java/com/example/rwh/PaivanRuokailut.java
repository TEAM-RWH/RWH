package com.example.rwh;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import static com.example.rwh.MainActivity.EXTRA;

public class PaivanRuokailut extends AppCompatActivity {

    private ListView paivanAteriat;
    private ArrayAdapter paivanRuokailutAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paivan_ruokailut);

        paivanRuokailutAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                OverallPattern.getInstance().getAteriat());
        ;
        paivanAteriat = findViewById(R.id.paivanAteriatListView);
        paivanAteriat.setAdapter(paivanRuokailutAdapter);

    }
}
