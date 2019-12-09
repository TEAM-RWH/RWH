package com.example.rwh;

import java.util.ArrayList;

public class OverallPattern {
    private static final OverallPattern ourInstance = new OverallPattern();

    public ArrayList<Henkilo> henkilot;
    public ArrayList<Pvm> paivamaarat;
    public final String[] ruoat;


    public static OverallPattern getInstance() {
        return ourInstance;
    }

    private OverallPattern() {

        henkilot = new ArrayList<>();
        paivamaarat = new ArrayList<>();
        ruoat = new String[]{
                "Amerikansalaatti/jäävuorisalaatti 10 kcal/100g",
                "Herne pakaste 123 kcal/100g",
                "Herne-maissi-paprikasekoitus 69 kcal/100g",
                "Kaali (valkokaali, keräkaali) 28 kcal/100g",
                "Kesäkurpitsa, Zucchini 18 kcal/100g",
                "Kiinankaali 20 kcal/100g",
                "Kurkku 11 kcal/100g",
                "Kurpitsa 17 kcal/100g",
                "Lanttu 29 kcal/100g",
                "Lehtisalaatti 10 kcal/100g",
                "Lehtiselleri 10 kcal/100g",
                "Linssit 320 kcal/100g",
                "Maissi pakaste, Pirkka 123 kcal/100g",
                "Mukulaselleri 30 kcal/100g",
                "Nauris 25 kcal/100g",
                "Palsternakka 77 kcal/100g",
                "Paprika 25 kcal/100g",
                "Paprika punainen ja keltainen 30 kcal/100g",
                "Parsakaali (broccoli) 30 kcal/100g",
                "Pavut, vihreät 35 kcal/100g",
                "Persilja, tuore 27 kcal/100g",
                "Porkkana 30 kcal/100g",
                "Punajuuri 36 kcal/100g",
                "Ruusukaali 20 kcal/100g",
                "Sipuli 30 kcal/100g",
                "Tilli, tuore 30 kcal/100g",
                "Tomaatti 23 kcal/100g",
                "Peruna 75 kcal/100g"

        };



    }
    public ArrayList<Pvm> getPaivamaarat(){
        return paivamaarat;
    }


    public ArrayList<Henkilo> getHenkilot() {
        return henkilot;
    }

    public String[] getRuoat(){
        return ruoat;
    }

}