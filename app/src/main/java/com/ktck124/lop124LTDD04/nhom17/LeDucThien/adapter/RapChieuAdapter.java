package com.ktck124.lop124LTDD04.nhom17.LeDucThien.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ktck124.lop124LTDD04.nhom17.LeDucThien.entity.ent_RapChieu;
import com.ktck124.lop124LTDD04.nhom17.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RapChieuAdapter extends RecyclerView.Adapter<RapChieuAdapter.viewHolder> {
    private List<ent_RapChieu> rapChieulist;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @SuppressLint("NotifyDataSetChanged")
    public void SetData(List<ent_RapChieu> dangChieulist) {
        this.rapChieulist = dangChieulist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_thoi_gian_chieu_theo_ngay, parent, false);
        return new viewHolder(view);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        ent_RapChieu rapChieu = rapChieulist.get(position);

        String imageName = rapChieu.getAnhRapChieu();
        Context context = holder.itemView.getContext();
        @SuppressLint("DiscouragedApi") int resourceId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());

        Picasso.get()
                .load(resourceId)  // Load resource ID
                .placeholder(R.drawable.camposter)  // Optional: Placeholder image
                .into(holder.cinemaLogo);
        holder.name.setText(rapChieu.getTenRapChieu());

        int _maRapChieu = rapChieu.getMaRapChieu();

        // Lấy giá trị maRapChieu từ SharedPreferences
        sharedPreferences = holder.itemView.getContext().getSharedPreferences("LeDucThien", Context.MODE_PRIVATE);
        int _maRapChieuPref = sharedPreferences.getInt("maRapChieu", -1);

        // Đặt background cho item
        if (_maRapChieuPref == _maRapChieu) {
            holder.container_imageview.setBackgroundResource(R.drawable.strock_1_pink_radius_10_white); // Nền khi được chọn
            holder.name.setTextColor(holder.itemView.getContext().getColor(R.color.primary_color));
            holder.name.setTypeface(holder.name.getTypeface(), Typeface.BOLD);
        } else {
            holder.container_imageview.setBackgroundResource(R.drawable.strock_1_darkergray_radius_10_white); // Nền khi không được chọn
            holder.name.setTextColor(holder.itemView.getContext().getColor(android.R.color.darker_gray));
            holder.name.setTypeface(holder.name.getTypeface(), Typeface.NORMAL);
        }

        // Xử lý sự kiện click
        holder.itemView.setOnClickListener(v -> {
            // Lưu giá trị maRapChieu vào SharedPreferences một cách đồng bộ
            editor = sharedPreferences.edit();
            editor.putInt("maRapChieu", rapChieu.getMaRapChieu()); // Lưu giá trị maRapChieu
            editor.apply();
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return rapChieulist.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        LinearLayout container_imageview;
        ImageView cinemaLogo;
        TextView name;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            container_imageview = itemView.findViewById(R.id.container_imageview);
            cinemaLogo = itemView.findViewById(R.id.cinema_logo);
            name = itemView.findViewById(R.id.cinema_name);
        }
    }
}
