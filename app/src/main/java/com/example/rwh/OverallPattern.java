package com.example.rwh;

import java.util.ArrayList;

public class OverallPattern {
    private static final OverallPattern ourInstance = new OverallPattern();

    public ArrayList<Henkilo> henkilot;

    public static OverallPattern getInstance() {
        return ourInstance;
    }

    private OverallPattern() {
        henkilot = new ArrayList<Henkilo>();
        henkilot.add(new Henkilo("Lauri", 178, 70, 27));

    }

    public ArrayList<Henkilo> getHenkilot() {
        return henkilot;
    }
}


