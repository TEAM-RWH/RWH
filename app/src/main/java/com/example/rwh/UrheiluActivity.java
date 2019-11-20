package com.example.rwh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class UrheiluActivity extends AppCompatActivity {

    private TextView urheilucaloriesView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urheilu);
        getSupportActionBar().setTitle("Urheilu page");

        urheilucaloriesView = findViewById(R.id.urheilucaloriesView);

        urheilucaloriesView.setText("1000");
    }
}
