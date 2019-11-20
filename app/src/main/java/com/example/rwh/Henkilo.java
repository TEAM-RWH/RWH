package com.example.rwh;

public class Henkilo {
    private int pituus;
    private int paino;
    private int ika;
    private int urheiluKalorit;
    private int ruokaKalorit;

    public Henkilo(int pituus, int paino, int ika, int urheiluKalorit, int ruokaKalorit) {
        this.pituus = pituus;
        this.paino = paino;
        this.ika = ika;
        this.urheiluKalorit = urheiluKalorit;
        this.ruokaKalorit = ruokaKalorit;
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

    public int getUrheiluKalorit() {
        return urheiluKalorit;
    }

    public void setUrheiluKalorit(int urheiluKalorit) {
        this.urheiluKalorit = urheiluKalorit;
    }

    public int getRuokaKalorit() {
        return ruokaKalorit;
    }

    public void setRuokaKalorit(int ruokaKalorit) {
        this.ruokaKalorit = ruokaKalorit;
    }
}
