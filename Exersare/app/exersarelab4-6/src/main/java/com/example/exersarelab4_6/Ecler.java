package com.example.exersarelab4_6;

public class Ecler {

        private String denumire;
        private boolean clientFidel;
        private boolean esteEuro;
        private String umplutura;
        private float rating;
        private boolean areAlergii;

    public Ecler(String denumire, boolean clientFidel, boolean esteEuro, String umplutura, float rating, boolean areAlergii) {
        this.denumire = denumire;
        this.clientFidel = clientFidel;
        this.esteEuro = esteEuro;
        this.umplutura = umplutura;
        this.rating = rating;
        this.areAlergii = areAlergii;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public boolean isClientFidel() {
        return clientFidel;
    }

    public void setClientFidel(boolean clientFidel) {
        this.clientFidel = clientFidel;
    }

    public boolean isEsteEuro() {
        return esteEuro;
    }

    public void setEsteEuro(boolean esteEuro) {
        this.esteEuro = esteEuro;
    }

    public String getUmplutura() {
        return umplutura;
    }

    public void setUmplutura(String umplutura) {
        this.umplutura = umplutura;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public boolean isAreAlergii() {
        return areAlergii;
    }

    public void setAreAlergii(boolean areAlergii) {
        this.areAlergii = areAlergii;
    }

    @Override
    public String toString() {
        return "com.example.exersarelab4_6.Ecler{" +
                "denumire='" + denumire + '\'' +
                ", clientFidel=" + clientFidel +
                ", esteEuro=" + esteEuro +
                ", umplutura='" + umplutura + '\'' +
                ", rating=" + rating +
                ", areAlergii=" + areAlergii +
                '}';
    }
}
