package com.aprianto.p9;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Activity_Barang extends AppCompatActivity implements adapter_listener_barang {
    EditText ed_kode, ed_nama, ed_satuan;
//    TextView tv_hasil;
    Button btn_save, btn_update, btn_delete, btn_to_karyawan;
    SQLiteDatabase db;
    RecyclerView recyclerView;
    ArrayList<Model_Barang> list_data = new ArrayList<>();
    Database_Helper helper_barang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_barang);

        helper_barang = new Database_Helper(getApplicationContext());

        ed_kode = findViewById(R.id.ed_kode);
        ed_nama = findViewById(R.id.ed_nama);
        ed_satuan = findViewById(R.id.ed_satuan);

        btn_save = findViewById(R.id.btn_save);
        btn_update = findViewById(R.id.btn_update);
        btn_delete = findViewById(R.id.btn_delete);
        btn_to_karyawan = findViewById(R.id.btn_to_karyawan);

//        tv_hasil = findViewById(R.id.tv_hasil);
        recyclerView = findViewById(R.id.rv_brg);
//        recyclerView = findViewById(R.id.rv_kry);
        load_data();


        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String kode = ed_kode.getText().toString();
                String nama = ed_nama.getText().toString();
                String satuan = ed_satuan.getText().toString();
                helper_barang.insert_data(new Model_Barang(kode,nama, satuan));
                load_data();
            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String kode = ed_kode.getText().toString();
                String nama = ed_nama.getText().toString();
                String satuan = ed_satuan.getText().toString();
                helper_barang.update_data(new Model_Barang(kode,nama, satuan));
                load_data();
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String kode = ed_kode.getText().toString();
                String nama = ed_nama.getText().toString();
                String satuan = ed_satuan.getText().toString();
                helper_barang.delete_data(new Model_Barang(kode,nama, satuan));
                load_data();
            }
        });

        btn_to_karyawan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    void load_data(){
        list_data.clear();
        list_data = helper_barang.read_data();
//        String hasil = "";
//        for(Model_Barang barang:list_data){
//            String kode = barang.getKode();
//            String nama = barang.getNama();
//            String satuan = barang.getSatuan();
//            hasil = hasil+"\n"+kode+" - "+nama+" - "+satuan;
//        }
//        tv_hasil.setText(hasil);

        Adapter_rv_barang adapter_rv = new Adapter_rv_barang(list_data,getApplicationContext(),this::onItemClicked);
        recyclerView.setLayoutManager(new LinearLayoutManager(Activity_Barang.this));
        recyclerView.setAdapter(adapter_rv);
//        Adapter_Rv adapter_rv = new Adapter_Rv(list_data,getApplicationContext(),this::onItemClicked);
//        recyclerView.setLayoutManager(new GridLayoutManager(Activity_Barang.this,2));
//        recyclerView.setAdapter(adapter_rv);
    }

    @Override
    public void onItemClicked(String kode, String nama, String satuan) {
        ed_kode.setText(kode);
        ed_nama.setText(nama);
        ed_satuan.setText(satuan);
    }
}