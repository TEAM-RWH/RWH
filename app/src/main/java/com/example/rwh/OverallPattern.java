package com.example.rwh;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;


public class OverallPattern {
    private static final OverallPattern ourInstance = new OverallPattern();
    private Context mContext;

    public ArrayList<Henkilo> henkilot;

    public static OverallPattern getInstance() {
        return ourInstance;
    }

    private OverallPattern() {
        //henkilot = new ArrayList<Henkilo>();
        //henkilot.add(new Henkilo("Lauri", 178, 70, 27, "Mies"));


    }

    public ArrayList<Henkilo> getHenkilot() {
        return henkilot;
    }

    public void saveData() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("Shared preferences", MODE_PRIVATE); //ONGELMA
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(henkilot);
        editor.putString("henkilo lista", json);
        editor.apply();
    }

    public void loadData() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("Shared preferences", MODE_PRIVATE); //ONGELMA
        Gson gson = new Gson();
        String json = sharedPreferences.getString("henkilo lista", null);
        Type type = new TypeToken<ArrayList<Henkilo>>() {
        }.getType();
        henkilot = gson.fromJson(json, type);
    }

    }



