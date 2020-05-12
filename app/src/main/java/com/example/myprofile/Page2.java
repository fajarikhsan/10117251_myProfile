package com.example.myprofile;

//Tanggal pengerjaan : 9-10-2020 sampai 12-10-2020
////NIM : 10117251
////Nama : Fajar Ikhsanul Faaizin
////Kelas : IF-8

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Page2 extends Fragment {

    View view;

    public Page2() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_page2, container, false);
        return view;
    }
}
