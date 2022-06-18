package com.example.tugasakhirgan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity_konfigurasijaringan extends AppCompatActivity {
    Button bayar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfigurasijaringan);

        bayar = (Button) findViewById(R.id.btn_bayar);


        bayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Activity_konfigurasijaringan.this, activity_pembayaran.class);
                startActivity(i);
            }
        });
    }
}