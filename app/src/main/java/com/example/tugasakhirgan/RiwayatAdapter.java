package com.example.tugasakhirgan;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class RiwayatAdapter extends RecyclerView.Adapter<RiwayatAdapter.HolderData> {

    private final List<RiwayatModel> mItems;
    private final Context context;

    /**
     * Deklarasi untuk menampung banyak data
     */
    public RiwayatAdapter(Context context, List<RiwayatModel> items) {
        this.mItems = items;
        this.context = context;
    }

    /**
     * Deklarasi layout_row yang menampung tiap data
     */
    @Override
    public HolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_riwayat, parent, false);
        HolderData holderData = new HolderData(layout);
        return holderData;
    }

    /**
     * Deklarasi tiap variabel yang menampung data sesuai dengan data di database
     */
    @Override
    public void onBindViewHolder(HolderData holder, int position) {
        RiwayatModel md = mItems.get(position);
        holder.txt_id.setText(md.getId());
        holder.txt_jasa.setText(md.getId_jasa());
        holder.txt_tgl.setText(md.getTanggal());
        holder.txt_tagihan.setText(md.getTagihan());
        holder.txt_status.setText(md.getStatus());
        holder.md = md;
    }

    /**
     * Menghitung banyaknya data yang diambil
     */
    @Override
    public int getItemCount() {
        return mItems.size();
    }


    /**
     * Membuat class HolderData turunan dari RecyclerView.ViewHolder
     */
    class HolderData extends RecyclerView.ViewHolder {
        TextView txt_id, txt_jasa, txt_tgl, txt_tagihan, txt_status;
        RiwayatModel md;

        /**
         * Deklarasikan setiap widget yang ada di layout_row
         */
        @SuppressLint("ResourceType")
        public HolderData(View view) {
            super(view);

            txt_id = itemView.findViewById(R.id.id);
            txt_jasa = itemView.findViewById(R.id.jasa);
            txt_tgl = itemView.findViewById(R.id.tgl);
            txt_tagihan = itemView.findViewById(R.id.tagihan);
            txt_status = itemView.findViewById(R.id.status);

            view.setOnClickListener(new View.OnClickListener() {
                /** Apabila salah satu data di klik maka akan menampilkan detail data*/
                @Override
                public void onClick(View view) {

                    Intent update = new Intent(context, ImageUpload.class);
                    update.putExtra("id", md.getId());
                    update.putExtra("status", md.getStatus());
                    context.startActivity(update);
                }
            });
        }
    }
}
