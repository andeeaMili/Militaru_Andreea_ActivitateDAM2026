
package com.example.dams8;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Adaptor extends BaseAdapter {

    private List<ImaginiDomeniu> img = null;
    private Context ctx;
    private int resursaLayout;

    public Adaptor(Context ctx, int resursaLayout, List<ImaginiDomeniu> img) {
        this.img = img;
        this.ctx = ctx;
        this.resursaLayout = resursaLayout;
    }

    @Override
    public int getCount() {
        return img.size();
    }

    @Override
    public Object getItem(int i) {
        return img.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = LayoutInflater.from(ctx);
        View v = inflater.inflate(resursaLayout, null, false);

        TextView textTV = v.findViewById(R.id.textAfisatTV);
        ImageView imagineIV = v.findViewById(R.id.imagineIV);

        ImaginiDomeniu imagine = (ImaginiDomeniu) getItem(i);

        textTV.setText(imagine.getTextAfisat());
        imagineIV.setImageBitmap(imagine.getImagine());

        return v;
    }
}
