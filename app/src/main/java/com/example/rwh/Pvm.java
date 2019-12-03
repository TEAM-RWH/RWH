package com.example.rwh;

import java.util.ArrayList;

public class Pvm{
    private String pvm;
    private ArrayList<Ateria> paivanateriat;

    public Pvm(String pvm, ArrayList<Ateria> valitutruoat){
        this.pvm = pvm;
        this.paivanateriat = valitutruoat;
    }

    public Pvm(String pvm){
        this.pvm = pvm;
    }

    public ArrayList<Ateria> getPaivanAteriat() {
        return paivanateriat;
    }

    public String getPvm() {
        return pvm;
    }

    public void setPvm(String pvm) {
        this.pvm = pvm;
    }

    @Override
    public String toString(){
        return pvm;
    }
}
