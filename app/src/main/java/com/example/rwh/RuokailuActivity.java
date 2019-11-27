package com.example.rwh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class RuokailuActivity extends AppCompatActivity {

    private EditText editText;
    private TextView caloriesView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ruokailu);
        getSupportActionBar().setTitle("Ruokailu page");

        caloriesView = findViewById(R.id.caloriesView);

        caloriesView.setText("500");


    }
}
