package com.example.rwh;

import java.util.ArrayList;

public class Liikkuminen {
    private String laji;
    private int kesto;

    public Liikkuminen(String nimi, int kesto){
        this.laji = nimi;
        this.kesto = kesto;
    }

    public String getLajiNimi() {
        return laji;
    }

    public void setLajiNimi(String laji)
    {
        this.laji = laji;
    }

    public int getKesto() {

        return kesto;
    }

    public void setKesto(int kesto) {
        this.kesto = kesto;
    }

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ateria ateria = (Ateria) o;
        return aterianNimi.equals(ateria.aterianNimi) &&
                ruoat.equals(ateria.ruoat);
    }*/
    //android:layout_width="176dp"
    //android:layout_height="27dp"
    @Override
    public String toString(){
        return this.laji + "\n" + this.kesto;
    }
}
