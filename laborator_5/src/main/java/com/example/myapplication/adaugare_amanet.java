
package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class adaugare_amanet extends AppCompatActivity {

    private EditText etAdministrator;
    private EditText etDenumire;
    private EditText etLocatie;
    private EditText etNrObiecte;
    private EditText etProfit;
    private CheckBox cbFideli;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adaugare_amanet);

        etAdministrator = findViewById(R.id.etAdministrator);
        etDenumire = findViewById(R.id.etDenumire);
        etLocatie = findViewById(R.id.etLocatie);
        etNrObiecte = findViewById(R.id.etNrObiecte);
        etProfit = findViewById(R.id.etProfit);
        cbFideli = findViewById(R.id.cbFideli);

        Intent intent = getIntent();
        Amanet amanet = null;

        if (intent.hasExtra("amanet")) {
            if (android.os.Build.VERSION.SDK_INT >= 33) {
                amanet = intent.getParcelableExtra(
                        "amanet", Amanet.class);
            } else {
                amanet = intent.getParcelableExtra("amanet");
            }
        }

        // Daca modificam, completam campurile
        if (amanet != null) {

            etAdministrator.setText(
                    amanet.getNume_administrator());

            etDenumire.setText(
                    amanet.getDenumire());

            etLocatie.setText(
                    amanet.getLocatie());

            cbFideli.setChecked(
                    amanet.isClient_fideli());

            etNrObiecte.setText(
                    String.valueOf(amanet.getNr_obiecte()));

            etProfit.setText(
                    String.valueOf(
                            amanet.getProfit_luna_anterioara()));
        }
    }

    public void culegeInfo(View view) {

        String administrator = etAdministrator
                .getText().toString().trim();

        String denumire = etDenumire
                .getText().toString().trim();

        String locatie = etLocatie
                .getText().toString().trim();

        boolean fideli = cbFideli.isChecked();

        String textObiecte = etNrObiecte
                .getText().toString().trim();

        String textProfit = etProfit
                .getText().toString().trim();

        if (administrator.isEmpty() ||
                denumire.isEmpty() ||
                locatie.isEmpty() ||
                textObiecte.isEmpty() ||
                textProfit.isEmpty()) {

            Toast.makeText(this,
                    "Completati toate campurile!",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        int nrObiecte;
        float profit;

        try {
            nrObiecte = Integer.parseInt(textObiecte);

            profit = Float.parseFloat(
                    textProfit.replace(',', '.'));

            if (nrObiecte < 0 || !Float.isFinite(profit)) {
                throw new NumberFormatException();
            }

        } catch (NumberFormatException e) {
            Toast.makeText(this,
                    "Introduceti valori numerice valide!",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        Amanet amanet = new Amanet(
                administrator,
                denumire,
                locatie,
                fideli,
                nrObiecte,
                profit
        );

        Intent rezultat = new Intent();
        Bundle bundle = new Bundle();

        bundle.putParcelable("amanet", amanet);
        rezultat.putExtras(bundle);

        setResult(RESULT_OK, rezultat);
        finish();
    }
}
