package com.example.rwh;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Luo HomeActivityn EnergyAgent sovellukselle.
 * @version 1.0
 * @author Hanne Hovilampi
 * @since 21.10.2019
 */
public class HomeActivity extends AppCompatActivity{
    /**
     * Luo Splashscreenin sovellukselle.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        View decorview = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorview.setSystemUiVisibility(uiOptions);

        setContentView(R.layout.activity_home);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i = new Intent(HomeActivity.this,BasicInformationActivity.class);
                startActivity(i);

                finish();
            }
        },3000);
    }
}