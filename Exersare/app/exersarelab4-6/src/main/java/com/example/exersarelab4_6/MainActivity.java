package com.example.exersarelab4_6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void deschideAdaugare(View view) {
        Intent it = new Intent(this, AdaugareEcler.class);
        startActivityForResult(it, 70);
    }
    public void deschideDesignAI(View view) {
        Intent it = new Intent(this, DesignAI.class);
        startActivityForResult(it, 71);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == 70 || requestCode == 71)
                && resultCode == RESULT_OK && data != null)  {
            Bundle bundle = data.getExtras();



            if (bundle != null) {
                String denumire = bundle.getString("denumire", "");
                boolean clientFidel = bundle.getBoolean("clientFidel", false);
                boolean esteEuro = bundle.getBoolean("esteEuro", false);
                String umplutura = bundle.getString("umplutura", "");
                float rating = bundle.getFloat("rating", 0);
                boolean areAlergii = bundle.getBoolean("areAlergii", false);
                Ecler ecler = new Ecler(
                        denumire,
                        clientFidel,
                        esteEuro,
                        umplutura,
                        rating,
                        areAlergii
                );
                Toast.makeText(this, ecler.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }
}