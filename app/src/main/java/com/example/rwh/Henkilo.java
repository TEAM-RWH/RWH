package com.example.rwh;

/**
 * Mallintaa henkilön ominaisuuksilla: nimi, pituus, paino, ikä ja sukupuoli.
 * @version 1.0
 * @author Lauri Riikonen
 * @since 21.10.2019
 */

public class Henkilo {

    private String nimi;
    private int pituus;
    private int paino;
    private int ika;
    private String sukupuoli;

    /**
     * Luo henkilön, jolla on nimi, pituus, paino, ikä ja sukupuoli.
     * @param nimi henkilön nimi
     * @param pituus henkilön pituus
     * @param paino henkilön paino
     * @param ika henkilön ikä
     * @param sukupuoli henkilön sukupuoli
     */

    public Henkilo(String nimi, int pituus, int paino, int ika, String sukupuoli) {
        this.nimi = nimi;
        this.pituus = pituus;
        this.paino = paino;
        this.ika = ika;
        this.sukupuoli = sukupuoli;
    }

    /**
     * Palauttaa henkilön nimen.
     * @return
     */

    @Override
    public String toString() {
        return this.nimi;
    }

    /**
     * Palauttaa henkilön nimen.
     * @return
     */

    public String getNimi() {
        return nimi;
    }

    /**
     * Asettaa nimen Henkilo -oliolle.
     * @param nimi
     */

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    /**
     * Palauttaa Henkilo -olion pituuden.
     * @return
     */

    public int getPituus() {
        return pituus;
    }

    /**
     * Asettaa pituuden Henkilo -oliolle.
     * @param pituus
     */

    public void setPituus(int pituus) {
        this.pituus = pituus;
    }

    /**
     * Palauttaa Henkilo -olion painon.
     * @return
     */

    public int getPaino() {
        return paino;
    }

    /**
     * Asettaa painon Henkilo -oliolle.
     * @param paino
     */

    public void setPaino(int paino) {
        this.paino = paino;
    }

    /**
     * Palauttaa Henkilo -olion iän.
     * @return
     */

    public int getIka() {
        return ika;
    }

    /**
     * Asettaa iän Henkilo -oliolle.
     * @param ika
     */

    public void setIka(int ika) {
        this.ika = ika;
    }

    /**
     * Palauttaa Henkilo -olion sukupuolen.
     * @return
     */

    public String getSukupuoli() {
        return sukupuoli;
    }

    /**
     * Asettaa sukupuolen Henkilo -oliolle.
     * @param sukupuoli
     */

    public void setSukupuoli(String sukupuoli) {
        this.sukupuoli = sukupuoli;
    }
}

