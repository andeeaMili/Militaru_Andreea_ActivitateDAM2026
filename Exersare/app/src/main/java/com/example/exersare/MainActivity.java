package com.example.exersare;

import android.os.Bundle;
import android.util.Log;

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
    @Override
    protected void onStart() {
        Log.e("Seminar" , "OnStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.w("Seminar" , "OnResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Seminar" , "OnPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Seminar" , "OnStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v("Seminar" , "OnDestroy");
    }

}