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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import static com.android.volley.VolleyLog.TAG;

public class activity_instalasiserver extends AppCompatActivity {
    final int[] id_jasa = {0};
    Button bayar, login;
    RadioGroup radiogroup;
    RadioButton rb1, rb2, rb3, rb4, rb5;
    TextView total;
    String harga = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instalasiserver);

        bayar = findViewById(R.id.btn_bayar);
        login = findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
            }
        });
        radiogroup = findViewById(R.id.radio_group);
        rb1 = findViewById(R.id.JasaInstalasiServerWindows_radio_btn);
        rb2 = findViewById(R.id.JasaSetupLinuxServer_radio_btn);
        rb3 = findViewById(R.id.JasaInstalasiBackupdanRestoreDataServer_radio_btn);
        rb4 = findViewById(R.id.JasaPerawatandanMaintenanceServerWindowsdanJaringan_radio_btn);
        rb5 = findViewById(R.id.JasaPerbaikanServerWindows_radio_btn);
        total = findViewById(R.id.tv_total);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://webadminbensae.my.id/api_ta/JasaController/jasa_instalasi_server",
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
                                rb5.setText(jsonObject.getJSONObject(4).getString("nama"));

                                radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                                    @Override
                                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                                        Log.d("chk", "id" + checkedId);

                                        if (checkedId == R.id.JasaInstalasiServerWindows_radio_btn) {
                                            try {
                                                if (SharedPrefmanager.getInstance(activity_instalasiserver.this).isLoggedIn()) {
                                                    login.setVisibility(View.GONE);
                                                    bayar.setVisibility(View.VISIBLE);
                                                } else {
                                                    login.setVisibility(View.VISIBLE);
                                                    bayar.setVisibility(View.GONE);
                                                }
                                                harga = jsonObject.getJSONObject(0).getString("harga");
                                                id_jasa[0] = jsonObject.getJSONObject(0).getInt("id");
                                                total.setText(jsonObject.getJSONObject(0).getString("harga"));
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        } else if (checkedId == R.id.JasaSetupLinuxServer_radio_btn) {
                                            try {
                                                if (SharedPrefmanager.getInstance(activity_instalasiserver.this).isLoggedIn()) {
                                                    login.setVisibility(View.GONE);
                                                    bayar.setVisibility(View.VISIBLE);
                                                } else {
                                                    login.setVisibility(View.VISIBLE);
                                                    bayar.setVisibility(View.GONE);
                                                }
                                                harga = jsonObject.getJSONObject(1).getString("harga");
                                                id_jasa[0] = jsonObject.getJSONObject(1).getInt("id");
                                                total.setText(jsonObject.getJSONObject(1).getString("harga"));
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        } else if (checkedId == R.id.JasaInstalasiBackupdanRestoreDataServer_radio_btn) {
                                            try {
                                                if (SharedPrefmanager.getInstance(activity_instalasiserver.this).isLoggedIn()) {
                                                    login.setVisibility(View.GONE);
                                                    bayar.setVisibility(View.VISIBLE);
                                                } else {
                                                    login.setVisibility(View.VISIBLE);
                                                    bayar.setVisibility(View.GONE);
                                                }
                                                harga = jsonObject.getJSONObject(2).getString("harga");
                                                id_jasa[0] = jsonObject.getJSONObject(2).getInt("id");
                                                total.setText(jsonObject.getJSONObject(2).getString("harga"));
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        } else if (checkedId == R.id.JasaPerawatandanMaintenanceServerWindowsdanJaringan_radio_btn) {
                                            try {
                                                if (SharedPrefmanager.getInstance(activity_instalasiserver.this).isLoggedIn()) {
                                                    login.setVisibility(View.GONE);
                                                    bayar.setVisibility(View.VISIBLE);
                                                } else {
                                                    login.setVisibility(View.VISIBLE);
                                                    bayar.setVisibility(View.GONE);
                                                }
                                                harga = jsonObject.getJSONObject(3).getString("harga");
                                                id_jasa[0] = jsonObject.getJSONObject(3).getInt("id");
                                                total.setText(jsonObject.getJSONObject(3).getString("harga"));
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        } else if (checkedId == R.id.JasaPerbaikanServerWindows_radio_btn) {
                                            try {
                                                if (SharedPrefmanager.getInstance(activity_instalasiserver.this).isLoggedIn()) {
                                                    login.setVisibility(View.GONE);
                                                    bayar.setVisibility(View.VISIBLE);
                                                } else {
                                                    login.setVisibility(View.VISIBLE);
                                                    bayar.setVisibility(View.GONE);
                                                }
                                                harga = jsonObject.getJSONObject(4).getString("harga");
                                                id_jasa[0] = jsonObject.getJSONObject(4).getInt("id");
                                                total.setText(jsonObject.getJSONObject(4).getString("harga"));
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

        bayar.setVisibility(View.GONE);
        login.setVisibility(View.GONE);

        bayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userBayar();
            }
        });
    }

    private void userBayar() {

        //if everything is fine
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://webadminbensae.my.id/api_ta/UserController/pembayaran",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "Response: " + response);
                        Toast.makeText(getApplicationContext(), "Memproses Pesanan", Toast.LENGTH_SHORT).show();
                        String value = harga;
                        Intent i = new Intent(getApplicationContext(), activity_pembayaran.class);
                        i.putExtra("harga", value);
                        startActivity(i);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                User user = SharedPrefmanager.getInstance(activity_instalasiserver.this).getUser();
                Map<String, String> params = new HashMap<>();
                params.put("id_user", String.valueOf(user.getId()));
                params.put("id_metode", "1");
                params.put("jasa", "jasa_instalasi_server");
                params.put("id_jasa", String.valueOf(id_jasa[0]));
                params.put("harga", harga);
                params.put("id_transfer", "1");
                params.put("bukti_pembayaran", "");
                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
}