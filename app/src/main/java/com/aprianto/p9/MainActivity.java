package com.aprianto.p9;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements adapter_listener_karyawan {
    EditText ed_kode, ed_nama, ed_telp;
    TextView tv_hasil;
    Button btn_save, btn_update, btn_delete;
    SQLiteDatabase db;
    RecyclerView recyclerView;
    ArrayList<Model_Karyawan> list_data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed_kode = findViewById(R.id.ed_kode);
        ed_nama = findViewById(R.id.ed_nama);
        ed_telp = findViewById(R.id.ed_telp);
        btn_save = findViewById(R.id.btn_save);
        btn_update = findViewById(R.id.btn_update);
        btn_delete = findViewById(R.id.btn_delete);
//        tv_hasil = findViewById(R.id.tv_hasil);
        recyclerView = findViewById(R.id.rv_kry);

        load_data();




        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String kode = ed_kode.getText().toString();
                String nama = ed_nama.getText().toString();
                String telp = ed_telp.getText().toString();
                db.execSQL("insert into karyawan values('"+kode+"','"+nama+"','"+telp+"')");
                load_data();
            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String kode = ed_kode.getText().toString();
                String nama = ed_nama.getText().toString();
                String telp = ed_telp.getText().toString();
                db.execSQL("update karyawan set nama = '"+nama+"', telp = '"+telp+"' where kode = '"+kode+"'");
                load_data();
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String kode = ed_kode.getText().toString();
                db.execSQL("delete from karyawan where kode = '"+kode+"'");
                load_data();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        load_data();
    }

    void load_data(){
        db = openOrCreateDatabase("toko.db",MODE_PRIVATE,null);
        db.execSQL("create table if not exists karyawan (kode varchar, nama varchar, telp varchar);");
        Cursor cursor = db.rawQuery("select * from karyawan",null);
//        String hasil = "";
        list_data.clear();
        if(cursor.moveToFirst()){
            do {
                String kode = cursor.getString(cursor.getColumnIndex("kode"));
                String nama = cursor.getString(cursor.getColumnIndex("nama"));
                String telp = cursor.getString(cursor.getColumnIndex("telp"));
//                hasil = hasil+"\n"+kode+"     "+nama+"    "+telp;
                Model_Karyawan model = new Model_Karyawan();
                model.setKode(kode);
                model.setNama(nama);
                model.setTelp(telp);
                list_data.add(model);
            }while (cursor.moveToNext());
        }
//        tv_hasil.setText(hasil);
        Adapter_rv_karyawan adapter_rv = new Adapter_rv_karyawan(list_data,getApplicationContext(),this::onItemClicked);
        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,2));
        recyclerView.setAdapter(adapter_rv);
    }

    @Override
    public void onItemClicked(String kode, String nama, String telp) {
        ed_kode.setText(kode);
        ed_nama.setText(nama);
        ed_telp.setText(telp);
    }

    public void to_barang(View view) {
        Intent intent = new Intent(getApplicationContext(),Activity_Barang.class);
        startActivity(intent);
    }


}