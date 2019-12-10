package com.example.rwh;

import java.util.ArrayList;

public class Ateria {
    //private String pvm;
    private String aterianNimi;
    private ArrayList<String> ruoat;

    public Ateria(String nimi, ArrayList<String> ruoat){
        //this.pvm = pvm;
        this.aterianNimi = nimi;
        this.ruoat = ruoat;
    }

    /*public String getAterianNimi() {
        return aterianNimi;
    }

    public void setAterianNimi(String aterianNimi) {
        this.aterianNimi = aterianNimi;
    }*/

    public ArrayList<String> getRuoat() {
        return ruoat;
    }

    public void setRuoat(ArrayList<String> ruoat) {
        this.ruoat = ruoat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ateria ateria = (Ateria) o;
        return aterianNimi.equals(ateria.aterianNimi) &&
                ruoat.equals(ateria.ruoat);
    }

    @Override
    public String toString(){
        return this.aterianNimi + "\n" + this.ruoat;
    }
}


