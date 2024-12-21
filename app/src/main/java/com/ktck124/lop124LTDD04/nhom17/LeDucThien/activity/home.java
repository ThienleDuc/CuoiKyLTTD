package com.ktck124.lop124LTDD04.nhom17.LeDucThien.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;
import java.util.Map;

public class home extends AppCompatActivity {
    private Map<Integer, Fragment> fragmentMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        // Khởi tạo fragment map
        fragmentMap = new HashMap<>();
        fragmentMap.put(R.id.action_movies, new Movies());
        fragmentMap.put(R.id.action_search, new Search());
        fragmentMap.put(R.id.action_tickets, new Tickets());
        fragmentMap.put(R.id.action_profile, new Profile());

        BottomNavigationView bottomNavigationView = findViewById(R.id.home_bottom_navigation);

        // Kiểm tra xem có fragment nào trong fragment_container không
        if (savedInstanceState == null) {
            // Nếu chưa có fragment, thêm fragment mặc định (ví dụ: Movies)
            FragmentUtils.loadFragment(this, new Movies(), R.id.home_fragment_container);
        }

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = fragmentMap.get(item.getItemId());

                if (selectedFragment != null) {
                    FragmentUtils.loadFragment(home.this, selectedFragment, R.id.home_fragment_container);
                }

                return true;
            }
        });
    }
}