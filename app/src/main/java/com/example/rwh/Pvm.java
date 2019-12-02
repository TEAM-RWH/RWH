package com.example.rwh;

public class Pvm{
    private String pvm;

    public Pvm(String pvm){
        //super(pvm);
    }

    public String getPvm() {
        return pvm;
    }

    public void setPvm(String pvm) {
        this.pvm = pvm;
    }

    @Override
    public String toString(){
        return pvm;
    }
}
