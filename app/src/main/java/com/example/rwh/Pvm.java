package com.example.rwh;

import java.util.ArrayList;

public class Pvm{
    private String pvm;
    private ArrayList<ArrayList<Ateria>> paivanAteriat;

    public Pvm(String pvm, ArrayList<ArrayList<Ateria>> paivanAteriat){
        this.pvm = pvm;
        this.paivanAteriat = paivanAteriat;
    }

    public ArrayList<ArrayList<Ateria>> getPaivanAteriat(){
        return paivanAteriat;
    }
}
