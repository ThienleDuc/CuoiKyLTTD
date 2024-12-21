package com.ktck124.lop124LTDD04.nhom17.LeDucThien.viewpager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;



import com.ktck124.lop124LTDD04.nhom17.LeDucThien.fragment.dang_chieu;
import com.ktck124.lop124LTDD04.nhom17.LeDucThien.fragment.kham_pha;
import com.ktck124.lop124LTDD04.nhom17.LeDucThien.fragment.lich_chieu;
import com.ktck124.lop124LTDD04.nhom17.LeDucThien.fragment.rap_chieu;
import com.ktck124.lop124LTDD04.nhom17.LeDucThien.fragment.sap_chieu;
import com.ktck124.lop124LTDD04.nhom17.LeDucThien.fragment.xep_hang;

import java.util.ArrayList;
import java.util.List;

public class MoviesViewPagerAdapter extends FragmentStateAdapter {

    private final List<Fragment> fragmentList = new ArrayList<>();

    public MoviesViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);

        fragmentList.add(new kham_pha());
        fragmentList.add(new lich_chieu());
        fragmentList.add(new dang_chieu());
        fragmentList.add(new sap_chieu());
        fragmentList.add(new rap_chieu());
        fragmentList.add(new xep_hang());

    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentList.get(position);
    }


    @Override
    public int getItemCount() {
        return fragmentList.size();
    }
}