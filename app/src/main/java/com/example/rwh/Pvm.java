package com.example.rwh;

import java.util.ArrayList;

public class Pvm {
        private String paivamaara;
        private int aamupala;
        private int lounas;
        private int valipala;
        private int paivallinen;
        private int illallinen;

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

    public int getAamupala() {
        return aamupala;
    }

    public void setAamupala(int aamupala) {
        this.aamupala = aamupala;
    }

    public int getLounas() {
        return lounas;
    }

    public void setLounas(int lounas) {
        this.lounas = lounas;
    }

    public int getValipala() {
        return valipala;
    }

    public void setValipala(int valipala) {
        this.valipala = valipala;
    }

    public int getPaivallinen() {
        return paivallinen;
    }

    public void setPaivallinen(int paivallinen) {
        this.paivallinen = paivallinen;
    }

    public int getIllallinen() {
        return illallinen;
    }

    public void setIllallinen(int illallinen) {
        this.illallinen = illallinen;
    }
}
