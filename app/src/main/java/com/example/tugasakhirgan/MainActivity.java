package com.example.tugasakhirgan;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button login, logout, chat;
    ImageView pindahHal1;
    ImageView pindahHal2;
    ImageView pindahHal3;
    ImageView pindahHal4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chat = (Button) findViewById(R.id.btn_chat);
        login = (Button) findViewById(R.id.btn_login);
        logout = (Button) findViewById(R.id.btn_logout);
        pindahHal1 = findViewById(R.id.tap1);
        pindahHal2 = findViewById(R.id.tap2);
        pindahHal3 = findViewById(R.id.tap3);
        pindahHal4 = findViewById(R.id.tap4);

        if (SharedPrefmanager.getInstance(this).isLoggedIn()) {
            login.setVisibility(View.GONE);
            logout.setVisibility(View.VISIBLE);
            chat.setVisibility(View.VISIBLE);
        } else {
            login.setVisibility(View.VISIBLE);
            logout.setVisibility(View.GONE);
            chat.setVisibility(View.GONE);
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                SharedPrefmanager.getInstance(getApplicationContext()).logout();
            }
        });

        pindahHal1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, activity_jasakonsultan.class);
                startActivity(i);
            }
        });
        pindahHal2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Activity_konfigurasijaringan.class);
                startActivity(i);
            }
        });
        pindahHal3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, activity_perdaganganti.class);
                startActivity(i);
            }
        });
        pindahHal4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, activity_instalasiserver.class);
                startActivity(i);
            }
        });
    }

    public void openWA(View view) {
        String number = "6282230535038";
        String url = "https://api.whatsapp.com/send?phone=" + number + "&text=Halo%20Admin";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setPackage("com.whatsapp");
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}