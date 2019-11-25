package com.example.rwh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class UrheiluActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TextView urheilulaji;
    private TextView urheilucaloriesView;
    private TextView kesto;
    private TextView kaloreitapoltettu;
    private TextView cheerup;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urheilu);
        getSupportActionBar().setTitle("Urheilu page");

        urheilucaloriesView = findViewById(R.id.urheilucaloriesView);
        urheilucaloriesView.setText("Urheilu Suoritus");

        urheilulaji = findViewById(R.id.urheilulaji);
        //urheilulaji.setText("blabla");

        kesto = findViewById(R.id.kesto);
        //kesto.setText("blabla");

        kaloreitapoltettu = findViewById(R.id.kaloreitapoltettu);
        kaloreitapoltettu.setText("blabla"); //Call for a calorie calculation

        cheerup = findViewById(R.id.cheerup);
        cheerup.setText("blabla");

       Spinner spinner = findViewById(R.id.spinner1);
       ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.urheilu, android.R.layout.simple_spinner_item);
       adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
       spinner.setAdapter(adapter);
       spinner.setOnItemSelectedListener(this);

        Spinner spinner2 = findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.kesto, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();
        urheilulaji.setText(text);
        kesto.setText(text);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
