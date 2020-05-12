package com.example.myprofile;

//Tanggal pengerjaan : 12-10-2020
////NIM : 10117251
////Nama : Fajar Ikhsanul Faaizin
////Kelas : IF-8

import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBDataSource {

    //inisialiasi SQLite Database
    private SQLiteDatabase database;

    //inisialisasi kelas DBHelper
    private DBHelper dbHelper;

    //ambil semua nama kolom
    private String[] allColumns = { DBHelper.COLUMN_ID, DBHelper.COLUMN_NIM,
            DBHelper.COLUMN_NAMA, DBHelper.COLUMN_KELAS,DBHelper.COLUMN_TELEPON,
            DBHelper.COLUMN_EMAIL, DBHelper.COLUMN_INSTA};

    //DBHelper diinstantiasi pada constructor
    public DBDataSource(Context context) {
        dbHelper = new DBHelper(context);
    }

    //membuka/membuat sambungan baru ke database
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    //menutup sambungan ke database
    public void close() {
        dbHelper.close();
    }

    //method untuk create/insert barang ke database
    public Model createBarang(String nim, String nama, String kelas, String telepon,
                              String email, String insta) {

        // membuat sebuah ContentValues, yang berfungsi
        // untuk memasangkan data dengan nama-nama
        // kolom pada database
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_NIM, nim);
        values.put(DBHelper.COLUMN_NAMA, nama);
        values.put(DBHelper.COLUMN_KELAS, kelas);
        values.put(DBHelper.COLUMN_TELEPON, telepon);
        values.put(DBHelper.COLUMN_EMAIL, email);
        values.put(DBHelper.COLUMN_INSTA, insta);

        // mengeksekusi perintah SQL insert data
        // yang akan mengembalikan sebuah insert ID
        long insertId = database.insert(DBHelper.TABLE_NAME, null, values);

        // setelah data dimasukkan, memanggil
        // perintah SQL Select menggunakan Cursor untuk
        // melihat apakah data tadi benar2 sudah masuk
        // dengan menyesuaikan ID = insertID
        Cursor cursor = database.query(DBHelper.TABLE_NAME,
                allColumns, DBHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);

        // pindah ke data paling pertama
        cursor.moveToFirst();

        // mengubah objek pada kursor pertama tadi
        // ke dalam objek model
        Model newTeman = cursorToModel(cursor);

        // close cursor
        cursor.close();

        // mengembalikan teman baru
        return newTeman;
    }

    private Model cursorToModel(Cursor cursor)
    {
        // buat objek barang baru
        Model model = new Model();
        // debug LOGCAT
        Log.v("info", "The setLatLng "+cursor.getString(0)+","+cursor.getString(1));

        /* Set atribut pada objek model dengan
         * data kursor yang diambil dari database*/
        model.setId(cursor.getLong(0));
        model.setNim(cursor.getString(1));
        model.setNama(cursor.getString(2));
        model.setKelas(cursor.getString(3));
        model.setTelepon(cursor.getString(4));
        model.setEmail(cursor.getString(5));
        model.setInsta(cursor.getString(6));

        //kembalikan sebagai objek model
        return model;
    }

    //mengambil semua data barang
    public ArrayList<Model> getAllBarang() {
        ArrayList<Model> daftarBarang = new ArrayList<Model>();

        // select all SQL query
        Cursor cursor = database.query(DBHelper.TABLE_NAME,
                allColumns, null, null, null, null, null);

        // pindah ke data paling pertama
        cursor.moveToFirst();
        // jika masih ada data, masukkan data barang ke
        // daftar barang
        while (!cursor.isAfterLast()) {
            Model daftarTeman = cursorToModel(cursor);
            daftarBarang.add(daftarTeman);
            cursor.moveToNext();
        }
        // Make sure to close the cursor
        cursor.close();
        return daftarBarang;
    }

    //update barang yang diedit
    public void updateTeman(Model b)
    {
        //ambil id teman
        String strFilter = "id=" + b.getId();
        //memasukkan ke content values
        ContentValues args = new ContentValues();
        //masukkan data sesuai dengan kolom pada database
        args.put(DBHelper.COLUMN_NIM, b.getNim());
        args.put(DBHelper.COLUMN_NAMA, b.getNama());
        args.put(DBHelper.COLUMN_KELAS, b.getKelas());
        args.put(DBHelper.COLUMN_TELEPON, b.getTelepon());
        args.put(DBHelper.COLUMN_EMAIL, b.getEmail());
        args.put(DBHelper.COLUMN_INSTA, b.getInsta());
        //update query
        database.update(DBHelper.TABLE_NAME, args, strFilter, null);
    }

    // delete barang sesuai ID
    public void deleteBarang(long id)
    {
        String strFilter = "id=" + id;
        database.delete(DBHelper.TABLE_NAME, strFilter, null);
    }
}
