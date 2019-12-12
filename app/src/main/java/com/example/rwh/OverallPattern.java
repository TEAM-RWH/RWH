package com.example.rwh;

import java.util.ArrayList;

/**
 * EnergyAgent sovelluksen Singleton -luokka.
 * @version 1.0
 * @author Kristian Wink
 * @since 21.10.2019
 */
public class OverallPattern {
    private static final OverallPattern ourInstance = new OverallPattern();

    public ArrayList<Henkilo> henkilot;
    public ArrayList<Pvm> paivamaarat;
    public ArrayList<String> ruokalista;

    /**
     * Palauttaa Singletonin tallennetun listan arvon.
     * @return
     */
    public static OverallPattern getInstance() {
        return ourInstance;
    }

    private OverallPattern() {
        paivamaarat = new ArrayList<>();
        henkilot = new ArrayList<>();
    }

    /**
     * Palauttaa ArrayListan, joka sisältää Henkilo -oliot.
     * @return
     */
    public ArrayList<Henkilo> getHenkilot() {
        return henkilot;
    }

    /**
     * Palauttaa ArrayListan, joka sisältää Pvm -oliot.
     * @return
     */
    public ArrayList<Pvm> getPaivamaarat(){
        return paivamaarat;
    }

    /**
     * Palauttaa ArrayListan, joka sisältää ruokalistan.
     * @return
     */
    public ArrayList<String> getRuokalista(){
        return  ruokalista;
    }
}


