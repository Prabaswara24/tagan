package com.example.tugasakhirgan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class riwayat extends AppCompatActivity {

    RiwayatAdapter adapter;
    List<RiwayatModel> mItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        mItems = new ArrayList<>();
        getNews();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RiwayatAdapter(this, mItems);
        recyclerView.setAdapter(adapter);
    }

    private void getNews() {
        User user = SharedPrefmanager.getInstance(this).getUser();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://webadminbensae.my.id/api_ta/UserController/riwayat/"+user.getId(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("Response: ", response.toString());
                        try {
                            JSONObject obj = new JSONObject(response);
                            JSONArray jsonArray = obj.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject news = jsonArray.getJSONObject(i);
                                RiwayatModel itemNews = new RiwayatModel();

                                itemNews.setId(news.getString("id"));

                                if (news.getString("jasa").equals("jasa_konsultasi")){
                                    itemNews.setId_jasa("Jasa Konsultasi");
                                }
                                else if (news.getString("jasa").equals("jasa_instalasi_server")){
                                    itemNews.setId_jasa("Jasa Instalasi Server");
                                }
                                else if (news.getString("jasa").equals("konfigurasi_jaringan")){
                                    itemNews.setId_jasa("Konfigurasi Jaringan");
                                }
                                else if (news.getString("jasa").equals("pengembangan_si")){
                                    itemNews.setId_jasa("Pengembangan SI");
                                }

                                SimpleDateFormat formatter = new SimpleDateFormat("yyy-MM-dd");
                                SimpleDateFormat formatterOut = new SimpleDateFormat("dd MMM yyyy");
                                Date date = formatter.parse(news.getString("pelaksanaan"));

                                itemNews.setTanggal(formatterOut.format(date));

                                itemNews.setTagihan(news.getString("harga"));
                                if (news.getString("validasi_pembayaran").equals("2") || news.getString("validasi_pembayaran").equals("3")){
                                    itemNews.setStatus("Terverifikasi");

                                }
                                else if (news.getString("validasi_pembayaran").equals("1")){
                                    itemNews.setStatus("Belum Terverifikasi");

                                }
                                mItems.add(itemNews);
                            }
                            adapter.notifyDataSetChanged();
                        } catch (JSONException | ParseException e) {
                            e.printStackTrace();
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
    }
}