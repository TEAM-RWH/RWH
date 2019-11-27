package com.example.rwh;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;

public class RuokailuActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText editText;
    private TextView aterian_paiva;
    private Spinner ateriaSpinner;
    private DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ruokailu);
        getSupportActionBar().setTitle("Ruokailu page");

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
            }
        };

        //caloriesView.setText("500");

        ateriaSpinner = findViewById(R.id.spinner_ateria);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Ateriat, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ateriaSpinner.setAdapter(adapter);
        ateriaSpinner.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String valittu_ateria = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(), valittu_ateria, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
