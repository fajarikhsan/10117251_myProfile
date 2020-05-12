package com.example.myprofile;

//Tanggal pengerjaan : 12-10-2020
////NIM : 10117251
////Nama : Fajar Ikhsanul Faaizin
////Kelas : IF-8

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityEdit extends AppCompatActivity implements View.OnClickListener {

    private DBDataSource dataSource;

    private long id;
    private String nim;
    private String nama;
    private String kelas;
    private String telepon;
    private String email;
    private String insta;

    private EditText et_nimE;
    private EditText et_namaE;
    private EditText et_kelasE;
    private EditText et_teleponE;
    private EditText et_emailE;
    private EditText et_instaE;

    private TextView txId;

    private Button btn_simpanE;

    private Model daftarTeman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        //inisialisasi variabel
        et_nimE = (EditText) findViewById(R.id.et_nimE);
        et_namaE = (EditText) findViewById(R.id.et_namaE);
        et_kelasE = (EditText) findViewById(R.id.et_kelasE);
        et_teleponE = (EditText) findViewById(R.id.et_teleponE);
        et_emailE = (EditText) findViewById(R.id.et_emailE);
        et_instaE = (EditText) findViewById(R.id.et_instaE);

        //buat sambungan baru ke database
        dataSource = new DBDataSource(this);
        dataSource.open();

        // ambil data barang dari extras
        Bundle bun = this.getIntent().getExtras();
        id = bun.getLong("id");
        nim = bun.getString("nim");
        nama = bun.getString("nama");
        kelas = bun.getString("kelas");
        telepon = bun.getString("telepon");
        email = bun.getString("email");
        insta = bun.getString("insta");

        //masukkan data-data barang tersebut ke field editor
        et_nimE.setText(nim);
        et_namaE.setText(nama);
        et_kelasE.setText(kelas);
        et_teleponE.setText(telepon);
        et_emailE.setText(email);
        et_instaE.setText(insta);

        //set listener pada tombol
        btn_simpanE = (Button) findViewById(R.id.btn_simpanE);
        btn_simpanE.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        daftarTeman = new Model();
        daftarTeman.setNim(et_nimE.getText().toString());
        daftarTeman.setNama(et_namaE.getText().toString());
        daftarTeman.setKelas(et_kelasE.getText().toString());
        daftarTeman.setTelepon(et_teleponE.getText().toString());
        daftarTeman.setEmail(et_emailE.getText().toString());
        daftarTeman.setInsta(et_instaE.getText().toString());
        daftarTeman.setId(id);
        dataSource.updateTeman(daftarTeman);
        Intent i = new Intent(this, ActivityDaftar.class);
        startActivity(i);
        ActivityEdit.this.finish();
        dataSource.close();
    }

    @Override
    public void onBackPressed() {
        finish();
        dataSource.close();
        super.onBackPressed();
    }
}
