package com.example.tugasakhirgan;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import static com.android.volley.VolleyLog.TAG;

public class activity_perdaganganti extends AppCompatActivity {
    final int[] id_jasa = {0};
    Button bayar, login;
    RadioGroup radiogroup;
    RadioButton rb1, rb2, rb3, rb4;
    TextView total;
    String harga = "";
    EditText txt_pelaksana;
    final Calendar myCalendar= Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengembangansi);

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
        rb1 = findViewById(R.id.PengembanganSistemKeuangan_radio_btn);
        rb2 = findViewById(R.id.PengembanganSistemAkademik_radio_btn);
        rb3 = findViewById(R.id.PengembangabnSistemInformasiInventori_radio_btn);
        rb4 = findViewById(R.id.PengembanganSistemRencanaKerja_radio_btn);
        total = findViewById(R.id.tv_total);
        txt_pelaksana = findViewById(R.id.txtTglLaksana);
        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };
        txt_pelaksana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(activity_perdaganganti.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://webadminbensae.my.id/api_ta/JasaController/jasa_pengembangan_si",
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

                                        if (checkedId == R.id.PengembanganSistemKeuangan_radio_btn) {
                                            try {
                                                if (SharedPrefmanager.getInstance(activity_perdaganganti.this).isLoggedIn()) {
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
                                        } else if (checkedId == R.id.PengembanganSistemAkademik_radio_btn) {
                                            try {
                                                if (SharedPrefmanager.getInstance(activity_perdaganganti.this).isLoggedIn()) {
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
                                        } else if (checkedId == R.id.PengembangabnSistemInformasiInventori_radio_btn) {
                                            try {
                                                if (SharedPrefmanager.getInstance(activity_perdaganganti.this).isLoggedIn()) {
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
                                        } else if (checkedId == R.id.PengembanganSistemRencanaKerja_radio_btn) {
                                            try {
                                                if (SharedPrefmanager.getInstance(activity_perdaganganti.this).isLoggedIn()) {
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

                if (txt_pelaksana.getText().toString().trim().isEmpty()) {
                    Toast.makeText(activity_perdaganganti.this, "Masukkan Tanggal Pelaksanaan", Toast.LENGTH_SHORT).show();
                    txt_pelaksana.requestFocus();
                    return;
                }

                String date_parse = txt_pelaksana.getText().toString();
                Date date = null;
                DateFormat inputFormat = new SimpleDateFormat("dd MMMM yyyy");
                try {
                    date = inputFormat.parse(date_parse);
                    System.out.println(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
                String outputText = outputFormat.format(date);
                String pelaksanaan = outputText;

                userBayar(pelaksanaan);
            }
        });
    }

    private void userBayar(String pelaksanaan) {

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
                User user = SharedPrefmanager.getInstance(activity_perdaganganti.this).getUser();
                Map<String, String> params = new HashMap<>();
                params.put("id_user", String.valueOf(user.getId()));
                params.put("id_metode", "1");
                params.put("jasa", "pengembangan_si");
                params.put("id_jasa", String.valueOf(id_jasa[0]));
                params.put("harga", harga);
                params.put("pelaksanaan", pelaksanaan);
                params.put("id_transfer", "1");
                params.put("bukti_pembayaran", "");
                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
    private void updateLabel(){
        String myFormat="dd MMMM yyyy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat);
        txt_pelaksana.setText(dateFormat.format(myCalendar.getTime()));
    }

}