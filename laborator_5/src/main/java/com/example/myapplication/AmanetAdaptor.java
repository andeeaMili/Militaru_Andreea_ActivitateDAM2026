
package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

public class AmanetAdaptor extends BaseAdapter {

    private List<Amanet> amanets = null;
    private Context ctx;
    private int resursaAmanet;

    public AmanetAdaptor(List<Amanet> amanets, Context ctx, int resursaAmanet) {
        this.amanets = amanets;
        this.ctx = ctx;
        this.resursaAmanet = resursaAmanet;
    }

    @Override
    public int getCount() {
        return amanets.size();
    }

    @Override
    public Object getItem(int position) {
        return amanets.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = LayoutInflater.from(ctx);
            v = inflater.inflate(resursaAmanet, parent, false);
        }

        TextView denumireTV = v.findViewById(R.id.denumieTv);
        TextView administratorTV = v.findViewById(R.id.tvAdministrator);
        TextView locatieTV = v.findViewById(R.id.tvLocatie);
        TextView nrObiecteTV = v.findViewById(R.id.etNrObiecte);
        TextView profitTV = v.findViewById(R.id.etProfit);
        CheckBox fideliCB = v.findViewById(R.id.cbFideli);

        Amanet amanet = (Amanet) getItem(position);

        denumireTV.setText(amanet.getDenumire());
        administratorTV.setText(amanet.getNume_administrator());
        locatieTV.setText(amanet.getLocatie());
        nrObiecteTV.setText(String.valueOf(amanet.getNr_obiecte()));
        profitTV.setText(String.valueOf(
                amanet.getProfit_luna_anterioara()));

        fideliCB.setChecked(amanet.isClient_fideli());
        fideliCB.setClickable(false);
        fideliCB.setFocusable(false);

        return v;
    }
}
