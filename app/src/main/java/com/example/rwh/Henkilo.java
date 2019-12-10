package com.example.rwh;

import java.util.ArrayList;

public class Henkilo {

    private String nimi;
    private int pituus;
    private int paino;
    private int ika;
    private String sukupuoli;
    //private double tarvittavaEnergia;
    //private double saatuEnergia;
    //private double kulutettuEnenergia
    //private double aktiivisuusTaso;

    public Henkilo(String nimi, int pituus, int paino, int ika, String sukupuoli) {
        this.nimi = nimi;
        this.pituus = pituus;
        this.paino = paino;
        this.ika = ika;
        this.sukupuoli = sukupuoli;
    }

    @Override
    public String toString() {
        return this.nimi;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public int getPituus() {
        return pituus;
    }

    public void setPituus(int pituus) {
        this.pituus = pituus;
    }

    public int getPaino() {
        return paino;
    }

    public void setPaino(int paino) {
        this.paino = paino;
    }

    public int getIka() {
        return ika;
    }

    public void setIka(int ika) {
        this.ika = ika;
    }

    public String getSukupuoli() {
        return sukupuoli;
    }

    public void setSukupuoli(String sukupuoli) {
        this.sukupuoli = sukupuoli;
    }
}