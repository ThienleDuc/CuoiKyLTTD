package com.ktck124.lop124LTDD04.nhom17.generalMethod;

import android.view.View;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

public class FragmentUtils {
    // Hàm load Fragment vào một container cụ thể
    public static void loadFragment(FragmentActivity activity, Fragment fragment, int containerId) {
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.replace(containerId, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    // Hàm hiển thị Overlay Fragment với animation
    public static void showOverlayFragment(FragmentActivity activity, Fragment fragment, int containerId) {
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.replace(containerId, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

        // Hiển thị FrameLayout chứa overlay
        FrameLayout overlayContainer = activity.findViewById(containerId);
        overlayContainer.setVisibility(View.VISIBLE);
    }

    // Hàm ẩn Overlay Fragment
    public static void hideOverlayFragment(FragmentActivity activity, int containerId) {
        Fragment overlayFragment = activity.getSupportFragmentManager().findFragmentById(containerId);
        if (overlayFragment != null) {
            activity.getSupportFragmentManager().beginTransaction().remove(overlayFragment).commit();
        }

        // Ẩn FrameLayout chứa overlay
        FrameLayout overlayContainer = activity.findViewById(containerId);
        overlayContainer.setVisibility(View.GONE);
    }

}