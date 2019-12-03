package com.example.rwh;

public class Pvm{
    private String pvm;
    private String mikaAteria;

    public Pvm(String pvm, String mikaAteria){
        this.pvm = pvm;
        this.mikaAteria = mikaAteria;
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
