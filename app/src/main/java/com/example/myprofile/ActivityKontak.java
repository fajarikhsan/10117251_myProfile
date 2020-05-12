package com.example.myprofile;

//Tanggal pengerjaan : 9-10-2020
////NIM : 10117251
////Nama : Fajar Ikhsanul Faaizin
////Kelas : IF-8

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class ActivityKontak extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private long backPressedTime;
    private Toast backToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kontak);

        Toolbar menuToolbar = findViewById(R.id.menuToolBar);
        setSupportActionBar(menuToolbar);
        getSupportActionBar().setTitle("Kontak");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawer = findViewById(R.id.drawer_kontak);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, menuToolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setCheckedItem(R.id.kontak);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case  R.id.home :
                Intent intent2 = new Intent(ActivityKontak.this,PrimaryActivity.class);
                finish();
                startActivity(intent2);
                break;

            case R.id.profile :
                Intent intent = new Intent(ActivityKontak.this,ActivityProfile.class);
                finish();
                startActivity(intent);
                break;

            case R.id.kontak :
                drawer.closeDrawer(GravityCompat.START);
                break;

            case R.id.daftar_teman :
                Intent intent1 = new Intent(ActivityKontak.this,ActivityDaftar.class);
                finish();
                startActivity(intent1);
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
}
