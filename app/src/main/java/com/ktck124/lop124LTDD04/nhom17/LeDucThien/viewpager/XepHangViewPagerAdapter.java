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
import com.ktck124.lop124LTDD04.nhom17.LeDucThien.fragment.xephangngay;
import com.ktck124.lop124LTDD04.nhom17.LeDucThien.fragment.xephangthang;
import com.ktck124.lop124LTDD04.nhom17.LeDucThien.fragment.xephangtuan;


import java.util.ArrayList;
import java.util.List;

public class XepHangViewPagerAdapter extends FragmentStateAdapter {

    private final List<Fragment> fragmentList = new ArrayList<>();

    public XepHangViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);

        fragmentList.add(new xephangngay());
        fragmentList.add(new xephangtuan());
        fragmentList.add(new xephangthang());

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