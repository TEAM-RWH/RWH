package com.example.rwh;

/**
 * Mallintaa päiväämärän, jolla on päivänateriat sekä muut relevantit oliomuuttujat laskuja varten
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
        private int paivanPituus;
        private int paivanPaino;
        private int paivanIka;
        private String sukupuoli;
        private double aktiivisuustaso;
        private double tarvittavaEnergia;
        private double kokonaisKalorimaara;
        private double poltetutKalorit;

    /**
     * Luo päivämäärän aterioilla ja henkilön kyseisen päivän tiedoilla
     * @param paivamaara valitunpäivän päivämäärä.
     */

    public Pvm(String paivamaara, int pituus, int paino, int ika, String sukupuoli) {
        this.paivamaara = paivamaara;
        this.aamupala = 0;
        this.lounas = 0;
        this.lounas = 0;
        this.illallinen = 0;
        this.paivanPituus = pituus;
        this.paivanPaino = paino;
        this.paivanIka = ika;
        this.sukupuoli = sukupuoli;
        this.aktiivisuustaso = 0;
        this.tarvittavaEnergia = 0;
        this.poltetutKalorit = 0;
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
     * Asettaa syötetyn arvon välipala -muuttujalle.
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

    /**
     * Palauttaa muuttujan paivanPituus arvon.
     * @return
     */

    public int getPaivanPituus() {
        return paivanPituus;
    }

    /**
     * Asettaa syötetyn arvon paivanPituus -muuttujalle.
     * @param paivanPituus
     */

    public void setPaivanPituus(int paivanPituus) {
        this.paivanPituus = paivanPituus;
    }

    /**
     * Palauttaa muuttujan paivanPaino arvon.
     * @return
     */

    public int getPaivanPaino() {
        return paivanPaino;
    }

    /**
     * Asettaa syötetyn arvon paivanPaino -muuttujalle.
     * @param paivanPaino
     */

    public void setPaivanPaino(int paivanPaino) {
        this.paivanPaino = paivanPaino;
    }

    /**
     * Palauttaa muuttujan paivanIka arvon.
     * @return
     */

    public int getPaivanIka() {
        return paivanIka;
    }

    /**
     * Asettaa syötetyn arvon paivanIka -muuttujalle.
     * @param paivanIka
     */

    public void setPaivanIka(int paivanIka) {
        this.paivanIka = paivanIka;
    }

    /**
     * Palauttaa muuttujan sukupuoli arvon.
     * @return
     */

    public String getSukupuoli() {
        return sukupuoli;
    }

    /**
     * Asettaa syötetyn arvon sukupuoli -muuttujalle.
     * @param sukupuoli
     */

    public void setSukupuoli(String sukupuoli) {
        this.sukupuoli = sukupuoli;
    }

    /**
     * Palauttaa muuttujan aktiivisuustaso arvon.
     * @return
     */

    public double getAktiivisuustaso() {
        return aktiivisuustaso;
    }

    /**
     * Asettaa syötetyn arvon aktiivisuustaso -muuttujalle.
     * @param aktiivisuustaso
     */

    public void setAktiivisuustaso(double aktiivisuustaso) {
        this.aktiivisuustaso = aktiivisuustaso;
    }

    /**
     * Palauttaa muuttujan tarvittavaEnergia arvon.
     * @return
     */

    public double getTarvittavaEnergia() {
        if (this.sukupuoli.equals("Nainen")) {
            this.tarvittavaEnergia = (447.593 + ((9.247 * this.paivanPaino) + (3.098 * this.paivanPituus) - (4.330 * this.paivanIka)) * aktiivisuustaso);
        } else {
            this.tarvittavaEnergia = (88.362 + ((13.397 * this.paivanPaino) + (4.799 * this.paivanPituus) - (5.677 * this.paivanIka)) * getAktiivisuustaso());
        }
        return this.tarvittavaEnergia;
    }

    /**
     * Palauttaa muuttujan kokonaisKalorimaara arvon.
     * @return
     */

    public double getkokonaisKalorimaara(){
        this.kokonaisKalorimaara = this.aamupala + this.lounas + this.valipala + this.paivallinen + this.illallinen;
        return this.kokonaisKalorimaara;
    }

    /**
     * Asettaa syötetyn arvon poltetutKalorit -muuttujalle.
     * @param urheiluSuoritus
     */

    public void setPoltetutKalorit(double urheiluSuoritus){
        this.poltetutKalorit = this.poltetutKalorit + urheiluSuoritus;
    }

    /**
     * Palauttaa muuttujan poltetutKalorit arvon.
     * @return
     */

    public double getPoltetutKalorit(){
        return this.poltetutKalorit;
    }
}
