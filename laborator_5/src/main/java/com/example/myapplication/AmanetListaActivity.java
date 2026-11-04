
package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AmanetListaActivity extends AppCompatActivity {

    private ArrayList<Amanet> listaAmanet = new ArrayList<>();
    private AmanetAdaptor adapter;
    private int pozitieSelectata = -1;

    private final ActivityResultLauncher<Intent> launcherEditare =
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

                            if (amanet != null &&
                                    pozitieSelectata >= 0 &&
                                    pozitieSelectata < listaAmanet.size()) {


                                listaAmanet.set(pozitieSelectata, amanet);


                                adapter.notifyDataSetChanged();

                                Toast.makeText(this,
                                        "Amanet modificat!",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }

                        pozitieSelectata = -1;
                    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amanet_lista);

        Bundle sursa;

        if (savedInstanceState != null) {
            sursa = savedInstanceState;
        } else {
            sursa = getIntent().getExtras();
        }

        if (sursa != null) {
            ArrayList<Amanet> listaPrimita;

            if (android.os.Build.VERSION.SDK_INT >= 33) {
                listaPrimita = sursa.getParcelableArrayList(
                        "lista", Amanet.class);
            } else {
                listaPrimita = sursa.getParcelableArrayList("lista");
            }

            if (listaPrimita != null) {
                listaAmanet.addAll(listaPrimita);
            }

            if (savedInstanceState != null) {
                pozitieSelectata = savedInstanceState
                        .getInt("pozitie", -1);
            }
        }


        ListView listView = findViewById(R.id.listViewAmanet);

        //adapterul personalizat
        adapter = new AmanetAdaptor(
                listaAmanet,
                this,
                R.layout.adaptor
        );

        listView.setAdapter(adapter);


        listView.setOnItemClickListener(
                (parent, view, position, id) -> {

                    Amanet amanet = listaAmanet.get(position);

                    Toast.makeText(this,
                            amanet.toString(),
                            Toast.LENGTH_SHORT).show();

                    pozitieSelectata = position;

                    Intent intent = new Intent(
                            AmanetListaActivity.this,
                            adaugare_amanet.class);

                    intent.putExtra("amanet", amanet);

                    launcherEditare.launch(intent);
                });

        listView.setOnItemLongClickListener(
                (parent, view, position, id) -> {

                    listaAmanet.remove(position);

                    adapter.notifyDataSetChanged();

                    Toast.makeText(this,
                            "Amanet sters!",
                            Toast.LENGTH_SHORT).show();

                    return true;
                });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("lista", listaAmanet);
        outState.putInt("pozitie", pozitieSelectata);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void finish() {
        Intent rezultat = new Intent();

        rezultat.putParcelableArrayListExtra(
                "lista", listaAmanet);

        setResult(RESULT_OK, rezultat);
        super.finish();
    }
}
