package com.example.myapplication;

public class Amanet {

   private String nume_administrator;
   private String denumire;
    private String locatie;
    private boolean client_fideli;
    private int nr_obiecte;
    private float profit_luna_anterioara;


    public Amanet(String nume_administrator, String denumire, String locatie, boolean client_fideli, int nr_obiecte, float profit_luna_anterioara) {
        this.nume_administrator = nume_administrator;
        this.denumire = denumire;
        this.locatie = locatie;
        this.client_fideli = client_fideli;
        this.nr_obiecte = nr_obiecte;
        this.profit_luna_anterioara = profit_luna_anterioara;
    }
}
