package com.example.dams9;

import android.os.Bundle;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import android.os.Handler;
import android.os.Looper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ActivitateVreme extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_activitate_vreme);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


            Executor executor = Executors.newSingleThreadExecutor();
            Handler handler = new Handler(Looper.getMainLooper());
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    String apiKey="zpka_6286d439e75d4d7d9339f6dd9b370d45_44e06529";
                    String link="https://dataservice.accuweather.com/locations/v1/cities/search?q=Paris";
                    try {
                        URL url=new URL(link);
                        HttpURLConnection conn=(HttpURLConnection) url.openConnection();
                        BufferedReader reader=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        StringBuilder builder=new StringBuilder("");
                        //pasrasre fiesier json
                        JSONArray vector=new JSONArray(builder.toString());
                        JSONObject obiect=vector.getJSONObject(0);
                        String cheieOras=obiect.getString("Key");
                    //partea 2 parsare rezultat
                        String linkVreme = "https://dataservice.accuweather.com/forecasts/v1/daily/1day/"
                                + cheieOras + "?apikey=" + apiKey + "&metric=true";
                        URL urlVreme=new URL(linkVreme);
                        HttpURLConnection connVreme = (HttpURLConnection) urlVreme.openConnection();
                        BufferedReader readerVreme=new BufferedReader(new InputStreamReader(connVreme.getInputStream()));
                        StringBuilder builderVreme=new StringBuilder("");


                    } catch (Exception e) {
                            throw new RuntimeException(e);
                    }

                }
            });

    }

}