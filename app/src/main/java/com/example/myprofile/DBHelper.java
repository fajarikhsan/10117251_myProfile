package com.example.myprofile;

//Tanggal pengerjaan : 12-10-2020
////NIM : 10117251
////Nama : Fajar Ikhsanul Faaizin
////Kelas : IF-8

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "tbl_daftarteman";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NIM = "nim";
    public static final String COLUMN_NAMA = "nama";
    public static final String COLUMN_KELAS = "kelas";
    public static final String COLUMN_TELEPON = "telepon";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_INSTA = "insta";
    private static final String db_name = "db_daftarteman.db";
    private static final int db_version = 1;

    //Perintah SQL untuk membuat tabel database baru
    private static final String db_create = "CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NIM + " VARCHAR(10) NOT NULL, "
            + COLUMN_NAMA + " VARCHAR(50), "
            + COLUMN_KELAS + " VARCHAR(5), "
            + COLUMN_TELEPON + " VARCHAR(12), "
            + COLUMN_EMAIL + " VARCHAR(50), "
            + COLUMN_INSTA + " VARCHAR(50));";

    public DBHelper(Context context) {
        super(context, db_name, null, db_version);
    }

    //Mengeksekusi perintah SQL di atas untuk membuat tabel database
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(db_create);
    }

    //dijalankan ketika ingin mengupgrade database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DBHelper.class.getName(), "Upgrading database from version "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
