
package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Amanet> listaAmanet = new ArrayList<>();

    private final ActivityResultLauncher<Intent> launcherAdaugare =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {

                        if (result.getResultCode() == RESULT_OK
                                && result.getData() != null) {

                            Intent data = result.getData();
                            Amanet amanet;

                            if (android.os.Build.VERSION.SDK_INT >= 33) {
                                amanet = data.getParcelableExtra(
                                        "amanet", Amanet.class);
                            } else {
                                amanet = data.getParcelableExtra("amanet");
                            }

                            if (amanet != null) {
                                listaAmanet.add(amanet);

                                Toast.makeText(this,
                                        "Amanet adaugat!",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

    private final ActivityResultLauncher<Intent> launcherLista =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {

                        if (result.getResultCode() == RESULT_OK
                                && result.getData() != null) {

                            Intent data = result.getData();
                            ArrayList<Amanet> listaNoua;

                            if (android.os.Build.VERSION.SDK_INT >= 33) {
                                listaNoua = data.getParcelableArrayListExtra(
                                        "lista", Amanet.class);
                            } else {
                                listaNoua = data.getParcelableArrayListExtra(
                                        "lista");
                            }

                            if (listaNoua != null) {
                                listaAmanet.clear();
                                listaAmanet.addAll(listaNoua);
                            }
                        }
                    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            ArrayList<Amanet> salvata;

            if (android.os.Build.VERSION.SDK_INT >= 33) {
                salvata = savedInstanceState.getParcelableArrayList(
                        "lista", Amanet.class);
            } else {
                salvata = savedInstanceState.getParcelableArrayList("lista");
            }

            if (salvata != null) {
                listaAmanet.clear();
                listaAmanet.addAll(salvata);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("lista", listaAmanet);
        super.onSaveInstanceState(outState);
    }

    public void deschideAdaugare(View view) {
        Intent intent = new Intent(
                MainActivity.this, adaugare_amanet.class);

        launcherAdaugare.launch(intent);
    }

    public void deschideLista(View view) {
        Intent intent = new Intent(
                MainActivity.this, AmanetListaActivity.class);

        intent.putParcelableArrayListExtra(
                "lista", listaAmanet);

        launcherLista.launch(intent);
    }
}
