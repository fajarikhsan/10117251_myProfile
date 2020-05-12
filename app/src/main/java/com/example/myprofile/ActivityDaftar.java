package com.example.myprofile;

//Tanggal pengerjaan : 9-10-2020 sampai 12-10-2020
////NIM : 10117251
////Nama : Fajar Ikhsanul Faaizin
////Kelas : IF-8

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class ActivityDaftar extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private DrawerLayout drawer;
    //inisialisasi kontroller
    private DBDataSource dataSource;

    //inisialisasi arraylist
    private ArrayList<Model> values;

    private long backPressedTime;
    private Toast backToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        Toolbar menuToolbar = findViewById(R.id.menuToolBar);
        setSupportActionBar(menuToolbar);
        getSupportActionBar().setTitle("Daftar Teman");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawer = findViewById(R.id.drawer_daftar);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, menuToolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setCheckedItem(R.id.daftar_teman);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);

        dataSource = new DBDataSource(this);
        // buka kontroller
        dataSource.open();

        // ambil semua data barang
        values = dataSource.getAllBarang();

        // masukkan data barang ke array adapter
        ArrayAdapter<Model> adapter = new ArrayAdapter<Model>(this,
                android.R.layout.simple_list_item_1, values);

        // set adapter pada list
        ListView listView = (ListView) findViewById(R.id.lv_teman);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListView listView = (ListView) findViewById(R.id.lv_teman);
                final Model b = (Model) listView.getAdapter().getItem(position);
                String[] pilihan = {"Ubah","Hapus"};
                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityDaftar.this);
                builder.setTitle("Pilihan");
                builder.setItems(pilihan, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0 :
                                Intent i = new Intent(ActivityDaftar.this, ActivityEdit.class);
                                i.putExtra("id", b.getId());
                                i.putExtra("nim", b.getNim());
                                i.putExtra("nama", b.getNama());
                                i.putExtra("kelas", b.getKelas());
                                i.putExtra("telepon", b.getTelepon());
                                i.putExtra("email", b.getEmail());
                                i.putExtra("insta", b.getInsta());
                                finale();
                                startActivity(i);
                                break;

                            case 1 :
                                // Delete barang
                                dataSource.deleteBarang(b.getId());
                                dialog.dismiss();
                                finish();
                                startActivity(getIntent());
                                break;
                        }
                    }
                });
                builder.show();
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case  R.id.home :
                Intent intent2 = new Intent(ActivityDaftar.this,PrimaryActivity.class);
                finish();
                startActivity(intent2);
                break;

            case R.id.profile :
                Intent intent = new Intent(ActivityDaftar.this,ActivityProfile.class);
                finish();
                startActivity(intent);
                break;

            case R.id.kontak :
                Intent intent1 = new Intent(ActivityDaftar.this,ActivityKontak.class);
                finish();
                startActivity(intent1);
                break;

            case R.id.daftar_teman :
                drawer.closeDrawer(GravityCompat.START);
                break;

            case R.id.exit :
                finish();
                System.exit(0);
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (backPressedTime + 2000 > System.currentTimeMillis() && !drawer.isDrawerOpen(GravityCompat.START)) {
            backToast.cancel();
            super.onBackPressed();
            return;
        } else {
            backToast = Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab :
                Intent i = new Intent(this, ActivityInput.class);
                startActivity(i);
                break;
        }
    }

    //method yang dipanggil ketika edit data selesai
    public void finale()
    {
        dataSource.close();
    }
    @Override
    protected void onResume() {
        dataSource.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        dataSource.close();
        super.onPause();
    }
}
