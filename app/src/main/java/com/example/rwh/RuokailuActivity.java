package com.example.rwh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class RuokailuActivity extends AppCompatActivity {

    private EditText editText;
    private TextView caloriesView;
    public static final String TAG = "Ruokailulista";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ruokailu);
        getSupportActionBar().setTitle("Ruokailu page");

        caloriesView = findViewById(R.id.caloriesView);

        caloriesView.setText("500");



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
}
