package com.example.exersarelab4_6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

public class AdaugareEcler extends AppCompatActivity {
    private String numeTV;
    private boolean clientFidelTV;
    private String monedaTV;
    private String umpluturaTV;
    private float ratingTV;
    private boolean alergiiTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_adaugare_ecler);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void AdaugaEcler(View view) {
        EditText nume1=findViewById(R.id.editTextTV);
        String denumire=nume1.getText().toString();

        CheckBox fidel = findViewById(R.id.CheckBoxTV);
        boolean clientFidel = fidel.isChecked();

        RadioButton moneda = findViewById(R.id.radioButtonTV);
        boolean esteEuro = moneda.isChecked();

        Spinner spinner = findViewById(R.id.SpinnerTV);
        String umplutura = spinner.getSelectedItem().toString();

        RatingBar rating = findViewById(R.id.RatingTV);
        float nota = rating.getRating();

        Switch alergii = findViewById(R.id.SwitchTV);
        boolean areAlergii = alergii.isChecked();

        Ecler ecler = new Ecler(denumire, clientFidel, esteEuro, umplutura, nota, areAlergii);

        Intent it = new Intent();
        Bundle bundle = new Bundle();

        bundle.putString("denumire", ecler.getDenumire());
        bundle.putBoolean("clientFidel", ecler.isClientFidel());
        bundle.putBoolean("esteEuro", ecler.isEsteEuro());
        bundle.putString("umplutura", ecler.getUmplutura());
        bundle.putFloat("rating", ecler.getRating());
        bundle.putBoolean("areAlergii", ecler.isAreAlergii());

        it.putExtras(bundle);
        setResult(RESULT_OK, it);
        finish();
    }
}