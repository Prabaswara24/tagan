package com.example.tugasakhirgan;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class activity_pembayaran extends AppCompatActivity {
    TextView total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran);

        total = findViewById(R.id.tv_total);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("harga");
            //The key argument here must match that used in the other activity
            total.setText(value);
        }
    }
}