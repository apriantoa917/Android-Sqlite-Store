package com.aprianto.p9;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter_rv_barang extends RecyclerView.Adapter<Adapter_rv_barang.ViewHolder>{

    ArrayList<Model_Barang> list_data = new ArrayList<>();
    Context context;
    adapter_listener_karyawan adapter_listener;

    public Adapter_rv_barang(ArrayList<Model_Barang> list_data, Context context, adapter_listener_karyawan adapter_listener) {
        this.list_data = list_data;
        this.context = context;
        this.adapter_listener = adapter_listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_rv_barang, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Model_Barang model = list_data.get(position);
        holder.tv_kode.setText(model.getKode());
        holder.tv_nama.setText(model.getNama());
        holder.tv_satuan.setText(model.getSatuan());
    }

    @Override
    public int getItemCount() {
        return list_data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_kode, tv_nama, tv_satuan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_kode = itemView.findViewById(R.id.tv_kode);
            tv_nama = itemView.findViewById(R.id.tv_nama);
            tv_satuan = itemView.findViewById(R.id.tv_satuan);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    adapter_listener.onItemClicked(
                            tv_kode.getText().toString(),
                            tv_nama.getText().toString(),
                            tv_satuan.getText().toString()
                    );
                }
            });
        }
    }
}

interface adapter_listener_barang {
    public void onItemClicked(String kode,String nama, String satuan);
}
