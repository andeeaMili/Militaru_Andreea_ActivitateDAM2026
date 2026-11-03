
package com.example.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

public class Amanet implements Parcelable {

    private String nume_administrator;
    private String denumire;
    private String locatie;
    private boolean client_fideli;
    private int nr_obiecte;
    private float profit_luna_anterioara;

    public Amanet(String nume_administrator, String denumire,
                  String locatie, boolean client_fideli,
                  int nr_obiecte, float profit_luna_anterioara) {

        this.nume_administrator = nume_administrator;
        this.denumire = denumire;
        this.locatie = locatie;
        this.client_fideli = client_fideli;
        this.nr_obiecte = nr_obiecte;
        this.profit_luna_anterioara = profit_luna_anterioara;
    }

    protected Amanet(Parcel in) {
        nume_administrator = in.readString();
        denumire = in.readString();
        locatie = in.readString();
        client_fideli = in.readByte() != 0;
        nr_obiecte = in.readInt();
        profit_luna_anterioara = in.readFloat();
    }

    public static final Parcelable.Creator<Amanet> CREATOR =
            new Parcelable.Creator<Amanet>() {
                @Override
                public Amanet createFromParcel(Parcel in) {
                    return new Amanet(in);
                }

                @Override
                public Amanet[] newArray(int size) {
                    return new Amanet[size];
                }
            };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nume_administrator);
        dest.writeString(denumire);
        dest.writeString(locatie);
        dest.writeByte((byte) (client_fideli ? 1 : 0));
        dest.writeInt(nr_obiecte);
        dest.writeFloat(profit_luna_anterioara);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        return denumire + " - " + locatie +
                " - " + nume_administrator;
    }

    public String getNume_administrator() {
        return nume_administrator;
    }

    public String getDenumire() {
        return denumire;
    }

    public String getLocatie() {
        return locatie;
    }

    public boolean isClient_fideli() {
        return client_fideli;
    }

    public int getNr_obiecte() {
        return nr_obiecte;
    }

    public float getProfit_luna_anterioara() {
        return profit_luna_anterioara;
    }
}
