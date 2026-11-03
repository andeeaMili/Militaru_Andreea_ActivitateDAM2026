
package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AmanetListaActivity extends AppCompatActivity {

    private ArrayList<Amanet> listaAmanet = new ArrayList<>();
    private AmanetAdapter adapter;
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

                                // Modificam obiectul, NU adaugam altul
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

        adapter = new AmanetAdapter(this, listaAmanet);
        listView.setAdapter(adapter);

        // Click simplu - Toast + modificare
        listView.setOnItemClickListener(
                (parent, view, position, id) -> {

                    Amanet amanet = listaAmanet.get(position);

                    Toast.makeText(this,
                            amanet.toString(),
                            Toast.LENGTH_SHORT).show();

                    pozitieSelectata = position;

                    Intent intent = new Intent(
                            this, adaugare_amanet.class);

                    intent.putExtra("amanet", amanet);

                    launcherEditare.launch(intent);
                });

        // Click lung - stergere
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
        rezultat.putParcelableArrayListExtra("lista", listaAmanet);
        setResult(RESULT_OK, rezultat);
        super.finish();
    }

    // Adapter personalizat, in aceeasi clasa
    private static class AmanetAdapter extends ArrayAdapter<Amanet> {

        public AmanetAdapter(Context context,
                             ArrayList<Amanet> lista) {
            super(context, 0, lista);
        }

        @Override
        public View getView(int position, View convertView,
                            ViewGroup parent) {

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext())
                        .inflate(R.layout.item_amanet,
                                parent, false);
            }

            Amanet amanet = getItem(position);

            TextView tvDenumire =
                    convertView.findViewById(R.id.tvDenumire);
            TextView tvAdministrator =
                    convertView.findViewById(R.id.tvAdministrator);
            TextView tvLocatie =
                    convertView.findViewById(R.id.tvLocatie);
            TextView tvDetalii =
                    convertView.findViewById(R.id.tvDetalii);

            if (amanet != null) {
                tvDenumire.setText(
                        amanet.getDenumire());

                tvAdministrator.setText(
                        "Administrator: " +
                                amanet.getNume_administrator());

                tvLocatie.setText(
                        "Locatie: " + amanet.getLocatie());

                tvDetalii.setText(
                        "Obiecte: " + amanet.getNr_obiecte() +
                                " | Profit: " +
                                amanet.getProfit_luna_anterioara() +
                                " lei\nClienti fideli: " +
                                (amanet.isClient_fideli() ? "Da" : "Nu"));
            }

            return convertView;
        }
    }
}
