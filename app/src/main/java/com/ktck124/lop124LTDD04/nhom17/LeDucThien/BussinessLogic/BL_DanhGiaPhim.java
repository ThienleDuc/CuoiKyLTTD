package com.ktck124.lop124LTDD04.nhom17.LeDucThien.BussinessLogic;

import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;



import com.ktck124.lop124LTDD04.nhom17.LeDucThien.ProcessData.PD_DanhGiaPhim;
import com.ktck124.lop124LTDD04.nhom17.LeDucThien.adapter.DanhGiaPhimAdapter;
import com.ktck124.lop124LTDD04.nhom17.LeDucThien.entity.ent_DanhGiaPhim;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BL_DanhGiaPhim {
    private final PD_DanhGiaPhim pdDanhGiaPhim;

    public BL_DanhGiaPhim() {
        pdDanhGiaPhim = new PD_DanhGiaPhim();
    }

    // Phương thức lấy danh sách đánh giá phim
    private List<ent_DanhGiaPhim> getDanhSachDanhGiaPhim() {
        return pdDanhGiaPhim.getDanhGiaPhimFromProcedure();
    }

    // Phương thức chung để load danh sách đánh giá phim vào RecyclerView
    public void loadDanhGiaPhimToRecyclerView(Context context, RecyclerView recyclerView, DanhGiaPhimAdapter adapter) {
        if (recyclerView == null) {
            Log.w("BL_DanhGiaPhim", "RecyclerView is null. Data will not be loaded.");
            return;
        }

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                // Lấy danh sách đánh giá phim từ cơ sở dữ liệu
                List<ent_DanhGiaPhim> list = getDanhSachDanhGiaPhim();

                // Cập nhật RecyclerView trên UI thread
                if (context instanceof android.app.Activity) {
                    ((android.app.Activity) context).runOnUiThread(() -> {
                        pdDanhGiaPhim.loadDanhGiaPhimToRecyclerView(context, recyclerView, list,adapter);
                    });
                }
            } catch (Exception e) {
                Log.e("BL_DanhGiaPhim", "Error while loading data", e);
            } finally {
                executor.shutdown();
            }
        });
    }
}
