package com.example.rwh;

public class Ruoka{
    private String ruoka;
    private int kalorit;

    public Ruoka(String ruoka, int kalorit){
        this.ruoka = ruoka;
        this.kalorit = kalorit;
    }

    public String getRuoka() {
        return ruoka;
    }
    public int getKalorit(){
        return kalorit;
    }

}
