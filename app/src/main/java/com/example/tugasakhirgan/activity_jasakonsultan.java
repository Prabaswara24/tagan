package com.example.tugasakhirgan;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class activity_jasakonsultan extends AppCompatActivity {
    Button bayar;
    RadioGroup radiogroup;
    RadioButton rb1, rb2, rb3, rb4;
    TextView total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jasakonsultan);

        radiogroup = findViewById(R.id.radio_group);
        rb1 = findViewById(R.id.KonsultasiPerancanganSistemInformasi_radio_btn);
        rb2 = findViewById(R.id.JasaKonsultasiPengembanganSistemIOT_radio_btn);
        rb3 = findViewById(R.id.JasaKonsultasiInstalasidanKonfigurasiPerangkatIT_radio_btn);
        rb4 = findViewById(R.id.JasaKonsultasiPengembanganSistemTrackingIndoordanOutdoor_radio_btn);
        total = findViewById(R.id.tv_total);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://webadminbensae.my.id/api_ta/JasaController/konsultasi",
                new Response.Listener<String>() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting response to json object
                            JSONObject obj = new JSONObject(response);

                            //if no error in response
                            if (obj.getBoolean("status")) {

                                JSONArray jsonObject = obj.getJSONArray("data");

                                rb1.setText(jsonObject.getJSONObject(0).getString("nama"));
                                rb2.setText(jsonObject.getJSONObject(1).getString("nama"));
                                rb3.setText(jsonObject.getJSONObject(2).getString("nama"));
                                rb4.setText(jsonObject.getJSONObject(3).getString("nama"));

                                radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                                    @Override
                                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                                        Log.d("chk", "id" + checkedId);

                                        if (checkedId == R.id.KonsultasiPerancanganSistemInformasi_radio_btn) {
                                            try {
                                                total.setText(jsonObject.getJSONObject(0).getString("harga"));
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        } else if (checkedId == R.id.JasaKonsultasiPengembanganSistemIOT_radio_btn) {
                                            try {
                                                total.setText(jsonObject.getJSONObject(1).getString("harga"));
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        } else if (checkedId == R.id.JasaKonsultasiInstalasidanKonfigurasiPerangkatIT_radio_btn) {
                                            try {
                                                total.setText(jsonObject.getJSONObject(2).getString("harga"));
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        } else if (checkedId == R.id.JasaKonsultasiPengembanganSistemTrackingIndoordanOutdoor_radio_btn) {
                                            try {
                                                total.setText(jsonObject.getJSONObject(3).getString("harga"));
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }

                                    }

                                });

                                Log.e("Successfully Get Data!", obj.toString());
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("Successfully Get Data!", e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

        bayar = (Button) findViewById(R.id.btn_bayar);


        bayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(activity_jasakonsultan.this, activity_pembayaran.class);
                startActivity(i);
            }
        });
    }
}