package com.example.dams8;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ActivitateLV extends AppCompatActivity {
private         List<ImaginiDomeniu>lista=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_activitate_lv);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;


        });
        List<String> linkuriImg=new ArrayList<>();
        List<Bitmap>imagini=new ArrayList<>();
        linkuriImg.add("https://www.kettleclub.co.uk/wp-content/themes/kettleclub/assets/img/brand-heroes/rolex.jpg");
        linkuriImg.add("https://lcdn.altex.ro/media/catalog/product/1/0/1000056883_05_ca6a70b2.jpg");
        linkuriImg.add("https://www.magazinuldepietre.ro/media/mageplaza/blog/post/c/e/ce_inseamna_karate_la_aur_ce_nu_stiai_despre_unitatea_de_masura_a_acestui_metal_pretios.jpg");
        linkuriImg.add("https://istyle.ro/cdn/shop/files/IMG-14772605_94c882eb-82b8-4b37-9349-acc53af23e60.jpg");
        linkuriImg.add("https://www.apple.com/newsroom/images/2026/09/apple-debuts-iphone-18-pro-and-iphone-18-pro-max/geo/Apple-iPhone-18-Pro-2up-Geo-260909_inline.jpg.large.jpg");
   Executor executor= Executors.newSingleThreadExecutor();
        Handler handler =new Handler(Looper.myLooper());
        executor.execute(new Runnable() {
            @Override
            public void run() {
                for (String link : linkuriImg) {
                    HttpURLConnection con = null;
                    try {
                        URL url = new URL(link);
                        con = (HttpURLConnection) url.openConnection();
                        InputStream is = con.getInputStream();
                        imagini.add(BitmapFactory.decodeStream(is));
                    } catch (Exception e) {

                    }
                }
            }
        }) ;
handler.post(new Runnable() {
    @Override
    public void run() {
    lista=new ArrayList<>();
    lista.add(new ImaginiDomeniu("Telefon", imagini.get(0),"https://www.apple.com/newsroom/images/2026/09/apple-debuts-iphone-18-pro-and-iphone-18-pro-max/geo/Apple-iPhone-18-Pro-2up-Geo-260909_inline.jpg.large.jpg" ) );
    lista.add(new ImaginiDomeniu("Ceas", imagini.get(1), "https://www.kettleclub.co.uk/wp-content/themes/kettleclub/assets/img/brand-heroes/rolex.jpg"));
    lista.add(new ImaginiDomeniu("Casti", imagini.get(2), "https://istyle.ro/cdn/shop/files/IMG-14772605_94c882eb-82b8-4b37-9349-acc53af23e60.jpg"));
    lista.add(new ImaginiDomeniu("Aur", imagini.get(3),"https://www.magazinuldepietre.ro/media/mageplaza/blog/post/c/e/ce_inseamna_karate_la_aur_ce_nu_stiai_despre_unitatea_de_masura_a_acestui_metal_pretios.jpg"));
    lista.add(new ImaginiDomeniu("PS5", imagini.get(4), "https://lcdn.altex.ro/media/catalog/product/1/0/1000056883_05_ca6a70b2.jpg"));
    }
});
    }
}