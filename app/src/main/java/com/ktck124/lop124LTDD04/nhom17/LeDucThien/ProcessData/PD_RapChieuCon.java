package com.ktck124.lop124LTDD04.nhom17.LeDucThien.ProcessData;

import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PD_RapChieuCon {
    private static final String TAG = "PD_RapChieuCon"; // Tag để log
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public List<ent_RapChieuCon> getRapChieuConByTinhThanh(int maTinhThanh, int maRapChieu) {
        List<ent_RapChieuCon> rapChieuConList = new ArrayList<>();

        try {
            // Mở kết nối đến cơ sở dữ liệu
            connection = new ConnectionDatabase().getConnection();
            if (connection == null) {
                Log.e(TAG, "Không thể kết nối đến cơ sở dữ liệu.");
                return rapChieuConList;
            }

            // Chuẩn bị câu lệnh SQL gọi procedure với tham số MaTinhThanh và MaRapChieu
            String sql = "EXEC pr_GetRapChieuCon @MaTinhThanh = ?, @MaRapChieu = ?";
            preparedStatement = connection.prepareStatement(sql);

            // Truyền tham số vào câu lệnh
            preparedStatement.setInt(1, maTinhThanh);
            preparedStatement.setInt(2, maRapChieu); // Truyền giá trị MaRapChieu

            resultSet = preparedStatement.executeQuery();

            // Duyệt qua kết quả và thêm vào danh sách đối tượng ent_RapChieuCon
            while (resultSet.next()) {
                int maRapChieuCon = resultSet.getInt("MaRapChieuCon");
                String anhRapChieu = resultSet.getString("AnhRapChieu");
                String tenRapChieuCon = resultSet.getString("TenRapChieuCon");
                String diaChiRapChieu = resultSet.getString("DiaChiRapChieu");
                String map = resultSet.getString("map");

                ent_RapChieuCon rapChieuCon = new ent_RapChieuCon(maRapChieuCon, anhRapChieu, tenRapChieuCon, diaChiRapChieu, map);
                rapChieuConList.add(rapChieuCon);
            }

        } catch (SQLException e) {
            Log.e(TAG, "Error when fetching cinema data from the database", e);
        } catch (Exception e) {
            Log.e(TAG, "Unexpected error occurred", e);
            throw new RuntimeException(e);
        } finally {
            // Đảm bảo đóng các tài nguyên
            closeResources();
        }

        return rapChieuConList;
    }


    public List<ent_RapChieuCon> getRapChieuConByMaRapChieuCon(int maRapChieuCon) {
        List<ent_RapChieuCon> rapChieuConList = new ArrayList<>();

        try {
            // Mở kết nối đến cơ sở dữ liệu
            connection = new ConnectionDatabase().getConnection();
            if (connection == null) {
                Log.e(TAG, "Không thể kết nối đến cơ sở dữ liệu.");
                return rapChieuConList;
            }

            // Chuẩn bị câu lệnh SQL gọi procedure với tham số MaRapChieuCon
            String sql = "EXEC pr_GetRapChieuConByMaRapChieuCon @MaRapChieuCon = ?";
            preparedStatement = connection.prepareStatement(sql);

            // Thiết lập giá trị tham số MaRapChieuCon
            preparedStatement.setInt(1, maRapChieuCon);

            resultSet = preparedStatement.executeQuery();

            // Duyệt qua kết quả và thêm vào danh sách đối tượng ent_RapChieuCon
            while (resultSet.next()) {
                int maRapChieuConResult = resultSet.getInt("MaRapChieuCon");
                String anhRapChieu = resultSet.getString("AnhRapChieu");
                String tenRapChieuCon = resultSet.getString("TenRapChieuCon");
                String diaChiRapChieu = resultSet.getString("DiaChiRapChieu");
                String map = resultSet.getString("map");

                ent_RapChieuCon rapChieuCon = new ent_RapChieuCon(maRapChieuConResult, anhRapChieu, tenRapChieuCon, diaChiRapChieu, map);
                rapChieuConList.add(rapChieuCon);
            }

        } catch (SQLException e) {
            Log.e(TAG, "Error when fetching cinema data by cinema sub ID from the database", e);
        } catch (Exception e) {
            Log.e(TAG, "Unexpected error occurred", e);
            throw new RuntimeException(e);
        } finally {
            // Đảm bảo đóng các tài nguyên
            closeResources();
        }

        return rapChieuConList;
    }


    public List<ent_RapChieuCon> getRapChieuConByTenRapChieuCon(int maTinhThanh, int maRapChieu, String TenRapChieuCon) {
        List<ent_RapChieuCon> rapChieuConList = new ArrayList<>();

        try {
            // Mở kết nối đến cơ sở dữ liệu
            connection = new ConnectionDatabase().getConnection();
            if (connection == null) {
                Log.e(TAG, "Không thể kết nối đến cơ sở dữ liệu.");
                return rapChieuConList;
            }

            // Chuẩn bị câu lệnh SQL gọi procedure với tham số MaRapChieuCon
            String sql = "EXEC pr_GetRapChieuConByTenRapChieu @MaTinhThanh = ?,  @MaRapChieu = ?, @TenRapChieuCon = ?";
            preparedStatement = connection.prepareStatement(sql);

            // Thiết lập giá trị tham số MaRapChieuCon
            preparedStatement.setInt(1, maTinhThanh);
            preparedStatement.setInt(2, maRapChieu);
            preparedStatement.setString(3, TenRapChieuCon);

            resultSet = preparedStatement.executeQuery();

            // Duyệt qua kết quả và thêm vào danh sách đối tượng ent_RapChieuCon
            while (resultSet.next()) {
                int maRapChieuConResult = resultSet.getInt("MaRapChieuCon");
                String anhRapChieu = resultSet.getString("AnhRapChieu");
                String tenRapChieuCon = resultSet.getString("TenRapChieuCon");
                String diaChiRapChieu = resultSet.getString("DiaChiRapChieu");
                String map = resultSet.getString("map");

                ent_RapChieuCon rapChieuCon = new ent_RapChieuCon(maRapChieuConResult, anhRapChieu, tenRapChieuCon, diaChiRapChieu, map);
                rapChieuConList.add(rapChieuCon);
            }

        } catch (SQLException e) {
            Log.e(TAG, "Error when fetching cinema data by cinema sub ID from the database", e);
        } catch (Exception e) {
            Log.e(TAG, "Unexpected error occurred", e);
            throw new RuntimeException(e);
        } finally {
            // Đảm bảo đóng các tài nguyên
            closeResources();
        }

        return rapChieuConList;
    }

    public int getMinMaRapChieuCon(int maRapChieu, int maTinhThanh) {
        int minMaRapChieuCon = -1;

        try {
            // Mở kết nối đến cơ sở dữ liệu
            connection = new ConnectionDatabase().getConnection();
            if (connection == null) {
                Log.e(TAG, "Không thể kết nối đến cơ sở dữ liệu.");
                return minMaRapChieuCon;
            }

            // Câu lệnh SQL gọi hàm
            String sql = "SELECT dbo.fn_GetMinMaRapChieuCon(?, ?) AS MinMaRapChieuCon";
            preparedStatement = connection.prepareStatement(sql);

            // Gán giá trị cho các tham số
            preparedStatement.setInt(1, maRapChieu);
            preparedStatement.setInt(2, maTinhThanh);

            // Thực thi câu lệnh và lấy kết quả
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                minMaRapChieuCon = resultSet.getInt("MinMaRapChieuCon");
            }
        } catch (SQLException e) {
            Log.e(TAG, "Lỗi khi truy vấn hàm fn_GetMinMaRapChieuCon", e);
        } finally {
            // Đóng các tài nguyên
            closeResources();
        }

        return minMaRapChieuCon;
    }

    // Phương thức đóng các tài nguyên
    private void closeResources() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            Log.e(TAG, "Error closing resources", e);
        }
    }

    public void loadRapChieuConToRecyclerView(Context context, RecyclerView recyclerView, List<ent_RapChieuCon> list, RapChieuConAdapter adapter) {
        if (recyclerView.getLayoutManager() == null) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(context);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
        }

        // Kiểm tra và thêm ItemDecoration nếu chưa được thêm
        boolean hasItemDecoration = false;
        for (int i = 0; i < recyclerView.getItemDecorationCount(); i++) {
            if (recyclerView.getItemDecorationAt(i) instanceof SpaceItemDecoration) {
                hasItemDecoration = true;
                break;
            }
        }

        if (!hasItemDecoration) {
            int spacingInPixels = context.getResources().getDimensionPixelSize(R.dimen.recycler_view_spacing_5);
            recyclerView.addItemDecoration(new SpaceItemDecoration(spacingInPixels));
        }

        // Thiết lập Adapter và dữ liệu nếu chưa có adapter
        if (recyclerView.getAdapter() == null) {
            recyclerView.setAdapter(adapter);
        }

        adapter.SetData(list);
    }

    public void loadDiaChiRapChieuConToRecyclerView(Context context, RecyclerView recyclerView, List<ent_RapChieuCon> list, DiaChiRapChieuAdapter adapter) {
        if (recyclerView.getLayoutManager() == null) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(context);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
        }

        // Kiểm tra và thêm ItemDecoration nếu chưa được thêm
        boolean hasItemDecoration = false;
        for (int i = 0; i < recyclerView.getItemDecorationCount(); i++) {
            if (recyclerView.getItemDecorationAt(i) instanceof SpaceItemDecoration) {
                hasItemDecoration = true;
                break;
            }
        }

        if (!hasItemDecoration) {
            int spacingInPixels = context.getResources().getDimensionPixelSize(R.dimen.recycler_view_spacing_5);
            recyclerView.addItemDecoration(new SpaceItemDecoration(spacingInPixels));
        }

        // Thiết lập Adapter và dữ liệu nếu chưa có adapter
        if (recyclerView.getAdapter() == null) {
            recyclerView.setAdapter(adapter);
        }

        adapter.SetData(list);
    }

}
