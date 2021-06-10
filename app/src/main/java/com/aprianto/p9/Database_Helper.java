package com.aprianto.p9;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Database_Helper extends SQLiteOpenHelper {
    public Database_Helper(@Nullable Context context) {
        super(context, "toko.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table if not exists barang (kode varchar, nama varchar, satuan varchar);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists barang;");
    }

    void insert_data(Model_Barang barang){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues isi = new ContentValues();
        isi.put("kode",barang.getKode());
        isi.put("nama",barang.getNama());
        isi.put("satuan",barang.getSatuan());
        database.insert("barang",null,isi);
        database.close();
    }

    public ArrayList<Model_Barang> read_data(){
        ArrayList<Model_Barang> list_barang = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();
        String SQL = "select * from barang";
        Cursor c =database.rawQuery(SQL,null);
        if (c.moveToFirst()){
            do{
                Model_Barang barang = new Model_Barang();
                barang.setKode(c.getString(0));
                barang.setNama(c.getString(1));
                barang.setSatuan(c.getString(2));
                list_barang.add(barang);
            }while (c.moveToNext());
        }
        return list_barang;
    }

    void update_data(Model_Barang barang){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues isi = new ContentValues();
        isi.put("kode",barang.getKode());
        isi.put("nama",barang.getNama());
        isi.put("satuan",barang.getSatuan());
        database.update("barang",isi,"kode=?",new String[]{String.valueOf(barang.getKode())});
        database.close();
    }

    void delete_data(Model_Barang barang){
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete("barang","kode=?",new String[]{String.valueOf(barang.getKode())});
        database.close();
    }
}
