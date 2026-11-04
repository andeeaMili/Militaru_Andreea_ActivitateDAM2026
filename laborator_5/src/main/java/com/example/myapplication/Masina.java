package com.example.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Masina implements Parcelable {
    private int codMasina;
    private String model;
    private int nrUsi;
    private float pret;

    public Masina(int codMasina, String model, int nrUsi, float pret) {
        this.codMasina = codMasina;
        this.model = model;
        this.nrUsi = nrUsi;
        this.pret = pret;
    }

    protected Masina(Parcel in) {
        codMasina = in.readInt();
        model = in.readString();
        nrUsi = in.readInt();
        pret = in.readFloat();
    }

    public static final Creator<Masina> CREATOR = new Creator<Masina>() {
        @Override
        public Masina createFromParcel(Parcel in) {
            return new Masina(in);
        }

        @Override
        public Masina[] newArray(int size) {
            return new Masina[size];
        }
    };

    public int getCodMasina() {
        return codMasina;
    }

    public void setCodMasina(int codMasina) {
        this.codMasina = codMasina;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getNrUsi() {
        return nrUsi;
    }

    public void setNrUsi(int nrUsi) {
        this.nrUsi = nrUsi;
    }

    public float getPret() {
        return pret;
    }

    public void setPret(float pret) {
        this.pret = pret;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(codMasina);
        dest.writeString(model);
        dest.writeInt(nrUsi);
        dest.writeFloat(pret);
    }
}
