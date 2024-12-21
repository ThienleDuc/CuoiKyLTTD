package com.ktck124.lop124LTDD04.nhom17.LeDucThien.ProcessData;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ktck124.lop124LTDD04.nhom17.LeDucThien.adapter.DanhGiaPhimAdapter;
import com.ktck124.lop124LTDD04.nhom17.LeDucThien.adapter.ThoiGianChieuAdapter;
import com.ktck124.lop124LTDD04.nhom17.LeDucThien.entity.ent_DanhGiaPhim;
import com.ktck124.lop124LTDD04.nhom17.LeDucThien.entity.ent_ThoiGianChieu;
import com.ktck124.lop124LTDD04.nhom17.R;
import com.ktck124.lop124LTDD04.nhom17.generalMethod.ConnectionDatabase;
import com.ktck124.lop124LTDD04.nhom17.generalMethod.SpaceItemDecoration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PD_ThoiGianChieu {
    private static final String TAG = "PD_ThoiGianChieu";

    // Phương thức mở kết nối
    private Connection getConnection() throws SQLException {
        Connection connection = new ConnectionDatabase().getConnection();
        if (connection == null) {
            throw new SQLException("Không thể kết nối đến cơ sở dữ liệu.");
        }
        return connection;
    }

    // Phương thức lấy thời gian bắt đầu và kết thúc của phim theo mã phim, rạp chiếu con, và thời gian chiếu
    public List<ent_ThoiGianChieu> getThoiGianBatDauKetThucTheoMaPhimVaRapChieuCon(int maPhim, int maRapChieuCon, int maThoiGianChieu) {
        List<ent_ThoiGianChieu> thoiGianList = new ArrayList<>();
        String sql = "EXEC pr_LayThoiGianBatDauKetThucTheoMaPhimVaRapChieuCon ?, ?, ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, maPhim);
            preparedStatement.setInt(2, maRapChieuCon);
            preparedStatement.setInt(3, maThoiGianChieu);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int maPhimResult = resultSet.getInt("MaPhim");
                    String thoiGianBatDau = resultSet.getString("ThoiGianBatDau");
                    String thoiGianKetThuc = resultSet.getString("ThoiGianKetThuc");

                    ent_ThoiGianChieu thoiGian = new ent_ThoiGianChieu(maPhimResult, thoiGianBatDau, thoiGianKetThuc);
                    thoiGianList.add(thoiGian);
                }
            }
        } catch (SQLException e) {
            Log.e(TAG, "Error when fetching showtime data from the database", e);
        }
        return thoiGianList;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void loadThoiGianToRecyclerView(Context context, RecyclerView recyclerView, List<ent_ThoiGianChieu> list, ThoiGianChieuAdapter adapter) {
        if (list == null || list.isEmpty()) {
            Log.e(TAG, "Danh sách thời gian chiếu trống hoặc không hợp lệ.");
            return;
        }

        // Thiết lập GridLayoutManager cho RecyclerView
        recyclerView.setLayoutManager(new GridLayoutManager(context, 3));

        // Thiết lập Adapter và dữ liệu nếu chưa có adapter
        if (recyclerView.getAdapter() == null) {
            recyclerView.setAdapter(adapter);
        }

        // Cập nhật dữ liệu cho adapter và đảm bảo adapter không rỗng
        adapter.setData(list);
    }
}
