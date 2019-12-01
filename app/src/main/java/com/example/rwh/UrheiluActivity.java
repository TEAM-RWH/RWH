package com.example.rwh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
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
    public static final String TAG = "Urheilulista";
    public static final String EXTRA = "123";



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

        Intent intent = getIntent();
        int i = intent.getIntExtra(EXTRA, 0);

        kaloreitapoltettu = findViewById(R.id.kaloreitapoltettu);
        kaloreitapoltettu.setText(String.valueOf(OverallPattern.getInstance().henkilot.get(i).getIka())); //Call for a calorie calculation

        cheerup = findViewById(R.id.cheerup);
        cheerup.setText("Hyvin Menee!");

        int kaloripoltto;

        //urheilulajijen kilokalori kulutus 10 minuutissa
        int ryömiminen=1;
        int kävely=2;
        int hölkkä=3;
        int juoksu=4;
        int pikajuoksu=5;

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
        switch(parent.getId())
        {
            case R.id.spinner1:
            { // code for first spinner
                String text1 = parent.getItemAtPosition(position).toString();
                urheilulaji.setText(text1);
                break;
                // code for first spinner. Depending on spinner.getselecteditem assign adapter to second spinner
            }
            case R.id.spinner2:
            { // code for second spinner
                String text2 = parent.getItemAtPosition(position).toString();
                kesto.setText(text2);
                break;

                //Use get item selected and get selected item position
            }

    }}
//String text = parent.getItemAtPosition(position).toString();
//        Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();
//        urheilulaji.setText(text);
//        kesto.setText(text);
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "Return to main from UrheiluActivity");
        int id = item.getItemId();

        if ( id == android.R.id.home ) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

