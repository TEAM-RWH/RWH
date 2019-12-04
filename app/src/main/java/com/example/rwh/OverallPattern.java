package com.example.rwh;


import java.util.ArrayList;

public class OverallPattern {
    private static final OverallPattern ourInstance = new OverallPattern();

    public ArrayList<Henkilo> henkilot;
    public ArrayList<Pvm> paivamaarat;
    public ArrayList<Ateria> ateriat;
    public ArrayList<Liikkuminen> liikunnat;


    public static OverallPattern getInstance() {
        return ourInstance;
    }

    private OverallPattern() {
        //henkilot = new ArrayList<Henkilo>();

        liikunnat = new ArrayList<>();
        paivamaarat = new ArrayList<>();
        ateriat = new ArrayList<>();
    }

    public ArrayList<Henkilo> getHenkilot() {
        return henkilot;
    }

    public ArrayList<Pvm> getPaivamaarat(){
        return paivamaarat;
    }
    public ArrayList<Ateria> getAteriat(){
        return ateriat;
    }
    public ArrayList<Liikkuminen> getLiikunnat(){
        return liikunnat;
    }
}


