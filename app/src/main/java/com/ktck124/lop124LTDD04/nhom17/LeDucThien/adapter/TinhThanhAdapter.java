package com.ktck124.lop124LTDD04.nhom17.LeDucThien.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.ktck124.lop124LTDD04.nhom17.LeDucThien.activity.DanhSachDiaDiemRap;
import com.ktck124.lop124LTDD04.nhom17.LeDucThien.entity.ent_TinhThanh;
import com.ktck124.lop124LTDD04.nhom17.R;

import java.util.List;

public class TinhThanhAdapter extends RecyclerView.Adapter<TinhThanhAdapter.ViewHolder> {
    private List<ent_TinhThanh> listTinhThanh;
    private final SharedPreferences.Editor editor;
    private final Context context;
    private int selectedMaTinhThanh;

    // Constructor
    @SuppressLint("NotifyDataSetChanged")
    public TinhThanhAdapter(Context context) {
        this.context = context;
        SharedPreferences sharedPreferences = context.getSharedPreferences("LeDucThien", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        selectedMaTinhThanh = sharedPreferences.getInt("maTinhThanh", -1);
    }

    // Set dữ liệu cho Adapter
    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<ent_TinhThanh> listTinhThanh) {
        this.listTinhThanh = listTinhThanh;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_tinh_thanh, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ent_TinhThanh tinhThanh = listTinhThanh.get(position);
        int maTinhThanhItem = tinhThanh.getMaTinhThanh();

        // Hiển thị tên tỉnh thành
        holder.tenTinhThanh.setText(tinhThanh.getTenTinhThanh());

        // Highlight item được chọn
        if (maTinhThanhItem == selectedMaTinhThanh) {
            holder.container_imageview.setBackgroundResource(R.drawable.strock_1_pink_radius_10_transparent);
            holder.tenTinhThanh.setTextColor(context.getColor(R.color.primary_color));
            holder.tenTinhThanh.setTypeface(Typeface.DEFAULT_BOLD);
        } else {
            holder.container_imageview.setBackgroundResource(R.drawable.strock_1_white_radius_10_transparent);
            holder.tenTinhThanh.setTextColor(context.getColor(android.R.color.darker_gray));
            holder.tenTinhThanh.setTypeface(Typeface.DEFAULT);
        }

        // Xử lý sự kiện click
        holder.itemView.setOnClickListener(v -> {
            editor.putInt("maTinhThanh", tinhThanh.getMaTinhThanh());
            editor.apply();
            notifyDataSetChanged(); // Refresh danh sách

            // Đóng Activity nếu cần
            if (context instanceof DanhSachDiaDiemRap) {
                ((DanhSachDiaDiemRap) context).dongActivity();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listTinhThanh != null ? listTinhThanh.size() : 0;
    }

    // ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout container_imageview;
        TextView tenTinhThanh;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            container_imageview = itemView.findViewById(R.id.container_imageview);
            tenTinhThanh = itemView.findViewById(R.id.ten_tinh_thanh);
        }
    }
}
