package com.example.rwh;


import java.util.ArrayList;

public class OverallPattern {
    private static final OverallPattern ourInstance = new OverallPattern();

    public ArrayList<Henkilo> henkilot;
    public ArrayList<Pvm> paivamaarat;
    public ArrayList<Ateria> ateriat;

    public static OverallPattern getInstance() {
        return ourInstance;
    }

    private OverallPattern() {
        //henkilot = new ArrayList<Henkilo>();


        paivamaarat = new ArrayList<>();
        ateriat = new ArrayList<>();
    }

    public ArrayList<Henkilo> getHenkilot() {
        return henkilot;
    }

    public ArrayList<Pvm> getPaivamaarat(){
        return  paivamaarat;
    }
}


