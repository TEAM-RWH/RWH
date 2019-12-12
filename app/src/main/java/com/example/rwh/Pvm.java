package com.example.rwh;

public class Pvm {
        private String paivamaara;
        private double aamupala;
        private double lounas;
        private double valipala;
        private double paivallinen;
        private double illallinen;

    public Pvm(String paivamaara) {
        this.paivamaara = paivamaara;
        this.aamupala = 0;
        this.lounas = 0;
        this.lounas = 0;
        this.illallinen = 0;
    }

    public String toString(){
        return this.paivamaara;
    }

    public String getPaivamaara() {
        return paivamaara;
    }

    public void setPaivamaara(String paivamaara) {
        this.paivamaara = paivamaara;
    }

    public double getAamupala() {
        return aamupala;
    }

    public void setAamupala(double aamupala) {
        this.aamupala = aamupala;
    }

    public double getLounas() {
        return lounas;
    }

    public void setLounas(double lounas) {
        this.lounas = lounas;
    }

    public double getValipala() {
        return valipala;
    }

    public void setValipala(double valipala) {
        this.valipala = valipala;
    }

    public double getPaivallinen() {
        return paivallinen;
    }

    public void setPaivallinen(double paivallinen) {
        this.paivallinen = paivallinen;
    }

    public double getIllallinen() {
        return illallinen;
    }

    public void setIllallinen(double illallinen) {
        this.illallinen = illallinen;
    }
}