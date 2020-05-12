package com.example.myprofile;

//Tanggal pengerjaan : 11-10-2020 sampai 12-10-2020
////NIM : 10117251
////Nama : Fajar Ikhsanul Faaizin
////Kelas : IF-8

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View.OnClickListener;

import java.nio.charset.Charset;
import java.util.Random;

public class ActivityInput extends AppCompatActivity implements OnClickListener {

    //Inisialisasi elemen layout
    private Button btn_simpan;
    private EditText et_nim, et_nama, et_kelas, et_telepon, et_email, et_insta;
    //Inisialisasi kontroller/Data Source
    private DBDataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        btn_simpan = (Button) findViewById(R.id.btn_simpan);
        btn_simpan.setOnClickListener(this);

        et_nim = (EditText) findViewById(R.id.et_nim);
        et_nama = (EditText) findViewById(R.id.et_nama);
        et_kelas = (EditText) findViewById(R.id.et_kelas);
        et_telepon = (EditText) findViewById(R.id.et_telepon);
        et_email = (EditText) findViewById(R.id.et_email);
        et_insta = (EditText) findViewById(R.id.et_insta);

        //Instansiasi kelas DBDataSource
        dataSource = new DBDataSource(this);

        //membuat sambungan baru ke database
        dataSource.open();
    }

    @Override
    public void onClick(View v) {
        // Inisialisasi data barang
        String nim = null;
        String nama = null;
        String kelas = null;
        String telepon = null;
        String email = null;
        String insta = null;
        @SuppressWarnings("unused")

        //inisialisasi barang baru (masih kosong)
        Model daftarTeman = null;
        if(et_nim.getText()!=null && et_nama.getText()!=null && et_kelas.getText()!=null
                && et_telepon.getText()!=null && et_email.getText()!=null && et_insta.getText()!=null)
        {
            /* jika field nim, nama, kelas, telepon, email dan insta tidak kosong
             * maka masukkan ke dalam data daftateman*/
            nim = et_nim.getText().toString();
            nama = et_nama.getText().toString();
            kelas = et_kelas.getText().toString();
            telepon = et_telepon.getText().toString();
            email = et_email.getText().toString();
            insta = et_insta.getText().toString();
        }

        switch(v.getId())
        {
            case R.id.btn_simpan:
                // insert data barang baru
                daftarTeman = dataSource.createBarang(nim, nama, kelas, telepon, email, insta);

                //konfirmasi kesuksesan
                Toast.makeText(this, "Data Teman Tersimpan\n" +
                        "nama" + daftarTeman.getNim() +
                        "merk" + daftarTeman.getNama(), Toast.LENGTH_LONG).show();
                finish();
                Intent intnt = new Intent(this, ActivityDaftar.class);
                startActivity(intnt);
                break;
        }

    }
}
