package com.example.laborator2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivitateRezultat extends AppCompatActivity {

    private int nr1; private int nr2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_activitate_noua2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent it=getIntent();
        Bundle bundle=it.getExtras();
        String mesaj=bundle.getString("mesaj", "");
        this.nr1=bundle.getInt("numar1", 0);
        this.nr2=bundle.getInt("numar2", 0);
        StringBuilder builder=new StringBuilder(mesaj);
        builder.append(" ");
        builder.append(this.nr1);
        builder.append(" ");
        builder.append(this.nr2);
        Toast.makeText(this, mesaj, Toast.LENGTH_SHORT).show();

    }

    public void intoarcereInActivitate(View view)
    {
        String altMesaj="Mesaj de intoarcere";
        int suma=this.nr1+this.nr2;
        Intent it=new Intent();
        it.putExtra("mesaj", altMesaj);
        it.putExtra("suma", suma);
        setResult(RESULT_OK, it);
        finish();
    }
}