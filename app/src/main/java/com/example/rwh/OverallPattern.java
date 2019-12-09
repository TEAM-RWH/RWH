package com.example.rwh;

import java.util.ArrayList;
import java.util.HashMap;

public class OverallPattern {
    private static final OverallPattern ourInstance = new OverallPattern();

    public ArrayList<Henkilo> henkilot;
    public ArrayList<HashMap<String, ArrayList<Ateria>>> listaPaivanAterioista;
    public HashMap<String, ArrayList<Ateria>> paivanAteriat;
    public ArrayList<Ateria> ateriat;
    /*public ArrayList<ArrayList<Pvm>> paivienLista;
    public ArrayList<Pvm> listaPvmOlioista;
    public ArrayList<ArrayList<Ateria>> aterioidenLista;
    public ArrayList<Ateria> listaAteriaOlioista;*/
    public ArrayList<Ruoka> ruoat;

    public static OverallPattern getInstance() {
        return ourInstance;
    }

    private OverallPattern() {
        //paivienLista = new ArrayList<>();
        //aterioidenLista = new ArrayList<>();
        listaPaivanAterioista = new ArrayList<>();
        paivanAteriat = new HashMap<>();
        ateriat = new ArrayList<>();
        henkilot = new ArrayList<>();

        ruoat = new ArrayList<>();
        ruoat.add(new Ruoka("Amerikansalaatti/jäävuorisalaatti", 10));
        ruoat.add(new Ruoka("Herne pakaste", 123));
        ruoat.add(new Ruoka("Herne-maissi-paprikasekoitus", 69));
        ruoat.add(new Ruoka("Herne-maissi-porkkana(Pirkka)", 69));
        ruoat.add(new Ruoka("Kaali(valkokaali, keräkaali)", 28));
        /*ruoat.add(new Ruoka("Amerikansalaatti/jäävuorisalaatti", 10));
        ruoat.add(new Ruoka("Amerikansalaatti/jäävuorisalaatti", 10));
        ruoat.add(new Ruoka("Amerikansalaatti/jäävuorisalaatti", 10));
        ruoat.add(new Ruoka("Amerikansalaatti/jäävuorisalaatti", 10));
        ruoat.add(new Ruoka("Amerikansalaatti/jäävuorisalaatti", 10));
        ruoat.add(new Ruoka("Amerikansalaatti/jäävuorisalaatti", 10));
        ruoat.add(new Ruoka("Amerikansalaatti/jäävuorisalaatti", 10));
        ruoat.add(new Ruoka("Amerikansalaatti/jäävuorisalaatti", 10));
        ruoat.add(new Ruoka("Amerikansalaatti/jäävuorisalaatti", 10));
        ruoat.add(new Ruoka("Amerikansalaatti/jäävuorisalaatti", 10));
        ruoat.add(new Ruoka("Amerikansalaatti/jäävuorisalaatti", 10));
        ruoat.add(new Ruoka("Amerikansalaatti/jäävuorisalaatti", 10));
        ruoat.add(new Ruoka("Amerikansalaatti/jäävuorisalaatti", 10));
        ruoat.add(new Ruoka("Amerikansalaatti/jäävuorisalaatti", 10));
        ruoat.add(new Ruoka("Amerikansalaatti/jäävuorisalaatti", 10));
        ruoat.add(new Ruoka("Amerikansalaatti/jäävuorisalaatti", 10));
        ruoat.add(new Ruoka("Amerikansalaatti/jäävuorisalaatti", 10));
        ruoat.add(new Ruoka("Amerikansalaatti/jäävuorisalaatti", 10));
        ruoat.add(new Ruoka("Amerikansalaatti/jäävuorisalaatti", 10));
        ruoat.add(new Ruoka("Amerikansalaatti/jäävuorisalaatti", 10));
        ruoat.add(new Ruoka("Amerikansalaatti/jäävuorisalaatti", 10));
        ruoat.add(new Ruoka("Amerikansalaatti/jäävuorisalaatti", 10));
        */
        //paivat = new ArrayListMultimap<>();
    }

    /*public ArrayListMultimap getPaivat() {
        return paivat;
    }*/

    public ArrayList<Henkilo> getHenkilot() {
        return henkilot;
    }

    public HashMap<String, ArrayList<Ateria>> getPaivanAteriat(){
        return paivanAteriat;
    }

    /*public ArrayList<ArrayList<Ateria>> getAterioidenLista(){
        return aterioidenLista;
    }

    public ArrayList<Pvm> getListaPvmOlioista(){
        return  listaPvmOlioista;
    }*/

    public ArrayList<HashMap<String, ArrayList<Ateria>>> getListaPaivanAterioista(){
        return listaPaivanAterioista;
    }

    public ArrayList<Ruoka> getRuoat(){
     return ruoat;
    }

}