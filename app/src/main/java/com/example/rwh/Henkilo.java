package com.example.rwh;

public class Henkilo {

    private String nimi;
    private int pituus;
    private int paino;
    private int ika;

    public Henkilo(String nimi, int pituus, int paino, int ika) {
        this.nimi = nimi;
        this.pituus = pituus;
        this.paino = paino;
        this.ika = ika;
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

}