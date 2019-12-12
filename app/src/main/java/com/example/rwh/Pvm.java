package com.example.rwh;

/**
 * Mallintaa päiväämärän, jolla on päivänateriat.
 * @version 1.0
 * @author Lauri Riikonen
 * @since 21.10.2019
 */

public class Pvm {
        private String paivamaara;
        private double aamupala;
        private double lounas;
        private double valipala;
        private double paivallinen;
        private double illallinen;

    /**
     * Luo päivämäärän aterioilla.
     * @param paivamaara valitunpäivän päivämäärä.
     */
    public Pvm(String paivamaara) {
        this.paivamaara = paivamaara;
        this.aamupala = 0;
        this.lounas = 0;
        this.lounas = 0;
        this.illallinen = 0;
    }

    /**
     * Palauttaa päivämäärän arvon String -muodossa.
     * @return
     */
    public String toString(){
        return this.paivamaara;
    }

    /**
     * Palauttaa päivämäärän arvon String -muodossa.
     * @return
     */
    public String getPaivamaara() {
        return paivamaara;
    }

    /**
     * Asettaa syötetyn arvon paivamaara -muuttujalle.
     * @param paivamaara
     */
    public void setPaivamaara(String paivamaara) {
        this.paivamaara = paivamaara;
    }

    /**
     * Palauttaa muuttujan -aamupala arvon.
     * @return
     */
    public double getAamupala() {
        return aamupala;
    }

    /**
     * Asettaa syötetyn arvon aamupala -muuttujalle.
     * @param aamupala
     */
    public void setAamupala(double aamupala) {
        this.aamupala = aamupala;
    }

    /**
     * Palauttaa muuttujan -lounas arvon.
     * @return
     */
    public double getLounas() {
        return lounas;
    }

    /**
     * Asettaa syötetyn arvon lounas -muuttujalle.
     * @param lounas
     */
    public void setLounas(double lounas) {
        this.lounas = lounas;
    }

    /**
     * Palauttaa muuttujan -välipala arvon.
     * @return
     */
    public double getValipala() {
        return valipala;
    }

    /**
     * Asettaa syötetyn arvon lounas -muuttujalle.
     * @param valipala
     */
    public void setValipala(double valipala) {
        this.valipala = valipala;
    }

    /**
     * Palauttaa muuttujan paivallinen arvon.
     * @return
     */
    public double getPaivallinen() {
        return paivallinen;
    }

    /**
     * Asettaa syötetyn arvon paivallinen -muuttujalle.
     * @param paivallinen
     */
    public void setPaivallinen(double paivallinen) {
        this.paivallinen = paivallinen;
    }

    /**
     * Palauttaa muuttujan illallinen arvon.
     * @return
     */
    public double getIllallinen() {
        return illallinen;
    }

    /**
     * Asettaa syötetyn arvon paivallinen -muuttujalle.
     * @param illallinen
     */
    public void setIllallinen(double illallinen) {
        this.illallinen = illallinen;
    }
}