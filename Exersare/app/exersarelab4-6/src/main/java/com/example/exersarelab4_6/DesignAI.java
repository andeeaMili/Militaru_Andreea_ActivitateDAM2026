
package com.example.exersarelab4_6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DesignAI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design_ai);

        Spinner spinner = findViewById(R.id.aiUmplutura);

        String[] optiuni = {
                "Vanilie",
                "Ciocolată",
                "Fistic",
                "Caramel",
                "Frișcă"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                optiuni
        );

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        spinner.setAdapter(adapter);
    }

    public void adaugaEclerAI(View view) {

        EditText nume = findViewById(R.id.aiDenumire);
        CheckBox fidel = findViewById(R.id.aiClientFidel);
        RadioButton euro = findViewById(R.id.aiEuro);
        Spinner spinner = findViewById(R.id.aiUmplutura);
        RatingBar ratingBar = findViewById(R.id.aiRating);
        Switch alergii = findViewById(R.id.aiAlergii);

        String denumire = nume.getText().toString().trim();

        if (denumire.isEmpty()) {
            nume.setError("Introdu denumirea eclerului");
            return;
        }

        boolean clientFidel = fidel.isChecked();
        boolean esteEuro = euro.isChecked();
        String umplutura = spinner.getSelectedItem().toString();
        float rating = ratingBar.getRating();
        boolean areAlergii = alergii.isChecked();

        Ecler ecler = new Ecler(
                denumire,
                clientFidel,
                esteEuro,
                umplutura,
                rating,
                areAlergii
        );

        Bundle bundle = new Bundle();

        bundle.putString("denumire", ecler.getDenumire());
        bundle.putBoolean("clientFidel", ecler.isClientFidel());
        bundle.putBoolean("esteEuro", ecler.isEsteEuro());
        bundle.putString("umplutura", ecler.getUmplutura());
        bundle.putFloat("rating", ecler.getRating());
        bundle.putBoolean("areAlergii", ecler.isAreAlergii());

        Intent it = new Intent();
        it.putExtras(bundle);

        setResult(RESULT_OK, it);
        finish();
    }
}
