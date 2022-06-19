package com.example.tugasakhirgan;

import android.content.Intent;
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
        login = (Button) findViewById(R.id.btn_login);
        logout = (Button) findViewById(R.id.btn_logout);
        chat = (Button) findViewById(R.id.button2);
        pindahHal1 = findViewById(R.id.tap1);
        pindahHal2 = findViewById(R.id.tap2);
        pindahHal3 = findViewById(R.id.tap3);
        pindahHal4 = findViewById(R.id.tap4);

        if (SharedPrefmanager.getInstance(this).isLoggedIn()) {
            login.setVisibility(View.GONE);
            logout.setVisibility(View.VISIBLE);
        } else {
            login.setVisibility(View.VISIBLE);
            logout.setVisibility(View.GONE);
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

        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), chat.class);
                startActivity(i);
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
}