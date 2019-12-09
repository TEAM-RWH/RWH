package com.example.rwh;

import java.util.ArrayList;

public class OverallPattern {
    private static final OverallPattern ourInstance = new OverallPattern();

    public ArrayList<Henkilo> henkilot;
    public ArrayList<Pvm> paivamaarat;



    public static OverallPattern getInstance() {
        return ourInstance;
    }

    private OverallPattern() {

        henkilot = new ArrayList<>();
        paivamaarat = new ArrayList<>();

    }
    public ArrayList<Pvm> getPaivamaarat(){
        return paivamaarat;
    }


    public ArrayList<Henkilo> getHenkilot() {
        return henkilot;
    }

}