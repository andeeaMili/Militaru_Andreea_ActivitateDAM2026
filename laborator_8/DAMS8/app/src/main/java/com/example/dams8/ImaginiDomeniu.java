package com.example.dams8;

import android.graphics.Bitmap;

public class ImaginiDomeniu {
    private String textAfisat;
    private Bitmap imagine;
    private String link;

    public ImaginiDomeniu(String textAfisat, Bitmap imagine, String link) {
        this.textAfisat = textAfisat;
        this.imagine = imagine;
        this.link = link;
    }
}
