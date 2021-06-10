package com.aprianto.p9;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter_rv_karyawan extends RecyclerView.Adapter<Adapter_rv_karyawan.ViewHolder>{

    ArrayList<Model_Karyawan> list_data = new ArrayList<>();
    Context context;
    adapter_listener_karyawan adapter_listener;

    public Adapter_rv_karyawan(ArrayList<Model_Karyawan> list_data, Context context, adapter_listener_karyawan adapter_listener) {
        this.list_data = list_data;
        this.context = context;
        this.adapter_listener = adapter_listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_rv_karyawan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Model_Karyawan model = list_data.get(position);
        holder.tv_kode.setText(model.getKode());
        holder.tv_nama.setText(model.getNama());
        holder.tv_telp.setText(model.getTelp());
    }

    @Override
    public int getItemCount() {
        return list_data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_kode, tv_nama, tv_telp;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_kode = itemView.findViewById(R.id.tv_kode);
            tv_nama = itemView.findViewById(R.id.tv_nama);
            tv_telp = itemView.findViewById(R.id.tv_telp);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    adapter_listener.onItemClicked(
                            tv_kode.getText().toString(),
                            tv_nama.getText().toString(),
                            tv_telp.getText().toString()
                    );
                }
            });
        }
    }
}

interface adapter_listener_karyawan {
    public void onItemClicked(String kode,String nama, String telp);
}
