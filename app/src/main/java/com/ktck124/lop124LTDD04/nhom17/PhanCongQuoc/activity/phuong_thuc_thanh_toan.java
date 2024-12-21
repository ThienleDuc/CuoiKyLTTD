package com.ktck124.lop124LTDD04.nhom17.PhanCongQuoc.activity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ktck124.lop124LTDD04.nhom17.R;

public class phuong_thuc_thanh_toan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_phuong_thuc_thanh_toan);

    }
}