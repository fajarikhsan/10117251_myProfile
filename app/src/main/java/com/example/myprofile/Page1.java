package com.example.myprofile;

//Tanggal pengerjaan : 9-10-2020 sampai 12-10-2020
////NIM : 10117251
////Nama : Fajar Ikhsanul Faaizin
////Kelas : IF-8

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


public class Page1 extends Fragment implements View.OnClickListener {

    View view;
    private ImageButton ib_daftar, ib_profil, ib_kontak, ib_exit;

    public Page1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_page1, container, false);
        ib_daftar = (ImageButton) view.findViewById(R.id.ib_daftar);
        ib_daftar.setOnClickListener(this);
        ib_profil = (ImageButton) view.findViewById(R.id.ib_profil);
        ib_profil.setOnClickListener(this);
        ib_kontak = (ImageButton) view.findViewById(R.id.ib_kontak);
        ib_kontak.setOnClickListener(this);
        ib_exit = (ImageButton) view.findViewById(R.id.ib_exit);
        ib_exit.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_daftar :
                Intent i1 = new Intent(getActivity(), ActivityDaftar.class);
                startActivity(i1);
                break;
            case R.id.ib_profil :
                Intent i2 = new Intent(getActivity(), ActivityProfile.class);
                startActivity(i2);
                break;
            case R.id.ib_kontak :
                Intent i3 = new Intent(getActivity(), ActivityKontak.class);
                startActivity(i3);
                break;
            case R.id.ib_exit :
                System.exit(0);
                break;
        }
    }
}
